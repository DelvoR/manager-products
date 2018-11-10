package br.com.mateus.manager.products.view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProdutoView extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2538628797157567245L;
	private JTable tableProduto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProdutoView frame = new ProdutoView();
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
	public ProdutoView() {
		setTitle("Produtos");
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 744, 474);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(0, 0, 727, 78);
		getContentPane().add(panel);

        JButton btnNovo = new JButton("Novo");
        btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnNovo.setBounds(10, 21, 123, 33);
        panel.add(btnNovo);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnEditar.setBounds(209, 21, 123, 33);
        panel.add(btnEditar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnExcluir.setBounds(401, 21, 124, 33);
        panel.add(btnExcluir);

        JButton btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnPesquisar.setBounds(594, 21, 123, 33);
        panel.add(btnPesquisar);

		JPanel panelTableProduto = new JPanel();
		panelTableProduto.setBounds(10, 253, 707, 189);
		getContentPane().add(panelTableProduto);
		panelTableProduto.setLayout(null);

		JScrollPane scrollPaneProduto = new JScrollPane();
		scrollPaneProduto.setBounds(10, 11, 687, 167);
		panelTableProduto.add(scrollPaneProduto);

		tableProduto = new JTable();
		tableProduto.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "C\u00F3digo", "Descri\u00E7\u00E3o", "Quantidade", "Pre\u00E7o" }));
		scrollPaneProduto.setViewportView(tableProduto);

	}
}
