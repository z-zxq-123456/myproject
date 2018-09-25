package com.dcits.galaxy.base.file;

/**
 * Created by Tim on 2016/1/19.
 */
public class FilePositions {

    public FilePositions() {
    }

    public FilePositions(long pos, int num) {
        this.pos = pos;
        this.num = num;
    }

    private long pos;

    private int num;

    public long getPos() {
        return pos;
    }

    public void setPos(long pos) {
        this.pos = pos;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
