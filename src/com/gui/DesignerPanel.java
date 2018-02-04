package com.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.Simulation;
import com.util.sprites.TerrainType;

public class DesignerPanel extends JPanel {

	private static final long serialVersionUID = -4222042599365568128L;

	private SimViewerPanel viewer;
	private int selectedTerrain;
	private boolean buildMode;

	// you were going to make a panel that has a scroll bar and a list of
	// predefined tiles (grass, stone, iron ore) that all have their own images
	// and characteristics from their sprite sheet and predefined data
	public DesignerPanel(SimViewerPanel viewer, Simulation sim) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		selectedTerrain = -1; // dummy value
		buildMode = false;

		this.viewer = viewer;

		// This will listen for changes and change the build mode accordingly
		BuildModeToggleListener bmtl = new BuildModeToggleListener();

		// radio buttons to toggle build mode
		JRadioButton buildModeOn = new JRadioButton("Build Mode On");
		buildModeOn.setToolTipText("Toggles the build mode on");
		buildModeOn.setActionCommand("On");
		buildModeOn.addActionListener(bmtl);
		buildModeOn.setAlignmentX(Component.CENTER_ALIGNMENT);
		JRadioButton buildModeOff = new JRadioButton("Build Mode Off");
		buildModeOff.setToolTipText("Toggles the build mode off");
		buildModeOff.setActionCommand("Off");
		buildModeOff.setSelected(true);
		buildModeOff.addActionListener(bmtl);
		buildModeOff.setAlignmentX(Component.CENTER_ALIGNMENT);

		// Deals with un-toggling old buttons.
		ButtonGroup buildToggleGroup = new ButtonGroup();
		buildToggleGroup.add(buildModeOn);
		buildToggleGroup.add(buildModeOff);

		this.add(buildModeOn);
		this.add(buildModeOff);

		/**
		 * This will display the texture of the selected terrain
		 */
		TextureViewerPanel textureViewer = new TextureViewerPanel();
		textureViewer.setAlignmentX(Component.CENTER_ALIGNMENT);
		textureViewer.setMinimumSize(new Dimension(64, 64));
		textureViewer.setPreferredSize(new Dimension(64, 64));
		textureViewer.setMaximumSize(new Dimension(64, 64));
		textureViewer.setBorder(BorderFactory.createLineBorder(Color.GRAY, TextureViewerPanel.BORDER_THICKNESS));
		this.add(textureViewer);

		/**
		 * Setup for the scroll pane and terrain selection stuff
		 */
		JList<String> terrainList = new JList<String>(TerrainType.TERRAIN_LIST);
		terrainList.addListSelectionListener(new ListSelectionListener() {

			/**
			 * Update the selected terrain every time it changes
			 */
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selectedTerrain = terrainList.getSelectedIndex();
				// Update the texture viewer
				textureViewer.repaint();
			}
		});
		terrainList.setSelectedIndex(ListSelectionModel.SINGLE_SELECTION);

		// make this a vertically arranged list
		terrainList.setLayoutOrientation(JList.VERTICAL);

		// makes it try and list as many as possible
		terrainList.setVisibleRowCount(-1);

		JScrollPane scrollPane = new JScrollPane(terrainList);
		scrollPane.setPreferredSize(new Dimension(200, 300));
		this.add(scrollPane);
	}

	public boolean isBuildModeOn() {
		return this.buildMode;
	}

	public int getSelectedTerrain() {
		return this.selectedTerrain;
	}

	private class BuildModeToggleListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("On")) {
				buildMode = true;
			} else if (e.getActionCommand().equals("Off")) {
				buildMode = false;
			} else {
				throw new IllegalStateException(
						"Didn't get on/off string action command. This class is probably being misused.");
			}
		}

	}

	private class TextureViewerPanel extends JPanel {

		private static final long serialVersionUID = -681999394947817129L;
		public static final int BORDER_THICKNESS = 2;

		public void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			BufferedImage textureToDraw = (BufferedImage) TerrainType
					.getTerrainDataCopy(TerrainType.TERRAIN_LIST[selectedTerrain]).getTerrainImage();
			g2d.drawImage(textureToDraw, 0, 0, this.getWidth(), this.getHeight(), null);
		}
	}

}
