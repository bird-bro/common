package org.birdbro.common.redis.lock;

/**
 * @author birdbro
 * @date 14:50 2022-12-12
 **/
public interface DistributedLock {

    boolean acquire();

    void release();

}
