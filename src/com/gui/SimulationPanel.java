package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.Simulation;

public class SimulationPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5457420362972555491L;
	public Simulation simulation;

	/**
	 * Create the panel.
	 */
	public SimulationPanel() {
		setLayout(new BorderLayout(0, 0));

		// 1
		JPanel optionBar = new JPanel();
		optionBar.setBackground(Color.GRAY);
		optionBar.setMinimumSize(new Dimension(100, 300));
		optionBar.setPreferredSize(new Dimension(150, 500));
		optionBar.setMaximumSize(new Dimension(200, 1920));
		add(optionBar, BorderLayout.EAST);

		// 2
		JButton runButton = new JButton("Run\r\n");
		optionBar.add(runButton);

		// 3
		JButton stopButton = new JButton("Stop");
		optionBar.add(stopButton);

		// 4
		SimViewerPanel mapViewer = new SimViewerPanel();
		mapViewer.setBackground(Color.LIGHT_GRAY);
		mapViewer.setMinimumSize(new Dimension(300, 300));
		mapViewer.setPreferredSize(new Dimension(800, 500));
		mapViewer.setMaximumSize(new Dimension(1080, 1920));
		add(mapViewer, BorderLayout.CENTER);
		simulation = new Simulation(mapViewer);

		// 5
		JMenuBar menuBar = new JMenuBar();
		add(menuBar, BorderLayout.NORTH);

		JMenu Map = new JMenu("Map\r\n");
		menuBar.add(Map);

		JMenuItem newMapItem = new JMenuItem("New Map\r\n");
		Map.add(newMapItem);

		JMenuItem loadMapItem = new JMenuItem("Load Map");
		Map.add(loadMapItem);
	}
}
