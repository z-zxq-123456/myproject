package com.dcits.galaxy.dal.mybatis.table.router.tableconfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;

import com.dcits.galaxy.dal.mybatis.exception.ParseFileException;
import com.dcits.galaxy.dal.mybatis.table.router.tableconfig.vo.InternalTable;
import com.dcits.galaxy.dal.mybatis.table.router.tableconfig.vo.InternalTableShard;
import com.dcits.galaxy.dal.mybatis.table.router.tableconfig.vo.InternalTables;
import com.dcits.galaxy.dal.mybatis.table.tableshard.TableShard;
import com.thoughtworks.xstream.XStream;

/**
 * 从XML文件中读取配置的默认实现
 * 
 * @author huang.zhe
 */
public class SimpleTableXmlFactoryBean extends AbstractSimpleTableConfigurationFactoryBean {

	@Override
	protected Set<TableShard> loadRulesFromExternal(Resource configLocation)
		throws IOException {
		XStream xstream = new XStream();
		xstream.alias("tables", InternalTables.class);
		xstream.alias("tableInfo", InternalTable.class);
		xstream.alias("tableShard", InternalTableShard.class);
		xstream.addImplicitCollection(InternalTables.class, "tables");
		xstream.useAttributeFor(InternalTableShard.class, "id");
		xstream.useAttributeFor(InternalTableShard.class, "name");
		InternalTables internalTables;
		try {
			internalTables = (InternalTables) xstream.fromXML(configLocation.getInputStream());

		} catch (RuntimeException e) {

			StringBuilder info = new StringBuilder("\nParse ");
			info.append(configLocation.getDescription());
			info.append(" has error. Error occurred near the ");

			String str = e.getMessage();
			int start = str.indexOf("line number");
			int end = str.indexOf("\n", start);
			str = str.substring(start, end);

			info.append(str.replaceAll("  +", " "));
			throw new ParseFileException(info.toString());
		}

		List<InternalTable> tables = internalTables.getTables();
		if (CollectionUtils.isEmpty(tables)) {
			return null;
		}
		Set<TableShard> tableShardSet = new HashSet<TableShard>();
		for (InternalTable tb : tables) {
			String table = StringUtils.trimToEmpty(tb.getTable());
			List<InternalTableShard> tableShards = tb.getTableShards();
			for (InternalTableShard ts : tableShards) {
				String tableShard = StringUtils.trimToEmpty(ts.getName());
				String id = StringUtils.trimToEmpty(ts.getId());
				Validate.notEmpty(table, " table must be given explicitly.");

				if (StringUtils.isEmpty(tableShard) && StringUtils.isEmpty(id) && StringUtils.isEmpty(table)) {
					throw new IllegalArgumentException("'table' , 'id' and 'tableShard' must be given.");
				}
				tableShardSet.add(new TableShard(table, id, tableShard));
			}
		}
		return tableShardSet;
	}
	
}
