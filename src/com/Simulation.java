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
	 * The container for all the gui elements (buttons, dials, slider, etc)
	 * which also contains the viewer as well.
	 */
	public SimulationPanel simPanel;
	/**
	 * The viewer for the simulation where everything is displayed. Pointer here
	 * just to make calls easier.
	 */
	public SimViewerPanel viewer;

	public Simulation(SimulationPanel simPanel) {
		this.simPanel = simPanel;
		viewer = (SimViewerPanel) simPanel.getComponent(3);
		// This MUST happen immediately afterwards
		viewer.getCamera().switchToPanel(viewer);
		map = new SimMap(90, 60);
	}

	public void runTurn() {
		// update entities here
		viewer.render(map);
	}
}
