package com.bird.common.tools;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 水印策略
 * @author bird
 * @date 2022-1-20 11:20
 **/
@RequiredArgsConstructor
public class WaterMarkTool implements SheetWriteHandler {

    private final String WATER_MARK;


    /**
     * 设置并创建水印
     * @author:bird
     * @date: 2022-1-20 13:39
     * @param: content 水印内容
     * @return: png
     **/
    private static ByteArrayOutputStream createWaterMark(String content) throws IOException {

        int width = 2200;
        int height = 600;

        //获取bufferedImage对象
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        String fontType = "微软雅黑";
        int fontStyle = Font.BOLD;
        int fontSize = 36;
        Font font = new Font(fontType, fontStyle, fontSize);

        // 获取Graphics2d对象
        Graphics2D g2d = image.createGraphics();
        image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g2d.dispose();
        g2d = image.createGraphics();
        //设置字体颜色和透明度，最后一个参数为透明度
        g2d.setColor(new Color(236, 88, 104, 80));
        // 设置字体
        g2d.setStroke(new BasicStroke(1));
        // 设置字体类型  加粗 大小
        g2d.setFont(font);
        //设置倾斜度
        g2d.rotate(-0.2, (double) image.getWidth() / 2, (double) image.getHeight() / 2);

        FontRenderContext context = g2d.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(content, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = -bounds.getY();
        double baseY = y + ascent;

        // 写入水印文字原定高度过小，所以累计写水印，增加高度
        g2d.drawString(content, (int) x, (int) baseY);
        // 设置透明度
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        // 释放对象
        g2d.dispose();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "png", os);
        return os;
    }


    /**
     * 为Excel打上水印工具函数
     *
     * @param sheet excel sheet
     * @param bytes 水印图片字节数组
     */
    public static void putWaterRemarkToExcel(XSSFSheet sheet, byte[] bytes) {
        XSSFWorkbook workbook = sheet.getWorkbook();
        int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
        String rID = sheet.addRelation(null, XSSFRelation.IMAGES, workbook.getAllPictures().get(pictureIdx))
                .getRelationship().getId();
        //设置背景图片----关键代码
        sheet.getCTWorksheet().addNewPicture().setId(rID);
    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }


    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        try (ByteArrayOutputStream waterMark = createWaterMark(WATER_MARK)) {
            XSSFSheet sheet = (XSSFSheet) writeSheetHolder.getSheet();
            putWaterRemarkToExcel(sheet, waterMark.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
