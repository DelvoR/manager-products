package br.com.mateus.manager.products.utils;

import javax.swing.*;

public class JanelaUtils {

	private static JDesktopPane desktop;

	@SuppressWarnings("static-access")
	public JanelaUtils(JDesktopPane desktop) {
		this.desktop = desktop;
	}

	public void abrirInternalFrame(JInternalFrame internalFrame) {
		if (internalFrame.isVisible()) {
			internalFrame.toFront();
			internalFrame.requestFocus();
		} else {
			desktop.add(internalFrame);
			internalFrame.setVisible(true);
		}
	}

}
