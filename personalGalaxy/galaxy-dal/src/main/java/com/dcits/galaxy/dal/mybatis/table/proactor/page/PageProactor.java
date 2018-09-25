package com.dcits.galaxy.dal.mybatis.table.proactor.page;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.dal.mybatis.plugins.RowArgs;
import com.dcits.galaxy.dal.mybatis.proactor.config.vo.ProactorSetting;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;
import com.dcits.galaxy.dal.mybatis.table.TableShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.table.proactor.Proactor;
import com.dcits.galaxy.dal.mybatis.table.proactor.page.approximate.ApproximatePageTable;
import com.dcits.galaxy.dal.mybatis.table.proactor.page.memory.MemoryPageTable;

/**
 * 分页处理器
 * @author huang.zhe
 */
public class PageProactor  implements Proactor{
	private static final Logger logger = LoggerFactory.getLogger(PageProactor.class);
	
	public final static String Const_total_statement = "statement_totoal";
	
	/**
	 * 启用内存分页的offset的值
	 */
	private int memoryPageOffset = 10;
	
	/**
	 * 最大 条数限制
	 */
	private int maxLimit = 10000;
	
	public int getMemoryPageOffset() {
		return memoryPageOffset;
	}

	public void setMemoryPageOffset(int memoryPageOffset) {
		this.memoryPageOffset = memoryPageOffset;
	}

	public int getMaxLimit() {
		return maxLimit;
	}

	public void setMaxLimit(int maxLimit) {
		this.maxLimit = maxLimit;
	}

	@Override
	public List<Object> execute(String statement, Object parameter, ProactorSetting setting, TableShardSqlSessionTemplate tableShardSqlSessionTempalte) {
		// TODO Auto-generated method stub
		List<Object> resultList = new ArrayList<Object>();
		
		try {
			ParameterWrapper paraWrapper = (ParameterWrapper)parameter;
			RowArgs rowArgs = paraWrapper.getRowArgs();
			int limit = rowArgs.getLimit();
			if( limit > maxLimit ){
				rowArgs.setLimit( maxLimit );
				logger.warn("the RowArgs.limit is bigger than the PageProactor.maxLimit,will use the PageProactor.maxLimit!" );
			} 
			long offset = rowArgs.getOffset();
			if( offset <= memoryPageOffset){
				resultList = doMemoryPage(statement, paraWrapper, setting, tableShardSqlSessionTempalte);
			}else{
				resultList = doApproximatePage(statement, paraWrapper, setting, tableShardSqlSessionTempalte);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return resultList;
	}
	
	private List<Object> doMemoryPage(String statement, Object parameter ,ProactorSetting setting, TableShardSqlSessionTemplate tableShardSqlSessionTempalte){
		MemoryPageTable memoryPage = new MemoryPageTable(tableShardSqlSessionTempalte, statement, parameter, setting);
		return memoryPage.query();
	}
	
	private List<Object> doApproximatePage(String statement, Object parameter ,ProactorSetting setting, TableShardSqlSessionTemplate tableShardSqlSessionTempalte){
		ApproximatePageTable approximatePage = new ApproximatePageTable(tableShardSqlSessionTempalte, statement, parameter, setting);
		approximatePage.setMemoryPageOffset( getMemoryPageOffset() );
		return approximatePage.query();
	}
	
}
