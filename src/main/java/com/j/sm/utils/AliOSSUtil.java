package com.j.sm.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;

import java.io.File;
import java.util.UUID;

/**
 * @ClassName AliOSSUtil
 * @Description 阿里云OSS上传工具类
 * @Author orange
 * @Date 2020-11-19 14:08
 **/

public class AliOSSUtil {
    /**
     * 将本地file上传到阿里云指定域名的目录下，并用UUID重命名
     *
     * @param file 待传文件
     * @return String 上传成功返回的url
     */

    public static String ossUpload(File file) {
        String bucketDomain = "https://student-management-img.oss-cn-hangzhou.aliyuncs.com/";
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        String accessKeyId = "LTAI4GCkq4z2WzJsQgvq8Uqf";
        String accessKeySecret = "JcgRc5GdXtWAfyy1FncWRwrQPCF5Ox";
        //Bucket名称
        String bucketName = "student-management-img";
        //目录名称
        String fileDir = "logo/";
        //获得本地文件的文件名
        String fileName = file.getName();
        //上传后的文件名生成，这里会将客户端的文件保留扩展名，主文件名用UUID生成
        String fileKey = UUID.randomUUID().toString() + fileName.substring(fileName.indexOf("."));
        //创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //上传file到bucketName的fileDir目录下，用fileKey作为新文件名存储
        ossClient.putObject(bucketName, fileDir + fileKey, file);
        //关闭OSSClient实例
        ossClient.shutdown();
        //返回URL组成：域名+目录+新文件名
        return  bucketDomain + fileDir + fileKey;
    }

    public static void main(String[] args) {
        File file = new File("/Users/orange/Documents/school/java/sm-logo/t.jpg");
        String url = ossUpload(file);
        System.out.println(url);
    }
}
