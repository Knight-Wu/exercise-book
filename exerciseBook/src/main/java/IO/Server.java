package IO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 该类为多线程类，用于服务端
 */

public class Server {
    public static void main(String[] args) throws Exception {
        //服务端在20006端口监听客户端请求的TCP连接
        ServerSocket server = new ServerSocket(20006);
        Socket client = null;
        boolean f = true;
        while (f) {
            System.out.println(Thread.currentThread().getName() + " server is waiting for client connect.");
            //等待客户端的连接，如果没有获取连接
            client = server.accept();
            System.out.println("与客户端连接成功！");
            //为每个客户端连接开启一个线程
            new Thread(new ServerThread(client)).start();
        }
        server.close();
    }


    public static class ServerThread implements Runnable {

        private Socket client = null;

        public ServerThread(Socket client) {
            this.client = client;
        }

        public void run() {
            try {
                //获取Socket的输出流，用来向客户端发送数据
                PrintStream out = new PrintStream(client.getOutputStream());
                //获取Socket的输入流，用来接收从客户端发送过来的数据
                BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
                boolean flag = true;
                while (flag) {
                    //接收从客户端发送过来的数据
                    String str = buf.readLine();
                    if (str == null || "".equals(str)) {
                        flag = false;
                    } else {
                        String threadName = Thread.currentThread().getName();
                        System.out.println("server: " + threadName + ", client says: " + str);
                        if ("bye".equals(str)) {
                            flag = false;
                        } else {
                            //将接收到的字符串前面加上echo，发送到对应的客户端
                            out.println("server " + threadName + ", got it");
                        }
                    }
                }
                out.close();
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}