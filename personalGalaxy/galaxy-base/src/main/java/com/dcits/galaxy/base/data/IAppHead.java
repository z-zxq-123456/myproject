/**
 * <p>Title: AppHead.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 20150127 14:25:48
 * @version V1.0
 */
package com.dcits.galaxy.base.data;

import com.dcits.galaxy.base.bean.IBean;

/**
 * @author Tim
 * @version V1.0
 * @description MBSD公共应用头
 * @update 20150127 14:25:48
 */
public interface IAppHead extends IBean {

    /**
     * 本页记录总数<br>
     * TOTAL_NUM
     */
    String getTotalNum();

    /**
     * 本页记录总数<br>
     * TOTAL_NUM
     */
    void setTotalNum(String totalNum);

    /**
     * 上翻/下翻标志<br>
     * PGUP_OR_PGDN
     */
    String getPgupOrPgdn();

    /**
     * 上翻/下翻标志<br>
     * PGUP_OR_PGDN
     */
    void setPgupOrPgdn(String pgupOrPgdn);

    /**
     * 总笔数<br>
     * TOTAL_ROWS
     */
    String getTotalRows();

    /**
     * 总笔数<br>
     * TOTAL_ROWS
     */
    void setTotalRows(String totalRows);

    /**
     * 汇总标志<br>
     * TOTAL_FLAG
     */
    String getTotalFlag();

    /**
     * 汇总标志<br>
     * TOTAL_FLAG
     */
    void setTotalFlag(String totalFlag);

    /**
     * 本页第一笔标志<br>
     * PAGE_START
     */
    @Deprecated
    String getPageStart();

    /**
     * 本页第一笔标志<br>
     * PAGE_START
     */
    @Deprecated
    void setPageStart(String pageStart);

    /**
     * 本页最后一笔标志<br>
     * PAGE_END
     */
    @Deprecated
    String getPageEnd();

    /**
     * 本页最后一笔标志<br>
     * PAGE_END
     */
    @Deprecated
    void setPageEnd(String pageEnd);

    /**
     * 总页数<br>
     * TOTAL_PAGES
     */
    @Deprecated
    String getTotalPages();

    /**
     * 总页数<br>
     * TOTAL_PAGES
     */
    @Deprecated
    void setTotalPages(String totalPages);

    /**
     * 当前记录号<br>
     * CURRENT_NUM
     */
    String getCurrentNum();

    /**
     * 当前记录号<br>
     * CURRENT_NUM
     */
    void setCurrentNum(String currentNum);

}

