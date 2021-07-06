package org.example.study.io;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @program: java-base-study
 * TODO
 * @author: yejx
 * @create: 2020-10-13 15:33
 */
public class FileChannelTest {
    public static void main(String[] args) throws Exception {
        try(FileChannel channel = new RandomAccessFile("test.txt", "rw").getChannel()){
            // 追加到文件末尾
            channel.position(channel.size());

            // 创建byte[]数组大小为20的缓存
            ByteBuffer byteBuffer = ByteBuffer.allocate(20);
            byteBuffer.put("你好，世界\n".getBytes(StandardCharsets.UTF_8));
            // limit置为position当前位置,position置为0,从头开始读取缓存数据，写入channel
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                channel.write(byteBuffer);
            }

            // channel的position置为0，从头读取文件内容
            channel.position(0);
            CharBuffer charBuffer = CharBuffer.allocate(10);
            CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();

            // byteBuffer清空， 作为读取文件的缓存
            byteBuffer.clear();
            while (channel.read(byteBuffer) != -1 || byteBuffer.position() > 0) {
                byteBuffer.flip();

                charBuffer.clear();
                decoder.decode(byteBuffer, charBuffer, false);
                System.out.println(charBuffer.flip().toString());

                // 将剩余没有转换的字节移到缓存数据头, 因为中文字符的UTF-8编码占据3个字节， 如果剩余最后的字节无法进行转换
                // 将不会读取
                byteBuffer.compact();
            }
        }
    }
}
