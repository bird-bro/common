package org.birdbro.common.enums;

/**
 * @author bird
 * @date 2022-1-20 10:36
 **/
public enum HeaderEnum {


    /**
     * 用户UID
     */
    UID("USER-UID", "用户UID"),

    /**
     * 用户UID
     */
    ACCOUNT("USER-ACCOUNT", "用户账号"),

    /**
     * 用户姓名
     */
    NAME( "USER-NAME", "用户姓名")

    ;


    /**
     * key
     */
    private  String key;

    /**
     * 提示
     */
    private  String info;



    public String getKey() {
        return key;
    }

    public String getInfo() {
        return info;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    HeaderEnum(String key , String info) {
        this.key = key;
        this.info = info;
    }
}
