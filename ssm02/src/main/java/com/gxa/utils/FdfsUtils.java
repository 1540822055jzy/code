package com.gxa.utils;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;
import java.util.Properties;


public class FdfsUtils {

    public static String  upload(byte[] bytes,String extName) throws Exception{
        TrackerServer ts = null;
        StorageServer ss = null;
        try {
            //读取FsatDFS的配置文件用于将所有的tracker的地址读取到内存中
//            ClientGlobal.init("classpath:fdfs_client.properties");
            Properties properties = new Properties();
            properties.load(FdfsUtils.class.getClassLoader().getResourceAsStream("fdfs_client.properties"));
            ClientGlobal.initByProperties(properties);

            TrackerClient tc = new TrackerClient();
            ts = tc.getConnection();
            ss = tc.getStoreStorage(ts);
            //定义storage的客户端对象，需要使用这个对象来完成具体文件上传 下载和删除
            StorageClient sc = new StorageClient(ts,ss);
            //文件上传
            //参数一 需要上传的文件的绝对路径
            //参数二 为需要上传的文件的扩展名
            //参数三 为文件的属性文件通常不上传
            //返回一个String数组 这个数据对我们非常重要，建议存入数据库
            //返回值，一个是组名，一个是上传文件存储在的目录
//            String[] result = sc.upload_appender_file("d:/do01.jpg","jpg",null);
            String[] result =   sc.upload_appender_file(bytes,extName,null);
//            for (String str:result) {
//                System.out.println(str);
//            }
            return "http://192.168.10.11:8080/" + result[0]+  "/" + result[1];
        } catch (IOException | MyException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ss != null){
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ts != null){
                try {
                    ts.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //文件下载
    public static void download() {
        TrackerServer ts = null;
        StorageServer ss = null;
        try {
            //读取FsatDFS的配置文件用于将所有的tracker的地址读取到内存中
            ClientGlobal.init("fdfs_client.properties");
            TrackerClient tc = new TrackerClient();
            ts = tc.getConnection();
            ss = tc.getStoreStorage(ts);
            //定义storage的客户端对象，需要使用这个对象来完成具体文件上传 下载和删除
            StorageClient sc = new StorageClient(ts,ss);
            //文件下载
            //参数一 需要下载的文件的组名
            //参数二 需要下载文件的远程文件名
            //参数三 需要保存到本地的文件名称
            //返回一个int类型的数据，返回0表示文件下载成功其它值表示文件下载失败
            String groupName = "group1";
            String remoteName = "M00/00/00/sBRcHmDIlLCEDtsjAAAAAE_bs3A176.png";
            String localFileName = "d:/download.png";
            int i = sc.download_file(groupName, remoteName,localFileName);
            System.out.println(i);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        } finally {
            if (ss != null){
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ts != null){
                try {
                    ts.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void delete() {
        TrackerServer ts = null;
        StorageServer ss = null;
        try {
            //读取FsatDFS的配置文件用于将所有的tracker的地址读取到内存中
            ClientGlobal.init("fdfs_client.properties");
            TrackerClient tc = new TrackerClient();
            ts = tc.getConnection();
            ss = tc.getStoreStorage(ts);
            //定义storage的客户端对象，需要使用这个对象来完成具体文件上传 下载和删除
            StorageClient sc = new StorageClient(ts,ss);
            //文件下载
            //参数一 需要删除的文件的组名
            //参数二 需要删除文件的远程文件名
            //返回一个int类型的数据，返回0表示文件删除成功其它值表示文件删除失败
            String groupName = "group1";
            String remoteName = "M00/00/00/wKgKDWMz_8eER7wTAAAAANfyc6Y178.jpg";
            int i = sc.delete_file(groupName,remoteName);
            System.out.println(i);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        } finally {
            if (ss != null){
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ts != null){
                try {
                    ts.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
