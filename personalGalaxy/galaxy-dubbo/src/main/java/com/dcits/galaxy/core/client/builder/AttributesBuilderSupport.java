package com.dcits.galaxy.core.client.builder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@SuppressWarnings("unchecked")
public abstract class AttributesBuilderSupport<T extends AttributesBuilderSupport<T>>
		implements AttributesBuilder {

	public static final String LOADBALANCE_RANDOM = "random";
	public static final String LOADBALANCE_ROUNDROBIN = "roundrobin";
	public static final String LOADBALANCE_LEASTACTIVE = "leastactive";
	public static final String CLUSTER_FAILOVER = "failover";
	public static final String CLUSTER_FAILFAST = "failfast";
	public static final String CLUSTER_FAILSAFE = "failsafe";
	public static final String CLUSTER_FAILBACK = "failback";

	private final Map<String, Object> attributesMap = new HashMap<String, Object>();
	private Attributes attributes;

	@Override
	public final synchronized Attributes build() {
		if (attributes == null) {
			// Get a copy of the original map and create an Attributes object
			// Note that a TreeMap is used because it will sort the key and get
			// the same toString result
			// if objects(must be simple object, primitive, TreeMap, String etc)
			// in it didn't change
			attributes = new AttributesImpl(new TreeMap<String, Object>(
					attributesMap));
		}

		return attributes;
	}

	protected final synchronized void setAttribute(String name, Object value) {
		attributesMap.put(name, value);
	}

	public final T setActives(int actives) {
		setAttribute(AttributesConstants.ACTIVES, actives);
		return (T) this;
	}

	public final T setAsync(boolean async) {
		setAttribute(AttributesConstants.ASYNC, async);
		return (T) this;
	}

	public final T setCache(String cache) {
		if (cache != null) {
			setAttribute(AttributesConstants.CACHE, cache);
		}
		return (T) this;
	}

	public final T setLoadbalance(String loadbalance) {
		if (loadbalance != null) {
			setAttribute(AttributesConstants.LOADBALANCE, loadbalance);
		}
		return (T) this;
	}

	public final T setMerger(String merger) {
		if (merger != null) {
			setAttribute(AttributesConstants.MERGER, merger);
		}
		return (T) this;
	}

	public final T setMock(boolean mock) {
		setAttribute(AttributesConstants.MOCK, mock);
		return (T) this;
	}

	public final T setParameters(Map<String, String> parameters) {
		if (parameters != null && !parameters.isEmpty()) {
			setAttribute(AttributesConstants.PARAMETERS, new TreeMap<String, String>(parameters));
		}
		return (T) this;
	}

	public final T setRetries(int retries) {
		setAttribute(AttributesConstants.RETRIES, retries);
		return (T) this;
	}

	public final T setSent(boolean sent) {
		setAttribute(AttributesConstants.SENT, sent);
		return (T) this;
	}

	public final T setSticky(boolean sticky) {
		setAttribute(AttributesConstants.STICKY, sticky);
		return (T) this;
	}

	public final T setTimeout(int timeout) {
		setAttribute(AttributesConstants.TIMEOUT, timeout);
		return (T) this;
	}

	public final T setValidation(String validation) {
		if (validation != null) {
			setAttribute(AttributesConstants.VALIDATION, validation);
		}
		return (T) this;
	}

	private static class AttributesImpl implements Attributes {

		private final Map<String, Object> attributesMap;
		private final String identifier;

		AttributesImpl(Map<String, Object> attributesMap) {
			this.attributesMap = attributesMap;
			identifier = attributesMap.toString();
		}

		@Override
		public Object getAttribute(String attributeName) {
			return attributesMap.get(attributeName);
		}

		@Override
		public boolean hasAttribute(String attributeName) {
			return attributesMap.containsKey(attributeName);
		}

		@Override
		public Set<String> getAttributeNames() {
			return Collections.unmodifiableSet(attributesMap.keySet());
		}

		@Override
		public String toString() {
			return identifier;
		}
	}
}
