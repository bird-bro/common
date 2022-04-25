package com.bird.common.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author bird
 * @date 2022-4-7 16:20
 **/
public class FileTool {

    /**
     * MultipartFile 转 File
     * @author: bird
     * @date: 2022-4-7 16:22
     * @param:
     * @return:
     **/
    public static File multipartFileToFile(MultipartFile file, String path) throws Exception {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(path);
            inputStream(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    /**
     * 获取文件流
     * @author: bird
     * @date: 2022-4-7 16:22
     * @param:
     * @return:
     **/
    private static void inputStream(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
