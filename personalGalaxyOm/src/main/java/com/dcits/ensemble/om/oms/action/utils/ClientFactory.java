package com.dcits.ensemble.om.oms.action.utils;


import com.dcits.galaxy.base.exception.GalaxyException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author wangbinaf
 * @date   2016-05-12
 * Constants
 * zookeeper 操作
 */
public class ClientFactory {
	public static ZooKeeper getInstance() {
		if (zk != null) {
			return zk;
		} else {
            zk = newClient();
            if (zk != null) {
            return zk;
            }
            return null;
		}
	}
    private static String connectionString;
    private static ZooKeeper zk;
    public static void setConnectionString(String conn)
    {
        if(conn.isEmpty()) {
            connectionString = "localhost:2181";
        }
        else {
            connectionString = conn;
        }
        zk = newClient();
    }
    
    private static ZooKeeper newClient() {
        return createSimple(connectionString);
    }
    
	public static ZooKeeper createSimple(final String connectionString){
        try {
            try {
                zk = new ZooKeeper(connectionString,
                        20000, new Watcher() {
                    @Override
                    public void process(WatchedEvent event) {
                        if (event.getState() == Event.KeeperState.Expired) {
                            System.out.println("session expired! zk="+zk);
                            if (zk == null || !zk.getState().isAlive()) {
                                System.out.println("reconnect!");
                                try {
                                    zk = new ZooKeeper(connectionString,
                                            2000, new Watcher() {
                                        @Override
                                        public void process(WatchedEvent event) {
                                            if (event.getState() == Event.KeeperState.Expired) {
                                                System.out.println("session expired!");
                                            }
                                            System.out.println("watch start " + event.getType() + " ...");
                                            System.out.println("Path=" + event.getPath());
                                        }
                                    });
                                } catch (Exception e) {
                                		throw new GalaxyException("连接ZK失败！"+ connectionString);
                                }
                            } else {
                                System.out.println("zk.State" + zk.getState());
                            }
                        }
                        System.out.println("watch start " + event.getType() + " ...");
                        System.out.println("Path=" + event.getPath());
                    }
                });
            } catch (Exception e) {
        		throw new GalaxyException("连接ZK失败！");
           }

        } catch (Exception e) {
            throw new GalaxyException("创建ZK对象失败！");
        }

        return zk;
	}
}
