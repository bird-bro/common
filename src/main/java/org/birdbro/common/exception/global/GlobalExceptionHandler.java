package org.birdbro.common.exception.global;

import org.birdbro.common.exception.WarnException;
import org.birdbro.common.exception.advice.Result;
import org.birdbro.common.exception.BusinessException;
import org.birdbro.common.annotation.IgnoreResponseAdvice;
import org.birdbro.common.enums.HttpStatusEnum;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * @author bird
 * @date 2021-7-21 8:56
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * 500 全局异常处理
     * Exception 类捕获
     */
    @ExceptionHandler(value = Exception.class)
    public Result handlerException(Exception e) throws Throwable {
        errorDispose(e);
        return ifDepthExceptionType(e);
    }

    /**
     * 404 ExceptionHandler
     * NoHandlerFoundException
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handlerNoHandlerFoundException(NoHandlerFoundException e) throws Throwable {
        errorDispose(e);
        outPutWarn(NoHandlerFoundException.class, HttpStatusEnum.NOT_FOUND, e);
        return Result.ofFail(HttpStatusEnum.NOT_FOUND);
    }



    /**
     * 二次深度检查错误类型
     */
    private Result ifDepthExceptionType(Throwable throwable) throws Throwable {
        Throwable cause = throwable.getCause();

        if (cause instanceof FeignException) {
            return handlerFeignException((FeignException) cause);
        }

        if(cause instanceof BusinessException){
            return handlerBusinessException((BusinessException) cause);
        }

        outPutError(Exception.class, HttpStatusEnum.EXCEPTION, throwable);
        return Result.ofFail(HttpStatusEnum.EXCEPTION);
    }

    /**
     * 类捕获
     * FeignException
     */
    @ExceptionHandler(value = FeignException.class)
    public Result handlerFeignException(FeignException e) throws Throwable {
        errorDispose(e);
        outPutError(FeignException.class, HttpStatusEnum.RPC_ERROR, e);
        return Result.ofFail(HttpStatusEnum.RPC_ERROR);
    }



    /**
     * 类捕获
     * BusinessException
     */
    @ExceptionHandler(value = BusinessException.class)
    public Result handlerBusinessException(BusinessException e) throws Throwable {
        errorDispose(e);
        outPutError(BusinessException.class, HttpStatusEnum.BUSINESS_ERROR, e);
        return Result.ofFail(e.getCode(),e.getMsg(),e.getMessage());
    }

    /**
     * 类捕获
     * warnException
     */
    @ExceptionHandler(value = WarnException.class)
    public Result warnException(WarnException e) throws Throwable {
        errorDispose(e);
        log.warn(String.format("code: %s ；\n  message: %s )", e.getCode(), e.getMsg()));
        return Result.ofFail(e.getCode(),e.getMsg(),e.getMessage());
    }

    /**
     * 参数错误异常
     * HttpMessageNotReadableException
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e) throws Throwable {
        errorDispose(e);
        outPutError(HttpMessageNotReadableException.class, HttpStatusEnum.PARAM_ERROR, e);
        String msg = String.format("%s : 错误详情( %s )", e.getMessage());
        return Result.ofFail(String.valueOf(HttpStatusEnum.PARAM_ERROR.getCode()),msg);
    }

    /**
     * 参数错误异常
     * ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result handleConstraintViolationException(ConstraintViolationException e) throws Throwable {
        errorDispose(e);
        String smg = "";
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        if (log.isDebugEnabled()) {
            for (ConstraintViolation error : constraintViolations) {
                log.error("{} -> {}", error.getPropertyPath(), error.getMessageTemplate());
                smg = error.getMessageTemplate();
            }
        }

        if (constraintViolations.isEmpty()) {
            log.error("validExceptionHandler error fieldErrors is empty");
            Result.ofFail(String.valueOf(HttpStatusEnum.BUSINESS_ERROR.getCode()), HttpStatusEnum.BUSINESS_ERROR.getMsg());
        }

        return Result.ofFail(String.valueOf(HttpStatusEnum.BUSINESS_ERROR.getCode()), HttpStatusEnum.BUSINESS_ERROR.getMsg());
    }

    /**
     * 参数错误异常
     * MethodArgumentNotValidException
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) throws Throwable {


        BindingResult result = e.getBindingResult();
        StringBuilder stringBuilder = new StringBuilder();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            if (errors != null) {
                StringBuilder finalStringBuilder = stringBuilder;
                errors.forEach(p -> {
                    FieldError fieldError = (FieldError) p;
                    log.warn("Bad Request Parameters: dto entity [{}],field [{}],message [{}]", fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
                    finalStringBuilder.append(String.format("%s[%s]%s", p.getObjectName(), ((FieldError) p).getField(), p.getDefaultMessage())).append("，");
                });
            }
            if (stringBuilder.length() > 0) {
                stringBuilder = stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
        }

        return Result.ofFail(String.valueOf(HttpStatusEnum.BUSINESS_ERROR.getCode()), stringBuilder.toString());
    }

    /**
     * 参数错误异常
     * BindException 异常处理
     * 作用于@Validated @Valid 注解
     * 仅用于表单提交参数进行异常处理，对于Json格式提交会失效
     */
    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException e) throws Throwable {
        errorDispose(e);
        outPutError(BindException.class, HttpStatusEnum.PARAM_ERROR, e);
        BindingResult bindingResult = e.getBindingResult();
        return getBindResultDTO(bindingResult);
    }

    


    private Result getBindResultDTO(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (log.isDebugEnabled()) {
            for (FieldError error : fieldErrors) {
                log.error("{} -> {}", error.getDefaultMessage(), error.getDefaultMessage());
            }
        }

        if (fieldErrors.isEmpty()) {
            log.error("validExceptionHandler error fieldErrors is empty");
            Result.ofFail(String.valueOf(HttpStatusEnum.BUSINESS_ERROR.getCode()), HttpStatusEnum.BUSINESS_ERROR.getMsg());
        }

        return Result
                .ofFail(String.valueOf(HttpStatusEnum.BUSINESS_ERROR.getCode()), HttpStatusEnum.BUSINESS_ERROR.getMsg());
    }




    /**
     * 校验是否进行异常处理
     * @param e   异常
     * @param <T> extends Throwable
     * @throws Throwable 异常
     */
    private <T extends Throwable> void errorDispose(T e) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HandlerMethod handlerMethod = (HandlerMethod) request.getAttribute("org.springframework.web.servlet.HandlerMapping.bestMatchingHandler");

        // 获取异常 Controller
        Class<?> beanType = handlerMethod.getBeanType();
        // 获取异常方法
        Method method = handlerMethod.getMethod();

        // 判断方法是否存在 IgnoreResponseAdvice 注解
        IgnoreResponseAdvice methodAnnotation = method.getAnnotation(IgnoreResponseAdvice.class);
        if (methodAnnotation != null) {
            // 是否使用异常处理
            if (!methodAnnotation.errorDispose()) {
                throw e;
            } else {
                return;
            }
        }
        // 判类是否存在 IgnoreResponseAdvice 注解
        IgnoreResponseAdvice classAnnotation = beanType.getAnnotation(IgnoreResponseAdvice.class);
        if (classAnnotation != null) {
            if (!classAnnotation.errorDispose()) {
                throw e;
            }
        }
    }



    public void outPutError(Class errorType, Enum secondaryErrorType, Throwable throwable) {
        log.error("-------------------Error throw（开始）---------------------");
        log.error("[{}] {}: {}", errorType.getSimpleName(), secondaryErrorType, throwable.getMessage(),
                throwable);
        log.error("-------------------Error throw（结束）---------------------");
    }

    public void outPutWarn(Class errorType, Enum secondaryErrorType, Throwable throwable) {
        log.warn("-------------------Warn throw（开始）---------------------");
        log.warn("[{}] {}: {}", errorType.getSimpleName(), secondaryErrorType, throwable.getMessage());
        log.warn("-------------------Warn throw（结束）---------------------");
    }




}
