package guava;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * The <code>guava.FutureTest</code> class have to be described
 *
 * <p>
 * The <code>FutureTest</code> class have to be detailed For example:
 * <p>
 *
 * @author Administrator
 * @date 2020/11/19 17:40
 * @see
 * @since 1.0
 */
public class FutureTest {

    public static void main(String[] args) {


        List<String> stringList = Lists.newArrayList("a","c","d","e","f","b");
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(3));
        List<Callable<String>> callables = new ArrayList<>();
        for (String s:stringList){
            callables.add(() -> s+"....");
        }
        try {
            String returnVal = executorService.invokeAny(callables);
//            List<Future<String>> futureList = executorService.invokeAll(callables);
          /*  for (Future future:futureList){
                System.out.println(future.get());
            }*/

            System.out.println(returnVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
