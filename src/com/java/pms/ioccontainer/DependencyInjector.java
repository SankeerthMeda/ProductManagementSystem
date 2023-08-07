package com.java.pms.ioccontainer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DependencyInjector {
	private static DependencyInjector dependencyInjector;

	private DependencyInjector() {

	}

	public static DependencyInjector getInstance() {
		if (dependencyInjector == null) {
			dependencyInjector = new DependencyInjector();
		}
		return dependencyInjector;
	}

	public <T> T createInstance(Class<T> className) {
		try {
			Constructor<T> constructor = className.getDeclaredConstructor();
			return constructor.newInstance();
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
}
