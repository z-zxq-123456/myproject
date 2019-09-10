package com.zxq.learn.thread.ReentrantReadAndWrite;

/**
 * Created{ by zhouxqh} on 2017/10/10.
 */
public class Test {
   public static void main(String[]args){
       PriceInfo pricesInfo = new PriceInfo();
       ReaderPrice[]  readerPrices= new ReaderPrice[5];
       Thread threadsReader[] = new Thread[5];
       WriterPrice[]  writerPrices= new WriterPrice[5];
       Thread threadWriter[] = new Thread[5];
       for (int i = 0; i < 5; i++){
           readerPrices[i] = new ReaderPrice(pricesInfo);
           threadsReader[i] = new Thread(readerPrices[i]);

           writerPrices[i] = new WriterPrice(pricesInfo);
           threadWriter[i] = new Thread(writerPrices[i]);

       }
       for (int i = 0; i < 5; i++){
//           threadsReader[i].setPriority(10);
           threadsReader[i].start();
//           threadWriter[i].setPriority(1);
           threadWriter[i].start();
       }
   }
}
