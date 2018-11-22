package br.com.mateus.manager.products.view;

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

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(0, 0, 736, 50);
		getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{123, 123, 124, 123, 123, 0};
		gbl_panel.rowHeights = new int[]{33, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(actionPerformedBtnNovo());
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gridBtnNovo = new GridBagConstraints();
		gridBtnNovo.fill = GridBagConstraints.BOTH;
		gridBtnNovo.insets = new Insets(0, 0, 0, 5);
		gridBtnNovo.gridx = 0;
		gridBtnNovo.gridy = 0;
		panel.add(btnNovo, gridBtnNovo);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gridBtnEditar = new GridBagConstraints();
		gridBtnEditar.fill = GridBagConstraints.BOTH;
		gridBtnEditar.insets = new Insets(0, 0, 0, 5);
		gridBtnEditar.gridx = 1;
		gridBtnEditar.gridy = 0;
		panel.add(btnEditar, gridBtnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gridBtnExcluir = new GridBagConstraints();
		gridBtnExcluir.fill = GridBagConstraints.BOTH;
		gridBtnExcluir.insets = new Insets(0, 0, 0, 5);
		gridBtnExcluir.gridx = 2;
		gridBtnExcluir.gridy = 0;
		panel.add(btnExcluir, gridBtnExcluir);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gridBtnPesquisar = new GridBagConstraints();
		gridBtnPesquisar.fill = GridBagConstraints.BOTH;
		gridBtnPesquisar.insets = new Insets(0, 0, 0, 5);
		gridBtnPesquisar.gridx = 3;
		gridBtnPesquisar.gridy = 0;
		panel.add(btnPesquisar, gridBtnPesquisar);

		JButton btnLojas = new JButton("Lojas");
		btnLojas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gridBtnLojas = new GridBagConstraints();
		gridBtnLojas.fill = GridBagConstraints.BOTH;
		gridBtnLojas.gridx = 4;
		gridBtnLojas.gridy = 0;
		panel.add(btnLojas, gridBtnLojas);

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

	private ActionListener actionPerformedBtnNovo() {
		return actionEvent -> {

		};
	}
}
