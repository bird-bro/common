package com.bird.common.tools;

import com.alibaba.excel.EasyExcelFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author bird
 * @date 2022-1-20 11:22
 **/
@Slf4j
public class ExcelTool {

    /**
    * 导出Excel
    * @author: birdbro
    * @date: 2022-4-22
    * @param:
    * @return:
    **/
    public static void exportExcel(List<?> list, String sheetName, Class<?> pojoClass, String fileName, String waterMark) {
        try {
            //字符编码
            String encode = "utf-8";
            //文件名
            fileName = URLEncoder.encode(fileName, encode);

            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            assert servletRequestAttributes != null;
            HttpServletResponse response = servletRequestAttributes.getResponse();

            assert response != null;
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding(encode);
            response.setHeader("Content-disposition","attachment;filename*=utf-8''" + fileName + ".xlsx");

            EasyExcelFactory.write(response.getOutputStream(), pojoClass)
                    .autoCloseStream(true)
                    .inMemory(true)
                    .registerWriteHandler(new WaterMarkTool(waterMark))
                    .sheet(sheetName)
                    .doWrite(list);
        }catch (Exception e){
            log.error("- error excel :" + e);
        }
    }


}
