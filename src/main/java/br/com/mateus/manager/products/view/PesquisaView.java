package br.com.mateus.manager.products.view;

import javax.swing.*;
import java.awt.*;

public class PesquisaView extends JInternalFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = -8163735968499630750L;
	private static PesquisaView instance;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				PesquisaView frame = new PesquisaView();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private PesquisaView() {
		setBounds(100, 100, 687, 417);

	}

	public static PesquisaView getInstance() {
		if (instance == null) {
			instance = new PesquisaView();
		}
		return instance;
	}

}
