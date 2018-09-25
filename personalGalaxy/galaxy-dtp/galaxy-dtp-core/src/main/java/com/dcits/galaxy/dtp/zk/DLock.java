package com.dcits.galaxy.dtp.zk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  事务恢复锁
 * @author Yin.Weicai
 *
 */
public class DLock {
	
	private static Logger logger = LoggerFactory.getLogger( DLock.class );
	
	/**
	 * 是否持有锁
	 */
	private volatile boolean locked = false;
	
	/**
	 * 锁节点的路径
	 */
	private String path = null;
	
	/**
	 * 锁节点的值 ( 目前使用UUID作为锁节点的值  )
	 */
	private String data = null;
	
	/**
	 * 监听器集合
	 */
	private List<DLockListener> listeners = 
			Collections.synchronizedList(new ArrayList<DLockListener>());
	
	public DLock( String path ){
		 this.path = path;
		 data = UUID.randomUUID().toString();
		 init();
	}
	
	private void init(){
		ZkClient zkClient = ZKSingleBuilder.getZkClient();
		String parentPath = path.substring(0, path.lastIndexOf("/"));
		zkClient.subscribeChildChanges(parentPath, new IZkChildListener() {
			@Override
			public void handleChildChange(String parentPath, List<String> currentChilds)
					throws Exception {
				if( !DLock.this.hasLock() ){	
					if( currentChilds == null || currentChilds.size() == 0){
						if( !DLock.this.tryLock() ){
							logger.info("tryLock failed! path:[" + path +"]");
						}
					}
				}
			}
		});
		
		zkClient.subscribeStateChanges(new IZkStateListener() {
			@Override
			public void handleStateChanged(KeeperState state) throws Exception {
				logger.debug(state.name());
				if( KeeperState.Disconnected == state){
					if( DLock.this.hasLock() ){
						DLock.this.unLock();
					}
				}else if( KeeperState.SyncConnected == state ){
					if ( !DLock.this.tryLock() ){
						logger.info("tryLock failed! path:[" + path +"]");
					}
				}else{
					//TODO
				}
			}
			
			@Override
			public void handleNewSession() throws Exception {
				//TODO
			}
		});
	}
	
	public boolean tryLock(){
		boolean result = false;
		ZkClient zkClient = ZKSingleBuilder.getZkClient();
		
		boolean isExists = zkClient.exists( path );
		if( isExists){
			Object obj = zkClient.readData( path, true);
			if( obj != null){
				String nodeData = (String)obj;
				if( data.equals( nodeData ) ){
					locked = result = true;
				}else{
					logger.warn("tryLock failed! path:[" + path +"]");
				}
			}
		}else{
			try {
				zkClient.createEphemeral( path, data);
				locked = result = true;
			} catch (Exception e) {
//				logger.warn("tryLock failed! path:[" + path +"]", e.getCause());
				logger.warn("tryLock failed! path:[" + path +"]", e.getCause());
			}
		}
		if( result ){
			fireEvent();
		}
		return result;
	}
	
	/**
	 * 是否持有锁
	 * @return
	 */
	public boolean hasLock(){
		return locked;
	}
	
	/**
	 * 释放锁，尝试到ZooKeeper Server上删除锁节点（）。
	 */
	public void unLock(){
		locked = false;
		fireEvent();
		try {
			ZkClient zkClient = ZKSingleBuilder.getZkClient();
			boolean isExists = zkClient.exists( path );
			if( isExists){
				Object obj = zkClient.readData( path, true);
				if( obj != null){
					String nodeData = (String)obj;
					if( data.equals( nodeData ) ){
						zkClient.delete(path);
					}
				}
			}
		} catch (Exception e) {
			logger.warn("unLock failed! path:[" + path +"]");
		}
	}
	
	public void addListener(DLockListener listener){
		this.listeners.add(listener);
	}
	
	public boolean removeListener( DLockListener listener){
		return this.listeners.remove(listener);
	}
	
	/**
	 * 触发事件
	 */
	private void fireEvent(){
		Iterator<DLockListener> it = this.listeners.iterator();
		while( it.hasNext() ){
			it.next().onEvent(this);
		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getData() {
		return data;
	}
	
}
