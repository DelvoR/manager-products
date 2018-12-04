package br.com.mateus.manager.products.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.mateus.manager.products.connection.ConnectionFactory;
import br.com.mateus.manager.products.utils.JanelaUtils;

public class MainView extends JFrame {

	private static final long serialVersionUID = -6823654718026202464L;
	private static JDesktopPane desktop;
	private JanelaUtils janelaUtils;

	private MainView() {
		initComponents();
		janelaUtils = new JanelaUtils(desktop);
		initConnection();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				MainView frame = new MainView();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	static JDesktopPane getDesktop() {
		return desktop;
	}

	private void initComponents() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1141, 692);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		desktop = new JDesktopPane();
		desktop.setBackground(SystemColor.activeCaptionBorder);
		desktop.setBounds(0, 29, 1135, 634);
		contentPane.add(desktop);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1135, 29);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1136, 30);
		panel.add(menuBar);
		
		JButton btnLojas = new JButton("Lojas");
		btnLojas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLojas.addActionListener(actionPerformedLojas());
		menuBar.add(btnLojas);
		
		JButton btnPesquisarLojas = new JButton("Pesquisar Lojas");
		btnPesquisarLojas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPesquisarLojas.addActionListener(actionPerformedBtnPesquisarProdutos());
		menuBar.add(btnPesquisarLojas);
	}

	private ActionListener actionPerformedLojas() {
		return actionEvent -> janelaUtils.abrirInternalFrame(LojaView.getInstance());
	}

	private ActionListener actionPerformedBtnPesquisarProdutos() {
		return actionEvent -> janelaUtils.abrirInternalFrame(PesquisaProdutoView.getInstance());
	}

	private void initConnection() {
		ConnectionFactory.getEntityManager();
	}
}
