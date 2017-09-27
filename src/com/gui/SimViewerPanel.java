package com.gui;

import javax.swing.JPanel;

import com.map.core.SimMap;
import com.map.core.camera.Camera;

public class SimViewerPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5457420362972555491L;

	/**
	 * The renderer for this viewer
	 */
	private Camera camera;

	public SimViewerPanel() {
		camera = new Camera();
	}

	public Camera getCamera() {
		return camera;
	}

	public void render(SimMap map) {
		camera.render(map);
	}
}
