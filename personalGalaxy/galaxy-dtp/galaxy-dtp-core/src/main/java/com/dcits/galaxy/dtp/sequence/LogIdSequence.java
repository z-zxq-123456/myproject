package com.dcits.galaxy.dtp.sequence;

public interface LogIdSequence {
	String nextLogid();
	int nextLogIndex(String bxid);
}
