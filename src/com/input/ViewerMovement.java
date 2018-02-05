package com.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.gui.SimViewerPanel;

public class ViewerMovement implements KeyListener {

	private SimViewerPanel viewer;

	public ViewerMovement(SimViewerPanel viewer) {
		this.viewer = viewer;
	}

	public float traversalSpeed = 1f;

	@Override
	public void keyTyped(KeyEvent e) {
		char key = e.getKeyChar();
		if (key == '=') {
			// plus
			traversalSpeed++;
			if (traversalSpeed < 1) {
				traversalSpeed = 1;
			}
		}
		if (key == '-') {
			// minus
			traversalSpeed--;
			if (traversalSpeed < 1) {
				traversalSpeed = 1;
			}
		}
		// Zoom in
		if (e.getKeyChar() == '-') {
			viewer.standardUnit = (int) Math.round(viewer.standardUnit * 2);
			if (viewer.standardUnit < 2) {
				viewer.standardUnit = 2;
			}
		}
		// Zoom out
		if (e.getKeyChar() == '+') {
			viewer.standardUnit = (int) Math.round(viewer.standardUnit / 2);
			if (viewer.standardUnit < 2) {
				viewer.standardUnit = 2;
			}
		}
		System.out.println("Standard Unit: " + viewer.standardUnit + "\nTraversal Speed: " + traversalSpeed);
		viewer.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		char key = e.getKeyChar();
		if (key == 'w' || key == KeyEvent.VK_UP) {
			viewer.renderY += traversalSpeed;
		}
		if (key == 'd' || key == KeyEvent.VK_DOWN) {
			viewer.renderX -= traversalSpeed;
		}
		if (key == 's' || key == KeyEvent.VK_RIGHT) {
			viewer.renderY -= traversalSpeed;
		}
		if (key == 'a' || key == KeyEvent.VK_LEFT) {
			viewer.renderX += traversalSpeed;
		}
		System.out.println("RenderX: " + viewer.renderX + "\nRenderY: " + viewer.renderY);
		viewer.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
