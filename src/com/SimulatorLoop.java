package com;

import com.gui.SimViewerPanel;
import com.map.camera.Camera;
import com.map.core.SimMap;

public class SimulatorLoop {
	private SimViewerPanel svp;
	private SimMap map;
	private Camera cam;

	public SimulatorLoop(SimViewerPanel svp) {
		this.svp = svp;
		map = new SimMap(30, 30);
		map.defaultInit();
		cam = new Camera(svp);
	}

	public void loop() {
		cam.render(map);
	}
}
