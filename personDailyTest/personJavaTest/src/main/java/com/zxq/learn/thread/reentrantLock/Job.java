package com.zxq.learn.thread.reentrantLock;

/**
 * Created{ by zhouxqh} on 2017/10/10.
 */
public class Job implements Runnable {
    private PrintQuene printQuene;

    public Job(PrintQuene printQuene) {
        this.printQuene = printQuene;
    }

    @Override
    public void run() {
        System.out.printf("%s: Going to print a document\n", Thread
                .currentThread().getName());
        printQuene.printJob(new Object());
        System.out.printf("%s: The document has been printed\n", Thread
                .currentThread().getName());
    }
}
