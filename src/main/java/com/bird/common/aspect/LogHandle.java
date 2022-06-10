package com.bird.common.aspect;

/**
 * @author birdbro
 * @date 14:05 2022-6-10
 **/
public interface LogHandle<T> {

    /**
     * 持久化日志
     * @param t 持久化日志对象到数据库
     * @throws Exception
     */
    void persistenceLog(T t) throws Exception;


}
