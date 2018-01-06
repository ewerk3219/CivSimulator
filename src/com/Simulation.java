package com;

import com.gui.SimViewerPanel;
import com.map.core.SimMap;

public class Simulation {
	/**
	 * The actual map containing information of the terrain.
	 */
	public SimMap map;
	/**
	 * The viewer for the simulation where everything is displayed. Pointer here
	 * just to make calls easier.
	 */
	public SimViewerPanel viewer;

	public Simulation() {
		map = new SimMap(90, 60);
		map.defaultInit();
	}

	public void runTurn() {
		// update entities here
	}
}
