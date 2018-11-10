package br.com.mateus.manager.products.view;

import br.com.mateus.manager.products.connection.ConnectionFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = -6823654718026202464L;
    private JPanel contentPane;
    private JDesktopPane desktop;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainView frame = new MainView();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public MainView() {
        initComponents();
        initConnection();
    }

    private void initComponents() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 972, 692);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        desktop = new JDesktopPane();
        desktop.setBackground(SystemColor.activeCaptionBorder);
        desktop.setBounds(0, 65, 966, 598);
        contentPane.add(desktop);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 966, 66);
        contentPane.add(panel);
        panel.setLayout(null);

        JButton btnProdutos = new JButton("Produtos");
        btnProdutos.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnProdutos.addActionListener(actionPerformedBtnProdutos());
        btnProdutos.setBounds(327, 11, 111, 44);
        panel.add(btnProdutos);

        JButton btnLojas = new JButton("Lojas");
        btnLojas.addActionListener(actionPerformedLojas());
        btnLojas.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnLojas.setBounds(206, 11, 111, 44);
        panel.add(btnLojas);

        JButton btnUsurios = new JButton("Usu\u00E1rios");
        btnUsurios.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnUsurios.setBounds(654, 11, 111, 44);
        panel.add(btnUsurios);

        JButton btnPesquisaAvanada = new JButton("Pesquisa Avan\u00E7ada");
        btnPesquisaAvanada.addActionListener(actionPerformedBtnPesquisaAvancada());
        btnPesquisaAvanada.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnPesquisaAvanada.setBounds(448, 11, 196, 44);
        panel.add(btnPesquisaAvanada);
    }

    private ActionListener actionPerformedBtnProdutos() {
        return actionEvent -> {
            ProdutoView produtoView = new ProdutoView();
            desktop.add(produtoView);
            desktop.setVisible(true);
            produtoView.setVisible(true);
        };
    }

    private ActionListener actionPerformedBtnPesquisaAvancada() {
        return actionEvent -> {
            PesquisaView pesquisaView = new PesquisaView();
            desktop.add(pesquisaView);
            desktop.setVisible(true);
            pesquisaView.setVisible(true);
        };
    }

    private ActionListener actionPerformedLojas() {
        return actionEvent -> {
            LojaView lojaView = new LojaView();
            desktop.add(lojaView);
            desktop.setVisible(true);
            lojaView.setVisible(true);
        };
    }

    private void initConnection() {
        ConnectionFactory.getEntityManager();
    }
}
