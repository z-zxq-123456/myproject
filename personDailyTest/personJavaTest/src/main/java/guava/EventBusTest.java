package guava;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * The <code>guava.EventBusTest</code> class have to be described
 *
 * <p>
 * The <code>EventBusTest</code> class have to be detailed For example:
 * <p>
 *
 * @author Administrator
 * @date 2020/11/19 17:56
 * @see
 * @since 1.0
 */
public class EventBusTest {

    static class OneEvent{};
    static class TwoEvent{};

    static class EventLog{
        @Subscribe
        public void one(OneEvent oneEvent){
            System.out.println(Thread.currentThread().getName());
            System.out.println(oneEvent.getClass().getCanonicalName()+" work");
        }
    }

    static class EventLog2{
        @Subscribe
        public void one(OneEvent oneEvent){
            System.out.println(Thread.currentThread().getName());
            System.out.println(oneEvent.getClass().getCanonicalName()+" work");
        }

        @Subscribe
        public void two(TwoEvent twoEvent){
            System.out.println(Thread.currentThread().getName());
            System.out.println(twoEvent.getClass().getCanonicalName()+" work");
        }
    }

    public static void main(String[] args) {

        EventBus eventBus = new EventBus();

        eventBus.register(new EventLog());
        eventBus.register(new EventLog2());

        OneEvent oneEvent = new OneEvent();
        TwoEvent twoEvent = new TwoEvent();

        System.out.println(Thread.currentThread().getName());
        eventBus.post(oneEvent);
        eventBus.post(twoEvent);
    }
}
