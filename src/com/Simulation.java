package com;

import com.gui.SimViewerPanel;
import com.gui.SimulationPanel;
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

	public Simulation(SimViewerPanel viewer) {
		this.viewer = viewer;
		// This MUST happen immediately afterwards
		map = new SimMap(90, 60);
		viewer.setMapToDraw(map);
	}

	public void runTurn() {
		// update entities here
		viewer.paint(viewer.getGraphics());
	}
}
