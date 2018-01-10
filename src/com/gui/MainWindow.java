package com.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.Simulation;
import com.input.MouseClickFocusTransfer;
import com.input.ViewerMovement;
import com.util.SimTime;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Simulation sim = new Simulation();
		MainWindow window = new MainWindow(sim);
		runLoop(sim);
	}

	public static final Dimension MAIN_WINDOW_SIZE = new Dimension(800, 600);
	public static final Dimension BUTTON_MIN = new Dimension(25, 15);
	public static final Dimension BUTTON_PREF = new Dimension(60, 20);
	public static final Dimension BUTTON_MAX = new Dimension(80, 30);

	public static SimViewerPanel viewer;
	public static Simulation sim;

	public MainWindow(Simulation sim) {
		this.sim = sim;
		this.setSize(MAIN_WINDOW_SIZE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("res/crown.png"));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		Panel contentPanel = new Panel();
		contentPanel.setPreferredSize(MAIN_WINDOW_SIZE);
		this.setContentPane(contentPanel);
		contentPanel.setLayout(new BorderLayout());

		viewer = new SimViewerPanel();
		viewer.setMinimumSize(new Dimension(200, 100));
		viewer.setPreferredSize(new Dimension(700, 600));
		viewer.setMaximumSize(new Dimension(1920, 1080));
		viewer.setMapToDraw(sim.map);
		viewer.setFocusTraversalKeysEnabled(true);
		viewer.setFocusable(true);
		contentPanel.add(viewer, BorderLayout.CENTER);
		viewer.addKeyListener(new ViewerMovement(viewer));

		// give viewer focus every time it is clicked on
		viewer.addMouseListener(new MouseClickFocusTransfer(viewer));

		// give viewer focus every time the frame gains focus
		this.addWindowFocusListener(new WindowAdapter() {
			public void windowGainedFocus(WindowEvent e) {
				viewer.requestFocusInWindow();
			}
		});

		/**
		 * Menu Bar setup
		 */

		JMenuBar menuBar = new JMenuBar();
		menuBar.setPreferredSize(new Dimension(800, 30));
		contentPanel.add(menuBar, BorderLayout.NORTH);

		JMenu Map = new JMenu("Map\r\n");
		menuBar.add(Map);

		JMenuItem newMapItem = new JMenuItem("New Map\r\n");
		Map.add(newMapItem);

		JMenuItem loadMapItem = new JMenuItem("Load Map");
		Map.add(loadMapItem);

		/**
		 * Action Bar setup
		 */

		JPanel actionBar = new JPanel();
		actionBar.setMinimumSize(new Dimension(100, 100));
		actionBar.setPreferredSize(new Dimension(125, 600));
		actionBar.setMaximumSize(new Dimension(150, 1080));
		actionBar.setLayout(new BoxLayout(actionBar, BoxLayout.Y_AXIS));
		contentPanel.add(actionBar, BorderLayout.EAST);

		Button toggleRunButton = new Button("Pause Sim");
		toggleRunButton.setMinimumSize(BUTTON_MIN);
		toggleRunButton.setPreferredSize(BUTTON_PREF);
		toggleRunButton.setMaximumSize(BUTTON_MAX);
		actionBar.add(toggleRunButton);
		toggleRunButton.addActionListener(new ActionListener() {
			/**
			 * Toggles the run/pause mechanic with the simulator. Should not
			 * pause rendering or effect anything else.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				sim.setPaused(!sim.isPaused());
				if (sim.isPaused()) {
					toggleRunButton.setLabel("Run Sim");
				} else {
					toggleRunButton.setLabel("Pause Sim");
				}
			}

		});

		Button fluffButton = new Button("Fluff");
		fluffButton.setMinimumSize(BUTTON_MIN);
		fluffButton.setPreferredSize(BUTTON_PREF);
		fluffButton.setMaximumSize(BUTTON_MAX);
		actionBar.add(fluffButton);

		Button fluffButton2 = new Button("Fluff2");
		fluffButton2.setMinimumSize(BUTTON_MIN);
		fluffButton2.setPreferredSize(BUTTON_PREF);
		fluffButton2.setMaximumSize(BUTTON_MAX);
		actionBar.add(fluffButton2);

		this.pack();
		// So the sim starts with keyboard focus for the main window
		viewer.requestFocusInWindow();

		this.addKeyListener(new ViewerMovement(viewer));
		this.setVisible(true);
	}

	public static final double ONE_SECOND = java.util.concurrent.TimeUnit.SECONDS.toNanos(1);
	public static boolean runSim = true;

	public static void runLoop(Simulation sim) {
		viewer.shouldPaint = true;
		SimTime timer = new SimTime();
		double desiredFPS = 60.0;
		long sleep = (long) (ONE_SECOND / desiredFPS);
		try {
			while (runSim) {

				// tick and print fps
				long lastTick = System.nanoTime();
				timer.tick();
				// System.out.println(timer.getFPS());

				// input();
				update();

				// sleep
				long wake = lastTick + sleep;
				do {
					Thread.sleep(0);
				} while (System.nanoTime() < wake);
			}
		} catch (InterruptedException e) {
			// oh someone paused the game
		}
	}

	private static void update() {
		sim.runTurn();
	}
}