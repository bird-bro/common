package org.birdbro.common.entity;

import lombok.Data;

/**
 * @author bird
 * @date 2022-1-20 15:39
 **/
@Data
public class Encryption {


    /**
     * 加密方式
     */
    private String method;

    /**
     *表示加密系数
     */
    private String hash;

    /**
     *表示加密所用盐值
     */
    private String salt;

}
