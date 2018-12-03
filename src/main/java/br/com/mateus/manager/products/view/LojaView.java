package br.com.mateus.manager.products.view;

import br.com.mateus.manager.products.controller.impl.LojaController;
import br.com.mateus.manager.products.model.entity.Endereco;
import br.com.mateus.manager.products.model.entity.Loja;
import br.com.mateus.manager.products.model.enums.Estado;
import br.com.mateus.manager.products.model.enums.Operacao;
import br.com.mateus.manager.products.utils.ColumnTitle;
import br.com.mateus.manager.products.utils.JOptionUtils;
import br.com.mateus.manager.products.utils.JanelaUtils;
import br.com.mateus.manager.products.utils.Title;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static br.com.mateus.manager.products.utils.JOptionUtils.*;

public class LojaView extends JInternalFrame {

	private static final long serialVersionUID = 5797416484063659840L;
	private static final int NO_ROWS = 0;
	private static final int NO_SELECTED_ROWS = -1;
	private static LojaView instance;
	private JanelaUtils janelaUtils;
	private Long idLoja;
	private Long idEndereco;
	private Integer operacao;
	private DefaultTableModel tableModel;
	private JTextField txtRazaoSocial;
	private JTextField txtCnpj;
	private JTextField txtRua;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JTextField txtCep;
	private JComboBox<Estado> comboBoxUf;
	private JTextField txtComplemento;
	private JTextField txtCidade;
	private JTable tableLoja;

	private JButton btnNovo;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnPesquisar;
	private JPanel panelAcoesComplementares;
	private JButton btnCancelar;
	private JButton btnSalvar;
	private JButton btnProdutos;

	private static final LojaController lojaController = new LojaController();

	private LojaView() {
		initComponets();
		janelaUtils = new JanelaUtils(MainView.getDesktop());
		carregarDadosTabela();

	}

	public static LojaView getInstance() {
		if (instance == null) {
			instance = new LojaView();
		}
		return instance;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				LojaView frame = new LojaView();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initComponets() {
		setTitle("Lojas");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 744, 474);
		getContentPane().setLayout(null);

		JPanel panelAcoes = new JPanel();
		panelAcoes.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelAcoes.setBounds(0, 0, 736, 50);
		getContentPane().add(panelAcoes);
		GridBagLayout gridPanelAcoes = new GridBagLayout();
		gridPanelAcoes.columnWidths = new int[] { 123, 123, 124, 123, 123, 0 };
		gridPanelAcoes.rowHeights = new int[] { 33, 0 };
		gridPanelAcoes.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gridPanelAcoes.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panelAcoes.setLayout(gridPanelAcoes);

		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(actionPerformedBtnEditar());

		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(actionPerformedBtnNovo());
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gridBtnNovo = new GridBagConstraints();
		gridBtnNovo.fill = GridBagConstraints.BOTH;
		gridBtnNovo.insets = new Insets(0, 0, 0, 5);
		gridBtnNovo.gridx = 0;
		gridBtnNovo.gridy = 0;
		panelAcoes.add(btnNovo, gridBtnNovo);

		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gridBtnEditar = new GridBagConstraints();
		gridBtnEditar.fill = GridBagConstraints.BOTH;
		gridBtnEditar.insets = new Insets(0, 0, 0, 5);
		gridBtnEditar.gridx = 1;
		gridBtnEditar.gridy = 0;
		panelAcoes.add(btnEditar, gridBtnEditar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(actionPerfomedBtnExcluir());
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gridBtnExcluir = new GridBagConstraints();
		gridBtnExcluir.fill = GridBagConstraints.BOTH;
		gridBtnExcluir.insets = new Insets(0, 0, 0, 5);
		gridBtnExcluir.gridx = 2;
		gridBtnExcluir.gridy = 0;
		panelAcoes.add(btnExcluir, gridBtnExcluir);

		btnProdutos = new JButton("Produtos");
		btnProdutos.setEnabled(false);
		btnProdutos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnProdutos.addActionListener(actionPerformedBtnProdutos());

		GridBagConstraints gridBtnProdutos = new GridBagConstraints();
		gridBtnProdutos.fill = GridBagConstraints.BOTH;
		gridBtnProdutos.insets = new Insets(0, 0, 0, 5);
		gridBtnProdutos.gridx = 3;
		gridBtnProdutos.gridy = 0;
		panelAcoes.add(btnProdutos, gridBtnProdutos);

		JLabel lblRazaoSocial = new JLabel("Raz\u00E3o Social:");
		lblRazaoSocial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRazaoSocial.setBounds(63, 89, 78, 20);
		getContentPane().add(lblRazaoSocial);

		txtRazaoSocial = new JTextField();
		txtRazaoSocial.setBounds(151, 89, 179, 20);
		getContentPane().add(txtRazaoSocial);
		txtRazaoSocial.setColumns(10);

		JLabel lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCnpj.setBounds(340, 89, 45, 20);
		getContentPane().add(lblCnpj);

		while (true) {
			try {
				txtCnpj = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
				break;
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null, "CNPJ Inv\u00e1lido","Erro", JOptionPane.ERROR_MESSAGE);
			}
		}

		txtCnpj.setEditable(false);
		txtCnpj.setEnabled(false);
		txtCnpj.setColumns(10);
		txtCnpj.setBounds(395, 89, 179, 20);
		getContentPane().add(txtCnpj);

		JPanel panelEndereco = new JPanel();
		panelEndereco
				.setBorder(new TitledBorder(null, "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEndereco.setBounds(10, 117, 579, 116);
		getContentPane().add(panelEndereco);
		panelEndereco.setLayout(null);

		JLabel lblRua = new JLabel("Rua:");
		lblRua.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRua.setBounds(10, 21, 94, 20);
		panelEndereco.add(lblRua);

		txtRua = new JTextField();
		txtRua.setEditable(false);
		txtRua.setEnabled(false);
		txtRua.setColumns(10);
		txtRua.setBounds(114, 21, 179, 20);
		panelEndereco.add(txtRua);

		JLabel lblNmero = new JLabel("N\u00BA.:");
		lblNmero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNmero.setBounds(303, 21, 40, 20);
		panelEndereco.add(lblNmero);

		txtNumero = new JTextField();
		txtNumero.setEditable(false);
		txtNumero.setEnabled(false);
		txtNumero.setColumns(10);
		txtNumero.setBounds(353, 21, 74, 20);
		panelEndereco.add(txtNumero);

		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBairro.setBounds(10, 52, 94, 20);
		panelEndereco.add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.setEditable(false);
		txtBairro.setEnabled(false);
		txtBairro.setColumns(10);
		txtBairro.setBounds(114, 52, 179, 20);
		panelEndereco.add(txtBairro);

		JLabel lblCep = new JLabel("CEP:");
		lblCep.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCep.setBounds(303, 52, 40, 20);
		panelEndereco.add(lblCep);

		txtCep = new JTextField();
		txtCep.setEditable(false);
		txtCep.setEnabled(false);
		txtCep.setColumns(10);
		txtCep.setBounds(353, 52, 74, 20);
		panelEndereco.add(txtCep);

		JLabel lblUf = new JLabel("UF:");
		lblUf.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUf.setBounds(452, 21, 31, 20);
		panelEndereco.add(lblUf);

		List<String> ufs = new ArrayList<>();
		for (Estado estado : Estado.values()) {
			ufs.add(estado.getSigla());
		}

		Collections.sort(ufs);
		comboBoxUf = new JComboBox(ufs.toArray());
		comboBoxUf.setEditable(false);
		comboBoxUf.setEnabled(false);
		comboBoxUf.setBounds(493, 21, 74, 20);
		panelEndereco.add(comboBoxUf);

		JLabel lblComplemento = new JLabel("Complemento:");
		lblComplemento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComplemento.setBounds(10, 83, 94, 20);
		panelEndereco.add(lblComplemento);

		txtComplemento = new JTextField();
		txtComplemento.setEditable(false);
		txtComplemento.setEnabled(false);
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(114, 83, 179, 20);
		panelEndereco.add(txtComplemento);

		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCidade.setBounds(437, 52, 46, 20);
		panelEndereco.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setEditable(false);
		txtCidade.setEnabled(false);
		txtCidade.setColumns(10);
		txtCidade.setBounds(493, 52, 74, 20);
		panelEndereco.add(txtCidade);

		JPanel panelTableLoja = new JPanel();
		panelTableLoja.setBounds(11, 244, 707, 189);
		getContentPane().add(panelTableLoja);
		panelTableLoja.setLayout(null);

		JScrollPane scrollPaneLoja = new JScrollPane();
		scrollPaneLoja.setBounds(10, 11, 687, 167);
		panelTableLoja.add(scrollPaneLoja);

		tableLoja = new JTable();
		tableLoja.addMouseListener(mouseClickedTableLoja());
		tableLoja.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "C\u00f3digo", "CNPJ", "Raz\u00E3o Social", "Cidade", "Bairro" }));

		scrollPaneLoja.setViewportView(tableLoja);

		panelAcoesComplementares = new JPanel();
		panelAcoesComplementares.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelAcoesComplementares.setBounds(599, 117, 119, 116);
		getContentPane().add(panelAcoesComplementares);
		panelAcoesComplementares.setLayout(null);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(actionPerformedBtnSalvar());

		btnSalvar.setBounds(12, 21, 97, 33);
		panelAcoesComplementares.add(btnSalvar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(actionPerformedBtnCancelar());
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setEnabled(false);

		btnCancelar.setBounds(12, 65, 97, 33);
		panelAcoesComplementares.add(btnCancelar);

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(actionPerformedBtnPesquisar());
		btnPesquisar.setBounds(599, 89, 119, 20);
		getContentPane().add(btnPesquisar);
	}

	private ActionListener actionPerformedBtnProdutos() {
		return actionEvent -> {
			if (idLoja != null) {
				Loja loja = lojaController.buscar(idLoja);
				ProdutoView.setLoja(loja);
				ProdutoView.setProdutos(loja.getProdutos());
				janelaUtils.abrirInternalFrame(ProdutoView.getInstance());
			} else {
				JOptionUtils.mostrarMensagem("Nenhuma loja selecionada!");
			}
		};
	}

	private ActionListener actionPerformedBtnPesquisar() {
		return actionEvent -> {
			tableModel = (DefaultTableModel) tableLoja.getModel();
			TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tableModel);
			tableLoja.setRowSorter(rowSorter);

			String razaoSocial = getRazaoSocial();
			if (StringUtils.isNotBlank(razaoSocial)) {
				rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + razaoSocial));
				openFields(true, btnCancelar, panelAcoesComplementares);
			} else {
				rowSorter.setRowFilter(null);
			}
		};
	}

	private MouseAdapter mouseClickedTableLoja() {
		return new MouseAdapter() {
			public void mouseClicked(MouseEvent mouseEvent) {
				clearAllFields();
				openFields(true, btnEditar, btnExcluir, panelAcoesComplementares, btnCancelar, btnProdutos);
				openFields(false, btnNovo, btnPesquisar, btnSalvar);

				ListSelectionModel selectionModel = tableLoja.getSelectionModel();
				tableLoja.setSelectionModel(selectionModel);

				setValueToFieldFromTable(tableLoja, ColumnTitle.CNPJ, txtCnpj);
				setValueToFieldFromTable(tableLoja, ColumnTitle.RAZAO_SOCIAL, txtRazaoSocial);
				setValueToFieldFromTable(tableLoja, ColumnTitle.CIDADE, txtCidade);
				setValueToFieldFromTable(tableLoja, ColumnTitle.BAIRRO, txtBairro);

				idLoja = Long.valueOf(getValueFromColumn(tableLoja, ColumnTitle.ID));
				Loja loja = lojaController.buscar(idLoja);
				Endereco endereco = loja.getEndereco();
				idEndereco = endereco.getId();
				txtRua.setText(endereco.getRua());
				txtNumero.setText(endereco.getNumero().toString());
				comboBoxUf.setSelectedItem(endereco.getUf().getSigla());
				txtBairro.setText(endereco.getBairro());
				txtCep.setText(endereco.getCep());
				txtCidade.setText(endereco.getCidade());
				txtComplemento.setText(endereco.getComplemento());
			}
		};
	}

	private ActionListener actionPerformedBtnEditar() {
		return actionEvent -> {
			operacao = Operacao.EDITAR.getOperacao();

			if (tableLoja.getSelectedRow() == NO_SELECTED_ROWS) {
				if (tableLoja.getRowCount() == NO_ROWS) {
					mostrarMensagem("A tabela esta vazia!");
				} else {
					mostrarMensagem("Deve ser selecionado uma loja!");
				}
			} else {
				openFields(false, btnExcluir, btnNovo);
				openFields(true, panelAcoesComplementares, btnSalvar, txtRazaoSocial, txtCnpj, txtRua, txtNumero,
						comboBoxUf, txtBairro, txtCep, txtCidade, txtComplemento, btnCancelar);

			}
		};
	}

	private ActionListener actionPerfomedBtnExcluir() {
		return actionEvent -> {
			operacao = Operacao.EXCLUIR.getOperacao();

			if (tableLoja.getSelectedRow() == NO_SELECTED_ROWS) {
				if (tableLoja.getRowCount() == NO_ROWS) {
					mostrarMensagem("A tabela esta vazia!");
				} else {
					mostrarMensagem("Deve ser selecionado uma loja!");
				}
			} else {
				openFields(false, btnNovo, btnSalvar);
				openFields(true, panelAcoesComplementares, btnCancelar);

				excluirDados();
				voltarTelaParaSituacaoParaInicial();

			}
		};
	}

	private ActionListener actionPerformedBtnSalvar() {
		return actionEvent -> {
			String cnpjSemMascara = getCnpj()
					.replaceAll("\\.", "")
					.replaceAll("/", "")
					.replaceAll("-", "");

			Endereco endereco = new Endereco.Builder()
					.rua(getRua())
					.numero(getNumero())
					.uf(getUf())
					.bairro(getBairro())
					.cep(getCep())
					.cidade(getCidade())
					.complemento(getComplemento())
					.build();
			if (StringUtils.isNotBlank(getRazaoSocial()) && StringUtils.isNotBlank(cnpjSemMascara)) {
				Loja loja = new Loja(getRazaoSocial(), endereco, cnpjSemMascara);
				try {
					if (operacao.equals(Operacao.NOVO.getOperacao())) {
						boolean resultado = lojaController.cadastrar(loja);
						if (resultado) {
							tableModel.addRow(new Object[] { loja.getId(), getCnpj(), getRazaoSocial(), getCidade(), getBairro() });
							int opcao = JOptionUtils.yesOrNo(
									"Loja cadastrada com sucesso! \nDeseja cadastrar os Produtos dessa Loja?",
									Title.SUCESSO);
							if (isTrue(opcao)) {
								ProdutoView.setLoja(loja);
								janelaUtils.abrirInternalFrame(ProdutoView.getInstance());
							}
						}
					} else if (operacao.equals(Operacao.EDITAR.getOperacao())) {
						loja.setId(idLoja);
						endereco.setId(idEndereco);
						lojaController.atualizar(loja);
						tableModel.setValueAt(getCnpj(), tableLoja.getSelectedRow(), 1);
						tableModel.setValueAt(getRazaoSocial(), tableLoja.getSelectedRow(), 2);
						tableModel.setValueAt(getCidade(), tableLoja.getSelectedRow(), 3);
						tableModel.setValueAt(getBairro(), tableLoja.getSelectedRow(), 4);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Loja " + getRazaoSocial() + " cadastrada com sucesso!", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"\u00c9 obrigat\u00f3rio informar Raz\u00e3o Social e CNPJ para cadastrar uma loja!", "Sucesso",
						JOptionPane.WARNING_MESSAGE);
			}
			voltarTelaParaSituacaoParaInicial();
		};
	}

	private String getBairro() {
		return txtBairro.getText();
	}

	private String getCidade() {
		return txtCidade.getText();
	}

	private String getCnpj() {
		return txtCnpj.getText();
	}

	private String getRazaoSocial() {
		return txtRazaoSocial.getText();
	}

	private String getRua() {
		return txtRua.getText();
	}

	private String getComplemento() {
		return txtComplemento.getText();
	}

	private String getCep() {
		return txtCep.getText();
	}

	private String getUf() {
		return Objects.requireNonNull(comboBoxUf.getSelectedItem()).toString();
	}

	private int getNumero() {
		return StringUtils.isNotBlank(txtNumero.getText()) ? Integer.parseInt(txtNumero.getText()) : 0;
	}

	private ActionListener actionPerformedBtnNovo() {
		return actionEvent -> {
			clearAllFields();
			operacao = Operacao.NOVO.getOperacao();
			openFields(false, btnEditar, btnExcluir, btnNovo, btnPesquisar);
			openFields(true, txtRazaoSocial, txtCnpj, txtRua, txtNumero, comboBoxUf, txtBairro, txtCep, txtCidade,
					txtComplemento, btnCancelar, btnSalvar, panelAcoesComplementares);
		};
	}

	private ActionListener actionPerformedBtnCancelar() {
		return actionEvent -> voltarTelaParaSituacaoParaInicial();
	}

	private void carregarDadosTabela() {
		List<Loja> lojas = lojaController.buscarTodos();

		tableLoja.getColumnModel().getColumn(0).setPreferredWidth(5);
		tableModel = (DefaultTableModel) tableLoja.getModel();
		JFormattedTextField cnpj = null;
		try {
			cnpj = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
		} catch (ParseException ignored) {
		}
		for (Loja loja : lojas) {
			tableModel.addRow(new Object[] { loja.getId(), cnpj != null ? cnpj.getText() : "CNPJ inv\u00e1lido", loja.getRazaoSocial(),
					loja.getEndereco().getCidade(), loja.getEndereco().getBairro() });
		}
	}

	private void excluirDados() {
		Long id = Long.valueOf(tableLoja.getValueAt(tableLoja.getSelectedRow(), 0).toString());
		String razaoSocial = tableLoja.getValueAt(tableLoja.getSelectedRow(), 1).toString();
		int opcaoEscolhida = yesOrNo(String.format("Deseja deletar a loja %s ?", razaoSocial), Title.EXCLUIR);

		if (isTrue(opcaoEscolhida)) {
			lojaController.excluir(id);

			tableModel.removeRow(tableLoja.getSelectedRow());
			mostrarInformacao(String.format("A loja (id : %d) foi removida!", id), Title.SUCESSO);

		}

	}

	private void voltarTelaParaSituacaoParaInicial() {
		openFields(false, btnEditar, btnExcluir, panelAcoesComplementares, btnSalvar, btnCancelar, btnProdutos, txtCnpj, txtRua,
				txtNumero, comboBoxUf, txtBairro, txtCep, txtCidade, txtComplemento);
		openFields(true, btnNovo, btnPesquisar, txtRazaoSocial);
		removerFiltroTabela();
		clearAllFields();
	}

	private void removerFiltroTabela() {
		tableModel = (DefaultTableModel) tableLoja.getModel();
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tableModel);
		tableLoja.setRowSorter(rowSorter);
		rowSorter.setRowFilter(null);
	}

	private void clearAllFields() {
		clearFields(txtRazaoSocial, txtCnpj, txtRua, txtNumero, comboBoxUf, txtBairro, txtCep, txtCidade,
				txtComplemento);
		idLoja = null;
		idEndereco = null;
		ProdutoView.setLoja(null);
		ProdutoView.setProdutos(null);
	}
}
