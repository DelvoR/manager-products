package br.com.mateus.manager.products.view;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.commons.collections4.CollectionUtils;

import br.com.mateus.manager.products.controller.impl.ProdutoController;
import br.com.mateus.manager.products.model.entity.Endereco;
import br.com.mateus.manager.products.model.entity.Loja;
import br.com.mateus.manager.products.model.entity.Produto;

public class PesquisaProdutoView extends JInternalFrame {

	private static final long serialVersionUID = 8131107221329848008L;
	private static PesquisaProdutoView instance;
	private JTable tableProdutos;
	private JTextField txtCidade;
	private DefaultTableModel tableModel;
	private JTextField txtBairro;

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
		carregarDadosTabela();

	}

	static PesquisaProdutoView getInstance() {
		if (instance == null) {
			instance = new PesquisaProdutoView();
		}
		return instance;
	}

	private void initComponents() {
		setClosable(true);
		setBounds(100, 100, 878, 492);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 63, 862, 399);
		getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panelTableProduto = new JPanel();
		panelTableProduto.setBounds(0, 0, 862, 398);
		panel.add(panelTableProduto);
		panelTableProduto.setLayout(null);

		JScrollPane scrollPaneProduto = new JScrollPane();
		scrollPaneProduto.setBounds(10, 11, 852, 387);
		panelTableProduto.add(scrollPaneProduto);

		tableProdutos = new JTable();
		tableProdutos.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Raz\u00E3o Social", "Bairro",
				"Rua", "Cidade", "UF", "Descri\u00E7\u00E3o", "Quantidade", "Pre\u00E7o" }) {
			private static final long serialVersionUID = -6932124133860570521L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		scrollPaneProduto.setViewportView(tableProdutos);

		JPanel panelPesquisa = new JPanel();
		panelPesquisa.setBounds(0, 0, 862, 64);
		getContentPane().add(panelPesquisa);
		panelPesquisa.setLayout(null);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCidade.setBounds(99, 11, 60, 23);
		panelPesquisa.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setBounds(163, 11, 171, 23);
		txtCidade.setColumns(10);
		panelPesquisa.add(txtCidade);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(actionPerformedBtnPesquisar());
		btnPesquisar.setBounds(618, 11, 119, 23);
		panelPesquisa.add(btnPesquisar);

		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(408, 11, 171, 23);
		panelPesquisa.add(txtBairro);

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBairro.setBounds(344, 11, 60, 23);
		panelPesquisa.add(lblBairro);
	}

	private ActionListener actionPerformedBtnPesquisar() {
		return actionEvent -> {
			tableModel = (DefaultTableModel) tableProdutos.getModel();
			TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tableModel);
			tableProdutos.setRowSorter(rowSorter);

			List<RowFilter<Object, Object>> filters = new ArrayList<>();
			filters.add(RowFilter.regexFilter("(?i)" + getCidade()));
			filters.add(RowFilter.regexFilter("(?i)" + getBairro()));
			RowFilter<Object, Object> filter = RowFilter.andFilter(filters);
			rowSorter.setRowFilter(filter);
		};
	}

	private String getBairro() {
		return txtBairro.getText();
	}

	private String getCidade() {
		return txtCidade.getText();
	}

	private void carregarDadosTabela() {
		ProdutoController produtoController = new ProdutoController();
		List<Produto> produtos = produtoController.buscarTodos();
		tableModel = (DefaultTableModel) tableProdutos.getModel();

		if (CollectionUtils.isNotEmpty(produtos)) {
			for (Produto produto : produtos) {
				Loja loja = produto.getLoja();
				Endereco endereco = loja.getEndereco();
				tableModel.addRow(new Object[] { loja.getRazaoSocial(), endereco.getBairro(), endereco.getRua(),
						endereco.getCidade(), endereco.getUf(), produto.getDescricao(),
						produto.getQuantidade(), produto.getValor() });
			}
		}
	}
}
