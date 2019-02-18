package com.zxq.learn.thread.ReentrantReadAndWrite;


/**
 * Created{ by zhouxqh} on 2017/10/10.
 */
public class WriterPrice implements Runnable {
    private PriceInfo priceInfo;

    public WriterPrice(PriceInfo priceInfo) {
        this.priceInfo = priceInfo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.printf("Writer: Attempt to modify the prices.\n");
            priceInfo.setPrices(Math.random()*10, Math.random()*5);
            System.out.printf("Writer: Prices have been modified.\n");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
