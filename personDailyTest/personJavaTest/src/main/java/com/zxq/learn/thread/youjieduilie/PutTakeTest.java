package com.zxq.learn.thread.youjieduilie;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/3/26
 */
public class PutTakeTest {

    private static final ExecutorService pool = Executors.newCachedThreadPool();
    public final AtomicInteger putSum = new AtomicInteger(0);
    public final AtomicInteger takeSum = new AtomicInteger(0);
    public  CyclicBarrier barrier;
    public BounderBuffer<Integer> buffer;
    public  int nTrialx,nPairs;

    public PutTakeTest(int capacity, int nTrialx, int nPairs) {
        this.barrier = new CyclicBarrier(nPairs * 2 +1, new Runnable() {
            @Override
            public void run() {

                System.out.println("all process is over! ");
            }
        });
        this.buffer = new BounderBuffer<>(capacity);
        this.nTrialx = nTrialx;
        this.nPairs = nPairs;
    }

    public static int xorShift(int y){
        y ^= (y << 6);
        y ^= (y >>> 21);
        y ^= (y << 7);
        return y;
    }

    public static void main(String[]args){

        new PutTakeTest(10,100000,10).test();
        pool.shutdown();
    }

    private void test(){
        try {
            for (int i = 0; i < this.nPairs; i++){
                pool.execute(new Provider());
                pool.execute(new Consumer());
            }
           this.barrier.await();
            this.barrier.await();
            System.out.println("===============");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    class Provider implements Runnable {

        @Override
        public void run() {

            System.out.println("Thread "+ Thread.currentThread().getName()+" is ready for writing...");
            try {
                int seed = this.hashCode() ^ (int) System.nanoTime();
                int sum = 0;
                barrier.await();
                for (int i = nTrialx; i > 0; --i) {
                    buffer.put(seed);
                    sum += seed;
                    seed = xorShift(seed);
                }
                putSum.getAndAdd(sum);
                System.out.println("writing Thread "+ Thread.currentThread().getName()+" dataAfter "+ seed);
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+" all writing thread is over, begin to process othes...");
        }
    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            System.out.println("Thread "+Thread.currentThread().getName()+" is ready for reading...");
            try {
                barrier.await();
                int sum = 0;
                for (int i = nTrialx; i > 0; --i) {
                    sum += buffer.take();
                }
                takeSum.getAndAdd(sum);
                System.out.println("reading Thread "+ Thread.currentThread().getName()+" data "+ sum);
                barrier.await();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+" all reading thread is over, begin to process othes...");
        }
    }
}
