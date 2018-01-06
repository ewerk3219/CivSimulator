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
		}
		if (key == '-') {
			// minus
			traversalSpeed--;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("press!");
		char key = e.getKeyChar();
		if (key == 'w') {
			viewer.renderY -= traversalSpeed;
		}
		if (key == 'd') {
			viewer.renderX += traversalSpeed;
		}
		if (key == 's') {
			viewer.renderY += traversalSpeed;
		}
		if (key == 'a') {
			viewer.renderX -= traversalSpeed;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
