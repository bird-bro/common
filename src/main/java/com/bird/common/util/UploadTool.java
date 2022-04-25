package com.bird.common.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

/**
 * 上传
 * @author bird
 * @date 2021-9-30 8:42
 **/
@Slf4j
public class UploadTool {

    /**
     * 批量上传文件
     * @author: bird
     * @date: 2022-4-7 15:34
     * @param:
     * @return:
     **/
    public static List<String> uploadFiles(List<MultipartFile> files, String baseDir, String subDir, String directory) throws IOException {
        List<String> msgs = new ArrayList();
        if (files.size() < 1) {
            msgs.add("file_empty");
            return msgs;
        } else {
            int i;
            MultipartFile file;
            String filename;
            String type;
            for(i = 0; i < files.size(); ++i) {
                file = (MultipartFile)files.get(i);
                if (!file.isEmpty()) {
                    filename = file.getOriginalFilename();
                    type = filename.indexOf(".") != -1 ? filename.substring(filename.lastIndexOf("."), filename.length()) : null;
                    if (type == null) {
                        msgs.add("file_empty！");
                        return msgs;
                    }

                    if (!".PNG".equals(type.toUpperCase()) && !".JPG".equals(type.toUpperCase())) {
                        msgs.add("wrong_file_extension");
                        return msgs;
                    }
                }
            }

            for(i = 0; i < files.size(); ++i) {
                file = (MultipartFile)files.get(i);
                filename = file.getOriginalFilename();
                type = filename.indexOf(".") != -1 ? filename.substring(filename.lastIndexOf("."), filename.length()) : null;
                String var10000 = UUID.randomUUID().toString();
                String fileName = var10000 + type;
                String filepath = baseDir + subDir + directory + fileName;
                String subPath = subDir + directory + fileName;
                File filesPath = new File(baseDir + subDir + directory);
                if (!filesPath.exists()) {
                    filesPath.mkdir();
                }

                BufferedOutputStream out = null;
                if (filepath.indexOf(".") != -1) {
                    filepath.substring(filepath.lastIndexOf(".") + 1, filepath.length());
                } else {
                    var10000 = null;
                }

                try {
                    out = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
                    out.write(file.getBytes());
                    msgs.add(subPath);
                } catch (Exception var18) {
                    var18.printStackTrace();
                } finally {
                    out.flush();
                    out.close();
                }
            }

            return msgs;
        }
    }



    /**
     * 上传文件
     * @author: bird
     * @date: 2022-4-7 15:34
     * @param:
     * @return:
     **/
    public static String uploadFile(MultipartFile file, String baseDir, String subDir, String directory) throws IOException {
        String msg = "";
        if (file != null) {
            msg = "file_empty";
            return msg;
        } else {
            String filename;
            String type;
            if (!file.isEmpty()) {
                filename = file.getOriginalFilename();
                type = filename.indexOf(".") != -1 ? filename.substring(filename.lastIndexOf("."), filename.length()) : null;
                if (type == null) {
                    msg = "file_empty";
                    return msg;
                }
                if (!".PNG".equals(type.toUpperCase()) && !".JPG".equals(type.toUpperCase()) && !".JPEG".equals(type.toUpperCase()) && !".PDF".equals(type.toUpperCase())) {
                    msg = "wrong_file_extension";
                    return msg;
                }
            }

            filename = file.getOriginalFilename();
            type = filename.indexOf(".") != -1 ? filename.substring(filename.lastIndexOf("."), filename.length()) : null;
            String var10000 = UUID.randomUUID().toString();
            String fileName = var10000 + type;
            String filepath = baseDir + subDir + directory + fileName;
            String subPath = subDir + directory + fileName;
            File filesPath = new File(baseDir + subDir + directory);
            if (!filesPath.exists()) {
                filesPath.mkdir();
            }

            BufferedOutputStream out = null;
            if (filepath.indexOf(".") != -1) {
                filepath.substring(filepath.lastIndexOf(".") + 1, filepath.length());
            } else {
                var10000 = null;
            }

            String var13;
            try {
                out = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
                out.write(file.getBytes());
                String var12 = subPath;
                return var12;
            } catch (Exception var17) {
                var17.printStackTrace();
                var13 = msg;
            } finally {
                out.flush();
                out.close();
            }

            return var13;
        }
    }








}
