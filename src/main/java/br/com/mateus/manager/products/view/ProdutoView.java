package br.com.mateus.manager.products.view;

import br.com.mateus.manager.products.controller.impl.ProdutoController;
import br.com.mateus.manager.products.exceptions.*;
import br.com.mateus.manager.products.model.entity.Loja;
import br.com.mateus.manager.products.model.entity.Produto;
import br.com.mateus.manager.products.model.enums.Operacao;
import br.com.mateus.manager.products.utils.ColumnTitle;
import br.com.mateus.manager.products.utils.JOptionUtils;
import br.com.mateus.manager.products.utils.Title;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
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
import java.io.IOException;
import java.util.List;

public class ProdutoView extends JInternalFrame {

	private static final long serialVersionUID = 2538628797157567245L;
	private static Loja loja;
	private static List<Produto> produtos;
	private DefaultTableModel tableModel;
	private Integer operacao;
	private Long produtoId;
	private JTable tableProduto;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnPesquisar;
	private JPanel panelAcoesComplementares;
	private JTextField txtDescricao;
	private JTextField txtQuantidade;
	private JTextField txtPreco;
	private JButton btnNovo;
	private JButton btnEditar;
	private JButton btnExcluir;
	private ProdutoController produtoController = new ProdutoController();

	private ProdutoView() {
		try {
			setFrameIcon(new ImageIcon(IOUtils.resourceToURL("/images/produto.png")));
		} catch (IOException ignored) {
		}
		initComponents();
		carregarDadosTabela();
	}

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
		return new ProdutoView();
	}

	static void setProdutos(List<Produto> produtos) {
		ProdutoView.produtos = produtos;
	}

	static void setLoja(Loja loja) {
		ProdutoView.loja = loja;
	}

	private void initComponents() {
		setTitle("Produtos");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 744, 474);
		getContentPane().setLayout(null);

		JPanel panelTableProduto = new JPanel();
		panelTableProduto.setBounds(10, 149, 707, 293);
		getContentPane().add(panelTableProduto);
		panelTableProduto.setLayout(null);

		JScrollPane scrollPaneProduto = new JScrollPane();
		scrollPaneProduto.setBounds(10, 11, 687, 271);
		panelTableProduto.add(scrollPaneProduto);

		tableProduto = new JTable();
		tableProduto.addMouseListener(mouseClickedProduto());

		tableProduto.setModel(new DefaultTableModel(new Object[][]{},
				new String[]{"C\u00F3digo", "Descri\u00E7\u00E3o", "Quantidade", "Pre\u00E7o"}) {

			private static final long serialVersionUID = -6353508273228958006L;
			boolean[] columnEditables = new boolean[]{false, false, false, false, false, false};

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPaneProduto.setViewportView(tableProduto);

		JPanel panelProduto = new JPanel();
		panelProduto.setBorder(
				new TitledBorder(null, "Dados do Produto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelProduto.setBounds(10, 32, 578, 106);
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
		lblPreo.setBounds(250, 50, 49, 20);
		panelProduto.add(lblPreo);

		txtDescricao = new JTextField();
		txtDescricao.setBounds(108, 22, 460, 20);
		panelProduto.add(txtDescricao);
		txtDescricao.setColumns(10);

		txtQuantidade = new JTextField();
		txtQuantidade.setEnabled(false);
		txtQuantidade.setEditable(false);
		txtQuantidade.setColumns(10);
		txtQuantidade.setBounds(108, 50, 132, 20);
		panelProduto.add(txtQuantidade);

		txtPreco = new JTextField();
		txtPreco.setEditable(false);
		txtPreco.setEnabled(false);
		txtPreco.setBounds(309, 50, 132, 20);
		panelProduto.add(txtPreco);
		txtPreco.setColumns(10);

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(actionPerformedBtnPesquisar());
		btnPesquisar.setBounds(449, 72, 119, 23);
		panelProduto.add(btnPesquisar);

		panelAcoesComplementares = new JPanel();
		panelAcoesComplementares.setLayout(null);
		panelAcoesComplementares.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelAcoesComplementares.setBounds(598, 32, 119, 106);
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

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 728, 21);
		getContentPane().add(menuBar);

		btnNovo = new JButton("Novo");
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNovo.addActionListener(actionPerformedBtnNovo());
		menuBar.add(btnNovo);

		btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEditar.addActionListener(actionPerformedBntEditar());
		menuBar.add(btnEditar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExcluir.addActionListener(actionPerformedBtnExcluir());
		menuBar.add(btnExcluir);
	}

	private ActionListener actionPerformedBtnPesquisar() {
		return actionEvent -> {
			tableModel = (DefaultTableModel) tableProduto.getModel();
			TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tableModel);
			tableProduto.setRowSorter(rowSorter);

			String nomeProduto = txtDescricao.getText();
			if (StringUtils.isNotBlank(nomeProduto)) {
				rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + nomeProduto));
				JOptionUtils.openFields(true, btnCancelar, panelAcoesComplementares);
			} else {
				rowSorter.setRowFilter(null);
			}
		};
	}

	private ActionListener actionPerformedBtnExcluir() {
		return actionEvent -> {
			operacao = Operacao.EXCLUIR.getOperacao();

			try {
				JOptionUtils.linhaSelecionada(tableProduto);
				JOptionUtils.openFields(false, btnNovo, btnSalvar);
				JOptionUtils.openFields(true, panelAcoesComplementares, btnCancelar);
				excluirDados();

			} catch (TabelaVaziaException | LinhaNaoSelecionadaException e) {
				JOptionUtils.mostrarAlerta(e.getMessage(), Title.ALERTA);
			}
			voltarTelaParaSituacaoParaInicial();
		};
	}

	private ActionListener actionPerformedBntEditar() {
		return actionEvent -> {
			operacao = Operacao.EDITAR.getOperacao();

			try {
				JOptionUtils.linhaSelecionada(tableProduto);
				JOptionUtils.openFields(false, btnExcluir, btnNovo, btnPesquisar);
				JOptionUtils.openFields(true, panelAcoesComplementares, btnSalvar, txtDescricao, txtQuantidade,
						txtPreco, btnCancelar);
			} catch (TabelaVaziaException | LinhaNaoSelecionadaException e) {
				JOptionUtils.mostrarAlerta(e.getMessage(), Title.ALERTA);
			}
		};
	}

	private ActionListener actionPerformedBtnCancelar() {
		return actionEvent -> voltarTelaParaSituacaoParaInicial();
	}

	private ActionListener actionPerformedBtnSalvar() {
		return actionEvent -> {
			Produto produto = new Produto.Builder().descricao(txtDescricao.getText())
					.quantidade(Double.parseDouble(getQuantidade())).valor(Double.parseDouble(getPreco())).loja(loja)
					.build();

			if (StringUtils.isNotBlank(produto.getDescricao())) {
				try {
					if (operacao.equals(Operacao.NOVO.getOperacao())) {
						produtoController.cadastrar(produto);
						tableModel.addRow(new Object[]{produto.getId(), produto.getDescricao(),
								produto.getQuantidade(), produto.getValor()});
						JOptionUtils.mostrarSucesso("Produto " + produto.getDescricao() + " cadastrado com sucesso!",
								Title.SUCESSO);
					} else if (operacao.equals(Operacao.EDITAR.getOperacao())) {
						produto.setId(produtoId);
						produtoController.atualizar(produto);
						tableModel.setValueAt(getDescricao(), tableProduto.getSelectedRow(), 1);
						tableModel.setValueAt(getQuantidade(), tableProduto.getSelectedRow(), 2);
						tableModel.setValueAt(getPreco(), tableProduto.getSelectedRow(), 3);
					}
				} catch (AtualizarException | CadastrarException e) {
					JOptionUtils.mostrarErro(e.getMessage() + "\nMotivo:" + e.getCause(), Title.ERRO);
				}
			} else {
				JOptionUtils.mostrarAlerta("Descicao obrigatoria", Title.ALERTA);
			}
			voltarTelaParaSituacaoParaInicial();
		};
	}

	private String getDescricao() {
		return txtDescricao.getText();
	}

	private String getQuantidade() {
		return txtQuantidade.getText();
	}

	private String getPreco() {
		return txtPreco.getText();
	}

	private MouseAdapter mouseClickedProduto() {
		return new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				clearAllFields();
				JOptionUtils.openFields(true, btnExcluir, btnEditar, btnCancelar);
				JOptionUtils.openFields(false, btnNovo, btnSalvar);
				ListSelectionModel selectionModel = tableProduto.getSelectionModel();
				tableProduto.setSelectionModel(selectionModel);
				produtoId = Long.valueOf(JOptionUtils.getValueFromColumn(tableProduto, ColumnTitle.ID));
				txtDescricao.setText(JOptionUtils.getValueFromColumn(tableProduto, ColumnTitle.DESCRICAO));
				txtQuantidade.setText(JOptionUtils.getValueFromColumn(tableProduto, ColumnTitle.QUANTIDADE));
				txtPreco.setText(JOptionUtils.getValueFromColumn(tableProduto, ColumnTitle.PRECO));
			}
		};
	}

	private ActionListener actionPerformedBtnNovo() {
		return actionEvent -> {
			operacao = Operacao.NOVO.getOperacao();
			JOptionUtils.openFields(false, btnEditar, btnExcluir, btnNovo, btnPesquisar);
			JOptionUtils.openFields(true, txtDescricao, txtQuantidade, txtPreco, btnSalvar, btnCancelar,
					panelAcoesComplementares);
			clearAllFields();
		};
	}

	private void carregarDadosTabela() {
		tableModel = (DefaultTableModel) tableProduto.getModel();
		if (CollectionUtils.isNotEmpty(produtos)) {
			for (Produto produto : produtos) {
				tableModel.addRow(new Object[]{produto.getId(), produto.getDescricao(), produto.getQuantidade(),
						produto.getValor()});
			}
		}
	}

	private void excluirDados() {
		Long id = Long.valueOf(tableProduto.getValueAt(tableProduto.getSelectedRow(), 0).toString());
		String nomeProduto = tableProduto.getValueAt(tableProduto.getSelectedRow(), 1).toString();
		int opcaoEscolhida = JOptionUtils.yesOrNo(String.format("Desjea deletar o produto %s?", nomeProduto),
				Title.EXCLUIR);

		if (JOptionUtils.isTrue(opcaoEscolhida)) {
			try {
				produtoController.excluir(id);
				tableModel.removeRow(tableProduto.getSelectedRow());
				JOptionUtils.mostrarSucesso(String.format("O produto (id: %d) foi removido!", id), Title.SUCESSO);
			} catch (ExcluirException e) {
				JOptionUtils.mostrarErro(e.getMessage() + "\nMotivo:" + e.getCause(), Title.ERRO);
			}
		}
	}

	private void voltarTelaParaSituacaoParaInicial() {
		JOptionUtils.openFields(false, btnEditar, btnExcluir, panelAcoesComplementares, btnSalvar, btnCancelar,
				txtDescricao, txtQuantidade, txtPreco);
		JOptionUtils.openFields(true, btnNovo);
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
		JOptionUtils.clearFields(txtDescricao, txtQuantidade, txtPreco);
	}
}
