package com.dcits.galaxy.sequences;

import com.alibaba.dubbo.rpc.RpcContext;
import com.dcits.galaxy.base.exception.NoProviderException;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.client.ServiceProxy;
import com.dcits.galaxy.core.client.builder.Attributes;
import com.dcits.galaxy.core.client.builder.AttributesBuilderSupport;
import com.dcits.galaxy.core.client.builder.ServiceAttributesBuilder;
import com.dcits.galaxy.core.threadpool.Executors;
import com.dcits.galaxy.sequences.model.Sequences;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tim on 2016/4/9.
 */
public class SequencesCommand {

    final static String encoding = "UTF-8";

    public static void main(String[] args) throws IOException {
        Console cons = System.console();
        if (null == cons) {
            System.out.println("Unable to get console!");
            return;
        }
        while (true) {
            String line = cons.readLine("%s>", "sequences");
            String[] strs = line.split("[\\t|\\s]+");
            switch (strs[0]) {
                case "help":
                    System.out.println("create - Add sequence");
                    System.out.println("exit\\quit\\q - Exit console");
                    System.out.println("nextval - Get the next sequence. SYNOPSIS - nextval [seqName] [forced creation. y/n] [frequency]");
                    System.out.println("show - View all sequences. SYNOPSIS - show [seqName]");
                    System.out.println("service - View the service provider's IP and PORT");
                    System.out.println("getSeqs - Obtain sequences by concurrent threads - getSeqs [threads] [iteration] [seqName] [size]");
                    break;
                case "show":
                    if (strs.length > 1)
                        show(strs[1]);
                    else
                        show(null);
                    break;
                case "quit":
                case "exit":
                case "q":
                    System.exit(0);
                    break;
                case "service":
                    service();
                    break;
                case "create":
                    create();
                    break;
                case "getSeqs":
                    getSeqs(strs[1], strs[2], strs[3], strs[4]);
                    break;
                case "nextval":
                    if (strs.length == 1) {
                        System.out.println("Sequence name must be entered!");
                        break;
                    } else if (strs.length == 2)
                        nextval(strs[1], null, null);
                    else if (strs.length == 3)
                        nextval(strs[1], strs[2], null);
                    else if (strs.length == 4)
                        nextval(strs[1], strs[2], strs[3]);
                    break;
            }
        }
    }

    private static void nextval(String seqName, String flag, String frequency) {
        if (null != flag) {
            if (!"y".equalsIgnoreCase(flag)) flag = null;
        }
        int freq = null == frequency ? 1 : Integer.valueOf(frequency);
        if (null == flag) {
            ShardSequences shardSequences = getService();
            if (null == shardSequences) return;

            Collection<Sequences> sequences = shardSequences.getSequences();
            Iterator it = sequences.iterator();
            Sequences seq;
            boolean findSeq = false;
            for (; it.hasNext(); ) {
                seq = (Sequences) it.next();
                if (seq.getSeqName().equals(seqName)) {
                    findSeq = true;
                    break;
                }
            }
            if (findSeq) {
                long start = System.currentTimeMillis();
                for (int i = 0; i < freq; i++) {
                    long seqNo = shardSequences.getSeqNo(seqName);
                    System.out.println("nextVal - " + seqNo);
                }
                System.out.println("elapsed:" + (System.currentTimeMillis() - start));
            } else {
                System.out.println("No sequence exists!");
                return;
            }
        } else {
            ShardSequences shardSequences = getService();
            if (null == shardSequences) return;
            long start = System.currentTimeMillis();
            for (int i = 0; i < freq; i++) {
                long seqNo = shardSequences.getSeqNo(seqName);
                System.out.println("nextVal - " + seqNo);
            }
            System.out.println("elapsed:" + (System.currentTimeMillis() - start));
        }
    }

    private static void create() {
        Console cons = System.console();
        // 序列名称
        String seqName = cons.readLine("Please enter Sequence's Name:");
        if (StringUtils.isEmpty(seqName)) {
            System.out.println("Cannot be null, or empty!");
            return;
        }
        // 最大序列
        String tmp;
        String maxSequences = "9999999999";
        tmp = cons.readLine("Please enter Max Sequence, default 9999999999:");
        long max = 0;
        long min = 0;
        long incr = 0;
        long cache = 0;
        if (StringUtils.isNotEmpty(tmp)) {
            if (!isNumeric(tmp)) {
                System.out.println("Must enter a numeric type!");
                return;
            } else {
                try {
                    max = Long.parseLong(tmp);
                } catch (NumberFormatException e) {
                    System.out.println("Number not allowed!");
                    return;
                }
                maxSequences = tmp;
            }
        }
        // 最小序列
        String minSequences = "1";
        tmp = cons.readLine("Please enter Min Sequence, default 1:");
        if (StringUtils.isNotEmpty(tmp)) {
            if (!isNumeric(tmp)) {
                System.out.println("Must enter a numeric type!");
                return;
            } else {
                try {
                    min = Long.parseLong(tmp);
                } catch (NumberFormatException e) {
                    System.out.println("Number not allowed!");
                    return;
                }
                if (min >= max) {
                    System.out.println("The Min Sequence must be smaller than the max!");
                    return;
                }
                minSequences = tmp;
            }
        }

        // 序列增长步长
        String seqIncrementBy = "1";
        tmp = cons.readLine("Please enter Sequence Increment By, default 1:");
        if (StringUtils.isNotEmpty(tmp)) {
            if (!isNumeric(tmp)) {
                System.out.println("Must enter a numeric type!");
                return;
            } else {
                try {
                    incr = Long.parseLong(tmp);
                } catch (NumberFormatException e) {
                    System.out.println("Number not allowed!");
                    return;
                }
                if (incr >= max) {
                    System.out.println("Number not allowed!");
                    return;
                }
                seqIncrementBy = tmp;
            }
        }

        // 序列增长步长
        String seqCache = "200";
        tmp = cons.readLine("Please enter Number of cache sequences, Input range 200-2000. default 200:");
        if (StringUtils.isNotEmpty(tmp)) {
            if (!isNumeric(tmp)) {
                System.out.println("Must enter a numeric type!");
                return;
            } else {
                try {
                    cache = Long.parseLong(tmp);
                } catch (NumberFormatException e) {
                    System.out.println("Number not allowed!");
                    return;
                }
                if (cache < 200 || cache > 2000) {
                    System.out.println("Number not allowed!");
                    return;
                }
                seqCache = tmp;
            }
        }

        // 序列增长步长
        String seqCycle = "Y";
        tmp = cons.readLine("Whether the sequence of cycles, please enter Y or N. default Y:");
        if (StringUtils.isNotEmpty(tmp)) {
            if (!tmp.equalsIgnoreCase("Y") && !tmp.equalsIgnoreCase("N")) {
                System.out.println("Please enter Y or N!");
                return;
            } else {
                seqCycle = tmp.toLowerCase();
            }
        }
        System.out.println("Sequence Name:" + seqName);
        System.out.println("Max Sequence:" + maxSequences);
        System.out.println("Min Sequence:" + minSequences);
        System.out.println("Sequence Increment By:" + seqIncrementBy);
        System.out.println("Cycle Sequence:" + seqCycle);
        System.out.println("Cache Sequence:" + seqCache);
        tmp = cons.readLine("Please confirm whether or not to continue, please enter YES or NO. default YES:");
        if (StringUtils.isNotEmpty(tmp)) {
            if (!tmp.equalsIgnoreCase("YES") && !tmp.equalsIgnoreCase("NO")) {
                System.out.println("Please enter YES or NO!");
                return;
            } else {
                tmp = tmp.toLowerCase();
            }
        } else {
            tmp = "YES";
        }

        if (tmp.equals("YES")) {
            // 创建序列
            Sequences seq = new Sequences();
            seq.setSeqName(seqName);
            seq.setSeqMaxValue(Long.valueOf(maxSequences));
            seq.setSeqMinValue(Long.valueOf(minSequences));
            seq.setSeqIncrementBy(Long.valueOf(seqIncrementBy));
            seq.setSeqCurrentValue(seq.getSeqMinValue() - seq.getSeqIncrementBy());
            seq.setSeqCache(Long.valueOf(seqCache));
            seq.setSeqCycle(seqCycle);

            ShardSequences shardSequences = getService();
            if (null == shardSequences) return;
            shardSequences.createSequence(seq);
        }
    }

    private static void getSeqs(String... arg) {
        // arg[0] threads ; arg[1] iteration ; arg[2] seqName ; arg[3] size
        if (null == arg || arg.length != 4) {
            System.out.println("Please input parameters , arg[0] threads ; arg[1] iteration ; arg[2] seqName ; arg[3] size");
            return;
        }
        final ShardSequences shardSequences = getService();
        if (null == shardSequences) return;

        final int threads = Integer.parseInt(arg[0]);
        final int iteration = Integer.parseInt(arg[1]);
        final String seqName = arg[2];
        final long size = Long.parseLong(arg[3]);

        List<Future<Boolean>> futures = new ArrayList();
        ExecutorService executorService = Executors.newFixedThreadPool("seqTest:", threads);
        for (int i = 0; i < threads; i++) {
            final Future<Boolean> submit = executorService.submit(new Callable<Boolean>() {

                /**
                 * Computes a result, or throws an exception if unable to do so.
                 *
                 * @return computed result
                 * @exception Exception
                 *         if unable to compute a result
                 */
                public Boolean call() throws Exception {
                    List<Long> seqs;
                    for (int j = 0; j < iteration; j++) {
                        try {
                            seqs = shardSequences.getSeqNoList(seqName, size);
                            System.out.println("[" + Thread.currentThread().getName() + "][" + j + "],");
                            System.out.println(seqs);
                        } catch (Throwable t) {
                            System.out.println("[" + Thread.currentThread().getName() + "][" + j + "], exception : " + t.getMessage());
                        }
                    }
                    return true;
                }
            });
            futures.add(submit);
        }

        for (Future future : futures) {
            if (null != future) {
                try {
                    future.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        executorService.shutdown();
    }

    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }


    private static void show(String seqName) {
        ShardSequences shardSequences = getService();
        if (null == shardSequences) return;
        // 获取序列列表
        Collection<Sequences> sequences;
        if (null != seqName) {
            sequences = shardSequences.getSequences(seqName);
        } else
            sequences = shardSequences.getSequences();

        if (sequences.size() == 0) {
            System.out.println("No Data!");
            return;
        }

        int[] col = {7, 15, 8, 11, 11, 14, 8, 8, 10};
        Iterator<Sequences> it = sequences.iterator();
        for (; it.hasNext(); ) {
            Sequences seq = it.next();
            col[0] = seq.getSeqName().length() > col[0] ? seq.getSeqName().length() : col[0];
            col[1] = String.valueOf(seq.getSeqCurrentValue()).length() > col[1] ? String.valueOf(seq.getSeqCurrentValue()).length() : col[1];
            col[2] = String.valueOf(seq.getSeqCount()).length() > col[2] ? String.valueOf(seq.getSeqCount()).length() : col[2];
            col[3] = String.valueOf(seq.getSeqMaxValue()).length() > col[3] ? String.valueOf(seq.getSeqMaxValue()).length() : col[3];
            col[4] = String.valueOf(seq.getSeqMinValue()).length() > col[4] ? String.valueOf(seq.getSeqMinValue()).length() : col[4];
            col[5] = String.valueOf(seq.getSeqIncrementBy()).length() > col[5] ? String.valueOf(seq.getSeqIncrementBy()).length() : col[5];
            col[6] = String.valueOf(seq.getSeqCycle()).length() > col[6] ? seq.getSeqCycle().length() : col[6];
            col[7] = String.valueOf(seq.getSeqCache()).length() > col[7] ? String.valueOf(seq.getSeqCache()).length() : col[7];
            col[8] = String.valueOf(seq.getCacheCount()).length() > col[8] ? String.valueOf(seq.getCacheCount()).length() : col[8];
        }

        System.out.println("+-" + StringUtils.rfillStr("-", col[0], "-") + "-+-"
                + StringUtils.rfillStr("-", col[1], "-") + "-+-"
                + StringUtils.rfillStr("-", col[2], "-") + "-+-"
                + StringUtils.rfillStr("-", col[3], "-") + "-+-"
                + StringUtils.rfillStr("-", col[4], "-") + "-+-"
                + StringUtils.rfillStr("-", col[5], "-") + "-+-"
                + StringUtils.rfillStr("-", col[6], "-") + "-+-"
                + StringUtils.rfillStr("-", col[7], "-") + "-+-"
                + StringUtils.rfillStr("-", col[8], "-") + "-+");
        System.out.println("| " + StringUtils.rfillStr("seqName", col[0], " ") + " | "
                + StringUtils.rfillStr("seqCurrentValue", col[1], " ") + " | "
                + StringUtils.rfillStr("seqCount", col[2], " ") + " | "
                + StringUtils.rfillStr("seqMaxValue", col[3], " ") + " | "
                + StringUtils.rfillStr("seqMinValue", col[4], " ") + " | "
                + StringUtils.rfillStr("seqIncrementBy", col[5], " ") + " | "
                + StringUtils.rfillStr("seqCycle", col[6], " ") + " | "
                + StringUtils.rfillStr("seqCache", col[7], " ") + " | "
                + StringUtils.rfillStr("cacheCount", col[8], " ") + " | ");

        it = sequences.iterator();
        for (; it.hasNext(); ) {
            System.out.println("+-" + StringUtils.rfillStr("-", col[0], "-") + "-+-"
                    + StringUtils.rfillStr("-", col[1], "-") + "-+-"
                    + StringUtils.rfillStr("-", col[2], "-") + "-+-"
                    + StringUtils.rfillStr("-", col[3], "-") + "-+-"
                    + StringUtils.rfillStr("-", col[4], "-") + "-+-"
                    + StringUtils.rfillStr("-", col[5], "-") + "-+-"
                    + StringUtils.rfillStr("-", col[6], "-") + "-+-"
                    + StringUtils.rfillStr("-", col[7], "-") + "-+-"
                    + StringUtils.rfillStr("-", col[8], "-") + "-+");
            Sequences seq = it.next();
            System.out.println("| " + StringUtils.rfillStr(seq.getSeqName(), col[0], " ") + " | "
                    + StringUtils.lfillStr(String.valueOf(seq.getSeqCurrentValue()), col[1], " ") + " | "
                    + StringUtils.lfillStr(String.valueOf(seq.getSeqCount()), col[2], " ") + " | "
                    + StringUtils.lfillStr(String.valueOf(seq.getSeqMaxValue()), col[3], " ") + " | "
                    + StringUtils.lfillStr(String.valueOf(seq.getSeqMinValue()), col[4], " ") + " | "
                    + StringUtils.lfillStr(String.valueOf(seq.getSeqIncrementBy()), col[5], " ") + " | "
                    + StringUtils.rfillStr(seq.getSeqCycle(), col[6], " ") + " | "
                    + StringUtils.lfillStr(String.valueOf(seq.getSeqCache()), col[7], " ") + " | "
                    + StringUtils.lfillStr(String.valueOf(seq.getCacheCount()), col[8], " ") + " | ");
        }
        System.out.println("+-" + StringUtils.rfillStr("-", col[0], "-") + "-+-"
                + StringUtils.rfillStr("-", col[1], "-") + "-+-"
                + StringUtils.rfillStr("-", col[2], "-") + "-+-"
                + StringUtils.rfillStr("-", col[3], "-") + "-+-"
                + StringUtils.rfillStr("-", col[4], "-") + "-+-"
                + StringUtils.rfillStr("-", col[5], "-") + "-+-"
                + StringUtils.rfillStr("-", col[6], "-") + "-+-"
                + StringUtils.rfillStr("-", col[7], "-") + "-+-"
                + StringUtils.rfillStr("-", col[8], "-") + "-+");
    }

    private static void service() {
        ShardSequences shardSequences = getService();
        if (null == shardSequences) return;
        shardSequences.probe(); // 探针服务
        System.out.println(String.format("RPC ShardSequences IP[%s] Port[%s]",
                RpcContext.getContext().getRemoteHost(), RpcContext.getContext().getRemotePort()));
    }

    private static ShardSequences getService() {
        try {
            ServiceProxy serviceProxy = ServiceProxy.getInstance();
            Attributes attributes = new ServiceAttributesBuilder()
                    .setLoadbalance(SequencesHandler.getProperties("galaxy.business.service.loadBalance",
                            AttributesBuilderSupport.LOADBALANCE_ROUNDROBIN))
                    .setGroup(SequencesHandler.getProperties("galaxy.business.sequences.group", "shardSequences"))
                    .setCheck(true)
                    .build();
            ShardSequences shardSequences = serviceProxy.getService(ShardSequences.class, attributes);
            return shardSequences;
        } catch (NoProviderException e) {
            System.out.println("No ShardSequences service provider!");
        }
        return null;
    }
}
