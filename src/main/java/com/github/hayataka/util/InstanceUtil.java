package com.github.hayataka.util;

public class InstanceUtil {

	public <T> T instantiate(Class<T> clazz) {

		if (clazz.isInterface()) {
			throw new RuntimeException("Specified class is an interface");
		}
		try {
			return clazz.newInstance();
		}
		catch (InstantiationException ex) {
			throw new RuntimeException("Is it an abstract class?", ex);
		}
		catch (IllegalAccessException ex) {
			throw new RuntimeException("Is the constructor accessible?", ex);
		}
	}
	
	public Object instantiate(String className) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("classNot found, className:" + className, e);
		}
		return instantiate(clazz);
	}

}
