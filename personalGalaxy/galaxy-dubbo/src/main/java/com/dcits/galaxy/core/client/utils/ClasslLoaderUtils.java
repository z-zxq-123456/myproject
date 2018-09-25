package com.dcits.galaxy.core.client.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class ClasslLoaderUtils {

	private ClasslLoaderUtils() {
	}

	public static ClassLoader createClassLoader(Class<?>... classes) {
		if (classes == null || classes.length == 0) {
			throw new IllegalArgumentException("Classes must not be null or empty.");
		}

		Set<ClassLoader> classLoaders = new HashSet<ClassLoader>(classes.length);
		for (Class<?> clazz : classes) {
			classLoaders.add(clazz.getClassLoader());
		}

		return new ChainedClassLoader(classLoaders);
	}

	/**
	 * ClassLoader组合
	 */
	private static class ChainedClassLoader extends ClassLoader {

		/**
		 * ClassLoader集合
		 */
		private final Collection<ClassLoader> classLoaders;

		/**
		 * 最佳候选
		 */
		private volatile ClassLoader bestCandidate;

		public ChainedClassLoader(Collection<ClassLoader> classLoaders) {
			this.classLoaders = classLoaders;
		}

		@Override
		protected Class<?> findClass(String name) throws ClassNotFoundException {
			if (bestCandidate != null) {
				try {
					return bestCandidate.loadClass(name);
				} catch (Throwable t) {
					bestCandidate = null;
				}
			}

			Throwable throwable = null;
			Class<?> clazz = null;
			ClassLoader classLoader = null;

			for (Iterator<ClassLoader> iterator = classLoaders.iterator(); iterator.hasNext();) {
				classLoader = iterator.next();

				try {
					clazz = classLoader.loadClass(name);
					break;
				} catch (Throwable t) {
					throwable = t;
				}
			}

			if (clazz == null) {
				if (throwable instanceof ClassNotFoundException) {
					throw (ClassNotFoundException) throwable;
				} else {
					throw new ClassNotFoundException(String.format(
							"Unable to load class %s by any known class loaders.", name), throwable);
				}
			}

			bestCandidate = classLoader;

			return clazz;
		}

		@Override
		protected URL findResource(String name) {
			URL result = null;

			if (bestCandidate != null) {
				result = bestCandidate.getResource(name);
				if (result == null) {
					bestCandidate = null;
				} else {
					return result;
				}
			}

			ClassLoader classLoader = null;

			for (Iterator<ClassLoader> iterator = classLoaders.iterator(); iterator.hasNext();) {
				classLoader = iterator.next();

				result = classLoader.getResource(name);
				if (result != null) {
					break;
				}
			}

			bestCandidate = classLoader;

			return result;
		}

		@Override
		protected Enumeration<URL> findResources(String name) throws IOException {
			Enumeration<URL> result = null;

			if (bestCandidate != null) {
				result = bestCandidate.getResources(name);
				if (result == null || !result.hasMoreElements()) {
					bestCandidate = null;
				} else {
					return result;
				}
			}

			ClassLoader classLoader = null;

			for (Iterator<ClassLoader> iterator = classLoaders.iterator(); iterator.hasNext();) {
				classLoader = iterator.next();

				result = classLoader.getResources(name);
				if (result != null && result.hasMoreElements()) {
					break;
				}
			}

			bestCandidate = classLoader;

			return result != null ? result : Collections.enumeration(Collections.<URL> emptyList());
		}
	}
}
