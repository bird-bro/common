package org.birdbro.common.tools;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.birdbro.common.entity.CookieVariable;
import org.birdbro.common.entity.HttpRequestInfo;
import org.birdbro.common.enums.ExceptionEnum;
import org.birdbro.common.enums.HeaderEnum;
import org.birdbro.common.exception.BusinessException;
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
 * HTTP请求
 * @author birdbro
 * @date 9:41 2022-12-7
 **/
@Slf4j
public class HttpTool {

    //cookies 有效期
    private static int cookie_expire = 24 * 60 * 60;


    /**
     * 获取HTTP请求
     * @author: bird
     * @date: 2022-4-20 14:04
     * @return: HttpServletRequest
     **/
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
     * @param: token
     * @return: String
     */
    public static String checkToken(String token){
        String loginCode;

        if(StringUtils.isBlank(token)){
            log.error("-- token,为空");
            throw new BusinessException(ExceptionEnum.ERROR_A0312, "token is null");
        }

        try {
            loginCode = JWT.decode(token).getAudience().get(0);
        }catch (JWTDecodeException e){
            log.error("-- token,解析失败");
            throw new BusinessException(ExceptionEnum.ERROR_A0340);
        }

        return loginCode;
    }


    /**
     * 写cookie 环境变量
     * @author: bird
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
     * @author: bird
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


    /**
     * 获取请求头信息
     * @author: bird
     * @date: 2022-4-20 14:06
     * @return: 用户ID
     **/
    public static Integer getUid(){
        HttpServletRequest request = getHttpServletRequest();
        if(StringUtils.isBlank(request.getHeader(HeaderEnum.UID.getKey())) ){
            return null;
        }
        return Integer.valueOf(request.getHeader(HeaderEnum.UID.getKey()));
    }

    /**
     * 获取请求头信息
     * @author: bird
     * @date: 2022-4-20 14:06
     * @return: 用户账号
     **/
    public static String getAccount(){
        HttpServletRequest request = getHttpServletRequest();
        if(StringUtils.isBlank(request.getHeader(HeaderEnum.ACCOUNT.getKey())) ){
            return null;
        }
        return request.getHeader(HeaderEnum.ACCOUNT.getKey());
    }

    /**
     * 获取请求头信息
     * @author: bird
     * @date: 2022-4-20 14:06
     * @return: 用户姓名
     **/
    public static String getName(){
        HttpServletRequest request = getHttpServletRequest();
        if(StringUtils.isBlank(request.getHeader(HeaderEnum.NAME.getKey())) ){
            return null;
        }
        return request.getHeader(HeaderEnum.NAME.getKey());
    }

    /**
     * 获取请求头信息
     * @author: bird
     * @date: 2022-4-20 14:06
     * @return: IP
     **/
    public static String getIp4(HttpServletRequest request){
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


    /**
     * 获取请求方信息
     * @author: birdbro
     * @date: 2022-4-22
     * @param:
     * @return:
     **/
    public static HttpRequestInfo getRequestInfo(HttpServletRequest request){

        String ip4 = getIp4(request);
        String agent = request.getHeader("User-Agent");
        String session = request.getSession().getId();
        UserAgent ags = UserAgentUtil.parse(agent);

        return HttpRequestInfo.builder()
                .session(session)
                .ip4(ip4)
                .browser(ags.getBrowser() + " " + ags.getVersion())
                .engine(ags.getEngine() + " " + ags.getEngineVersion())
                .os(ags.getOs().toString())
                .mobile(ags.isMobile())
                .build();
    }


    /**
     * 获取请求方 入参
     * @author: bird
     * @date: 2022-4-7 11:08
     * @param:
     * @return:
     **/
    public static Map<String, String> getRequestParam(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<String, String>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
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
