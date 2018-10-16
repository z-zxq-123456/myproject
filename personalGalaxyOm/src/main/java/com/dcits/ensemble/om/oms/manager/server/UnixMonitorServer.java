package com.dcits.ensemble.om.oms.manager.server;

import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.server.EcmServerInfo;
import com.dcits.ensemble.om.oms.shellcmd.ICmd;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * unix服务器监控实现*
 *
 * @author luolang
 * @date 2016-01-28
 */
@Component
public class UnixMonitorServer extends AbstractedMonitorServer {

    private final static int IntegerDigits = 2; //小数点前保留位数
    private final static int FractionDigits = 2; //小数点前保留位数

    /**
     * 解析cpu信息
     *
     * @param List<String> resultList  shell中截取的cpu结果
     * @return String               cpu信息
     */
    @Override
    String parseCpuInfo(List<String> resultList) {
        float cpuUsage;
        float userCpuTime = 0, sysCpuTime = 0;
        String cpuMessage = null;
        for (String str : resultList) {
            if (str.startsWith("tty")) {
                cpuMessage = resultList.get(resultList.indexOf(str) + 1);
                cpuMessage = cpuMessage.trim();
                break;
            }
        }
        // modify for sonar扫描
        String cpuResult = "";
        if (null != cpuMessage) {
            String[] temp = cpuMessage.split("\\s+");
            userCpuTime = Float.parseFloat(temp[2]);
            sysCpuTime = Float.parseFloat(temp[3]);
            cpuUsage = (userCpuTime + sysCpuTime);
            cpuResult = cpuUsage + "%";
        }
        return cpuResult;
    }

    /**
     * 解析mem信息
     *
     * @param List<String> resultList  shell中截取的mem结果
     * @return String               mem信息
     */
    @Override
    String parseMemInfo(List<String> resultList) {
        long totalMem = 0, freeMem = 0;
        float memUsage;
        for (String str : resultList) {
            if (str.startsWith("memory")) {
                String[] memInfo = str.split("\\s+");
                totalMem = Long.parseLong(memInfo[1]);
                freeMem = Long.parseLong(memInfo[3]);
                break;
            }
        }
        memUsage = 1 - (float) freeMem / (float) totalMem;
        String memResult = getPercentFormat(memUsage, IntegerDigits, FractionDigits);
        return memResult;
    }

    /**
     * 解析net信息
     *
     * @param List<String> resultList  shell中截取的net结果
     * @return String               net信息
     */
    @Override
    String parsenNetInfo(List<String> resultList) {
        //float netUsage ;
        long inSize1 = 0, outSize1 = 0, inSize2 = 0, outSize2 = 0;
        float interval = 0;
        String netMessage;
        netMessage = resultList.get(resultList.indexOf(FIRST_NET_MARK) + 1);
        netMessage = netMessage.trim();
        String[] temp1 = netMessage.split("\\s+");
        inSize1 = Long.parseLong(temp1[1].substring(5));
        outSize1 = Long.parseLong(temp1[3]);
        netMessage = resultList.get(resultList.indexOf(SECOND_NET_MARK) + 1);
        netMessage = netMessage.trim();
        String[] temp2 = netMessage.split("\\s+");
        inSize2 = Long.parseLong(temp2[1].substring(5)); //Receive bytes,单位为Byte
        outSize2 = Long.parseLong(temp2[3]);
        for (int i = resultList.indexOf(SECOND_NET_MARK) + 1; i < resultList.size(); i++) {
            if (resultList.get(i).startsWith("real")) {
                String realTime = resultList.get(i);
                realTime = realTime.trim();
                String[] tempTime = realTime.split("\\s+");
                realTime = tempTime[1];
                realTime = realTime.replace("0m", "");
                realTime = realTime.replace("s", "");
                interval = Float.parseFloat(realTime);
            }
        }
        float curRate = (float) (inSize2 - inSize1 + outSize2 - outSize1) * 8 / (1000 * interval);
        DecimalFormat df = new DecimalFormat("0.00");
        String netResult = df.format(curRate) + "KB/S";
        return netResult;
    }

    /**
     * 解析IO信息
     *
     * @param List<String> resultList  shell中截取的IO结果
     * @return String               IO信息
     */
    @Override
    String parseIoInfo(List<String> resultList) {
        int start = 0;
        int end = 0;
        float ioUsage = 0.0f;
        for (String str : resultList) {
            if (str.startsWith("Disks")) {
                start = resultList.indexOf(str) + 1;
            }
            if (str.startsWith("memory")) {
                end = resultList.indexOf(str);
                break;
            }
        }
        for (int i = start; i < end; i++) {
            String str = resultList.get(i);
            String[] temp = str.split("\\s+");
            if (temp.length > 1) {
                float util = Float.parseFloat(temp[1]);
                ioUsage = (ioUsage > util) ? ioUsage : util;
            }
        }
        if (ioUsage > 0) {
            ioUsage /= 100;
        }
        String ioResult = getPercentFormat(ioUsage, IntegerDigits, FractionDigits);
        return ioResult;
    }

    /**
     * 产生适应本系统的shell命令
     *
     * @param EcmServerInfo serverInfo  服务器信息
     * @return String             shell命令
     */
    @Override
    String makeCmd(EcmServerInfo serverInfo) {
        ICmd unixCmd = cmdFactory.getCmd(serverInfo.getSerOs());
        String timeArgs = "(" + unixCmd.echoCmd(FIRST_NET_MARK) + SysConfigConstants.SHELL_LINK_SIGN +
                unixCmd.netCmd(null) + SysConfigConstants.SHELL_LINK_SIGN + unixCmd.sleepCmd(NET_SLEEPTIME) +
                SysConfigConstants.SHELL_LINK_SIGN + unixCmd.echoCmd(SECOND_NET_MARK) + SysConfigConstants.SHELL_LINK_SIGN +
                unixCmd.netCmd(null) + ")";
        String cmd = unixCmd.cpuCmd(null) + SysConfigConstants.SHELL_LINK_SIGN +
                unixCmd.memoryCmd(null) + SysConfigConstants.SHELL_LINK_SIGN +
                unixCmd.timeCmd(timeArgs);
        return cmd;
    }

    private String getPercentFormat(float d, int IntegerDigits, int FractionDigits) {
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumIntegerDigits(IntegerDigits);//小数点前保留几位
        nf.setMinimumFractionDigits(FractionDigits);// 小数点后保留几位
        String str = nf.format(d);
        return str;
    }

}
