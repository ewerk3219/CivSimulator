package com.entityFramework.core;

import java.util.HashMap;
import java.util.Map;

public class Entity {

	public final int id;

	private Map<Class, Component> components;

	public Entity(int id) {
		this.id = id;
		components = new HashMap<Class, Component>();
	}

	public void addComponent(Component component) {
		if (!hasComponentInterface(component.getClass())) {
			throw new IllegalArgumentException(component.getClass().toString()
					+ " : additional component must implement the Component interface");
		}
		components.put(component.getClass(), component);
	}

	private boolean hasComponentInterface(Class addition) {
		for (Class testCase : addition.getInterfaces()) {
			if (testCase == Component.class) {
				return true;
			}
		}
		return false;
	}

	public Component getComponent(Class componentType) {
		return components.get(componentType);
	}
}
