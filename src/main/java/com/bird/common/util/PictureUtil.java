package com.bird.common.util;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 头像
 * @author bird
 * @date 2021-7-21 11:12
 **/
public class PictureUtil {

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
                new String[]{"0,179,54", "1,83,255","168,57,208","208,57,173","92,57,208"};
        int len = beautifulColors.length;
        Random random = new Random();
        String[] color = beautifulColors[random.nextInt(len)].split(",");
        return new Color(Integer.parseInt(color[0]), Integer.parseInt(color[1]),
                Integer.parseInt(color[2]));
    }

    /**
     * 是否是中文
     * @author:bird
     * @date: 2021-7-21 11:39
     * @param: String str
     * @return: boolean
     **/
    public static boolean isChinese(String str) {
        String regEx = "[\\u4e00-\\u9fa5]+";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        else {
            return false;
        }
    }
}
