package com.dcits.galaxy.dtp.resume.trunk;

import com.dcits.galaxy.dtp.zk.DLock;
import com.dcits.galaxy.dtp.zk.DLockListener;

public class TrunkLockListener implements DLockListener {
	
	private TrunkResumer trunkResume = null;
	
	public TrunkLockListener (TrunkResumer trunkResume){
		this.trunkResume = trunkResume;
	}
	
	@Override
	public void onEvent(DLock lock) {
		boolean hasLock = lock.hasLock();
		if( hasLock ){
			trunkResume.start();
		}else{
			trunkResume.shutdown();
		}
	}
}
