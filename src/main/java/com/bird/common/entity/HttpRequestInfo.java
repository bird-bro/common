package com.bird.common.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author bird
 * @date 2022-4-20 14:09
 **/
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class HttpRequestInfo {

    private String session;

    private String ip4;

    private String browser;

    private String engine;

    private String os;

    private Boolean mobile;

}
