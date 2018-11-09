package br.com.mateus.manager.products.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.lang3.StringUtils;

import br.com.mateus.manager.products.controller.impl.LojaController;
import br.com.mateus.manager.products.model.entity.Endereco;
import br.com.mateus.manager.products.model.entity.Loja;
import br.com.mateus.manager.products.model.enums.Estado;
import br.com.mateus.manager.products.model.enums.Operacao;
import br.com.mateus.manager.products.utils.JOptionUtils;

public class LojaView extends JInternalFrame {

	private static final int NO_SELECTED_ROWS = -1;
	private Integer operacao;
	private DefaultTableModel tableModel;

	private Long idLoja;
	private String nomeLoja;
	/**
	 * 
	 */
	private static final long serialVersionUID = 5797416484063659840L;
	private JTextField txtRazaoSocial;
	private JTextField txtCnpj;
	private JTextField txtRua;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JTextField txtCep;
	private JTextField txtUf;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LojaView frame = new LojaView();
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
	public LojaView() {
		initComponets();
		carregarDadosTabela();

	}

	private void initComponets() {
		setTitle("Lojas");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 744, 474);
		getContentPane().setLayout(null);

		JPanel panelAcoes = new JPanel();
		panelAcoes.setBorder(
				new TitledBorder(null, "A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAcoes.setBounds(0, 0, 728, 78);
		getContentPane().add(panelAcoes);
		panelAcoes.setLayout(null);

		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operacao = Operacao.NOVO.getOperacao();

				JOptionUtils.openFields(false, btnEditar, btnExcluir, btnPesquisar);
				JOptionUtils.openFields(true, txtRazaoSocial, txtCnpj, txtRua, txtNumero, txtUf, txtBairro, txtCep,
						txtCidade, txtComplemento, btnCancelar, btnSalvar);
				JOptionUtils.clearFields(txtRazaoSocial, txtCnpj, txtRua, txtNumero, txtUf, txtBairro, txtCep,
						txtCidade, txtComplemento);

				JOptionUtils.openFields(false, btnNovo);

			}
		});
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNovo.setBounds(10, 21, 123, 33);
		panelAcoes.add(btnNovo);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				operacao = Operacao.EDITAR.getOperacao();

				if (tableLoja.getSelectedRow() == NO_SELECTED_ROWS) {
					if (tableLoja.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "A tabela esta vazia!");
					} else {
						JOptionPane.showMessageDialog(null, "Deve ser selecionado um cliente!");
					}
				} else {
					JOptionUtils.openFields(false, btnExcluir, btnNovo, btnSalvar, btnPesquisar);
					panelAcoesComplementares.setEnabled(true);
					JOptionUtils.openFields(true, btnSalvar);
					JOptionUtils.openFields(true, txtRazaoSocial, txtCnpj, txtRua, txtNumero, txtUf, txtBairro, txtCep,
							txtCidade, txtComplemento, btnCancelar);

				}
			}
		});
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEditar.setBounds(214, 21, 123, 33);
		panelAcoes.add(btnEditar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (tableLoja.getSelectedRow() == -1) {
					if (tableLoja.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "A tabela esta vazia!");
					} else {
						JOptionPane.showMessageDialog(null, "Deve ser selecionado um cliente!");
					}
				} else {
					JOptionUtils.openFields(false, btnNovo, btnSalvar);
					panelAcoesComplementares.setVisible(true);
					btnCancelar.setEnabled(true);

					excluirDados();

					JOptionUtils.clearFields(txtRazaoSocial, txtCnpj, txtRua, txtNumero, txtUf, txtBairro, txtCep,
							txtCidade, txtComplemento);

				}
			}

		});
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExcluir.setBounds(403, 21, 124, 33);
		panelAcoes.add(btnExcluir);

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPesquisar.setBounds(595, 21, 123, 33);
		panelAcoes.add(btnPesquisar);

		JLabel lblRazaoSocial = new JLabel("Raz\u00E3o Social:");
		lblRazaoSocial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRazaoSocial.setBounds(118, 89, 78, 20);
		getContentPane().add(lblRazaoSocial);

		txtRazaoSocial = new JTextField();
		txtRazaoSocial.setEditable(false);
		txtRazaoSocial.setEnabled(false);
		txtRazaoSocial.setBounds(206, 89, 179, 20);
		getContentPane().add(txtRazaoSocial);
		txtRazaoSocial.setColumns(10);

		JLabel lblCnpj = new JLabel("CNPJ:");
		lblCnpj.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCnpj.setBounds(395, 89, 45, 20);
		getContentPane().add(lblCnpj);

		txtCnpj = new JTextField();
		txtCnpj.setEditable(false);
		txtCnpj.setEnabled(false);
		txtCnpj.setColumns(10);
		txtCnpj.setBounds(450, 89, 179, 20);
		getContentPane().add(txtCnpj);

		JPanel panelEndereco = new JPanel();
		panelEndereco
				.setBorder(new TitledBorder(null, "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEndereco.setBounds(10, 117, 597, 116);
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

		JLabel lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNmero.setBounds(303, 21, 46, 20);
		panelEndereco.add(lblNmero);

		txtNumero = new JTextField();
		txtNumero.setEditable(false);
		txtNumero.setEnabled(false);
		txtNumero.setColumns(10);
		txtNumero.setBounds(359, 21, 74, 20);
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
		lblCep.setBounds(303, 52, 46, 20);
		panelEndereco.add(lblCep);

		txtCep = new JTextField();
		txtCep.setEditable(false);
		txtCep.setEnabled(false);
		txtCep.setColumns(10);
		txtCep.setBounds(359, 52, 74, 20);
		panelEndereco.add(txtCep);

		JLabel lblUf = new JLabel("UF:");
		lblUf.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUf.setBounds(458, 21, 31, 20);
		panelEndereco.add(lblUf);

		txtUf = new JTextField();
		txtUf.setEditable(false);
		txtUf.setEnabled(false);
		txtUf.setColumns(10);
		txtUf.setBounds(499, 21, 74, 20);
		panelEndereco.add(txtUf);

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
		lblCidade.setBounds(443, 52, 46, 20);
		panelEndereco.add(lblCidade);

		txtCidade = new JTextField();
		txtCidade.setEditable(false);
		txtCidade.setEnabled(false);
		txtCidade.setColumns(10);
		txtCidade.setBounds(499, 52, 74, 20);
		panelEndereco.add(txtCidade);

		JPanel panelTableLoja = new JPanel();
		panelTableLoja.setBounds(11, 244, 707, 189);
		getContentPane().add(panelTableLoja);
		panelTableLoja.setLayout(null);

		JScrollPane scrollPaneLoja = new JScrollPane();
		scrollPaneLoja.setBounds(10, 11, 687, 167);
		panelTableLoja.add(scrollPaneLoja);

		tableLoja = new JTable();
		tableLoja.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				ListSelectionModel selectionModel = tableLoja.getSelectionModel();
				tableLoja.setSelectionModel(selectionModel);

				idLoja = Long.parseLong(tableLoja.getValueAt(tableLoja.getSelectedRow(), 0).toString());
				nomeLoja = tableLoja.getValueAt(tableLoja.getSelectedRow(), 1).toString();

			}

		});

		tableLoja.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "CNPJ", "Raz\u00E3o Social", "Cidade", "Bairro" }));

		scrollPaneLoja.setViewportView(tableLoja);

		panelAcoesComplementares = new JPanel();
		panelAcoesComplementares.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelAcoesComplementares.setBounds(612, 120, 106, 113);
		getContentPane().add(panelAcoesComplementares);
		panelAcoesComplementares.setLayout(null);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String razaoSocial = txtRazaoSocial.getText();
				String cnpj = txtCnpj.getText();
				String rua = txtRua.getText();
				Integer numero = StringUtils.isNotBlank(txtNumero.getText()) ? Integer.parseInt(txtNumero.getText())
						: 0;
				Estado uf = Estado.fromString(txtUf.getText());
				String bairro = txtBairro.getText();
				String cep = txtCep.getText();
				String cidade = txtCidade.getText();
				String complemento = txtComplemento.getText();

				Endereco endereco = new Endereco(rua, numero, bairro, cep, complemento, cidade, uf);
				if (StringUtils.isNotBlank(razaoSocial) && StringUtils.isNotBlank(cnpj)) {
					Loja loja = new Loja(razaoSocial, endereco, cnpj);
					LojaController lojaController = new LojaController();

					try {
						if (operacao == Operacao.NOVO.getOperacao()) {
							boolean resultado = lojaController.cadastrar(loja);
							if (resultado) {
								tableModel.addRow(
										new Object[] { lojaController.getId(loja), cnpj, razaoSocial, cidade, bairro });
								JOptionPane.showMessageDialog(null,
										"Loja " + loja.getRazaoSocial() + " cadastrada com sucesso!", "Sucesso",
										JOptionPane.INFORMATION_MESSAGE);
							}
						} else if (operacao == Operacao.EDITAR.getOperacao()) {
							lojaController.atualizar(loja);
							tableModel.setValueAt(cnpj, tableLoja.getSelectedRow(), 1);
							tableModel.setValueAt(razaoSocial, tableLoja.getSelectedRow(), 2);
							tableModel.setValueAt(cidade, tableLoja.getSelectedRow(), 3);
							tableModel.setValueAt(bairro, tableLoja.getSelectedRow(), 4);
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
								"Loja " + loja.getRazaoSocial() + " cadastrada com sucesso!", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"É obrigatório informar Razão Social e CNPJ para cadastrar uma loja!", "Sucesso",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnSalvar.setBounds(12, 7, 84, 23);
		panelAcoesComplementares.add(btnSalvar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				JOptionUtils.openFields(true, btnNovo, btnEditar, btnExcluir);
				JOptionUtils.openFields(false, btnCancelar, btnSalvar, txtRazaoSocial, txtCnpj, txtRua, txtNumero,
						txtUf, txtBairro, txtCep, txtCidade, txtComplemento);
				JOptionUtils.clearFields(txtRazaoSocial, txtCnpj, txtRua, txtNumero, txtUf, txtBairro, txtCep,
						txtCidade, txtComplemento);
			}
		});
		btnCancelar.setBounds(12, 79, 84, 23);
		panelAcoesComplementares.add(btnCancelar);
	}

	private void carregarDadosTabela() {
		LojaController lojaController = new LojaController();
		List<Loja> lojas = new ArrayList<>();
		lojas = lojaController.buscarTodos();

		tableModel = (DefaultTableModel) tableLoja.getModel();

		for (Loja loja : lojas) {
			tableModel.addRow(new Object[] { loja.getCnpj(), loja.getRazaoSocial(), loja.getEndereco().getCidade(),
					loja.getEndereco().getBairro() });
		}
	}

	private void excluirDados() {
		String cnpj = tableModel.getValueAt(tableLoja.getSelectedRow(), 1).toString();
		String razaoSocial = tableModel.getValueAt(tableLoja.getSelectedRow(), 2).toString();
		int opcaoEscolhida = JOptionPane.showConfirmDialog(null, "Deseja DELETAR a loja " + razaoSocial + "?",
				"Excluir", JOptionPane.YES_NO_OPTION);
		if (opcaoEscolhida == JOptionPane.YES_OPTION) {
			LojaController lojaController = new LojaController();
			// lojaController.excluir()

		}

	}
}
