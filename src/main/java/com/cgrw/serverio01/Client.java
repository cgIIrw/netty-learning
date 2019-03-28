package com.cgrw.serverio01;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;

    public static void main(String[] args) {

        new Thread(() -> {
            System.out.println("Client：" + Thread.currentThread().getName());
            System.out.print("客户端发送数据：");
            try (
                    final Socket socket = new Socket(HOST, PORT);
                    Scanner scan = new Scanner(System.in)) {
                while (scan.hasNext()) {
                    System.out.print("客户端发送数据：");
                    String message = scan.next();
                    if (message.equals("EXIT")) {
                        System.out.println("关闭当前客户端！");
                        break;
                    }
                    socket.getOutputStream().write(message.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ).start();
    }
}
