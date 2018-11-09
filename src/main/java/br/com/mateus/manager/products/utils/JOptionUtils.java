package br.com.mateus.manager.products.utils;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

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

}
