package br.com.mateus.manager.products.view;

import br.com.mateus.manager.products.controller.impl.LojaController;
import br.com.mateus.manager.products.exceptions.BuscarException;
import br.com.mateus.manager.products.model.entity.Endereco;
import br.com.mateus.manager.products.model.entity.Loja;
import br.com.mateus.manager.products.model.entity.Produto;
import br.com.mateus.manager.products.utils.JOptionUtils;
import br.com.mateus.manager.products.utils.Title;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PesquisaProdutoView extends JInternalFrame {

	private static final long serialVersionUID = 8131107221329848008L;
	private static PesquisaProdutoView instance;
	private JTable tableProdutos;
	private JTextField txtCidade;
	private DefaultTableModel tableModel;
	private JTextField txtBairro;

	private PesquisaProdutoView() {
		try {
			setFrameIcon(new ImageIcon(IOUtils.resourceToURL("/images/pesquisa.png")));
		} catch (IOException ignored) {
		}
		setIconifiable(true);
		setTitle("Pesquisa Avan\u00E7ada");
		initComponents();
		carregarDadosTabela();

	}

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
		tableProdutos.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Raz\u00E3o Social", "Bairro",
				"Rua", "Cidade", "UF", "Descri\u00E7\u00E3o", "Quantidade", "Pre\u00E7o"}) {
			private static final long serialVersionUID = -6932124133860570521L;
			boolean[] columnEditables = new boolean[]{false, false, false, false, false, false};

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
		lblCidade.setBounds(106, 11, 60, 23);
		panelPesquisa.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setBounds(170, 11, 171, 23);
		txtCidade.setColumns(10);
		panelPesquisa.add(txtCidade);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(actionPerformedBtnPesquisar());
		btnPesquisar.setBounds(625, 11, 119, 23);
		panelPesquisa.add(btnPesquisar);

		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(415, 11, 171, 23);
		panelPesquisa.add(txtBairro);

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBairro.setBounds(351, 11, 60, 23);
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

	private void carregarDadosTabela() {
		LojaController lojaController = new LojaController();
		try {
			List<Loja> lojas = lojaController.buscarTodos();
			tableModel = (DefaultTableModel) tableProdutos.getModel();

			for (Loja loja : lojas) {
				if (CollectionUtils.isNotEmpty(lojas)) {
					for (Produto produto : loja.getProdutos()) {
						Endereco endereco = loja.getEndereco();
						tableModel.addRow(new Object[]{loja.getRazaoSocial(), endereco.getBairro(), endereco.getRua(),
								endereco.getCidade(), endereco.getUf(), produto.getDescricao(),
								produto.getQuantidade(), produto.getValor()});
					}
				}
			}
		} catch (BuscarException e) {
			JOptionUtils.mostrarErro(e.getMessage() + "\nMotivo:" + e.getCause(), Title.ERRO);
		}
	}

	private String getBairro() {
		return txtBairro.getText();
	}

	private String getCidade() {
		return txtCidade.getText();
	}
}
