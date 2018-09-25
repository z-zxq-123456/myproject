package com.dcits.galaxy.dal.mybatis.parser;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;

public class SelectColumnParser {
	
	private final static String Regex_As = " AS ";
	
	private final static String Regex_Comma = "[,]";
	
	public static void parser( SelectStatement stmt, ResultMap resultMap){
		String originalColumns = stmt.getSelectSql();
		int index_as_selectSql = originalColumns.toUpperCase().indexOf(Regex_As);
		if( index_as_selectSql > 0  ){
			String[] columns = originalColumns.split(Regex_Comma);
			for (String column : columns) {
				if( !column.isEmpty() ){
					String upperCaseColumn = column.toUpperCase();
					int index_as = upperCaseColumn.indexOf(Regex_As);
					if( index_as > 0 ){
						String name = column.substring(0, index_as);
						name = name.trim();
						String alias = column.substring(index_as + Regex_As.length());
						alias = alias.trim();
						SelectColumn selectColumn = new SelectColumn( name, alias);
						stmt.addSelectColumns(selectColumn);
					}
				}
			}
		}else{
			Set<String> mappedColumns = resultMap.getMappedColumns();
			if( mappedColumns.size() > 0){
				List<ResultMapping> propertyMappings = resultMap.getResultMappings();
				for (ResultMapping resultMapping : propertyMappings) {
					String name = resultMapping.getColumn();
					String mappedName = resultMapping.getProperty();
					SelectColumn selectColumn = new SelectColumn( name ,mappedName);
					stmt.addSelectColumns(selectColumn);
				}
			}else{
				String[] columns = originalColumns.split(Regex_Comma);
				for (String column : columns) {
					column = column.trim();
					if(!column.isEmpty()){
						SelectColumn selectColumn = new SelectColumn( column);
						stmt.addSelectColumns(selectColumn);
					}
				}
			}
		}
	}
}
