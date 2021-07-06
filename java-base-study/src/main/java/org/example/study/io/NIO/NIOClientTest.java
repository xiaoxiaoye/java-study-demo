package org.example.study.io.NIO;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @program: java-base-study
 * TODO
 * @author: yejx
 * @create: 2020-10-13 17:00
 */
public class NIOClientTest {
    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 9999)){
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            out.write("Hello, Server!\0".getBytes());

            int b;
            while ((b = in.read()) != 0) {
                System.out.println((char) b);
                if (b == -1){
                    break;
                }
            }
            System.out.println();
        }
    }
}
