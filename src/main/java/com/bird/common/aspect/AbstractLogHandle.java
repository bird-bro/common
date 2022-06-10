package com.bird.common.aspect;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author birdbro
 * @date 14:04 2022-6-10
 **/
public abstract class AbstractLogHandle<T> implements LogHandle<T> {

    protected ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public AbstractLogHandle(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor=threadPoolTaskExecutor;
    }

    /**
     * 日志持久化抽象方法，由子类实现
     * @param t 持久化日志
     * @throws Exception
     */
    @Override
    public abstract void persistenceLog(T t) throws Exception;

}
