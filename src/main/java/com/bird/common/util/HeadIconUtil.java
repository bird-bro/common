package com.bird.common.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 头像生成
 * @author bird
 * @date 2022-4-7 16:26
 **/
public class HeadIconUtil {

    public static String fileType(String fileName){
        return fileName.contains(".") ? fileName.substring(fileName.lastIndexOf("."), fileName.length()) : null;
    }

    public static String createIcon(String name,String outputPath, String outputName, String baseDir, String baseUrl)
            throws IOException {

        int width = 200;
        int height = 200;
        int nameLen = name.length();
        String first = name.substring(0, 1);
        String nameWritten;
        //如果用户输入的姓名少于等于3个字符，不用截取
        if (nameLen <= 3 && PictureUtil.isChinese(first)) {
            nameWritten = name;
        } else {
            nameWritten = name.substring(0,1);
        }
        //文件存储路径
        String pathName = outputPath + File.separator + outputName + ".png";
        String filename = baseDir + pathName;
        File file = new File(filename);

        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setBackground(PictureUtil.getRandomColor());
        g2.clearRect(0, 0, width, height);
        g2.setPaint(Color.WHITE);
        Font font = null;
        switch (nameWritten.length()){
            case 3:
                font = new Font("微软雅黑", Font.PLAIN, 60);
                g2.setFont(font);
                g2.drawString(nameWritten, 14, 120);
                break;
            case 2:
                font = new Font("微软雅黑", Font.PLAIN, 90);
                g2.setFont(font);
                g2.drawString(nameWritten, 12, 130);
                break;
            case 1:
                //中文
                if(PictureUtil.isChinese(nameWritten)) {
                    font = new Font("微软雅黑", Font.PLAIN, 130);
                    g2.setFont(font);
                    g2.drawString(nameWritten, 35, 140);
                }
                //英文
                else {
                    font = new Font("微软雅黑", Font.PLAIN, 140);
                    g2.setFont(font);
                    g2.drawString(nameWritten.toUpperCase(), 50, 140);
                }
                break;
            default:
                font = new Font("微软雅黑", Font.PLAIN, 130);
                g2.setFont(font);
                g2.drawString(nameWritten, 35, 140);
                break;
        }

        BufferedImage rounded = PictureUtil.makeRoundedCorner(bi, 99);
        ImageIO.write(rounded, "png", file);
        return  pathName;
    }

}
