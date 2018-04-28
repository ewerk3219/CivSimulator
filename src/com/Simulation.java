package com;

import com.gui.SimViewerPanel;
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
<<<<<<< HEAD
=======
		viewer.setMapToDraw(map);
>>>>>>> 3415391987dd3345e8bab5eea4f4cf97751dc622
	}

	public void runTurn() {
		// update entities here
<<<<<<< HEAD
=======
		viewer.drawScene();
>>>>>>> 3415391987dd3345e8bab5eea4f4cf97751dc622
	}
}
