package com.bird.dispose;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * GlobalDefault 配置
 *
 * @Description TODO
 * @Date 16:17 2020/6/24
 * @Created wangpeng
 */
@ConfigurationProperties(GlobalDefaultProperties.PREFIX)
public class GlobalDefaultProperties {

    public static final String PREFIX = "dispose";

    /**
     * 统一返回过滤包
     */
    private List<String> adviceFilterPackage = new ArrayList<>();

    /**
     * 统一返回过滤类
     */
    private List<String> adviceFilterClass = new ArrayList<>();

    public List<String> getAdviceFilterPackage() {
        return adviceFilterPackage;
    }

    public void setAdviceFilterPackage(List<String> adviceFilterPackage) {
        this.adviceFilterPackage = adviceFilterPackage;
    }

    public List<String> getAdviceFilterClass() {
        return adviceFilterClass;
    }

    public void setAdviceFilterClass(List<String> adviceFilterClass) {
        this.adviceFilterClass = adviceFilterClass;
    }

}
