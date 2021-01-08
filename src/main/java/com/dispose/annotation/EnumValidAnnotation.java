package com.dispose.annotation;

import com.dispose.advice.EnumValidtor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Description TODO:配合@Validate一起使用的、用来校验枚举的注解
 * @Date 14:30 2020-8-5
 * @Author  wangpeng
 * @title EnumValidAnnotation
 */
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EnumValidtor.class})
@Documented
public @interface EnumValidAnnotation {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?>[] target() default {};
}
