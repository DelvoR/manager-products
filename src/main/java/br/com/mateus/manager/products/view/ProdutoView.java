package br.com.mateus.manager.products.view;

import br.com.mateus.manager.products.controller.impl.ProdutoController;
import br.com.mateus.manager.products.model.entity.Produto;
import br.com.mateus.manager.products.model.enums.Operacao;
import br.com.mateus.manager.products.utils.ColumnTitle;
import br.com.mateus.manager.products.utils.JOptionUtils;
import br.com.mateus.manager.products.utils.Title;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import static br.com.mateus.manager.products.utils.JOptionUtils.clearFields;
import static br.com.mateus.manager.products.utils.JOptionUtils.openFields;

import java.awt.event.ActionEvent;

public class ProdutoView extends JInternalFrame {

	private static final long serialVersionUID = 2538628797157567245L;
	private static final int NO_SELECTED_ROWS = -1;
	private static final int NO_ROWS = 0;
	private static ProdutoView instance;
	private DefaultTableModel tableModel;
	private Integer operacao;
	private Long produtoId;

	private JTable tableProduto;
	private JButton btnNovo;
	private JButton btnExcluir;
	private JButton btnPesquisa;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnEditar;
	private JButton btnPesquisar;
	private JPanel panelAcoesComplementares;
	private JTextField txtNomeProduto;
	private JTextField txtQuantidade;
	private JTextField txtPreco;

	/**
	 * Create the frame.
	 */
	private ProdutoView() {
		initComponents();
		carregarDadosTabela();

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ProdutoView frame = new ProdutoView();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	static ProdutoView getInstance() {
		if (instance == null) {
			instance = new ProdutoView();
		}
		return instance;
	}

	private void initComponents() {
		setTitle("Produtos");
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 744, 474);
		getContentPane().setLayout(null);

		JPanel panelAcoes = new JPanel();
		panelAcoes.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelAcoes.setBounds(0, 0, 736, 50);
		getContentPane().add(panelAcoes);
		GridBagLayout gridPanelAcoes = new GridBagLayout();
		gridPanelAcoes.columnWidths = new int[]{123, 123, 124, 123, 123, 0};
		gridPanelAcoes.rowHeights = new int[]{33, 0};
		gridPanelAcoes.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridPanelAcoes.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelAcoes.setLayout(gridPanelAcoes);

		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(actionPerformedBtnNovo());
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gridBtnNovo = new GridBagConstraints();
		gridBtnNovo.fill = GridBagConstraints.BOTH;
		gridBtnNovo.insets = new Insets(0, 0, 0, 5);
		gridBtnNovo.gridx = 0;
		gridBtnNovo.gridy = 0;
		panelAcoes.add(btnNovo, gridBtnNovo);

		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(actionPerformedBntEditar());
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gridBtnEditar = new GridBagConstraints();
		gridBtnEditar.fill = GridBagConstraints.BOTH;
		gridBtnEditar.insets = new Insets(0, 0, 0, 5);
		gridBtnEditar.gridx = 1;
		gridBtnEditar.gridy = 0;
		panelAcoes.add(btnEditar, gridBtnEditar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(actionPerformedBtnExcluir());
		btnExcluir.setEnabled(false);
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gridBtnExcluir = new GridBagConstraints();
		gridBtnExcluir.fill = GridBagConstraints.BOTH;
		gridBtnExcluir.insets = new Insets(0, 0, 0, 5);
		gridBtnExcluir.gridx = 2;
		gridBtnExcluir.gridy = 0;
		panelAcoes.add(btnExcluir, gridBtnExcluir);

		btnPesquisa = new JButton("Pesquisa");
		btnPesquisa.addActionListener(actionPerformedBtnPesquisa());
		btnPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gridBtnPesquisa = new GridBagConstraints();
		gridBtnPesquisa.fill = GridBagConstraints.BOTH;
		gridBtnPesquisa.insets = new Insets(0, 0, 0, 5);
		gridBtnPesquisa.gridx = 3;
		gridBtnPesquisa.gridy = 0;
		panelAcoes.add(btnPesquisa, gridBtnPesquisa);

		JButton btnLojas = new JButton("Lojas");
		btnLojas.setEnabled(false);
		btnLojas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gridBtnLojas = new GridBagConstraints();
		gridBtnLojas.fill = GridBagConstraints.BOTH;
		gridBtnLojas.gridx = 4;
		gridBtnLojas.gridy = 0;
		panelAcoes.add(btnLojas, gridBtnLojas);

		JPanel panelTableProduto = new JPanel();
		panelTableProduto.setBounds(10, 253, 707, 189);
		getContentPane().add(panelTableProduto);
		panelTableProduto.setLayout(null);

		JScrollPane scrollPaneProduto = new JScrollPane();
		scrollPaneProduto.setBounds(10, 11, 687, 167);
		panelTableProduto.add(scrollPaneProduto);

		tableProduto = new JTable();
		tableProduto.addMouseListener(mouseClickedProduto());

		tableProduto.setModel(new DefaultTableModel(new Object[][]{},
				new String[]{"C\u00F3digo", "Descri\u00E7\u00E3o", "Quantidade", "Pre\u00E7o"}));
		scrollPaneProduto.setViewportView(tableProduto);

		JPanel panelProduto = new JPanel();
		panelProduto.setBorder(
				new TitledBorder(null, "Dados do Produto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelProduto.setBounds(10, 61, 578, 154);
		getContentPane().add(panelProduto);
		panelProduto.setLayout(null);

		JLabel lblNomeProduto = new JLabel("Nome Produto:");
		lblNomeProduto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomeProduto.setBounds(10, 22, 88, 20);
		panelProduto.add(lblNomeProduto);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQuantidade.setBounds(10, 50, 88, 20);
		panelProduto.add(lblQuantidade);

		JLabel lblPreo = new JLabel("Pre\u00E7o:");
		lblPreo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreo.setBounds(49, 81, 49, 20);
		panelProduto.add(lblPreo);

		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategoria.setBounds(250, 50, 88, 20);
		panelProduto.add(lblCategoria);

		JLabel lblSubcategoria = new JLabel("Sub-Categoria:");
		lblSubcategoria.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubcategoria.setBounds(250, 78, 88, 20);
		panelProduto.add(lblSubcategoria);

		txtNomeProduto = new JTextField();
		txtNomeProduto.setEnabled(false);
		txtNomeProduto.setEditable(false);
		txtNomeProduto.setBounds(108, 22, 460, 20);
		panelProduto.add(txtNomeProduto);
		txtNomeProduto.setColumns(10);

		txtQuantidade = new JTextField();
		txtQuantidade.setEnabled(false);
		txtQuantidade.setEditable(false);
		txtQuantidade.setColumns(10);
		txtQuantidade.setBounds(108, 50, 132, 20);
		panelProduto.add(txtQuantidade);

		txtPreco = new JTextField();
		txtPreco.setEditable(false);
		txtPreco.setEnabled(false);
		txtPreco.setBounds(108, 81, 132, 20);
		panelProduto.add(txtPreco);
		txtPreco.setColumns(10);

		JComboBox comboBoxCategoria = new JComboBox();
		comboBoxCategoria.setBounds(348, 50, 220, 20);
		panelProduto.add(comboBoxCategoria);

		JComboBox comboBoxSubCategoria = new JComboBox();
		comboBoxSubCategoria.setBounds(348, 78, 220, 20);
		panelProduto.add(comboBoxSubCategoria);

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(actionPerformedBtnPesquisar());
		btnPesquisar.setEnabled(false);
		btnPesquisar.setBounds(479, 120, 89, 23);
		panelProduto.add(btnPesquisar);

		panelAcoesComplementares = new JPanel();
		panelAcoesComplementares.setLayout(null);
		panelAcoesComplementares.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelAcoesComplementares.setBounds(598, 61, 119, 116);
		getContentPane().add(panelAcoesComplementares);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(actionPerformedBtnSalvar());

		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSalvar.setBounds(12, 21, 97, 33);
		panelAcoesComplementares.add(btnSalvar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(actionPerformedBtnCancelar());
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(12, 65, 97, 33);
		panelAcoesComplementares.add(btnCancelar);
	}

	private ActionListener actionPerformedBtnPesquisar() {
		return actionEvent -> {
			tableModel = (DefaultTableModel) tableProduto.getModel();
			TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tableModel);
			tableProduto.setRowSorter(rowSorter);

			String nomeProduto = txtNomeProduto.getText();
			if (StringUtils.isNotBlank(nomeProduto)) {
				rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + nomeProduto));
			} else {
				rowSorter.setRowFilter(null);
			}
		};
	}

	private ActionListener actionPerformedBtnPesquisa() {
		return actionEvent -> {
			JOptionUtils.openFields(true, btnCancelar, txtNomeProduto, btnPesquisar);
			JOptionUtils.openFields(false, btnNovo, btnExcluir, btnSalvar, btnEditar);
		};
	}

	private ActionListener actionPerformedBtnExcluir() {
		return actionEvent -> {
			operacao = Operacao.EXCLUIR.getOperacao();

			if (tableProduto.getSelectedRow() == NO_SELECTED_ROWS) {
				if (tableProduto.getRowCount() == NO_ROWS) {
					JOptionUtils.mostrarMensagem("A tablea est\u00e1 vazia!");
				} else {
					JOptionUtils.mostrarMensagem("Deve ser selecionado um produto!");
				}
			} else {
				JOptionUtils.openFields(false, btnNovo, btnSalvar);
				JOptionUtils.openFields(true, panelAcoesComplementares, btnCancelar);
				excluirDados();
				voltarTelaParaSituacaoParaInicial();
			}
		};
	}

	private ActionListener actionPerformedBntEditar() {
		return actionEvent -> {
			operacao = Operacao.EDITAR.getOperacao();

			if (tableProduto.getSelectedRow() == NO_SELECTED_ROWS) {
				if (tableProduto.getRowCount() == NO_ROWS) {
					JOptionUtils.mostrarMensagem("A tabela est\u00e1 vaiza!");
				} else {
					JOptionUtils.mostrarMensagem("Deve ser selecionado um produto!");
				}
			} else {
				JOptionUtils.openFields(false, btnExcluir, btnNovo, btnPesquisa);
				JOptionUtils.openFields(true, panelAcoesComplementares, btnSalvar, txtNomeProduto, txtQuantidade, txtPreco, btnCancelar);
			}
		};
	}

	private ActionListener actionPerformedBtnCancelar() {
		return actionEvent -> voltarTelaParaSituacaoParaInicial();
	}

	private ActionListener actionPerformedBtnSalvar() {
		return actionEvent -> {
			Produto produto = new Produto.Builder().descricao(txtNomeProduto.getText())
					.quantidade(Double.parseDouble(txtQuantidade.getText()))
					.valor(Double.parseDouble(txtPreco.getText()))
					.build();

			if (StringUtils.isNotBlank(produto.getDescricao())) {
				ProdutoController produtoController = new ProdutoController();
				try {
					if (operacao.equals(Operacao.NOVO.getOperacao())) {
						boolean resultado = produtoController.cadastrar(produto);
						if (resultado) {
							tableModel.addRow(new Object[]{produto.getId(), produto.getDescricao(),
									produto.getDescricao(), produto.getValor()});
							JOptionUtils.mostrarInformacao(
									"Produto " + produto.getDescricao() + " cadastrado com sucesso!",
									Title.INFORMACAO);
						} else if (operacao.equals(Operacao.EDITAR.getOperacao())) {
							produtoController.atualizar(produto);

						}
					}
				} catch (Exception e) {
					throw e;
				}
			} else {
				JOptionUtils.mostrarMensagem("Descicao obrigatoria");
			}
			voltarTelaParaSituacaoParaInicial();
		};
	}

	private MouseAdapter mouseClickedProduto() {
		return new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				ListSelectionModel selectionModel = tableProduto.getSelectionModel();
				tableProduto.setSelectionModel(selectionModel);
				JOptionUtils.setValueToFieldFromTable(tableProduto, ColumnTitle.DESCRICAO, txtNomeProduto);
				JOptionUtils.setValueToFieldFromTable(tableProduto, ColumnTitle.QUANTIDADE, txtQuantidade);
				JOptionUtils.setValueToFieldFromTable(tableProduto, ColumnTitle.PRECO, txtPreco);

				// produtoId = Long.valueOf(JOptionUtils.getValueFromColumn(tableProduto,
				// ColumnTitle.ID));
				// ProdutoController produtoController = new ProdutoController();
				// produtoController.buscar(produtoId);

			}
		};
	}

	private ActionListener actionPerformedBtnNovo() {
		return actionEvent -> {
			operacao = Operacao.NOVO.getOperacao();
			JOptionUtils.openFields(false, btnEditar, btnExcluir, btnPesquisa, btnNovo, btnPesquisar);
			JOptionUtils.openFields(true, txtNomeProduto, txtQuantidade, txtPreco, btnSalvar, btnCancelar, panelAcoesComplementares);
			clearAllFields();
		};
	}

	private void carregarDadosTabela() {
		ProdutoController produtoController = new ProdutoController();
		List<Produto> produtos = produtoController.buscarTodos();

		tableProduto.getColumnModel().getColumn(0).setPreferredWidth(5);
		tableModel = (DefaultTableModel) tableProduto.getModel();

		for (Produto produto : produtos) {
			tableModel.addRow(new Object[]{
					produto.getId(),
					produto.getDescricao(),
					produto.getQuantidade(),
					produto.getValor()});
		}
	}

	private void excluirDados() {
		Long id = Long.valueOf(tableProduto.getValueAt(tableProduto.getSelectedRow(), 0).toString());
		String nomeProduto = tableProduto.getValueAt(tableProduto.getSelectedRow(), 1).toString();
		int opcaoEscolhida = JOptionUtils.yesOrNo(String.format("Desjea deletar o produto %s?", nomeProduto), Title.EXCLUIR);
		if (JOptionUtils.isTrue(opcaoEscolhida)) {
			ProdutoController produtoController = new ProdutoController();
			produtoController.excluir(id);

			tableModel.removeRow(tableProduto.getSelectedRow());
			JOptionUtils.mostrarInformacao(String.format("O produto (id: %d) foi removido!", id), Title.INFORMACAO);
		}
	}

	private void voltarTelaParaSituacaoParaInicial() {
		openFields(false, btnEditar, btnExcluir, btnPesquisa, panelAcoesComplementares, btnSalvar, btnCancelar,
				txtNomeProduto, txtQuantidade, txtPreco);
		openFields(true, btnNovo, btnPesquisa);
		removerFiltroTabela();
		clearAllFields();
	}

	private void removerFiltroTabela() {
		tableModel = (DefaultTableModel) tableProduto.getModel();
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tableModel);
		tableProduto.setRowSorter(rowSorter);
		rowSorter.setRowFilter(null);
	}

	private void clearAllFields() {
		clearFields(txtNomeProduto, txtQuantidade, txtPreco);
	}
}
