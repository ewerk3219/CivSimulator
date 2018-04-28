package com;

import com.gui.SimViewerPanel;
import com.gui.SimulationPanel;
import com.map.core.SimMap;

/**
 * A Simulation will have everything it needs to simulate the separate elements
 * that will be simulated at each tic or turn
 * 
 * @author Eric
 *
 */
public class Simulation {
	/**
	 * The actual map containing information of the terrain.
	 */
	public SimMap map;

	public Simulation() {
		map = new SimMap(90, 60);
		map.defaultInit();
	}

	public void runTurn() {
		// update entities here
	}
}
