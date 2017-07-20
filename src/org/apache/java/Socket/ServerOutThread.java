package org.apache.java.Socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by SamsungPC on 2017/7/14.
 */
public class ServerOutThread {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            while(true){
                serverSocket = new ServerSocket(6666);
                Socket socket = serverSocket.accept();
                System.out.println(socket.getInetAddress());

                OutputStream os = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os);
                pw.write("hello world");
                pw.flush();

                pw.close();
                os.close();
                socket.close();
                serverSocket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
