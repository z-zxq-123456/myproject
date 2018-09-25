package com.dcits.galaxy.dal.mybatis.proactor.page;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.plugins.RowArgs;
import com.dcits.galaxy.dal.mybatis.proactor.Proactor;
import com.dcits.galaxy.dal.mybatis.proactor.config.vo.ProactorSetting;
import com.dcits.galaxy.dal.mybatis.proactor.page.approximate.ApproximatePage;
import com.dcits.galaxy.dal.mybatis.proactor.page.memory.MemoryPage;

/**
 * 分页处理器
 * @author yin.weicai
 */
public class PageProactor implements Proactor {
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
	
	public List<Object> execute(String statement, Object parameter ,ProactorSetting setting, ShardSqlSessionTemplate shardSqlSessionTempalte){
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
				resultList = doMemoryPage(statement, paraWrapper, setting, shardSqlSessionTempalte);
			}else{
				resultList = doApproximatePage(statement, paraWrapper, setting, shardSqlSessionTempalte);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return resultList;
	}
	
	private List<Object> doMemoryPage(String statement, Object parameter ,ProactorSetting setting, ShardSqlSessionTemplate shardSqlSessionTempalte){
		MemoryPage memoryPage = new MemoryPage(shardSqlSessionTempalte, statement, parameter, setting);
		return memoryPage.query();
	}
	
	private List<Object> doApproximatePage(String statement, Object parameter ,ProactorSetting setting, ShardSqlSessionTemplate shardSqlSessionTempalte){
		ApproximatePage approximatePage = new ApproximatePage(shardSqlSessionTempalte, statement, parameter, setting);
		approximatePage.setMemoryPageOffset( getMemoryPageOffset() );
		return approximatePage.query();
	}

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
	
}
