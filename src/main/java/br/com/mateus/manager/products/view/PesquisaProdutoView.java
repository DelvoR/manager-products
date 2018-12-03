package br.com.mateus.manager.products.view;

import java.awt.EventQueue;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.mateus.manager.products.model.entity.Produto;
import br.com.mateus.manager.products.utils.JanelaUtils;
import org.apache.commons.collections4.CollectionUtils;

public class PesquisaProdutoView extends JInternalFrame {

	private static final long serialVersionUID = 8131107221329848008L;
	private static PesquisaProdutoView instance;
	private JTable tableProdutos;
	private JTextField txtDescricao;
	private DefaultTableModel tableModel;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				PesquisaProdutoView frame = new PesquisaProdutoView();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private PesquisaProdutoView() {
		initComponents();

	}

	static PesquisaProdutoView getInstance() {
		if (instance == null) {
			instance = new PesquisaProdutoView();
		}
		return instance;
	}

	private void initComponents() {
		setClosable(true);
		setBounds(100, 100, 609, 409);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 63, 593, 316);
		getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panelTableProduto = new JPanel();
		panelTableProduto.setBounds(0, 0, 593, 316);
		panel.add(panelTableProduto);
		panelTableProduto.setLayout(null);

		JScrollPane scrollPaneProduto = new JScrollPane();
		scrollPaneProduto.setBounds(10, 11, 573, 294);
		panelTableProduto.add(scrollPaneProduto);

		tableProdutos = new JTable();
		tableProdutos.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Descri\u00E7\u00E3o", "Quantidade", "Pre\u00E7o" }) {

			private static final long serialVersionUID = -3182412249823201336L;
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		tableProdutos.getColumnModel().getColumn(0).setResizable(false);
		tableProdutos.getColumnModel().getColumn(1).setResizable(false);
		tableProdutos.getColumnModel().getColumn(2).setResizable(false);
		scrollPaneProduto.setViewportView(tableProdutos);

		JPanel panelPesquisa = new JPanel();
		panelPesquisa.setBounds(0, 0, 593, 64);
		getContentPane().add(panelPesquisa);
		panelPesquisa.setLayout(null);

		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o:");
		lblDescricao.setBounds(24, 22, 60, 23);
		panelPesquisa.add(lblDescricao);

		txtDescricao = new JTextField();
		txtDescricao.setBounds(94, 22, 389, 23);
		txtDescricao.setColumns(10);
		panelPesquisa.add(txtDescricao);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(493, 22, 90, 23);
		panelPesquisa.add(btnPesquisar);
	}

}
