package com.zxq.learn.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @description
 * @author: zhouxqh
 * @create: 2020-04-17 11:58
 **/
public class NIOClient {

    public void run(String host,int port){

        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.socket().setSoTimeout(5000);
            SocketAddress remote = new InetSocketAddress(host,port);
            socketChannel.configureBlocking(false);
            socketChannel.connect(remote);
            Selector selector = Selector.open();
            socketChannel.register(selector,
                    SelectionKey.OP_CONNECT|SelectionKey.OP_READ|SelectionKey.OP_WRITE);

           /* Socket socket = socketChannel.socket();
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            BufferedReader bw = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw.write("hello server ");
            pw.flush();
            String msg;
            while ((msg = bw.readLine()) != null){
                System.out.println(msg);
            }*/

           while (selector.select()>0){
               Set keys = selector.selectedKeys();
               Iterator iterator = keys.iterator();
               while (iterator.hasNext()){
                   SelectionKey key = (SelectionKey)iterator.next();
                   iterator.remove();
                   if (key.isConnectable()){
                       SocketChannel channel =(SocketChannel) key.channel();
                       channel.finishConnect();
                       InetSocketAddress address = (InetSocketAddress)channel.socket().getRemoteSocketAddress();
                       System.out.println(String.format("访问地址：%s:%s 连接成功！",address.getHostName(),address.getPort()));
                   }else if (key.isWritable()){
                       SocketChannel channel =(SocketChannel) key.channel();
                       InetSocketAddress address = (InetSocketAddress)channel.socket().getRemoteSocketAddress();
                       System.out.println(address.getHostName());
                       channel.write(ByteBuffer.wrap(address.getHostName().getBytes()));
                       key.interestOps(SelectionKey.OP_READ);
                   }else if (key.isReadable()){
                       SocketChannel channel =(SocketChannel) key.channel();
                       ByteBuffer buffer = ByteBuffer.allocate(1024);
                       channel.read(buffer);
                       buffer.flip();
                       System.out.println(buffer.toString());
                   }
               }
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new NIOClient().run("localhost",8080);
    }
}
