package org.birdbro.common.entity;

import lombok.Data;
import org.springframework.boot.convert.DataSizeUnit;

/**
 * @author bird
 * @date 2022-1-20 15:20
 **/
@Data
public class CookieVariable {

    private String env;


    private String header;


    private String domain;
}
