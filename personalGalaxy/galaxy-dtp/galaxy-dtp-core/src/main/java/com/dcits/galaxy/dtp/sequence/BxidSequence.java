package com.dcits.galaxy.dtp.sequence;

public interface BxidSequence {
	String nextBxid();
	int nextIndexInBranch(String bxid);
	int nextIndexInTrunk(String txid);
}
