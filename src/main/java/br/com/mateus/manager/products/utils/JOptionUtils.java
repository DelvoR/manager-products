package br.com.mateus.manager.products.utils;

import br.com.mateus.manager.products.exceptions.LinhaNaoSelecionadaException;
import br.com.mateus.manager.products.exceptions.TabelaVaziaException;
import com.toedter.calendar.JDateChooser;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;

public class JOptionUtils {

	private static final int NO_SELECTED_ROWS = -1;
	private static final int NO_ROWS = 0;

	private JOptionUtils() {

	}

	public static void openFields(boolean ativo, Object... objects) {
		for (Object object : objects) {
			if (object instanceof JTextField) {
				((JTextField) object).setEditable(ativo);
				((JTextField) object).setEnabled(ativo);
			} else if (object instanceof JButton) {
				((JButton) object).setEnabled(ativo);
			} else if (object instanceof JRadioButton) {
				((JRadioButton) object).setEnabled(ativo);
			} else if (object instanceof JDateChooser) {
				((JDateChooser) object).setEnabled(ativo);
			} else if (object instanceof JPanel) {
				((JPanel) object).setEnabled(ativo);
			} else if (object instanceof JComboBox) {
				((JComboBox<?>) object).setEnabled(ativo);
			}
		}
	}

	public static void clearFields(Object... objects) {
		for (Object object : objects) {
			if (object instanceof JTextField) {
				((JTextField) object).setText(null);
			} else if (object instanceof JDateChooser) {
				((JDateChooser) object).setDate(null);
			} else if (object instanceof JRadioButton) {
				((JRadioButton) object).setSelected(false);
			}

		}
	}

	public static void mostrarMensagem(String mensagem) {
		mostrarMensagem(mensagem, null, JOptionPane.PLAIN_MESSAGE);
	}

	public static void mostrarInformacao(String mensagem, Title titulo) {
		mostrarSucesso(mensagem, titulo);
	}

	public static void mostrarErro(String mensagem, Title titulo) {
		mostrarMensagem(mensagem, titulo, JOptionPane.ERROR_MESSAGE);
	}

	public static void mostrarAlerta(String mensagem, Title titulo) {
		mostrarMensagem(mensagem, titulo, JOptionPane.WARNING_MESSAGE);
	}

	public static void mostrarSucesso(String mensagem, Title titulo) {
		mostrarMensagem(mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

	public static void mostrarMensagem(String mensagem, Title titulo, int typeMessage) {
		JOptionPane.showMessageDialog(null, mensagem, titulo.getTitulo(), typeMessage);
	}

	public static int yesOrNo(String mensagem, Title titulo) {
		return JOptionPane.showConfirmDialog(null, mensagem, titulo.getTitulo(), JOptionPane.YES_NO_OPTION);
	}

	public static boolean isTrue(int opcao) {
		return opcao == JOptionPane.YES_OPTION;
	}

	public static String getValueFromColumn(JTable table, ColumnTitle columnTitle) {
		int columnCount = table.getColumnCount();
		for (int column = 0; column < columnCount; column++) {
			if (table.getColumnName(column).equalsIgnoreCase(columnTitle.getColumnTitle())) {
				return table.getValueAt(table.getSelectedRow(), column).toString();
			}
		}
		return StringUtils.EMPTY;
	}

	public static void linhaSelecionada(JTable table) throws TabelaVaziaException, LinhaNaoSelecionadaException {
		if (table.getSelectedRow() == NO_SELECTED_ROWS) {
			if (table.getRowCount() == NO_ROWS) {
				throw new TabelaVaziaException("A tablea est\u00e1 vazia!");
			} else {
				throw new LinhaNaoSelecionadaException("Deve ser selecionado pelo um item na tabela!");
			}
		}
	}

}
