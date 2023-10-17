package org.birdbro.common.log;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 实现日志操作接口
 *
 * @author birdbro
 * @date 16:06 2023-2-1
 **/
public abstract class AbstractLogHandler<T> implements ILogHandler<T> {


    protected ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public AbstractLogHandler(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }


    /**
     * 处理日志
     *
     * @param: t 日志对象
     * @param: isPersistent 是否持久化
     **/
    @Override
    public void processLog(T t, boolean isPersistent) throws Exception {
        if (customerWantsPersistenceLog(isPersistent)) {
            persistenceLog(t);
        }
    }


    /**
     * 日志持久化抽象方法，由子类实现
     *
     * @param: t 持久化日志
     **/
    @Override
    public void persistenceLog(T t) throws Exception {

    }


    /**
     * 是否需要持久化日志
     *
     * @param: boolean true持久化日志，false不持久化日志
     **/
    @Override
    public boolean customerWantsPersistenceLog(boolean isPersistent) {
        return isPersistent;
    }
}
