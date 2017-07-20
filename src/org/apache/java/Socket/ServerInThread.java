package org.apache.java.Socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SamsungPC on 2017/7/14.
 */
public class ServerInThread extends Thread {
    ArrayList<Socket> DeviceList = new ArrayList<Socket>();
    ArrayList<Socket> NodeList = new ArrayList<Socket>();

    Socket socket = null;
    public ServerInThread(Socket socket){
        this.socket = socket;
    }
    public void run(){
        try {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String info = null;
            while((info = br.readLine()) != null){
                System.out.println(info);
                System.out.println(socket.getLocalSocketAddress());
                String[] infos = info.split(":");
                if(infos.length == 2){
                    if(NodeList.contains(socket) == false)
                        NodeList.add(socket);
                }
                else{
                    if(DeviceList.contains(socket) == false)
                        DeviceList.add(socket);
                }
                for(int i=0;i<DeviceList.size();i++){
                    OutputStream os = DeviceList.get(i).getOutputStream();
                    PrintWriter pw = new PrintWriter(os);
                    pw.write(info);
                    pw.flush();
                    pw.close();
                    os.close();
                }
//                System.out.println(info);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(6666);
//        Socket socket = null;
        int count = 0;
        while(true){
            Socket socket = serverSocket.accept();
            if(socket!= null){
                ServerInThread serverInThread = new ServerInThread(socket);
                serverInThread.start();
            }
             Thread.sleep(100);
        }
    }
}
