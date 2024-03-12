package org.birdbro.common.tools;

import org.birdbro.common.entity.Account;
import org.birdbro.common.enums.ExceptionEnum;
import org.birdbro.common.exception.BusinessException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Bean操作辅助
 *
 * @author bird
 * @date 2021-7-21 11:07
 **/
public final class AccountTool {


    public static final String AUTH_KEY = "X-User-Code";

    public static Map<String, Account> dataMap = new HashMap<>();


    public static Account getAccount() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        Object attribute = request.getAttribute(AUTH_KEY);
        if (attribute == null) {
            throw new BusinessException(ExceptionEnum.ERROR_B0001, "[ERROR]--用户未登录! \n");
        }
        Account account = dataMap.get(attribute.toString());
        if (account == null) {
            throw new BusinessException(ExceptionEnum.ERROR_B0001, "[ERROR]--用户未登录! \n");
        }
        return account;
    }

}
