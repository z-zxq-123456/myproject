package com.dcits.galaxy.base.data;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.base.validate.V;

/**
 * Created by Tim on 2016/6/3.
 */
public class AppHead extends AbstractBean implements IAppHead {

    /**
     * @fields serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 本页记录总数<br>
     * TOTAL_NUM<br>
     * seqNo:2<br>
     * dataType:STRING(12)<br>
     * cons:数字
     */
    @V(desc = "本页记录总数")
    private String totalNum = "-1";

    /**
     * 上翻/下翻标志<br>
     * PGUP_OR_PGDN<br>
     * seqNo:1<br>
     * dataType:STRING(1)<br>
     * cons:取值范围：<br>0-上翻；<br>1-下翻；<br>
     */
    @V(desc = "上翻/下翻标志", in = "0,1")
    private String pgupOrPgdn = "1";

    /**
     * 总笔数<br>
     * TOTAL_ROWS<br>
     * seqNo:6<br>
     * dataType:STRING(12)<br>
     * cons:数字
     */
    @V(desc = "总笔数")
    private String totalRows;

    /**
     * 汇总标志<br>
     * TOTAL_FLAG<br>
     * seqNo:8<br>
     * dataType:STRING(1)<br>
     * cons:取值范围：<br>N-不查询总笔数；<br>E-查询总记录数；
     */
    @V(desc = "汇总标志", in = "N,E")
    private String totalFlag = "E";

    /**
     * 本页第一笔标志<br>
     * PAGE_START<br>
     * seqNo:4<br>
     * dataType:STRING(12)<br>
     * cons:数字
     */
    @V(desc = "本页第一笔标志")
    @Deprecated
    private String pageStart;

    /**
     * 本页最后一笔标志<br>
     * PAGE_END<br>
     * seqNo:5<br>
     * dataType:STRING(12)<br>
     * cons:数字
     */
    @V(desc = "本页最后一笔标志")
    @Deprecated
    private String pageEnd;

    /**
     * 总页数<br>
     * TOTAL_PAGES<br>
     * seqNo:7<br>
     * dataType:STRING(12)<br>
     * cons:数字
     */
    @V(desc = "总页数")
    @Deprecated
    private String totalPages;

    /**
     * 当前记录号<br>
     * CURRENT_NUM<br>
     * seqNo:3<br>
     * dataType:STRING(12)<br>
     * cons:数字
     */
    @V(desc = "当前记录号")
    private String currentNum = "0";


    /**
     * 本页记录总数<br>
     * TOTAL_NUM
     */
    public String getTotalNum() {
        return totalNum;
    }

    /**
     * 本页记录总数<br>
     * TOTAL_NUM
     */
    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * 上翻/下翻标志<br>
     * PGUP_OR_PGDN
     */
    public String getPgupOrPgdn() {
        return pgupOrPgdn;
    }

    /**
     * 上翻/下翻标志<br>
     * PGUP_OR_PGDN
     */
    public void setPgupOrPgdn(String pgupOrPgdn) {
        this.pgupOrPgdn = pgupOrPgdn;
    }

    /**
     * 总笔数<br>
     * TOTAL_ROWS
     */
    public String getTotalRows() {
        return totalRows;
    }

    /**
     * 总笔数<br>
     * TOTAL_ROWS
     */
    public void setTotalRows(String totalRows) {
        this.totalRows = totalRows;
    }

    /**
     * 汇总标志<br>
     * TOTAL_FLAG
     */
    public String getTotalFlag() {
        return totalFlag;
    }

    /**
     * 汇总标志<br>
     * TOTAL_FLAG
     */
    public void setTotalFlag(String totalFlag) {
        this.totalFlag = totalFlag;
    }

    /**
     * 本页第一笔标志<br>
     * PAGE_START
     */
    @Deprecated
    public String getPageStart() {
        return pageStart;
    }

    /**
     * 本页第一笔标志<br>
     * PAGE_START
     */
    @Deprecated
    public void setPageStart(String pageStart) {
        this.pageStart = pageStart;
    }

    /**
     * 本页最后一笔标志<br>
     * PAGE_END
     */
    @Deprecated
    public String getPageEnd() {
        return pageEnd;
    }

    /**
     * 本页最后一笔标志<br>
     * PAGE_END
     */
    @Deprecated
    public void setPageEnd(String pageEnd) {
        this.pageEnd = pageEnd;
    }

    /**
     * 总页数<br>
     * TOTAL_PAGES
     */
    @Deprecated
    public String getTotalPages() {
        return totalPages;
    }

    /**
     * 总页数<br>
     * TOTAL_PAGES
     */
    @Deprecated
    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * 当前记录号<br>
     * CURRENT_NUM
     */
    public String getCurrentNum() {
        return currentNum;
    }

    /**
     * 当前记录号<br>
     * CURRENT_NUM
     */
    public void setCurrentNum(String currentNum) {
        this.currentNum = currentNum;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AppHead [totalNum=");
        builder.append(totalNum);
        builder.append(", pgupOrPgdn=");
        builder.append(pgupOrPgdn);
        builder.append(", totalRows=");
        builder.append(totalRows);
        builder.append(", totalFlag=");
        builder.append(totalFlag);
        builder.append(", currentNum=");
        builder.append(currentNum);
        builder.append("]");
        return builder.toString();
    }
}

