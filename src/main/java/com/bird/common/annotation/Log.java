package com.bird.common.annotation;

import java.lang.annotation.*;

/**
 * @author bird
 * @date 2022-4-7 13:31
 **/
@Target(ElementType.METHOD) //注解放置的目标位置，METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented
public @interface Log {
    String value() default "";   // 操作模块
    String type() default "";   // 操作类型
    String desc() default "";  // 操作说明
    boolean record() default false;
    boolean persistent() default true;  // 是否持久化日志
}
