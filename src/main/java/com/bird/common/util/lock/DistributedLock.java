package com.bird.common.util.lock;

/**
 * @author bird
 * @date 2021-7-22 15:52
 **/
public interface DistributedLock {

    boolean acquire();

    void release();


}
