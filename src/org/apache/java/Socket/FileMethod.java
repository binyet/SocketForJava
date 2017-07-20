package org.apache.java.Socket;

import java.io.*;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 * Created by SamsungPC on 2017/7/16.
 * 处理KNN训练集数据，
 */
public class FileMethod {

    public static void main(String[] args) {
        try{
            InetAddress addr = InetAddress.getLocalHost();
            String ip = addr.getHostAddress();
            System.out.print(ip);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static class Data{
        public String msg;
        public String info;

        public Data(String msg, String info){
            this.msg = msg;
            this.info = info;
        }
    }
}

