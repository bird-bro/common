package org.birdbro.common.tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * 头像生成工具
 * @author birdbro
 * @date 9:42 2022-12-7
 **/
public class HeadIconTool {

    /**
     * 图片做圆角处理
     * @author:bird
     * @date: 2021-7-21 11:41
     * @param: BufferedImage image
     * @param: int cornerRadius
     * @return: BufferedImage
     **/
    public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return output;
    }


    /**
     * 随机背景色
     * @author:bird
     * @date: 2021-7-21 11:40
     * @return: Color
     **/
    public static Color getRandomColor() {
        String[] beautifulColors =
                new String[]{"52,145,250","20,201,201","90,170,251","63,212,207"};
        int len = beautifulColors.length;
        Random random = new Random();
        String[] color = beautifulColors[random.nextInt(len)].split(",");
        return new Color(Integer.parseInt(color[0]), Integer.parseInt(color[1]),
                Integer.parseInt(color[2]));
    }


    public static String fileType(String fileName){
        return fileName.contains(".") ? fileName.substring(fileName.lastIndexOf("."), fileName.length()) : null;
    }

    /**
     * 头像
     * @author: bird
     * @date: 2022-4-18 13:43
     * @param:
     * @return:
     **/
    public static String headIcon(String name,String outputPath, String outputName, String baseDir, String baseUrl)
            throws IOException {

        int width = 200;
        int height = 200;
        int nameLen = name.length();
        String first = name.substring(0, 1);
        String nameWritten;
        //如果用户输入的姓名少于等于3个字符，不用截取
        if (nameLen <= 3 && CharsTool.isChinese(first)) {
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
        g2.setBackground(HeadIconTool.getRandomColor());
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
                if(CharsTool.isChinese(nameWritten)) {
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

        BufferedImage rounded = HeadIconTool.makeRoundedCorner(bi, 99);
        ImageIO.write(rounded, "png", file);
        return  pathName;
    }
}
