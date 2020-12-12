package com.j.sm.task;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @ClassName CarouselThread
 * @Description 轮播线程
 * @Author orange
 * @Date 2020-12-12 14:48
 **/

public class CarouselThread implements Runnable {
    private String[] imgs = {
            "https://share--app.oss-cn-hangzhou.aliyuncs.com/bg/20201212161100.JPG",
            "https://pic-go-test0.oss-cn-hangzhou.aliyuncs.com/image/20201210125844.jpeg",
            "https://share--app.oss-cn-hangzhou.aliyuncs.com/bg/20201212162112.JPG",
            "https://share--app.oss-cn-hangzhou.aliyuncs.com/avatar/55354673-67a3-42c8-8db1-d97b203e41bc.JPG",
            "https://share--app.oss-cn-hangzhou.aliyuncs.com/bg/20201212170425.JPG"
    };

    //背景图标签
    private JLabel bgLabel;


    public void setBgLabel(JLabel bgLabel) {
        this.bgLabel = bgLabel;
    }


    @Override
    public void run() {
        int index = 0;
        //设置轮播长度
        int length = imgs.length;
        while (true) {
            try {
                //构建url
                URL url = new URL(imgs[index]);
                //连接
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                //通过连接得到字节输入流对象
                InputStream is = conn.getInputStream();
                //定义缓冲区（一般为8的倍数）
                byte[] buffer = new byte[1024];
                //每次读取的字节大小
                int len = 0;
                //定义输出流,ByteArrayOutputStream可以直接写入数组
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                //将获取到的字节流存储data数组
                byte[] data = baos.toByteArray();
                //读取图片对应的字节数组
                is.read(data);
                //通过字节数组构建一个icon
                Icon icon = new ImageIcon(data);
                //设置背景标签图片
                bgLabel.setIcon(icon);
                Thread.sleep(1000);
            } catch (Exception e) {
                System.err.println("异常");
            }
            index++;
            if (index == length) {
                index = 0;
            }
        }
    }
}

