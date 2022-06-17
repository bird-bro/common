package com.bird.common.annotation;

import java.lang.annotation.*;

/**
 * 日志注解
 * @author birdbro
 * @date 13:58 2022-6-10
 **/
@Target(ElementType.METHOD) //注解放置的目标位置，METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented
public @interface Log {
    String value() default "";   // 操作模块
    String type() default "";   // 操作类型
    String desc() default "";  // 操作说明
    boolean record() default false; //记录请求源
    boolean sign() default  false; //记录登录session

}
