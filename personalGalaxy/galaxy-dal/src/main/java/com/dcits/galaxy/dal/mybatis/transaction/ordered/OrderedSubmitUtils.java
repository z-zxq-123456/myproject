package com.dcits.galaxy.dal.mybatis.transaction.ordered;

import java.sql.Connection;
import java.util.LinkedHashSet;

import javax.sql.DataSource;

import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.dcits.galaxy.dal.mybatis.transaction.TransactionExtensionConfig;

public class OrderedSubmitUtils {
	
	public static final String ORDERED_SUBMIT = "ORDERED_SUBMIT";
	
	public static Connection getOrderedProxy(DataSource dataSource, Connection conn) {
		OrderedConnectionHolder holder = (OrderedConnectionHolder) TransactionSynchronizationManager.getResource(ORDERED_SUBMIT);
		
		if(holder == null){
			TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter(){
				private Object holder = null;
				@Override
				public void suspend() {
					this.holder = TransactionSynchronizationManager.unbindResourceIfPossible(ORDERED_SUBMIT);
				}

				@Override
				public void resume() {
					TransactionSynchronizationManager.bindResource(ORDERED_SUBMIT, holder);
				}
				
				@Override
				public void afterCompletion(int status) {
					TransactionSynchronizationManager.unbindResourceIfPossible(ORDERED_SUBMIT);
				}
			});
			holder = new OrderedConnectionHolder();
			TransactionSynchronizationManager.bindResource(ORDERED_SUBMIT, holder);
		}
		
		return holder.getOrderedConnection(dataSource, conn);
	}
	
	public static LinkedHashSet<DataSource> getOrderInfo(){
		OrderedConnectionHolder holder = (OrderedConnectionHolder) TransactionSynchronizationManager.getResource(ORDERED_SUBMIT);
		if(holder !=null){
			return holder.getOrder();
		}
		return null;
	}
}
