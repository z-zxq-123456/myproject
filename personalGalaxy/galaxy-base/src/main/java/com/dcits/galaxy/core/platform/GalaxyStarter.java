package com.dcits.galaxy.core.platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Galaxy启动入口
 *
 * @author xuecy
 */
public class GalaxyStarter {

    public static final String SHUTDOWN_HOOK_KEY = "dubbo.shutdown.hook";

    private static final Logger logger = LoggerFactory
            .getLogger(GalaxyStarter.class);

    private static volatile boolean running = true;

    public static void main(String[] args) {
        System.setProperty("dubbo.spring.config",
                "classpath*:META-INF/spring/**/*.xml");
        try {
        	final SpringContainer container = new SpringContainer();
            logger.info("Use container type(" + Arrays.toString(args)
                    + ") to run galaxy serivce.");

            if ("true".equals(System.getProperty(SHUTDOWN_HOOK_KEY))) {
                Runtime.getRuntime().addShutdownHook(new Thread() {
                    public void run() {
                            try {
                                container.stop();
                                logger.info("Galaxy "
                                        + container.getClass().getSimpleName()
                                        + " stopped!");
                            } catch (Throwable t) {
                                logger.error(t.getMessage(), t);
                            }
                            synchronized (GalaxyStarter.class) {
                                running = false;
                                GalaxyStarter.class.notify();
                            }
                    }
                });
            }

            container.start();
            logger.info("Galaxy " + container.getClass().getSimpleName()
                    + " started!");
            System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]")
                    .format(new Date()) + " Galaxy service server started!");
        } catch (RuntimeException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            System.exit(1);
        }
        synchronized (GalaxyStarter.class) {
            while (running) {
                try {
                    GalaxyStarter.class.wait();
                } catch (Throwable e) {
                }
            }
        }
    }

}
