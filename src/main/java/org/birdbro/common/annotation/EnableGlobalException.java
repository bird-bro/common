package org.birdbro.common.annotation;

import org.birdbro.common.exception.global.GlobalConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author bird
 * @date 2021-7-21 8:49
 **/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(GlobalConfiguration.class)
public @interface EnableGlobalException {
}
