package com.zyy.springcloud.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 文字水印
 */
public final class ImageMarkUtils {
    private static Logger logger = LoggerFactory.getLogger(ImageMarkUtils.class);

    /**
     * @param srcImgPath       源图片路径
     * @param tarImgPath       保存的图片路径
     * @param markContentColor 水印颜色
     * @param waterMarkContent 水印内容
     * @param font             水印字体
     * @throws Exception
     */
    private static void addWaterMarkTextForUrl(String srcImgPath, String tarImgPath, Color markContentColor, String waterMarkContent, Font font) throws Exception {
        FileOutputStream outImgStream = null;
        URL url = new URL(srcImgPath);
        URLConnection urlConn = url.openConnection();
        InputStream input = urlConn.getInputStream();
        try {
            // 读取原图片信息
            Image srcImg = ImageIO.read(input);//文件转化为图片
            int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
            int srcImgHeight = srcImg.getHeight(null);//获取图片的高
            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            g.setColor(markContentColor); //根据图片的背景设置水印颜色
            g.setFont(font); //设置字体

            //设置水印的坐标
            int x = srcImgWidth - 20 - ImageMarkUtils.getWatermarkWidth(waterMarkContent, g);
            int y = srcImgHeight - 1 - ImageMarkUtils.getWatermarkHeight(waterMarkContent, g);

            g.drawString(waterMarkContent, x, y); //画出水印
            g.dispose();
            // 输出图片
            outImgStream = new FileOutputStream(tarImgPath);
            ImageIO.write(bufImg, "jpg", outImgStream);
            logger.debug("[ImageMarkUtils.addWaterMarkText]添加文字水印完成");
            outImgStream.flush();
            outImgStream.close();
            input.close();

        } catch (Exception e) {
            logger.error("[ImageMarkUtils.addWaterMarkText]出错", e);
            //			throw e;
        } finally {
            try {
                if (null != outImgStream) {
                    outImgStream.close();
                }
                if (null != input) {
                    input.close();
                }
            } catch (Exception e) {
                logger.error("[ImageMarkUtils.addWaterMarkText]出错", e);
            }
        }
    }

    /**
     * @param srcImgPath       源图片路径
     * @param tarImgPath       保存的图片路径
     * @param waterMarkContent 水印内容
     * @param markContentColor 水印颜色
     * @param font             水印字体
     */
    public static void addWaterMarkText(String srcImgPath, String tarImgPath, Color markContentColor, String waterMarkContent, Font font) {
        FileOutputStream outImgStream = null;
        try {
            // 读取原图片信息
            File srcImgFile = new File(srcImgPath);//得到文件
            Image srcImg = ImageIO.read(srcImgFile);//文件转化为图片
            int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
            int srcImgHeight = srcImg.getHeight(null);//获取图片的高
            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            g.setColor(markContentColor); //根据图片的背景设置水印颜色
            g.setFont(font);              //设置字体

            //设置水印的坐标
            int x = srcImgWidth - 20 - ImageMarkUtils.getWatermarkWidth(waterMarkContent, g);
            int y = srcImgHeight - 1 - ImageMarkUtils.getWatermarkHeight(waterMarkContent, g);

            g.drawString(waterMarkContent, x, y);  //画出水印
            g.dispose();
            // 输出图片  
            outImgStream = new FileOutputStream(tarImgPath);
            ImageIO.write(bufImg, "jpg", outImgStream);
            logger.debug("[ImageMarkUtils.addWaterMarkText]添加文字水印完成");
            outImgStream.flush();
            outImgStream.close();
        } catch (Exception e) {
            logger.error("[ImageMarkUtils.addWaterMarkText]出错", e);
        } finally {
            try {
                if (null != outImgStream) {
                    outImgStream.close();
                }
            } catch (Exception e) {
                logger.error("[ImageMarkUtils.addWaterMarkText]出错", e);
            }
        }
    }

    /**
     * @param @param  waterMarkContent
     * @param @param  g
     * @param @return 设定文件
     * @return int    返回类型
     * @throws
     * @Title: getWatermarkLength
     * @Description: 得到内容的长度
     */
    private static int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }

    /**
     * @param @param  waterMarkContent
     * @param @param  g
     * @param @return 设定文件
     * @return int    返回类型
     * @throws
     * @Title: getWatermarkWidth
     * @Description: 得到内容宽度
     */
    public static int getWatermarkWidth(String waterMarkContent, Graphics2D g) {
        Font f = g.getFont();
        FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(f);
        return fm.stringWidth(waterMarkContent);
    }

    /**
     * @param @param  waterMarkContent
     * @param @param  g
     * @param @return 设定文件
     * @return int    返回类型
     * @throws
     * @Title: getWatermarkHeight
     * @Description: 得到内容高度
     */
    private static int getWatermarkHeight(String waterMarkContent, Graphics2D g) {
        Font f = g.getFont();
        FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(f);
        return fm.getHeight();
    }

    /**
     * @param @param  url
     * @param @param  text
     * @param @param  currentDir
     * @param @throws Exception    设定文件
     * @return void    返回类型
     * @throws
     * @Title: markText
     * @Description: 加文字水印
     */
    public static void markText(String url, String text, String currentDir) throws Exception {
        String srcImgPath = url; //源图片地址
        String tarImgPath = url;
        String waterMarkContent = text;
        if (text.length() > 10) {
            waterMarkContent = text.substring(0, 10);
        }

        //水印内容
        Font font = new Font("隶书", Font.PLAIN, 35); //水印字体
        Color color = new Color(255, 255, 255, 200); //水印图片色彩以及透明度
        ImageMarkUtils.addWaterMarkText(srcImgPath, tarImgPath, color, waterMarkContent, font);
    }

    public static void main(String[] args) {
        String srcImgPath = "D:/temp/source.jpg"; //源图片地址
        String tarImgPath = "D:/temp/target.jpg"; //待存储的地址
        String waterMarkContent = "ZYY TEST"; //水印内容
        Font font = new Font("隶书", Font.BOLD, 60); //水印字体
        Color color = new Color(0, 255, 255, 200); //水印图片色彩以及透明度
        ImageMarkUtils.addWaterMarkText(srcImgPath, tarImgPath, color, waterMarkContent, font);
    }

}
