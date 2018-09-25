package com.dcits.galaxy.core.client.builder;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.dubbo.config.MethodConfig;

@SuppressWarnings("unchecked")
public abstract class AbstractServiceAttributesBuilder<T> extends
		AttributesBuilderSupport<AbstractServiceAttributesBuilder<T>> {

	private static final Map<String, Method> METHOD_MAPPINGS = new HashMap<String, Method>();

	static {
		BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(MethodConfig.class,
					Object.class);
		} catch (IntrospectionException e) {
			throw new IllegalStateException("Can't introspect bean of type ["
					+ MethodConfig.class.getName() + "].", e);
		}

		for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
			Method writeMethod = descriptor.getWriteMethod();
			if (writeMethod != null) {
				METHOD_MAPPINGS.put(descriptor.getName(), writeMethod);
			}
		}
	}

	// ==============================================================
	// xuecy 2015-08-04 add
	public static final String SCOPE_LOCAL = "local";
	public static final String SCOPE_REMOTE = "remote";

	public final T setScope(String scope) {
		setAttribute(AttributesConstants.SCOPE, scope);
		return (T) this;
	}
	// ==============================================================

	public final T setCallbacks(int callbacks) {
		setAttribute(AttributesConstants.CALLBACKS, callbacks);
		return (T) this;
	}

	public final T setCheck(boolean check) {
		setAttribute(AttributesConstants.CHECK, check);
		return (T) this;
	}

	public final T setClient(String client) {
		if (client != null) {
			setAttribute(AttributesConstants.CLIENT, client);
		}
		return (T) this;
	}

	public final T setCluster(String cluster) {
		if (cluster != null) {
			setAttribute(AttributesConstants.CLUSTER, cluster);
		}
		return (T) this;
	}

	public final T setConnections(int connections) {
		setAttribute(AttributesConstants.CONNECTIONS, connections);
		return (T) this;
	}

	public final T setFilter(String filter) {
		if (filter != null) {
			setAttribute(AttributesConstants.FILTER, filter);
		}
		return (T) this;
	}

	public final T setGeneric(boolean generic) {
		setAttribute(AttributesConstants.GENERIC, generic);
		return (T) this;
	}
	
	public final T setGeneric(String generic) {
		setAttribute(AttributesConstants.GENERIC, generic);
		return (T) this;
	}

	public final T setGroup(String group) {
		if (group != null) {
			setAttribute(AttributesConstants.GROUP, group);
		}
		return (T) this;
	}

	public final T setInit(boolean init) {
		setAttribute(AttributesConstants.INIT, init);
		return (T) this;
	}

	public final T setLayer(String layer) {
		if (layer != null) {
			setAttribute(AttributesConstants.LAYER, layer);
		}
		return (T) this;
	}

	public final T setLazy(boolean lazy) {
		setAttribute(AttributesConstants.LAZY, lazy);
		return (T) this;
	}

	public final T setListener(String listener) {
		if (listener != null) {
			setAttribute(AttributesConstants.LISTENER, listener);
		}
		return (T) this;
	}

	public final T setOnconnect(String onconnect) {
		if (onconnect != null) {
			setAttribute(AttributesConstants.ONCONNECT, onconnect);
		}
		return (T) this;
	}

	public final T setOndisconnect(String ondisconnect) {
		if (ondisconnect != null) {
			setAttribute(AttributesConstants.ONDISCONNECT, ondisconnect);
		}
		return (T) this;
	}

	public final T setReconnect(String reconnect) {
		if (reconnect != null) {
			setAttribute(AttributesConstants.RECONNECT, reconnect);
		}
		return (T) this;
	}

	public final T setRemote(boolean isRemote) {
		setAttribute(AttributesConstants.SCOPE, isRemote ? "remote" : "local");
		return (T) this;
	}

	public final T setStub(boolean stub) {
		setAttribute(AttributesConstants.STUB, stub);
		return (T) this;
	}

	public final T setUrl(String url) {
		if (url != null) {
			setAttribute(AttributesConstants.URL, url);
		}
		return (T) this;
	}

	public final T setVersion(String version) {
		if (version != null) {
			setAttribute(AttributesConstants.VERSION, version);
		}
		return (T) this;
	}

	public final T setMethods(
			MethodAttributesBuilder... methodAttributesBuilders) {
		if (methodAttributesBuilders != null
				&& methodAttributesBuilders.length > 0) {
			List<MethodConfig> methodConfigs = new ArrayList<MethodConfig>();

			for (MethodAttributesBuilder methodAttributesBuilder : methodAttributesBuilders) {
				if (methodAttributesBuilder == null) {
					continue;
				}

				Attributes attributes = methodAttributesBuilder.build();

				MethodConfig methodConfig = toMethodConfig(attributes);
				if (methodConfig != null) {
					methodConfigs.add(methodConfig);
				}
			}

			if (!methodConfigs.isEmpty()) {
				setAttribute(AttributesConstants.METHODS, methodConfigs);
			}
		}
		return (T) this;
	}

	private MethodConfig toMethodConfig(Attributes attributes) {
		Set<String> attributeNames = attributes.getAttributeNames();

		if (attributeNames.isEmpty()) {
			return null;
		} else {
			MethodConfig methodConfig = new MethodConfig();

			for (String attributeName : attributeNames) {
				Method writeMethod = METHOD_MAPPINGS.get(attributeName);
				if (writeMethod != null) {
					if (!attributes.hasAttribute(attributeName)) {
						continue;
					}

					try {
						writeMethod.invoke(methodConfig,
								attributes.getAttribute(attributeName));
					} catch (Exception e) {
						throw new IllegalStateException("Error set attribute ["
								+ attributeName + "] to MethodConfig object.",
								e);
					}
				}
			}

			return methodConfig;
		}
	}
}
