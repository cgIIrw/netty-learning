package com.cgrw.serverio02;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: cgrw
 **/
public class ServerIO {
    public static void main(String[] args) throws IOException {
        int port = 8989;
        ServerSocket ss = new ServerSocket(port);
        while (true) {
            final Socket socket = ss.accept();
            new Thread(() -> {
                while (true) {
                    try {
                        BufferedInputStream in = new BufferedInputStream(
                                socket.getInputStream());
                        byte[] buf = new byte[1024];
                        int len = in.read(buf);
                        String message = new String(buf, 0, len);
                        BufferedOutputStream out = new BufferedOutputStream(
                                socket.getOutputStream());
                        out.write(message.getBytes());
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
