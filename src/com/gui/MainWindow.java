package com.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.Simulation;
import com.input.MouseClickFocusTransfer;
import com.input.ViewerMovement;
import com.map.core.SimMap;
import com.util.SimTime;
import java.awt.Font;

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
	public static final Dimension MENU_BAR_PREF = new Dimension(800, 50);
	public static final Dimension ACTION_PANEL_MIN = new Dimension(100, 100);
	public static final Dimension ACTION_PANEL_PREF = new Dimension(140, 600);
	public static final Dimension ACTION_PANEL_MAX = new Dimension(150, 1080);

	public static final Font STANDARD_FONT = new Font("Corbel", Font.BOLD, 16);

	// Action Commands
	public static final String CREATE_NEW_MAP = "New Map";
	public static final String LOAD_MAP = "Load Map";

	public static SimViewerPanel viewer;
	public static Simulation sim;

	public MainWindow(Simulation sim) {
		MainWindow.sim = sim;
		this.setSize(MAIN_WINDOW_SIZE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("res/crown.png"));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(600, 400));
		this.setPreferredSize(new Dimension(1200, 800));
		this.setMaximumSize(new Dimension(1920, 1080));

		// Listener for all action commands
		MainWindowListener mainWindowListener = new MainWindowListener();

		Panel contentPanel = new Panel();
		contentPanel.setPreferredSize(MAIN_WINDOW_SIZE);
		this.setContentPane(contentPanel);
		contentPanel.setLayout(new BorderLayout());

		viewer = new SimViewerPanel();
		viewer.setMinimumSize(new Dimension(200, 100));
		viewer.setPreferredSize(new Dimension(700, 600));
		viewer.setMaximumSize(new Dimension(1920, 1080));
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
		menuBar.setPreferredSize(MENU_BAR_PREF);
		menuBar.putClientProperty("JComponent.sizeVariant", "large");
		contentPanel.add(menuBar, BorderLayout.NORTH);

		JMenu map = new JMenu("Map\r\n");
		map.setFont(STANDARD_FONT);
		map.setBackground(Color.WHITE);
		map.putClientProperty("JComponent.sizeVariant", "large");
		menuBar.add(map);

		JMenuItem newMapItem = new JMenuItem("New Map\r\n");
		newMapItem.setFont(STANDARD_FONT);
		newMapItem.setActionCommand(MainWindow.CREATE_NEW_MAP);
		newMapItem.addActionListener(mainWindowListener);
		map.add(newMapItem);

		JMenuItem loadMapItem = new JMenuItem("Load Map");
		loadMapItem.setFont(STANDARD_FONT);
		loadMapItem.addActionListener(mainWindowListener);
		map.add(loadMapItem);

		/**
		 * Action Bar setup
		 */

		JPanel actionBar = new JPanel();
		actionBar.setMinimumSize(ACTION_PANEL_MIN);
		actionBar.setPreferredSize(ACTION_PANEL_PREF);
		actionBar.setMaximumSize(ACTION_PANEL_MAX);
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

		DesignerPanel designPanel = new DesignerPanel(viewer);
		contentPanel.add(designPanel, BorderLayout.WEST);
		this.pack();
		// Setting the clipping area of the viewer
		viewer.getGraphics().setClip(-viewer.standardUnit, -viewer.standardUnit,
				viewer.getWidth() + viewer.standardUnit * 2,
				viewer.getHeight() + viewer.standardUnit * 2);
		// Reset the clipping area when the window is resized
		viewer.addComponentListener(new ComponentListener() {

			@Override
			public void componentResized(ComponentEvent e) {
				viewer.getGraphics().setClip(-viewer.standardUnit, -viewer.standardUnit,
						viewer.getWidth() + viewer.standardUnit * 2,
						viewer.getHeight() + viewer.standardUnit * 2);

			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

		});
		// So the sim starts with keyboard focus for the main window
		viewer.requestFocusInWindow();

		SwingUtilities.updateComponentTreeUI(this);
		this.addKeyListener(new ViewerMovement(viewer));
		this.setVisible(true);
	}

	private class MainWindowListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals(MainWindow.CREATE_NEW_MAP)) {
				JFrame newMapFrame = new JFrame();
				newMapFrame.setMinimumSize(new Dimension(200, 125));
				newMapFrame.setPreferredSize(new Dimension(200, 125));
				newMapFrame.setMaximumSize(new Dimension(200, 125));

				JPanel newMapPanel = new JPanel();
				newMapPanel.setBackground(Color.red);
				newMapPanel.setLayout(new FlowLayout());

				// These texts fields are janky since they allow all characters
				JTextField mapWidth = new JTextField();
				mapWidth.setToolTipText("Map Width");
				mapWidth.setMinimumSize(BUTTON_PREF);
				mapWidth.setPreferredSize(BUTTON_MAX);
				mapWidth.setMaximumSize(BUTTON_MAX);
				// Janky text field here!
				JTextField mapHeight = new JTextField();
				mapHeight.setToolTipText("Map Height");
				mapHeight.setMinimumSize(BUTTON_PREF);
				mapHeight.setPreferredSize(BUTTON_MAX);
				mapHeight.setMaximumSize(BUTTON_MAX);

				JButton submit = new JButton("Submit");
				submit.setMinimumSize(BUTTON_PREF);
				submit.setPreferredSize(BUTTON_MAX);
				submit.setMaximumSize(BUTTON_MAX);
				// This will check for when the submit button is hit and do
				// everything required afterward like closing this frame.
				submit.addActionListener(
						new MapSubmitListener(mapWidth, mapHeight, newMapFrame));

				newMapFrame.getContentPane().add(newMapPanel);
				newMapFrame.pack();
				newMapPanel.add(mapWidth);
				newMapPanel.add(mapHeight);
				newMapPanel.add(submit);
				newMapFrame.pack();
				newMapFrame.setVisible(true);
			}
			if (e.getActionCommand().equals(MainWindow.LOAD_MAP)) {
				// Nothing in here for now
			}
		}

		private class MapSubmitListener implements ActionListener {

			private JTextField mapWidth;
			private JTextField mapHeight;
			private JFrame newMapFrame;

			public MapSubmitListener(JTextField mapWidth, JTextField mapHeight,
					JFrame newMapFrame) {
				this.mapWidth = mapWidth;
				this.mapHeight = mapHeight;
				this.newMapFrame = newMapFrame;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				sim.map = new SimMap(Integer.parseInt(mapWidth.getText()),
						Integer.parseInt(mapHeight.getText()));
				viewer.repaint();
				newMapFrame.setVisible(false);
				newMapFrame.dispose(); // get rid of this panel now
			}

		}

	}

	public static final double ONE_SECOND = java.util.concurrent.TimeUnit.SECONDS
			.toNanos(1);
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