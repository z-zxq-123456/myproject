package com.zxq.learn.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-04-17 11:27
 **/
public class NIOServer {

    public  void server(int port) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        serverSocket.bind(address);
        //打开selector处理channel
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//注册serverSocket到selector

        final ByteBuffer byteBuffer = ByteBuffer.wrap("HI !\r\n".getBytes());
        for (;;){
            selector.select();//等待需要处理的新事件，阻塞到传入事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
            while (selectionKeyIterator.hasNext()) {
                SelectionKey selectionKey = selectionKeyIterator.next();
                selectionKeyIterator.remove();
                if (selectionKey.isAcceptable()) {//检查是否就绪
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, byteBuffer.duplicate());
                    System.out.println("Accept client connection from " + socketChannel);
                }
                if (selectionKey.isWritable()) {//检查套接字是否准备好写入
                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                    while (buffer.hasRemaining()) {
                        if (client.write(buffer) == 0) {
                            break;
                        }
                    }
                    client.close();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            new NIOServer().server(8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
