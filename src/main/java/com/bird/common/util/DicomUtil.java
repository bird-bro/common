package com.bird.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author bird
 * @date 2022-1-21 11:31
 **/
@Slf4j
public class DicomUtil {


    public static String getTag(Object object, String defaultValue) {
        return getTag(object, defaultValue, "Value", null);
    }


    public static String getTag(Object object, String defaultValue, String key, String otherKey) {
        if (!ObjectUtils.isEmpty(object)) {
            if (object instanceof Map) {
                Object list = ((Map) object).get(key);
                if (ObjectUtils.isNotEmpty(list)) {
                    if (list instanceof Collection) {
                        Object value = ((List) list).get(0);
                        if (ObjectUtils.isNotEmpty(value)) {
                            if (StringUtils.isNotEmpty(otherKey) && value instanceof Map) {
                                return Objects.toString(((Map) value).get(otherKey), defaultValue);
                            } else if (value instanceof String) {
                                return Objects.toString(value, defaultValue);
                            }
                        }
                    } else if (list instanceof String) {
                        return Objects.toString(list, defaultValue);
                    }
                }
            }
        }
        return defaultValue;
    }

    public static String getDateTime(String date, String time) {
        String dateStr = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
        String timeStr = "00:00:00";
        if (ObjectUtils.isNotEmpty(time)){
            String str = time.substring(0, 6);
            timeStr = str.substring(0, 2) + ":" + str.substring(2, 4) + ":" + str.substring(4, 6);
        }
        return dateStr + " " + timeStr;
    }

    public static String getDate(String date) {
        if(StringUtils.isBlank(date)){
            return "";
        }
        return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
    }

    public static String getTime(String time) {
        if(StringUtils.isBlank(time)){
            return "00:00:00";
        }
        String str = time.substring(0, 6);
        return str.substring(0, 2) + ":" + str.substring(2, 4) + ":" + str.substring(4, 6);
    }


    public static String getPatientName(Object object, String defaultValue, String key, String oneKey, String twoKey) {
        if (!ObjectUtils.isEmpty(object)) {
            if (object instanceof Map) {
                Object list = ((Map) object).get(key);
                if (!ObjectUtils.isEmpty(list)) {
                    if (list instanceof Collection) {
                        Object value = ((List) list).get(0);
                        if (!ObjectUtils.isEmpty(value)) {
                            if (StringUtils.isNotEmpty(oneKey) && value instanceof Map) {
                                return Objects.toString(((Map) value).get(oneKey), Objects.toString(((Map) value).get(twoKey), defaultValue));
                            }
                        }
                    }
                }
            }
        }
        return defaultValue;
    }
}
