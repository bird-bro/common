package org.birdbro.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录账号信息
 *
 * @author birdbro
 * @date 10:57 2023-11-01
 **/
@Data
public class Account implements Serializable {


    /**
     * 用户主键
     */
    private Integer uid;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 用户注册的医院信息
     */
    private Integer oid;


}
