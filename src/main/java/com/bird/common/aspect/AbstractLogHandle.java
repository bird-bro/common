package com.bird.common.aspect;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author bird
 * @date 2022-4-7 13:33
 **/
public abstract class AbstractLogHandle<T> implements LogHandle<T> {

    protected ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public AbstractLogHandle(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor=threadPoolTaskExecutor;
    }

    /**
     * 处理日志
     * @param t 日志对象
     * @param isPersistent 是否持久化
     * @throws Exception
     */
    @Override
    public void processLog(T t,boolean isPersistent) throws Exception {
        if (ifPersistenceLog(isPersistent)) {
            persistenceLog(t);
        }
    }

    /**
     * 日志持久化抽象方法，由子类实现
     * @param t 持久化日志
     * @throws Exception
     */
    @Override
    public abstract void persistenceLog(T t) throws Exception;


    /**
     * 是否需要持久化日志
     * @return boolean true持久化日志，false不持久化日志
     */
    @Override
    public boolean ifPersistenceLog(boolean isPersistent) {
        return isPersistent;
    }

}
