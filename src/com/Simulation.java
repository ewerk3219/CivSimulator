package com;

import com.gui.SimViewerPanel;
import com.map.core.SimMap;

public class Simulation {
	/**
	 * The actual map containing information of the terrain.
	 */
	public SimMap map;
	private boolean isPaused = false;

	/**
	 * The viewer for the simulation where everything is displayed. Pointer here
	 * just to make calls easier.
	 */
	public SimViewerPanel viewer;

	public Simulation() {
		map = new SimMap(90, 60);
		map.defaultInit();
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public void runTurn() {
		if (isPaused) {
			// run turn
		}
	}
}
