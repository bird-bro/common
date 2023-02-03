//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.birdbro.common.exception.global;

import feign.FeignException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.birdbro.common.annotation.IgnoreResponseAdvice;
import org.birdbro.common.enums.HttpStatusEnum;
import org.birdbro.common.exception.BusinessException;
import org.birdbro.common.exception.WarnException;
import org.birdbro.common.exception.advice.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler() {
    }

    @ExceptionHandler({Exception.class})
    public Result handlerException(Exception e) throws Throwable {
        this.errorDispose(e);
        return this.ifDepthExceptionType(e);
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handlerNoHandlerFoundException(NoHandlerFoundException e) throws Throwable {
        this.errorDispose(e);
        this.outPutWarn(NoHandlerFoundException.class, HttpStatusEnum.NOT_FOUND, e);
        return Result.ofFail(HttpStatusEnum.NOT_FOUND);
    }

    private Result ifDepthExceptionType(Throwable throwable) throws Throwable {
        Throwable cause = throwable.getCause();
        if (cause instanceof FeignException) {
            return this.handlerFeignException((FeignException)cause);
        } else if (cause instanceof BusinessException) {
            return this.handlerBusinessException((BusinessException)cause);
        } else {
            this.outPutError(Exception.class, HttpStatusEnum.EXCEPTION, throwable);
            return Result.ofFail(HttpStatusEnum.EXCEPTION);
        }
    }

    @ExceptionHandler({FeignException.class})
    public Result handlerFeignException(FeignException e) throws Throwable {
        this.errorDispose(e);
        this.outPutError(FeignException.class, HttpStatusEnum.RPC_ERROR, e);
        return Result.ofFail(HttpStatusEnum.RPC_ERROR);
    }

    @ExceptionHandler({BusinessException.class})
    public Result handlerBusinessException(BusinessException e) throws Throwable {
        this.errorDispose(e);
        this.outPutError(BusinessException.class, HttpStatusEnum.BUSINESS_ERROR, e);
        return Result.ofFail(e.getCode(), e.getMsg(), e.getMessage());
    }

    @ExceptionHandler({WarnException.class})
    public Result warnException(WarnException e) throws Throwable {
        this.errorDispose(e);
        log.warn(String.format("code: %s ；\n  message: %s )", e.getCode(), e.getMsg()));
        return Result.ofFail(e.getCode(), e.getMsg(), e.getMessage());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e) throws Throwable {
        this.errorDispose(e);
        this.outPutError(HttpMessageNotReadableException.class, HttpStatusEnum.PARAM_ERROR, e);
        String msg = String.format("%s : 错误详情( %s )", e.getMessage());
        return Result.ofFail(String.valueOf(HttpStatusEnum.PARAM_ERROR.getCode()), msg);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public Result handleConstraintViolationException(ConstraintViolationException e) throws Throwable {
        this.errorDispose(e);
        String smg = "";
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        ConstraintViolation error;
        if (log.isDebugEnabled()) {
            for(Iterator var4 = constraintViolations.iterator(); var4.hasNext(); smg = error.getMessageTemplate()) {
                error = (ConstraintViolation)var4.next();
                log.error("{} -> {}", error.getPropertyPath(), error.getMessageTemplate());
            }
        }

        if (constraintViolations.isEmpty()) {
            log.error("validExceptionHandler error fieldErrors is empty");
            Result.ofFail(String.valueOf(HttpStatusEnum.BUSINESS_ERROR.getCode()), HttpStatusEnum.BUSINESS_ERROR.getMsg());
        }

        return Result.ofFail(String.valueOf(HttpStatusEnum.BUSINESS_ERROR.getCode()), HttpStatusEnum.BUSINESS_ERROR.getMsg());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) throws Throwable {
        BindingResult result = e.getBindingResult();
        StringBuilder stringBuilder = new StringBuilder();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            if (errors != null) {
                StringBuilder finalStringBuilder = stringBuilder;
                errors.forEach((p) -> {
                    FieldError fieldError = (FieldError)p;
                    log.warn("Bad Request Parameters: dto entity [{}],field [{}],message [{}]", new Object[]{fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage()});
                    finalStringBuilder.append(String.format("%s[%s]%s", p.getObjectName(), ((FieldError)p).getField(), p.getDefaultMessage())).append("，");
                });
            }

            if (stringBuilder.length() > 0) {
                stringBuilder = stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
        }

        return Result.ofFail(String.valueOf(HttpStatusEnum.BUSINESS_ERROR.getCode()), stringBuilder.toString());
    }

    @ExceptionHandler({BindException.class})
    public Result handleBindException(BindException e) throws Throwable {
        this.errorDispose(e);
        this.outPutError(BindException.class, HttpStatusEnum.PARAM_ERROR, e);
        BindingResult bindingResult = e.getBindingResult();
        return this.getBindResultDTO(bindingResult);
    }

    private Result getBindResultDTO(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (log.isDebugEnabled()) {
            Iterator var3 = fieldErrors.iterator();

            while(var3.hasNext()) {
                FieldError error = (FieldError)var3.next();
                log.error("{} -> {}", error.getDefaultMessage(), error.getDefaultMessage());
            }
        }

        if (fieldErrors.isEmpty()) {
            log.error("validExceptionHandler error fieldErrors is empty");
            Result.ofFail(String.valueOf(HttpStatusEnum.BUSINESS_ERROR.getCode()), HttpStatusEnum.BUSINESS_ERROR.getMsg());
        }

        return Result.ofFail(String.valueOf(HttpStatusEnum.BUSINESS_ERROR.getCode()), HttpStatusEnum.BUSINESS_ERROR.getMsg());
    }

    private <T extends Throwable> void errorDispose(T e) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HandlerMethod handlerMethod = (HandlerMethod)request.getAttribute("org.springframework.web.servlet.HandlerMapping.bestMatchingHandler");
        Class<?> beanType = handlerMethod.getBeanType();
        Method method = handlerMethod.getMethod();
        IgnoreResponseAdvice methodAnnotation = (IgnoreResponseAdvice)method.getAnnotation(IgnoreResponseAdvice.class);
        if (methodAnnotation != null) {
            if (!methodAnnotation.errorDispose()) {
                throw e;
            }
        } else {
            IgnoreResponseAdvice classAnnotation = (IgnoreResponseAdvice)beanType.getAnnotation(IgnoreResponseAdvice.class);
            if (classAnnotation != null && !classAnnotation.errorDispose()) {
                throw e;
            }
        }
    }

    public void outPutError(Class errorType, Enum secondaryErrorType, Throwable throwable) {
        log.error("-------------------Error throw（开始）---------------------");
        log.error("[{}] {}: {}", new Object[]{errorType.getSimpleName(), secondaryErrorType, throwable.getMessage(), throwable});
        log.error("-------------------Error throw（结束）---------------------");
    }

    public void outPutWarn(Class errorType, Enum secondaryErrorType, Throwable throwable) {
        log.warn("-------------------Warn throw（开始）---------------------");
        log.warn("[{}] {}: {}", new Object[]{errorType.getSimpleName(), secondaryErrorType, throwable.getMessage()});
        log.warn("-------------------Warn throw（结束）---------------------");
    }
}
