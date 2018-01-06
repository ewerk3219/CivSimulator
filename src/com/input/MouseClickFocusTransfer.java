package com.input;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 
 * @author Ewerk4
 *
 *         When mouse is clicked on the component, the component will request
 *         focus.
 */
public class MouseClickFocusTransfer implements MouseListener {

	private Component comp;

	public MouseClickFocusTransfer(Component comp) {
		this.comp = comp;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (comp.contains(e.getPoint())) {
			comp.requestFocusInWindow();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
