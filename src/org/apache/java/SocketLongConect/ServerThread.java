package org.apache.java.SocketLongConect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by SamsungPC on 2017/7/20.
 */
public class ServerThread implements Runnable{

    //定义当前线程所处理的Socket
    Socket s = null;
    //该线程所处理的Socket所对应的输入流
    BufferedReader br = null;
    public ServerThread(Socket s) throws IOException {
        this.s = s;
        //初始化该Socket对应的输入流
        br = new BufferedReader(new InputStreamReader(s.getInputStream(),
                "utf-8"));
    }

    @Override
    public void run() {
        try
        {

            String content = null;
            //采用循环不断从Socket中读取客户端发送过来的数据
            while((content = readFromClient())!=null){
                //遍历socketList中的每个Socket
                //将读到的内容向每个Socket发送一次
                System.out.println(content);
                for(Socket s : ServerLongConn.DeviceSocketList){
//                    System.out.println(s.getInetAddress());
                    OutputStream os = s.getOutputStream();
                    try{
                        os.write((content+"\n").getBytes("utf-8"));//注意发送消息的时候一定要加换行符，因为在readLine的时候看的就是换行符
                    }catch (Exception e){
                        ServerLongConn.DeviceSocketList.remove(s);
                        ServerLongConn.NodeSocketList.remove(s);
                        break;
                    }
                }
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    private String readFromClient() {
        try{
            return br.readLine();
        }catch(IOException e){   //如果捕捉到异常，表明该Socket对应的客户端已经被关闭
            //删除该Socket
            ServerLongConn.DeviceSocketList.remove(s);
            ServerLongConn.NodeSocketList.remove(s);

            System.out.println(ServerLongConn.DeviceSocketList.size()+" "+ServerLongConn.NodeSocketList.size());

        }
        return null;
    }
}
