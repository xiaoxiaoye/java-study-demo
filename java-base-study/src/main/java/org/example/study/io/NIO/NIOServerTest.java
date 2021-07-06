package org.example.study.io.NIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @program: java-base-study
 * TODO
 * @author: yejx
 * @create: 2020-10-13 16:30
 */
public class NIOServerTest {
    public static void main(String[] args) throws Exception {
        // 创建一个selector
        Selector selector = Selector.open();

        // 初始化tcp监听通道
        ServerSocketChannel listenChannel = ServerSocketChannel.open();
        listenChannel.bind(new InetSocketAddress(9999));
        listenChannel.configureBlocking(false);
        // 注册到selector,  监听其ACCEPT事件
        listenChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 创建缓冲区, 用于读取socket发送过来的数据，和用于发送数据
        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        while (true) {
            // 阻塞，直到有监听的事件发生
            selector.select();

            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();

                if (key.isAcceptable()) { // 有新的连接建立
                    SocketChannel channel = ((ServerSocketChannel) key.channel()).accept();
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_READ);

                    System.out.println("与【" + channel.getRemoteAddress() + "】建立了连接");
                } else if (key.isReadable()) { // 有数据可读
                    byteBuffer.clear();

                    // 读到流末尾，说明TCP连接断开
                    // 因此需要关闭通道或者去掉监听READ事件
                    // 否则会无限循环
                    if (((SocketChannel) key.channel()).read(byteBuffer) == -1) {
                        key.channel().close();
                        continue;
                    }

                    byteBuffer.flip();
                    while (byteBuffer.hasRemaining()) {
                        byte b = byteBuffer.get();

                        if (b == 0) {
                            System.out.println();

                            byteBuffer.clear();
                            byteBuffer.put("Hello, Client!\0".getBytes());
                            byteBuffer.flip();
                            while (byteBuffer.hasRemaining()) {
                                ((SocketChannel) key.channel()).write(byteBuffer);
                            }
                        } else {
                            System.out.println((char) b);
                        }
                    }
                }
                // 已经处理的事件，要手动移除, 不然下次获取SelectionKey事件列表，已经处理过的事件还会在里面，会导致空指针异常
                keyIterator.remove();
            }
        }
    }
}
