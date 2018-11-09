package br.com.mateus.manager.products.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class PesquisaView extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PesquisaView frame = new PesquisaView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
