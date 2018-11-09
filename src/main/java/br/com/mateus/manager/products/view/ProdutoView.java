package br.com.mateus.manager.products.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

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

		JButton button = new JButton("Novo");
		button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button.setBounds(10, 21, 123, 33);
		panel.add(button);

		JButton button_1 = new JButton("Editar");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_1.setBounds(209, 21, 123, 33);
		panel.add(button_1);

		JButton button_2 = new JButton("Excluir");
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_2.setBounds(401, 21, 124, 33);
		panel.add(button_2);

		JButton button_3 = new JButton("Pesquisar");
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button_3.setBounds(594, 21, 123, 33);
		panel.add(button_3);

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
