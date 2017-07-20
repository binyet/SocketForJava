package org.apache.java.SocketLongConect;

import javax.swing.plaf.synth.SynthTextAreaUI;
import javax.xml.soap.Node;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by SamsungPC on 2017/7/20.
 */
public class ServerLongConn {
    //定义保存所有Socket的ArrayList
    public static ArrayList<Socket> NodeSocketList = new ArrayList<Socket>();
    public static ArrayList<Socket> DeviceSocketList = new ArrayList<Socket>();

    public static void main(String[] args) throws IOException {
        // TODOAuto-generated method stub
        ServerSocket ss = new ServerSocket(6666);
        int num = 0;
        while(true){
            //此代码会阻塞，将一直等待别人连接
            Socket s = ss.accept();
            System.out.println(s.getInetAddress());
            if (s.getInetAddress().equals("/219.218.130.246"))
            {
                if (NodeSocketList.contains(s) == false)
                    NodeSocketList.add(s);
            }
            else
            {
                if(DeviceSocketList.contains(s) == false)
                    DeviceSocketList.add(s);
            }
            //每当客户端连接后启动一条ServerThread线程为该客户端服务
//            if(DeviceSocketList.contains(s) == false && NodeSocketList.contains(s) == false)
                new Thread(new ServerThread(s)).start();
        }
    }
}
