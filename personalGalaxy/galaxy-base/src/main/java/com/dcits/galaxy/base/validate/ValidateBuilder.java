package com.dcits.galaxy.base.validate;

import java.lang.annotation.Annotation;

public class ValidateBuilder {

	private String desc = "";

	private String restraint = "";

	private boolean notNull = true;

	private boolean notEmpty = false;

	private boolean digit = false;

	private int min = Integer.MIN_VALUE;

	private int max = Integer.MAX_VALUE;

	private String pattern = "";

	private int minSize = -1;

	private int maxSize = -1;

	private String length = "";

	private String in = "";

	private String laterTime = "";
	
	private String dateFormat = "";

	private int decimalLength = -1;
	
	private int integerLength = -1;

	private volatile V v = null;
	
	public V build() {
		if (v == null) {
			synchronized (this) {
				if (v == null) {
					v = new V() {
						@Override
						public Class<? extends Annotation> annotationType() {
							return V.class;
						}

						@Override
						public String restraint() {
							return restraint;
						}

						@Override
						public String pattern() {
							return pattern;
						}

						@Override
						public boolean notNull() {
							return notNull;
						}

						@Override
						public boolean notEmpty() {
							return notEmpty;
						}

						@Override
						public int minSize() {
							return minSize;
						}

						@Override
						public int min() {
							return min;
						}

						@Override
						public int maxSize() {
							return maxSize;
						}

						@Override
						public int max() {
							return max;
						}

						@Override
						public String length() {
							return length;
						}

						@Override
						public String laterTime() {
							return laterTime;
						}

						@Override
						public String in() {
							return in;
						}

						@Override
						public boolean digit() {
							return digit;
						}

						@Override
						public String desc() {
							return desc;
						}

						@Override
						public int decimalLength() {
							return decimalLength;
						}

						@Override
						public int integerLength() {
							return integerLength;
						}

						@Override
						public String dateFormat() {
							return dateFormat;
						}
					};
				}
			}
		}
		return v;
	}

	public static ValidateBuilder newInstance() {
		return new ValidateBuilder();
	}

	public ValidateBuilder desc(String desc) {
		this.desc = desc;
		return this;
	};

	ValidateBuilder restraint(String restraint) {
		this.restraint = restraint;
		return this;
	}

	ValidateBuilder notNull(boolean notNull) {
		this.notNull = notNull;
		return this;
	}

	ValidateBuilder notEmpty(boolean notEmpty) {
		this.notEmpty = notEmpty;
		return this;
	}

	ValidateBuilder digit(boolean digit) {
		this.digit = digit;
		return this;
	}

	ValidateBuilder min(int min) {
		this.min = min;
		return this;
	}

	ValidateBuilder max(int max) {
		this.max = max;
		return this;
	}

	ValidateBuilder pattern(String pattern) {
		this.pattern = pattern;
		return this;
	}

	ValidateBuilder minSize(int minSize) {
		this.minSize = minSize;
		return this;
	}

	ValidateBuilder maxSize(int maxSize) {
		this.maxSize = maxSize;
		return this;
	}

	ValidateBuilder length(String length) {
		this.length = length;
		return this;
	}

	ValidateBuilder in(String in) {
		this.in = in;
		return this;
	}

	ValidateBuilder laterTime(String laterTime) {
		this.laterTime = laterTime;
		return this;
	}
	
	ValidateBuilder dateFoarmat(String dateFoarmat){
		this.dateFormat = dateFoarmat;
		return this;
	}
	
	ValidateBuilder decimalLength(int decimalLength) {
		this.decimalLength = decimalLength;
		return this;
	}

	ValidateBuilder integerLength(int integerLength) {
		this.integerLength = integerLength;
		return this;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setRestraint(String restraint) {
		this.restraint = restraint;
	}

	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}

	public void setNotEmpty(boolean notEmpty) {
		this.notEmpty = notEmpty;
	}

	public void setDigit(boolean digit) {
		this.digit = digit;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public void setMinSize(int minSize) {
		this.minSize = minSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public void setIn(String in) {
		this.in = in;
	}

	public void setLaterTime(String laterTime) {
		this.laterTime = laterTime;
	}
	
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	public void setDecimalLength(int decimalLength) {
		this.decimalLength = decimalLength;
	}

	public void setIntegerLength(int integerLength) {
		this.integerLength = integerLength;
	}

	public void setV(V v) {
		this.v = v;
	}
}
