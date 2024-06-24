package guava;


import com.google.common.cache.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * The <code>guava.localCache</code> class have to be described
 *
 * <p>
 * The <code>localCache</code> class have to be detailed For example:
 * <p>
 *
 * @author Administrator
 * @date 2020/11/13 17:03
 * @see
 * @since 1.0
 */
public class localCache {


    public static void main(String[] args) {

        Cache cache = CacheBuilder.newBuilder().build();
        cache.put("test","hello world");


        try {
            System.out.println(cache.get("test",()->cache.get("test",null)));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //不抛异常
        System.out.println(        cache.getIfPresent("test"));


        System.out.println("------------------");


        Cache cache1 = builderMum(2).build();
        cache1.put("test","hello");
        cache1.put("test2","world");
        cache1.put("test3","helloworld");
        System.out.println(cache1.getIfPresent("test"));
        System.out.println(cache1.getIfPresent("test2"));
        System.out.println(cache1.getIfPresent("test3"));//test3插入会导致test1删除

        System.out.println("------------------");


        Cache cache2 = builderExprire(2).build();
        cache2.put("test","hello");
        cache2.put("test2","world");
        System.out.println(cache2.getIfPresent("test"));
     /*   try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println(cache2.getIfPresent("test"));



        System.out.println("------------------");

        Cache cache3 = buildAfterAccess(2).build();
        cache3.put("test","hello");
        cache3.put("test2","world");
        System.out.println(cache3.getIfPresent("test"));
      /*  try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println(cache3.getIfPresent("test"));
        System.out.println(cache3.getIfPresent("test2"));


        System.out.println("------------------");

        Cache cache4 = buildWeak().build();
        Object val = new Object();
        cache4.put("test",val);
        cache4.put("test2","world");

        val = new Object();//原对象不再有强引用
        System.gc();
        System.out.println(cache4.getIfPresent("test"));


        System.out.println("------------------");

        invalid();

        System.out.println("------------------");

        Cache cache5 = buildListener().build();
        cache5.put("test1","hello");
        cache5.put("test2","word");
        cache5.invalidate("test1");

        System.out.println("------------------");

//        buildCallAble();

        System.out.println("------------------");

        Cache<String,String> cache6 = buildStat().build();
        cache6.put("test1","1");
        cache6.put("test2","2");

        System.out.println(cache6.getIfPresent("test1"));
        System.out.println(cache6.getIfPresent("test1"));
        System.out.println(cache6.getIfPresent("test1"));
        System.out.println(cache6.getIfPresent("test2"));
        System.out.println(cache6.getIfPresent(cache6.stats()));

        System.out.println("------------------");

        //LoadingCache类型的对象也是通过CacheBuilder进行构建，不同的是，在调用CacheBuilder的build方法时，必须传递一个CacheLoader类型的参数，
        // CacheLoader的load方法需要我们提供实现。当调用LoadingCache的get方法时，如果缓存不存在对应key的记录，
        // 则CacheLoader中的load方法会被自动调用从外存加载数据，load方法的返回值会作为key对应的value存储到LoadingCache中，并从get方法返回
        LoadingCache cache7 = builderMum(2).build(new CacheLoader() {
            @Override
            public Object load(Object key) throws Exception {
                System.out.println("load " + key);
                return "hello";
            }
        });

        try {
            System.out.println(cache7.get("test1"));
            System.out.println(cache7.get("test1"));
            System.out.println(cache7.get("test2"));
            System.out.println(cache7.get("test2"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    /**
     * 设置最多存储
     * @param sum
     * @return
     */
    private static CacheBuilder builderMum(int sum){
        return  CacheBuilder.newBuilder().maximumSize(sum);
    }

    /**
     * 设过期时间
     * @param sum
     * @return
     */
    private static CacheBuilder builderExprire(int expire){
        return  CacheBuilder.newBuilder().expireAfterWrite(expire, TimeUnit.SECONDS);
    }

    /**
     * 设访问过期时间
     * @param sum
     * @return
     */
    private static CacheBuilder buildAfterAccess(int expire){
        return  CacheBuilder.newBuilder().expireAfterAccess(expire, TimeUnit.SECONDS);
    }

    /**
     * 弱引用
     * 可以通过weakKeys和weakValues方法指定Cache只保存对缓存记录key和value的弱引用。
     * 这样当没有其他强引用指向key和value时，key和value对象就会被垃圾回收器回收。
     * @param sum
     * @return
     */
    private static CacheBuilder buildWeak(){
        return  CacheBuilder.newBuilder().weakValues();
    }

    /**
     * invalidateAll或invalidate方法显示删除Cache中的记录。invalidate方法一次只能删除Cache中一个记录，
     * 接收的参数是要删除记录的key。invalidateAll方法可以批量删除Cache中的记录，当没有传任何参数时，
     * invalidateAll方法将清除Cache中的全部记录。invalidateAll也可以接收一个Iterable类型的参数，参数中包含要删除记录的所有key值
     */
    private static void invalid(){

        Cache cache = builderMum(2).build();

        Object value = new Object();
        cache.put("key1","value1");
        cache.put("key2","value2");
        cache.put("key3","value3");

        List<String> list = new ArrayList<String>();
        list.add("key1");
        list.add("key2");
        cache.invalidate("key3");
        System.out.println(cache.getIfPresent("key2"));

        cache.invalidateAll(list);//批量清除list中全部key对应的记录
        System.out.println(cache.getIfPresent("key1"));
        System.out.println(cache.getIfPresent("key2"));
        System.out.println(cache.getIfPresent("key3"));
    }

    /**
     * 可以为Cache对象添加一个移除监听器，这样当有记录被删除时可以感知到这个事件。
     */
    private static CacheBuilder buildListener(){

        return  CacheBuilder.newBuilder().removalListener((RemovalListener<String, String>) notification -> System.out.println(notification.getKey()+" is removed"));
    }


    /**
     * Cache的get方法有两个参数，第一个参数是要从Cache中获取记录的key，第二个记录是一个Callable对象。当缓存中已经存在key对应的记录时，
     * get方法直接返回key对应的记录。如果缓存中不包含key对应的记录，
     * Guava会启动一个线程执行Callable对象中的call方法，call方法的返回值会作为key对应的值被存储到缓存中，并且被get方法返回
     */
    private static void buildCallAble(){
        Cache<String,String> cache = builderMum(2).build();


        new Thread(()->{
            String val = null;
            try {
                val = cache.get("test1",()->{
                    System.out.println("load 1");
                    Thread.sleep(1000L);
                    return "hello";
                });
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println("Thread1" + val);
        }).start();

        new Thread(()->{
            String val = null;
            try {
                val = cache.get("test1",()->{
                    System.out.println("load 2");
                    Thread.sleep(1000L);
                    return "hello";
                });
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println("Thread2" + val);
        }).start();
    }


    private static CacheBuilder buildStat(){

        return CacheBuilder.newBuilder().recordStats();
    }

}
