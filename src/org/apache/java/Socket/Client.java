package org.apache.java.Socket;

import java.io.*;
import java.net.Socket;

/**
 * Created by SamsungPC on 2017/7/14.
 */
public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 6666);
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("A1:0 \n");
            pw.flush();
            socket.shutdownOutput();

            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String info = null;
            while((info = br.readLine()) != null){
                System.out.println("I am Client, Server Said : "+info);
            }
            br.close();
            is.close();
            pw.close();
            os.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
