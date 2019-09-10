package com.zxq.learn.thread.ReentrantReadAndWrite;


/**
 * Created{ by zhouxqh} on 2017/10/10.
 */
public class ReaderPrice implements Runnable {
    private PriceInfo priceInfo;

    public ReaderPrice(PriceInfo priceInfo) {
        this.priceInfo = priceInfo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s: Price 1: %f\n", Thread.currentThread()
                    .getName(), priceInfo.getPrice1());
            System.out.printf("%s: Price 2: %f\n", Thread.currentThread()
                    .getName(), priceInfo.getPrice2());
        }
    }
}
