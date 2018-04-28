package com.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import com.Simulation;

public class MainWindow {

	private static JFrame frame;
	private static SimulationPanel simPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
				} catch (Exception e) {
					e.printStackTrace();
				}
<<<<<<< HEAD
=======

				while (true) {
					sleepFor(1000);
					SimulationPanel simulationPanel = (SimulationPanel) frame.getContentPane();
					simulationPanel.simulation.runTurn();
				}
>>>>>>> 3415391987dd3345e8bab5eea4f4cf97751dc622
			}
		});
		sleepFor(1000);
		boolean keepGoing = true;
		while (keepGoing) {
			simPanel.simulation.runTurn();
			updateMapViewer();
			sleepFor(40);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private static void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("res/crown.png"));
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(400, 400));
		simPanel = new SimulationPanel();
		frame.setContentPane(simPanel);
		frame.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Dimension currentDim = frame.getSize();
				Dimension minD = frame.getMinimumSize();
				if (currentDim.width < minD.width) {
					currentDim.width = minD.width;
				}
				if (currentDim.height < minD.height) {
					currentDim.height = minD.height;
				}
				frame.setSize(currentDim);
			}
		});
		frame.pack();
		frame.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}

	private static void updateMapViewer() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				SimulationPanel simulationPanel = (SimulationPanel) frame
						.getContentPane();
				SimViewerPanel viewer = simulationPanel.viewer;
				simulationPanel.simulation.runTurn();
				viewer.paint(viewer.getGraphics());
			}
		});
	}

	private static void sleepFor(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
