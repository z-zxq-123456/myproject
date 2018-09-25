package com.dcits.galaxy.core.client.builder;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.config.ArgumentConfig;

public final class MethodAttributesBuilder extends AttributesBuilderSupport<MethodAttributesBuilder> {

	public MethodAttributesBuilder setArguments(Argument... arguments) {
		if (arguments != null && arguments.length > 0) {
			List<ArgumentConfig> argumentConfigs = new ArrayList<ArgumentConfig>();

			for (Argument argument : arguments) {
				if (argument == null) {
					continue;
				}

				ArgumentConfig argumentConfig = new ArgumentConfig();
				argumentConfig.setCallback(argument.isCallback());
				argumentConfig.setIndex(argument.getIndex());
				argumentConfig.setType(argument.getType());

				argumentConfigs.add(argumentConfig);
			}

			if (!argumentConfigs.isEmpty()) {
				setAttribute(AttributesConstants.ARGUMENTS, argumentConfigs);
			}
		}
		return this;
	}

	public MethodAttributesBuilder setExecutes(int executes) {
		setAttribute(AttributesConstants.EXECUTES, executes);
		return this;
	}

	public MethodAttributesBuilder setName(String name) {
		if (name != null) {
			setAttribute(AttributesConstants.NAME, name);
		}
		return this;
	}

	public MethodAttributesBuilder setOninvoke(Object oninvoke) {
		if (oninvoke != null) {
			setAttribute(AttributesConstants.ONINVOKE, oninvoke);
		}
		return this;
	}

	public MethodAttributesBuilder setOninvokeMethod(String oninvokeMethod) {
		if (oninvokeMethod != null) {
			setAttribute(AttributesConstants.ONINVOKE_METHOD, oninvokeMethod);
		}
		return this;
	}

	public MethodAttributesBuilder setOnreturn(Object onreturn) {
		if (onreturn != null) {
			setAttribute(AttributesConstants.ONRETURN, onreturn);
		}
		return this;
	}

	public MethodAttributesBuilder setOnreturnMethod(String onreturnMethod) {
		if (onreturnMethod != null) {
			setAttribute(AttributesConstants.ONRETURN_METHOD, onreturnMethod);
		}
		return this;
	}

	public MethodAttributesBuilder setOnthrow(Object onthrow) {
		if (onthrow != null) {
			setAttribute(AttributesConstants.ONTHROW, onthrow);
		}
		return this;
	}

	public MethodAttributesBuilder setOnthrowMethod(String onthrowMethod) {
		if (onthrowMethod != null) {
			setAttribute(AttributesConstants.ONTHROW_METHOD, onthrowMethod);
		}
		return this;
	}

	public MethodAttributesBuilder setReturn(boolean isReturn) {
		setAttribute(AttributesConstants.RETURN, isReturn);
		return this;
	}
}
