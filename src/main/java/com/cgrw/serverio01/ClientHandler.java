package com.cgrw.serverio01;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientHandler {

    public static final int MAX_DATA_LEN = 1024;
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        System.out.println("新客户端接入");

        new Thread(this::doStart
        ).start();
    }

    private void doStart() {
        // 打印当前线程名
        String threadName = Thread.currentThread().getName();
        System.out.println("ClientHandler：" + threadName);
        try (
                InputStream inputStream = socket.getInputStream()) {
            byte[] data = new byte[MAX_DATA_LEN];
            int len;
            while ((len = inputStream.read(data)) != -1) {
                String message = new String(data, 0, len);
                System.out.println("客户端" + threadName + "传来消息： " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
