package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.SimulatorLoop;

public class SimViewerPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5457420362972555491L;
	private SimulatorLoop looper; // YOU WERE ADDING IN A NEW LOOP TO RUN THE
									// CAMERA AS A TEST, YOU THINK THE ISSUE WAS
									// THE PANEL WAS NOT INITIALIZED WHEN YOU
									// TRIED TO RENDER ON TOP OF IT.

	/**
	 * Create the panel.
	 */
	public SimViewerPanel() {
		setLayout(new BorderLayout(0, 0));

		JPanel optionBar = new JPanel();
		optionBar.setBackground(Color.GRAY);
		optionBar.setMaximumSize(new Dimension(200, 3000));
		add(optionBar, BorderLayout.EAST);

		JButton runButton = new JButton("Run\r\n");
		optionBar.add(runButton);

		JButton stopButton = new JButton("Stop");
		optionBar.add(stopButton);

		JPanel mapViewer = new JPanel();
		mapViewer.setBackground(Color.LIGHT_GRAY);
		mapViewer.setMinimumSize(new Dimension(300, 300));
		add(mapViewer, BorderLayout.CENTER);

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
