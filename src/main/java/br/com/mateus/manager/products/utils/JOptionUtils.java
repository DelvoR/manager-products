package br.com.mateus.manager.products.utils;

import com.toedter.calendar.JDateChooser;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;

public class JOptionUtils {

	private JOptionUtils() {

	}

	/**
	 * TODO
	 * 
	 * @param ativo
	 * @param objects
	 */
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
			} else if (object instanceof JButton) {
				((JButton) object).setEnabled(ativo);
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
				((JTextField) object).setText("");
			} else if (object instanceof JDateChooser) {
				((JDateChooser) object).setDate(null);
			} else if (object instanceof JRadioButton) {
				((JRadioButton) object).setSelected(false);
			}

		}
	}

	public static void mostrarMensagem(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem);
	}

	public static void mostrarInformacao(String mensagem, Title titulo) {
		JOptionPane.showMessageDialog(null, mensagem, titulo.getTitulo(), JOptionPane.INFORMATION_MESSAGE);
	}

	public static int yesOrNo(String mensagem, Title titulo) {
		return JOptionPane.showConfirmDialog(null, mensagem, titulo.getTitulo(), JOptionPane.YES_NO_OPTION);
	}

	public static boolean isTrue(int opcao) {
		return opcao == JOptionPane.YES_OPTION;
	}

	public static void setValueToFieldFromTable(JTable table, ColumnTitle columnTitle, JTextField field) {
		int columnCount = table.getColumnCount();
		for (int column = 0; column < columnCount; column++) {
			if (table.getColumnName(column).equalsIgnoreCase(columnTitle.getColumnTitle())) {
				field.setText(table.getValueAt(table.getSelectedRow(), column).toString());
			}
		}
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

}
