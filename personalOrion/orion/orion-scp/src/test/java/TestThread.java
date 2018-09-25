import com.dcits.galaxy.core.threadpool.support.NamedThreadFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by lixiaobin on 2017/3/17.
 */
public class TestThread {

    public static void main(String [] args) throws InterruptedException {
        int threadPoolSize = Runtime.getRuntime().availableProcessors() * 5;
        ThreadFactory namedThreadFactory = new NamedThreadFactory("OrionScpWorker");
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
        ExecutorService executorService = new ThreadPoolExecutor(threadPoolSize,threadPoolSize,60, TimeUnit.SECONDS,queue,namedThreadFactory);
        final Map map = new HashMap();
        for (int i = 0 ; i < 10; i++) {

            Callable call = new Callable() {
                @Override
                public Object call() throws Exception {
                    test(map);
                    return null;
                }
            };
            executorService.submit(call);
            System.out.println("OK");
        }

        Thread.sleep(100000);

    }


    public static void test(Map map) throws InterruptedException {
        synchronized (map)
        {
            map.put(Thread.currentThread().getName(),"");
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName());
        }
    }
}
