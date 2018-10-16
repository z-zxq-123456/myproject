package com.dcits.ensemble.om.cmc.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/5/21
 */
public class MyNetUtils {

    private static MyNetUtils instance;

    public static MyNetUtils getInstance(){

        if (instance==null){
            return instance = new  MyNetUtils();
        }
        return instance;
    }

    /**
     * 获取机器码
     * @return
     */
    public Long getMacId() {
        String macId = "";
        InetAddress ip = null;
        NetworkInterface ni = null;
        try {
            boolean bFindIP = false;
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                if (bFindIP) {
                    break;
                }
                ni = netInterfaces.nextElement();
                // 遍历所有ip
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    ip = ips.nextElement();
                    if (!ip.isLoopbackAddress() // 非127.0.0.1
                            && ip.getHostAddress().matches(
                            "(\\d{1,3}\\.){3}\\d{1,3}")) {
                        bFindIP = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("获得服务器的MAC地址出错:"+ e.getMessage());
        }
        if (null != ip) {
            try {
                macId = getMacFromBytes(ni.getHardwareAddress());
            } catch (SocketException e) {
                throw new RuntimeException("获得服务器的MAC地址出错:{}"+e.getMessage());
            }
        }
        return Long.parseLong(macId,16) ;
    }

    private static String getMacFromBytes(byte[] bytes) {
        StringBuffer mac = new StringBuffer();
        byte currentByte;
        for (byte b : bytes) {
            currentByte = (byte) ((b & 240) >> 4);
            mac.append(Integer.toHexString(currentByte));
            currentByte = (byte) (b & 15);
            mac.append(Integer.toHexString(currentByte));
        }
        return mac.toString().toUpperCase();
    }
}
