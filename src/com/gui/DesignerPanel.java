package com.gui;

import javax.swing.JPanel;

import com.Simulation;

public class DesignerPanel extends JPanel {

	private static final long serialVersionUID = -4222042599365568128L;

	private SimViewerPanel viewer;
	private Simulation sim;

	// you were going to make a panel that has a scroll bar and a list of
	// predefined tiles (grass, stone, iron ore) that all have their own images
	// and characteristics from their sprite sheet and predefined data
	public DesignerPanel(SimViewerPanel viewer, Simulation sim) {
		this.viewer = viewer;
		this.sim = sim;
	}

}
