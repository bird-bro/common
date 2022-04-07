package com.bird.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.bird.common.entity.CookieVariable;
import com.bird.common.enums.HeaderEnum;
import com.bird.common.exception.advice.BusinessException;
import com.bird.common.exception.enums.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author bird
 * @date 2022-1-20 10:34
 **/
@Slf4j
public class HeadUtil {

    //cookies 有效期
    private static int cookie_expire = 24 * 60 * 60;


    public static HttpServletRequest getHttpServletRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }


    /**
     * 生成 Token
     * @param code 账号
     * @param password  密码
     * @return token
     */
    public static String getToken(String code, String password){
        String token = "";
        token = JWT.create().withAudience(code).sign(Algorithm.HMAC256(password));
        return token;
    }


    /**
     * 验证Token
     * @param token
     * @return
     */
    public static String checkToken(String token){
        String loginCode;

        if(StringUtils.isBlank(token)){
            log.error("-- token,为空");
            throw new BusinessException("token is null.", ErrorCodeEnum.BUSINESS_ERROR_A0312);
        }

        try {
            loginCode = JWT.decode(token).getAudience().get(0);
        }catch (JWTDecodeException e){
            log.error("-- token,解析失败");
            throw new BusinessException(ErrorCodeEnum.BUSINESS_ERROR_A0340);
        }

        return loginCode;
    }




    /**
     * 写cookie 环境变量
     * @author:bird
     * @date: 2021-10-14 18:40
     * @param:
     * @return:
     **/
    public static void setCookie(String token, String userInfo, HttpServletResponse response, CookieVariable variable){
        try {
            String[] domainArr = variable.getDomain().split(";");
            for (int i = 0; i < domainArr.length; i++) {
                Cookie cookie = new Cookie(variable.getHeader() + "-" + variable.getEnv(), token );
                cookie.setMaxAge(cookie_expire);
                cookie.setDomain(domainArr[i]);
                cookie.setPath("/");
                response.addCookie(cookie);
                cookie = new Cookie("user_info-" + variable.getEnv(), userInfo);
                cookie.setMaxAge(cookie_expire);
                cookie.setDomain(domainArr[i]);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }catch (Exception e){
            log.error("- cookie 写入失败", e);
        }
    }


    /**
     * 删除Cookie
     * @author:bird
     * @date: 2021-10-14 18:41
     * @param:
     * @return:
     **/
    public static void removeCookie (Cookie[] cookies, HttpServletResponse servletResponse, CookieVariable variable){
        String[] domainArr = variable.getDomain().split(";");
        if (ArrayUtils.isNotEmpty(cookies) && ArrayUtils.isNotEmpty(domainArr)) {
            for (int i = 0; i < domainArr.length; i++) {
                //删除cookie
                for (Cookie cookie : cookies) {
                    if (cookie.getName().contains(variable.getEnv())) {
                        cookie.setMaxAge(0);
                        cookie.setDomain(domainArr[i]);
                        cookie.setPath("/");
                        servletResponse.addCookie(cookie);
                    }
                }
            }
        }
    }


    public static Integer getLoginUid(){
        HttpServletRequest request = getHttpServletRequest();
        if(StringUtils.isBlank(request.getHeader(HeaderEnum.LOGIN_UID.getKey())) ){
            return null;
        }
        return Integer.valueOf(request.getHeader(HeaderEnum.LOGIN_UID.getKey()));
    }


    public static String getLoginCode(){
        HttpServletRequest request = getHttpServletRequest();
        if(StringUtils.isBlank(request.getHeader(HeaderEnum.LOGIN_CODE.getKey())) ){
            return null;
        }
        return request.getHeader(HeaderEnum.LOGIN_CODE.getKey());
    }


    public static String getLoginName(){
        HttpServletRequest request = getHttpServletRequest();
        if(StringUtils.isBlank(request.getHeader(HeaderEnum.LOGIN_NAME.getKey())) ){
            return null;
        }
        return request.getHeader(HeaderEnum.LOGIN_NAME.getKey());
    }


    public static String getIpAddr(HttpServletRequest request){
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        log.warn("get ip ERROR: {}" ,e.getMessage());
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            // "***.***.***.***".length()
            if (ipAddress != null && ipAddress.length() > 15) {
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "0.0.0.0";
        }
        return ipAddress;
    }


    public static Map<String, String> converMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<String, String>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
    }

    public static String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strbuff = new StringBuffer();
        for (StackTraceElement stet : elements) {
            strbuff.append(stet + "\n");
        }
        return exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
    }


}