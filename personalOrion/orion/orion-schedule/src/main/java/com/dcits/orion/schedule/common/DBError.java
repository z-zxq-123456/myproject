package com.dcits.orion.schedule.common;

import org.apache.sqoop.common.ErrorCode;

public enum DBError implements ErrorCode {

	DB_0000("Unable to load the driver class"),
	DB_0001("Unable to get a connection"),
	DB_0002("Unable to execute the SQL statement"),
	DB_0003("Unable to access meta data"),
	DB_0004("Error occurs while retrieving data from result"),
	DB_0005("No column is found to partition data"),
	DB_0006("No boundaries are found for partition column"),
	DB_0007("The table name and the table sql cannot be specified together"),
	DB_0008("Neither the table name nor the table sql " + "are specified"),
	DB_0010("No substitute token in the specified sql"),
	DB_0011("The type is not supported"),
	DB_0012("The required option has not been set yet"),
	DB_0013("No parameter marker in the specified sql"),
	DB_0014("The table columns cannot be specified when the table sql is specified during export"),
	DB_0015("Partition column contains unsupported values"),
	DB_0016("Can't fetch schema"),
	DB_0017("The stage table is not empty."),
	DB_0018("Error occurred while transferring data from stage table to destination table."), ;

	private final String message;

	private DBError(String message) {
		this.message = message;
	}

	public String getCode() {
		return name();
	}

	public String getMessage() {
		return message;
	}
}
