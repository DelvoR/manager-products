package br.com.mateus.manager.products.view;

import javax.swing.*;
import java.awt.*;

public class PesquisaView extends JInternalFrame {

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
	public PesquisaView() {
		setBounds(100, 100, 687, 417);

	}

}
