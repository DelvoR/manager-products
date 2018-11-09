package br.com.mateus.manager.products.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6823654718026202464L;
	private JPanel contentPane;
	private JDesktopPane desktop;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
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
	public MainView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 972, 692);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		desktop = new JDesktopPane();
		desktop.setBackground(SystemColor.activeCaptionBorder);
		desktop.setBounds(0, 65, 966, 598);
		contentPane.add(desktop);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 966, 66);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnProdutos = new JButton("Produtos");
		btnProdutos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutoView produtoView = new ProdutoView();
				desktop.add(produtoView);
				desktop.setVisible(true);
				produtoView.setVisible(true);
			}
		});
		btnProdutos.setBounds(327, 11, 111, 44);
		panel.add(btnProdutos);

		JButton btnLojas = new JButton("Lojas");
		btnLojas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LojaView lojaView = new LojaView();
				desktop.add(lojaView);
				desktop.setVisible(true);
				lojaView.setVisible(true);
			}
		});
		btnLojas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLojas.setBounds(206, 11, 111, 44);
		panel.add(btnLojas);

		JButton btnUsurios = new JButton("Usu\u00E1rios");
		btnUsurios.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUsurios.setBounds(654, 11, 111, 44);
		panel.add(btnUsurios);

		JButton btnPesquisaAvanada = new JButton("Pesquisa Avan\u00E7ada");
		btnPesquisaAvanada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PesquisaView pesquisaView = new PesquisaView();
				desktop.add(pesquisaView);
				desktop.setVisible(true);
				pesquisaView.setVisible(true);
			}
		});
		btnPesquisaAvanada.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPesquisaAvanada.setBounds(448, 11, 196, 44);
		panel.add(btnPesquisaAvanada);
	}
}
