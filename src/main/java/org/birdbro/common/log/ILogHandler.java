package org.birdbro.common.log;

/**
 * 抽象日志操作
 *
 * @author birdbro
 * @date 15:13 2023-2-1
 **/
public interface ILogHandler<T> {


    /**
     * 处理日志
     *
     * @param t            日志对象
     * @param isPersistent 是否需要持久化 true表示需要持久化、false表示不需要
     */
    void processLog(T t, boolean isPersistent) throws Exception;


    /**
     * 持久化日志
     *
     * @param t 持久化日志对象到数据库
     */
    void persistenceLog(T t) throws Exception;


    /**
     * 是否需要持久化日志
     *
     * @param isPersistent true表示需要持久化日志
     * @return boolean true表示持久化日志，false表示不持久化日志
     */
    boolean customerWantsPersistenceLog(boolean isPersistent);

}


