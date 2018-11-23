package br.com.mateus.manager.products.view;

import br.com.mateus.manager.products.model.entity.Categoria;
import br.com.mateus.manager.products.model.entity.SubCategoria;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProdutoView extends JInternalFrame {

	private static final long serialVersionUID = 2538628797157567245L;
	private static ProdutoView instance;

	private JTable tableProduto;
	private JButton btnNovo;
	private JTextField textNomeProduto;
	private JTextField textQuantidade;
	private JTextField textPreco;

	/**
	 * Create the frame.
	 */
	private ProdutoView() {
		initComponents();

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
		GridBagLayout gbl_panelAcoes = new GridBagLayout();
		gbl_panelAcoes.columnWidths = new int[]{123, 123, 124, 123, 123, 0};
		gbl_panelAcoes.rowHeights = new int[]{33, 0};
		gbl_panelAcoes.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelAcoes.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelAcoes.setLayout(gbl_panelAcoes);

		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(actionPerformedBtnNovo());
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gridBtnNovo = new GridBagConstraints();
		gridBtnNovo.fill = GridBagConstraints.BOTH;
		gridBtnNovo.insets = new Insets(0, 0, 0, 5);
		gridBtnNovo.gridx = 0;
		gridBtnNovo.gridy = 0;
		panelAcoes.add(btnNovo, gridBtnNovo);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gridBtnEditar = new GridBagConstraints();
		gridBtnEditar.fill = GridBagConstraints.BOTH;
		gridBtnEditar.insets = new Insets(0, 0, 0, 5);
		gridBtnEditar.gridx = 1;
		gridBtnEditar.gridy = 0;
		panelAcoes.add(btnEditar, gridBtnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gridBtnExcluir = new GridBagConstraints();
		gridBtnExcluir.fill = GridBagConstraints.BOTH;
		gridBtnExcluir.insets = new Insets(0, 0, 0, 5);
		gridBtnExcluir.gridx = 2;
		gridBtnExcluir.gridy = 0;
		panelAcoes.add(btnExcluir, gridBtnExcluir);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gridBtnPesquisar = new GridBagConstraints();
		gridBtnPesquisar.fill = GridBagConstraints.BOTH;
		gridBtnPesquisar.insets = new Insets(0, 0, 0, 5);
		gridBtnPesquisar.gridx = 3;
		gridBtnPesquisar.gridy = 0;
		panelAcoes.add(btnPesquisar, gridBtnPesquisar);

		JButton btnLojas = new JButton("Lojas");
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
		tableProduto.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "C\u00F3digo", "Descri\u00E7\u00E3o", "Quantidade", "Pre\u00E7o" }));
		scrollPaneProduto.setViewportView(tableProduto);

		JPanel panelProduto = new JPanel();
		panelProduto.setBorder(
				new TitledBorder(null, "Dados do Produto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelProduto.setBounds(10, 61, 578, 114);
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

		textNomeProduto = new JTextField();
		textNomeProduto.setBounds(108, 22, 460, 20);
		panelProduto.add(textNomeProduto);
		textNomeProduto.setColumns(10);

		textQuantidade = new JTextField();
		textQuantidade.setColumns(10);
		textQuantidade.setBounds(108, 50, 132, 20);
		panelProduto.add(textQuantidade);

		textPreco = new JTextField();
		textPreco.setBounds(108, 81, 132, 20);
		panelProduto.add(textPreco);
		textPreco.setColumns(10);

		JComboBox<Categoria> comboBoxCategoria = new JComboBox();
		comboBoxCategoria.setBounds(348, 50, 220, 20);
		panelProduto.add(comboBoxCategoria);

		JComboBox<SubCategoria> comboBoxSubCategoria = new JComboBox();
		comboBoxSubCategoria.setBounds(348, 78, 220, 20);
		panelProduto.add(comboBoxSubCategoria);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),

				"A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(598, 61, 119, 116);
		getContentPane().add(panel);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(12, 21, 97, 33);
		panel.add(btnSalvar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(12, 65, 97, 33);
		panel.add(btnCancelar);
	}

	private ActionListener actionPerformedBtnNovo() {
		return actionEvent -> {

		};
	}
}
