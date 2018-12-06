package br.com.mateus.manager.products.view;

import br.com.mateus.manager.products.connection.ConnectionFactory;
import br.com.mateus.manager.products.utils.JanelaUtils;
import org.apache.commons.io.IOUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainView extends JFrame {

	private static final long serialVersionUID = -6823654718026202464L;
	private static JDesktopPane desktop;
	private JanelaUtils janelaUtils;

	private MainView() {
		setBackground(Color.WHITE);
		setTitle("Gerenciador de Produtos / Lojas");
		initComponents();
		janelaUtils = new JanelaUtils(desktop);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		try {
			lblNewLabel.setIcon(new ImageIcon(IOUtils.resourceToURL("/images/main-view-background.png")));
		} catch (IOException ignored) {
		}
		lblNewLabel.setBounds(10, 11, 1115, 612);
		desktop.add(lblNewLabel);
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
		try {
			setIconImage(Toolkit.getDefaultToolkit().getImage(IOUtils.resourceToURL("/images/main-view.png")));
		} catch (IOException ignored) {
		}
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1141, 692);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		desktop = new JDesktopPane();
		desktop.setBackground(Color.WHITE);
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
		try {
			btnLojas.setIcon(new ImageIcon(IOUtils.resourceToURL("/images/lojas.png")));
		} catch (IOException ignored) {
		}
		btnLojas.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLojas.addActionListener(actionPerformedBtnLojas());
		menuBar.add(btnLojas);

		JButton btnPesquisarProdutosELojas = new JButton("Pesquisar Produtos / Lojas");
		try {
			btnPesquisarProdutosELojas.setIcon(new ImageIcon(IOUtils.resourceToURL("/images/pesquisar-lojas.png")));
		} catch (IOException ignored) {
		}
		btnPesquisarProdutosELojas.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPesquisarProdutosELojas.addActionListener(actionPerformedBtnPesquisarProdutos());
		menuBar.add(btnPesquisarProdutosELojas);
	}

	private ActionListener actionPerformedBtnLojas() {
		return actionEvent -> janelaUtils.abrirInternalFrame(LojaView.getInstance());
	}

	private ActionListener actionPerformedBtnPesquisarProdutos() {
		return actionEvent -> janelaUtils.abrirInternalFrame(PesquisaProdutoView.getInstance());
	}
}
