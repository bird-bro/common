package org.birdbro.common.tools;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.birdbro.common.entity.ArchiveMessage;
import org.birdbro.common.entity.sopInfo.Failed;
import org.birdbro.common.entity.sopInfo.Success;
import org.birdbro.common.entity.sopInfo.Warnning;
import org.birdbro.common.enums.DicomUploadEnum;
import org.birdbro.common.enums.UploadEnum;
import org.birdbro.common.exception.WarnException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;

/**
 * Dicom文件解析
 * @author birdbro
 * @date 9:40 2022-12-7
 **/
@Slf4j
public class DicomTool {

    static boolean retry = false;

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



    /**
     * 页面上传DICOM文件
     * @author: birdbro
     * @date: 2022-4-7 15:34
     * @param:
     * @return:
     **/
    public static String uploadByFile(File file, String boundary, String stowUrl) throws Exception {
        OutputStream out = null;
        FileInputStream fileInStream = null;
        BufferedReader reader = null;
        try {
            // 换行符
            final String newLine = "\r\n";
            final String boundaryPrefix = "--";
            // 传输字节大小
            long allSize = 0;
            // 定义数据分隔线
            String BOUNDARY = boundary;
            // 定义最后数据分隔线，即--加上BOUNDARY再加上--。
            byte[] end_data = (boundaryPrefix + BOUNDARY + boundaryPrefix + newLine).getBytes();
            // 上传文件头
            StringBuilder sb = new StringBuilder();
            sb.append(boundaryPrefix);
            sb.append(BOUNDARY);
            sb.append(newLine);
            sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"" + newLine);
            sb.append("Content-Type:application/dicom");
            // 参数头设置完以后需要两个换行，然后才是参数内容
            sb.append(newLine);
            sb.append(newLine);
            allSize = allSize + sb.length() + newLine.length() + end_data.length + file.length();
            // 服务器的域名
            URL url = new URL(stowUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            setConnect(allSize, BOUNDARY, conn);

            out = new DataOutputStream(conn.getOutputStream());

            // 上传文件
            // 将参数头的数据写入到输出流中
            out.write(sb.toString().getBytes());

            // 数据输入流,用于读取文件数据
            DataInputStream in = new DataInputStream(new FileInputStream(file));
            byte[] bufferOut = new byte[1024];
            int bytes = 0;
            // 每次读1KB数据,并且将文件数据写入到输出流中
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
                out.flush();
            }
            // 最后换添加行
            out.write(newLine.getBytes());
            in.close();
            // 写上结尾标识
            out.write(end_data);
            out.flush();
            out.close();
            StringBuffer jsonResult = new StringBuffer();
            String line = null;
            // 判断是否文件上传异常
            log.info("文件上传返回码:{},返回描述:{}", conn.getResponseCode(), conn.getResponseMessage());
            if (conn.getResponseCode() != HttpStatus.SC_OK && conn.getResponseCode() != HttpStatus.SC_ACCEPTED) {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    jsonResult.append(line);
                }
                log.info("调用dicom服务器,返回的错误信息如下:{}", jsonResult.toString());
                if (conn.getResponseCode() == HttpStatus.SC_CONFLICT) {
                    return jsonResult.toString();
                } else {
                    throw new WarnException("DICOM文件上传失败,返回码：" + conn.getResponseCode());
                }
            }
            // 定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                jsonResult.append(line);
            }
            return jsonResult.toString();

        } catch (WarnException ue) {
            throw ue;
        } catch (ConnectException ce) {
            throw ce;
        } catch (Exception e) {
            throw e;
        } finally {
            closeStream(out, fileInStream, reader);
        }

    }


    /**
     * 数据流上传DICOM文件
     * @author: birdbro
     * @date: 2022-4-7 15:34
     * @param:
     * @return:
     **/
    public static String uploadByStream(InputStream inputStream, String boundary, String fileName, Long fileSize, String stowUrl, String userId) throws Exception {
        OutputStream out = null;
        FileInputStream fileInStream = null;
        BufferedReader reader = null;
        try {
            // 换行符
            final String newLine = "\r\n";
            final String boundaryPrefix = "--";
            // 传输字节大小
            long allSize = 0;
            // 定义数据分隔线
            String BOUNDARY = boundary;
            // 定义最后数据分隔线，即--加上BOUNDARY再加上--。
            byte[] end_data = (boundaryPrefix + BOUNDARY + boundaryPrefix + newLine).getBytes();
            // 上传文件头
            StringBuilder sb = new StringBuilder();
            sb.append(boundaryPrefix);
            sb.append(BOUNDARY);
            sb.append(newLine);
            sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + fileName + "\"" + newLine);
            sb.append("Content-Type:application/dicom");
            // 参数头设置完以后需要两个换行，然后才是参数内容
            sb.append(newLine);
            sb.append(newLine);
            allSize = allSize + sb.length() + newLine.length() + end_data.length + fileSize;
            // 服务器的域名
            URL url = new URL(stowUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            setConnect(allSize, BOUNDARY, conn);

            out = new DataOutputStream(conn.getOutputStream());

            // 上传文件
            // 将参数头的数据写入到输出流中
            out.write(sb.toString().getBytes());

            // 数据输入流,用于读取文件数据
            byte[] bufferOut = new byte[1024 * 512];
            int bytes = 0;
            fileInStream = (FileInputStream) inputStream;
            while ((bytes = fileInStream.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
                out.flush();
            }
            // 最后换添加行
            out.write(newLine.getBytes());

            // 写上结尾标识
            out.write(end_data);
            out.flush();
            out.close();
            StringBuffer jsonResult = new StringBuffer();
            String line = null;
            // 判断是否文件上传异常
            log.info("文件上传返回码:{},返回描述:{}", conn.getResponseCode(), conn.getResponseMessage());
            if (conn.getResponseCode() != HttpStatus.SC_OK && conn.getResponseCode() != HttpStatus.SC_ACCEPTED) {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    jsonResult.append(line);
                }
                log.info("调用dicom服务器,返回的错误信息如下:{}", jsonResult.toString());
                if (conn.getResponseCode() == HttpStatus.SC_CONFLICT) {
                    return jsonResult.toString();
                } else {
                    throw new WarnException("DICOM文件上传失败,返回码：" + conn.getResponseCode());
                }
            }
            // 定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                jsonResult.append(line);
            }
            return jsonResult.toString();

        } catch (WarnException ue) {
            throw ue;
        } catch (ConnectException ce) {
            throw ce;
        } catch (Exception e) {
            throw e;
        } finally {
            closeStream(out, fileInStream, reader);
        }
    }


    /**
     * 关闭文件流
     * @author: birdbro
     * @date: 2022-4-7 15:34
     * @param:
     * @return:
     **/
    public static void closeStream(OutputStream out, FileInputStream in, BufferedReader reader){
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                log.error("dicom文件关闭in流!", e);
            }
        }
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                log.error("dicom文件关闭out流!", e);
            }
        }
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                log.error("dicom文件关闭reader流!", e);
            }
        }
    }


    /**
     * 设置链接参数
     * @author: birdbro
     * @date: 2022-4-7 15:34
     * @param:
     * @return:
     **/
    public static void setConnect(long allSize, String BOUNDARY, HttpURLConnection conn) throws ProtocolException {

        // 连接服务器时间
        conn.setConnectTimeout(300 * 1000);
        // 服务器响应response时间
        conn.setReadTimeout(300 * 1000);
        // 设置为POST请求
        conn.setRequestMethod("POST");
        // 发送POST请求必须设置如下两行
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        // 设置请求头参数
        // conn.setRequestProperty("Host", "192.168.10.92:8080");
        conn.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");
        conn.setRequestProperty("Accept", "application/dicom+json");
        conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
        conn.setRequestProperty("Content-Type", "multipart/related;type=application/dicom;boundary=" + BOUNDARY);
        // conn.setRequestProperty("Referer",
        // "http://192.168.10.92:8080/dcm4chee-arc/ui2/");
        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("Content-Length", Long.toString(allSize));
    }


    /**
     * 这个方法用来判断 dicom服务器返回的结果里面(返回的HTTP状态为:200,202,409)是否有错误信息 当200,202中有错误信息的时候
     * 会返回203 当200,202中没有错误信息的时候 会返回202 当409中的错误原因有 272 42752 - 43007 时,会返回500
     * ,如果不在上述错误原因时,应该返回203拒收！
     */

    /**
     * 202情况下返回Archive消息
     * @author: birdbro
     * @date: 2022-4-7 15:34
     * @param:
     * @return:
     **/
    public static Map archiveMessageMap(ArchiveMessage arcMsg){

        Map map = new HashMap();

        List<String> retrieveURL = arcMsg.getSuccessList().get(0).getRetrieveURL();
        String successUrl = "";
        if (retrieveURL != null && retrieveURL.size() > 0) {
            successUrl = StringUtils.isNotBlank(retrieveURL.get(0)) ? retrieveURL.get(0) : "";
        }
        String studyUID = CharsTool.matcher(successUrl, "studies/(.*?)/series");
        map.put("studyUid", studyUID);
        String seriesUID = CharsTool.matcher(successUrl, "series/(.*?)/instances");
        map.put("seriesUid", seriesUID);
        String sopUID = CharsTool.matcher(successUrl, "instances/(.*?)$");
        map.put("imageUid", sopUID);
        return map;
    }

    /**
     * 202情况下返回Archive消息
     * @author: birdbro
     * @date: 2022-4-7 15:34
     * @param:
     * @return:
     **/
    public static HashMap<String, Object> archiveMessageMap(ArchiveMessage arcMsg, String userId, String archiveUrl) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        HashMap<String, Object> mapUid = new HashMap<String, Object>();
        List<String> retrieveURL = arcMsg.getSuccessList().get(0).getRetrieveURL();
        String successUrl = "";
        if (retrieveURL != null && retrieveURL.size() > 0) {
            successUrl = StringUtils.isNotBlank(retrieveURL.get(0)) ? retrieveURL.get(0) : "";
        }
        String studyUID = CharsTool.matcher(successUrl, "studies/(.*?)/series");
        mapUid.put("studyUID", studyUID);
        String seriesUID = CharsTool.matcher(successUrl, "series/(.*?)/instances");
        mapUid.put("seriesUID", seriesUID);
        String sopUID = CharsTool.matcher(successUrl, "instances/(.*?)$");
        mapUid.put("sopUID", sopUID);
        mapUid.put("userId", userId);
        mapUid.put("archiveUrl", archiveUrl);

        String[] urlArr = archiveUrl.split("\\|");
        map.put("priUrl", urlArr[0]);
        map.put("pubUrl", urlArr[1]);
        map.put("userId", userId);
        map.put("sopUID", sopUID);
        map.put("studyUID", studyUID);
        map.put("seriesUID", seriesUID);
        map.put("code", DicomUploadEnum.FILE_ARC_SUCCESS.getCode());
        map.put("message", JSON.toJSONString(mapUid));
        map.put("level","info");
        return map;


    }

    /**
     * 返回值Json转arcMsg
     * @author: birdbro
     * @date: 2022-4-7 15:34
     * @param:
     * @return:
     **/
    public static int resultToArcMsg(String json, ArchiveMessage arcMsg){
        json = StringUtils.isBlank(json) ? "{}" : json;

        HashMap map = JSONObject.parseObject(json, HashMap.class);
        List<Success> successSopInfoList = new ArrayList<Success>();
        List<Warnning> warnningSopInfoList = new ArrayList<Warnning>();
        List<Failed> failedSopInfoList = new ArrayList<Failed>();
        List<String> retrieveURL = new ArrayList<String>();

        // 获取成功信息
        String str99 = "";
        if (map.get("00081199") != null) {
            str99 = map.get("00081199").toString();
        }
        // 获取失败信息
        String str98 = "";
        if (map.get("00081198") != null) {
            str98 = map.get("00081198").toString();
        }
        // 获取其他失败信息
        String str9A = "";
        if (map.get("0008119A") != null) {
            str9A = map.get("0008119A").toString();
        }
        // 获取路径信息
        String str90 = "";
        if (map.get("00081190") != null) {
            str90 = map.get("00081190").toString();
        }

        if (StringUtils.isNotBlank(str90) && str90.startsWith("{") && str90.endsWith("}")) {
            dealWith90(retrieveURL, str90);
            arcMsg.setRetrieveURL(retrieveURL);
        }
        if (StringUtils.isNotBlank(str99) && str99.startsWith("{") && str99.endsWith("}")) {
            dealWithStr99(successSopInfoList, warnningSopInfoList, str99);
            if (successSopInfoList.size() != 0) {
                arcMsg.setSuccessList(successSopInfoList);
            }
            if (warnningSopInfoList.size() != 0) {
                arcMsg.setWarnningList(warnningSopInfoList);
            }
        }
        if (StringUtils.isNotBlank(str98) && str98.startsWith("{") && str98.endsWith("}")) {
            dealWithStr98(failedSopInfoList, str98);
            arcMsg.setFailedList(failedSopInfoList);
        }
        if (StringUtils.isNotBlank(str9A)) {
            dealWithStr9A(str9A);
            arcMsg.setOtherFailed(str9A);
        }
        if ((arcMsg.getFailedList() == null || arcMsg.getFailedList().size() == 0)
                && (arcMsg.getSuccessList() == null || arcMsg.getSuccessList().size() == 0)) {
            return UploadEnum.ERROR_CODE_203.getCode();
        } else if ((arcMsg.getFailedList() == null || arcMsg.getFailedList().size() == 0)
                && arcMsg.getSuccessList() != null && arcMsg.getSuccessList().size() > 0) {
            return UploadEnum.ERROR_CODE_202.getCode();
        } else if (arcMsg.getFailedList() != null && arcMsg.getFailedList().size() > 0 && retry) {
            return UploadEnum.ERROR_CODE_500.getCode();
        } else if (arcMsg.getFailedList() != null && arcMsg.getFailedList().size() > 0 && !retry) {
            return UploadEnum.ERROR_CODE_203.getCode();
        } else {
            return UploadEnum.ERROR_CODE_202.getCode();
        }
    }


    public static void dealWithStr9A(String str9A) {
        HashMap map9A = JSONObject.parseObject(str9A, HashMap.class);
        String map9AValue = "";
        if (map9A.get("Value") != null) {
            map9AValue = map9A.get("Value").toString();
            if(StringUtils.isNotBlank(map9AValue)&&map9AValue.startsWith("[")&&map9AValue.endsWith("]")){
                map9AValue=map9AValue.substring(1,map9AValue.length()-1);
            }
            if (StringUtils.isNotBlank(map9AValue) && StringUtils.isNumeric(map9AValue)) {
                int parseValue = Integer.parseInt(map9AValue);
                if (parseValue == 272 || (42752 <= parseValue && parseValue <= 43007)) {
                    retry = true;
                }
            }
        }
    }

    public static void dealWith90(List<String> retrieveURL, String str90) {
        HashMap map90 = JSONObject.parseObject(str90, HashMap.class);
        String map90Value = "";
        if (map90.get("Value") != null) {
            map90Value = map90.get("Value").toString();
        }
        if (StringUtils.isNotBlank(map90Value) && map90Value.startsWith("[") && map90Value.endsWith("]")) {
            retrieveURL.add(map90Value);
        }
    }

    public static void dealWithStr98(List<Failed> failedList, String str98) {
        HashMap map98 = JSONObject.parseObject(str98, HashMap.class);
        String map98Value = "";
        if (map98.get("Value") != null) {
            map98Value = map98.get("Value").toString();
        }
        if (StringUtils.isNotBlank(map98Value) && map98Value.startsWith("[") && map98Value.endsWith("]")) {
            ArrayList map98ValueList = JSONObject.parseObject(map98Value, ArrayList.class);
            for (int i = 0; i < map98ValueList.size(); i++) {
                String map98ValueListn = "";
                if (map98ValueList.get(i) != null) {
                    map98ValueListn = map98ValueList.get(i).toString();
                }
                if (StringUtils.isNotBlank(map98ValueListn) && map98ValueListn.startsWith("{")
                        && map98ValueListn.endsWith("}")) {
                    Failed fn = new Failed();
                    HashMap map98ValueListnMap = JSONObject.parseObject(map98ValueListn, HashMap.class);
                    String map98_50 = "";
                    if (map98ValueListnMap.get("00081150") != null) {
                        map98_50 = map98ValueListnMap.get("00081150").toString();
                    }
                    String map98_55 = "";
                    if (map98ValueListnMap.get("00081155") != null) {
                        map98_55 = map98ValueListnMap.get("00081155").toString();
                    }
                    String map98_97 = "";
                    if (map98ValueListnMap.get("00081197") != null) {
                        map98_97 = map98ValueListnMap.get("00081197").toString();
                    }

                    if (StringUtils.isNotBlank(map98_50) && map98_50.startsWith("{") && map98_50.endsWith("}")) {
                        HashMap map98_50Map = JSONObject.parseObject(map98_50, HashMap.class);
                        String map98_50MapValue = "";
                        if (map98_50Map.get("Value") != null) {
                            map98_50MapValue = map98_50Map.get("Value").toString();
                        }
                        if (StringUtils.isNotBlank(map98_50MapValue) && map98_50MapValue.startsWith("[")
                                && map98_50MapValue.endsWith("]")) {
                            ArrayList map98_50MapValueList = JSONObject.parseObject(map98_50MapValue, ArrayList.class);
                            fn.setReferencedSOPClassUID(map98_50MapValueList);
                        }
                    }
                    if (StringUtils.isNotBlank(map98_55) && map98_55.startsWith("{") && map98_55.endsWith("}")) {
                        HashMap map98_55Map = JSONObject.parseObject(map98_55, HashMap.class);
                        String map98_55MapValue = "";
                        if (map98_55Map.get("Value") != null) {
                            map98_55MapValue = map98_55Map.get("Value").toString();
                        }
                        if (StringUtils.isNotBlank(map98_55MapValue) && map98_55MapValue.startsWith("[")
                                && map98_55MapValue.endsWith("]")) {
                            ArrayList map99_55MapValueList = JSONObject.parseObject(map98_55MapValue, ArrayList.class);
                            fn.setReferencedSOPInstanceUID(map99_55MapValueList);
                        }
                    }
                    if (StringUtils.isNotBlank(map98_97) && map98_97.startsWith("{") && map98_97.endsWith("}")) {
                        HashMap map98_97Map = JSONObject.parseObject(map98_97, HashMap.class);
                        String map98_97MapValue = "";
                        if (map98_97Map.get("Value") != null) {
                            map98_97MapValue = map98_97Map.get("Value").toString();
                            if(StringUtils.isNotBlank(map98_97MapValue)&&map98_97MapValue.startsWith("[")&&map98_97MapValue.endsWith("]")){
                                map98_97MapValue=map98_97MapValue.substring(1,map98_97MapValue.length()-1);
                            }
                            if (StringUtils.isNotBlank(map98_97MapValue) && StringUtils.isNumeric(map98_97MapValue)) {
                                int parseValue = Integer.parseInt(map98_97MapValue);
                                if (parseValue == 272 || (42752 <= parseValue && parseValue <= 43007 && parseValue != 42872)) {
                                    retry = true;
                                }
                            }
                        }
                        fn.setFailureReason(map98_97MapValue);
                    }
                    failedList.add(fn);
                }
            }
        }
    }

    public static void dealWithStr99(List<Success> successList, List<Warnning> warnningList, String str99) {
        HashMap map99 = JSONObject.parseObject(str99, HashMap.class);
        String map99Value = "";
        if (map99.get("Value") != null) {
            map99Value = map99.get("Value").toString();
        }
        if (StringUtils.isNotBlank(map99Value) && map99Value.startsWith("[") && map99Value.endsWith("]")) {
            ArrayList map99ValueList = JSONObject.parseObject(map99Value, ArrayList.class);
            for (int i = 0; i < map99ValueList.size(); i++) {
                String map99ValueListn = "";
                if (map99ValueList.get(i) != null) {
                    map99ValueListn = map99ValueList.get(i).toString();
                }
                if (StringUtils.isNotBlank(map99ValueListn) && map99ValueListn.startsWith("{")
                        && map99ValueListn.endsWith("}")) {
                    Success sn = new Success();
                    Warnning wn = new Warnning();
                    boolean isWarnning = false;
                    HashMap map99ValueListnMap = JSONObject.parseObject(map99ValueListn, HashMap.class);
                    String map99_50 = "";
                    String map99_55 = "";
                    String map99_90 = "";
                    String map99_96 = "";
                    String map99_61 = "";
                    if (map99ValueListnMap.get("00081150") != null) {
                        map99_50 = map99ValueListnMap.get("00081150").toString();
                    }
                    if (map99ValueListnMap.get("00081155") != null) {
                        map99_55 = map99ValueListnMap.get("00081155").toString();
                    }
                    if (map99ValueListnMap.get("00081190") != null) {
                        map99_90 = map99ValueListnMap.get("00081190").toString();
                    }
                    if (map99ValueListnMap.get("00081196") != null) {
                        map99_96 = map99ValueListnMap.get("00081196").toString();
                    }
                    if (map99ValueListnMap.get("04000561") != null) {
                        map99_61 = map99ValueListnMap.get("04000561").toString();
                    }

                    if (StringUtils.isNotBlank(map99_96) && map99_96.startsWith("{") && map99_96.endsWith("}")) {
                        HashMap map99_96Map = JSONObject.parseObject(map99_96, HashMap.class);
                        isWarnning = true;
                        String map99_96MapValue = "";
                        if (map99_96Map.get("Value") != null) {
                            map99_96MapValue = map99_96Map.get("Value").toString();
                        }
                        wn.setWarnningReason(map99_96MapValue);
                    }
                    if (StringUtils.isNotBlank(map99_61) && map99_61.startsWith("{") && map99_61.endsWith("}")) {
                        HashMap map99_61Map = JSONObject.parseObject(map99_61, HashMap.class);
                        isWarnning = true;
                        String map99_61MapValue = "";
                        if (map99_61Map.get("Value") != null) {
                            map99_61MapValue = map99_61Map.get("Value").toString();
                        }
                        if (isWarnning) {
                            wn.setOriginalAttributesSequence(map99_61MapValue);
                        } else {
                            sn.setOriginalAttributesSequence(map99_61MapValue);
                        }
                    }
                    if (StringUtils.isNotBlank(map99_50) && map99_50.startsWith("{") && map99_50.endsWith("}")) {
                        HashMap map99_50Map = JSONObject.parseObject(map99_50, HashMap.class);
                        String map99_50MapValue = "";
                        if (map99_50Map.get("Value") != null) {
                            map99_50MapValue = map99_50Map.get("Value").toString();
                        }
                        if (StringUtils.isNotBlank(map99_50MapValue) && map99_50MapValue.startsWith("[")
                                && map99_50MapValue.endsWith("]")) {
                            ArrayList map99_50MapValueList = JSONObject.parseObject(map99_50MapValue, ArrayList.class);
                            if (isWarnning) {
                                wn.setReferencedSOPClassUID(map99_50MapValueList);
                            } else {
                                sn.setReferencedSOPClassUID(map99_50MapValueList);
                            }
                        }
                    }
                    if (StringUtils.isNotBlank(map99_55) && map99_55.startsWith("{") && map99_55.endsWith("}")) {
                        HashMap map99_55Map = JSONObject.parseObject(map99_55, HashMap.class);
                        String map99_55MapValue = "";
                        if (map99_55Map.get("Value") != null) {
                            map99_55MapValue = map99_55Map.get("Value").toString();
                        }
                        if (StringUtils.isNotBlank(map99_55MapValue) && map99_55MapValue.startsWith("[")
                                && map99_55MapValue.endsWith("]")) {
                            ArrayList map99_55MapValueList = JSONObject.parseObject(map99_55MapValue, ArrayList.class);
                            if (isWarnning) {
                                wn.setReferencedSOPInstanceUID(map99_55MapValueList);
                            } else {
                                sn.setReferencedSOPInstanceUID(map99_55MapValueList);
                            }
                        }
                    }
                    if (StringUtils.isNotBlank(map99_90) && map99_90.startsWith("{") && map99_90.endsWith("}")) {
                        HashMap map99_90Map = JSONObject.parseObject(map99_90, HashMap.class);
                        String map99_90MapValue = "";
                        if (map99_90Map.get("Value") != null) {
                            map99_90MapValue = map99_90Map.get("Value").toString();
                        }
                        if (StringUtils.isNotBlank(map99_90MapValue) && map99_90MapValue.startsWith("[")
                                && map99_90MapValue.endsWith("]")) {
                            ArrayList map99_90MapValueList = JSONObject.parseObject(map99_90MapValue, ArrayList.class);
                            if (isWarnning) {
                                wn.setRetrieveURL(map99_90MapValueList);
                            } else {
                                sn.setRetrieveURL(map99_90MapValueList);
                            }
                        }
                    }
                    if (isWarnning) {
                        warnningList.add(wn);
                    } else {
                        successList.add(sn);
                    }
                }
            }

        }
    }

}
