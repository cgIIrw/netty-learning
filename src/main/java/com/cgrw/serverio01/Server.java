package com.cgrw.serverio01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
    private ServerSocket serverSocket;

    Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("服务端启动成功，端口:" + port);
        } catch (IOException exception) {
            System.out.println("服务端启动失败");
        }
    }

    void doStart() {
        // 打印当前线程名
        System.out.println("Server：" + Thread.currentThread().getName());
        while (true) {
            try {
                Socket client = serverSocket.accept();
                // 处理客户端接收的信息
                new ClientHandler(client).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

