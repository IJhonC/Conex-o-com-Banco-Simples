/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sig_telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author joao_cristofolini
 */
public class FormMain extends JFrame {

    public static int x, y;

    public FormMain() {
        super();
        setBounds(100, 100, 900, 700);
        setTitle("Sistema Inteligente de Gerenciamento");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(this);
        setLayout(null);

        initComponentes();
    }

    private void deslogar() {
        FormLogin formLogin = new FormLogin();
        formLogin.setVisible(true);

        // Fecha o FormMain
        super.setVisible(false);

    }

    private void initComponentes() {
        FormCliente frmCliente = new FormCliente();
        FormProduto frmProduto = new FormProduto();
        FormFornecedor frmFornecedor = new FormFornecedor();
        FormUsuario frmUser = new FormUsuario();

        //Inicia a barra de menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 900, 30);

        //Adciona o menu Cadastro
        JMenu mCadastro = new JMenu("Cadastro");

        //Adciona o menu Relatório
        JMenu mRelat = new JMenu("Relatório");

        //Adciona o menu Sair
        JMenu mSair = new JMenu("Sair");

        //Adiciona a barra de menu ao formulário principal
        this.add(menuBar);

        //Adiciona os menus Cadastro, Relatório e Sair a barra de menus
        menuBar.add(mCadastro);
        menuBar.add(mRelat);
        menuBar.add(mSair);

        //Cria os itens de menu para o menu Cadastro
        JMenuItem mItemCli = new JMenuItem("Cliente");
        JMenuItem mItemPro = new JMenuItem("Produto");
        JMenuItem mItemFor = new JMenuItem("Fornecedor");
        JMenuItem mItemUser = new JMenuItem("Usuário");
        

        //Adiciona os itens de menu ao menu Cadastro
        mCadastro.add(mItemCli);
        mCadastro.add(mItemPro);
        mCadastro.add(mItemFor);
        mCadastro.add(mItemUser);

        JMenuItem mItemLogout = new JMenuItem("Logout");
        mSair.add(mItemLogout);
        mItemLogout.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                deslogar();
            }
        });

        JMenuItem mItemSair = new JMenuItem("Sair");
        mSair.add(mItemSair);
        mItemSair.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //Cria as definições do JDesktopPanel para receber as janelas internas
        Color pDskTpCor = new Color(100, 102, 120);
        JDesktopPane jDskTop1 = new JDesktopPane();
        jDskTop1.setBounds(0, 30, 900, 670);
        this.add(jDskTop1);
        jDskTop1.setLayout(null);

        //Torna visivel o formulario principal
        this.setVisible(true);

        //Guarda as dimensões do formulario principal para centralizar
        x = this.getWidth();
        y = this.getHeight();

        //Evento do menu Cliente para abrir a Janela Interna
        mItemCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!frmCliente.isVisible()) {
                    frmCliente.setVisible(true);
                    frmCliente.setClosable(true); // frame fechado pelo borão fechar
                    frmCliente.setIconifiable(true);// frame poder ser minimizado
                    frmCliente.setResizable(true);

                    //Centraliza a janela interna do cliente
                    frmCliente.setLocation((x / 2) - frmCliente.getWidth() / 2 - 10, (y / 2) - (frmCliente.getHeight() / 2) - 50);
                    jDskTop1.add(frmCliente);
                }
            }
        });

        mItemPro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!frmProduto.isVisible()) {
                    frmProduto.setVisible(true);
                    frmProduto.setClosable(true); // frame fechado pelo borão fechar
                    frmProduto.setIconifiable(true);// frame poder ser minimizado
                    frmProduto.setResizable(true);

                    //Centraliza a janela interna do cliente
                    frmProduto.setLocation((x / 2) - frmProduto.getWidth() / 2 - 10, (y / 2) - (frmProduto.getHeight() / 2) - 50);
                    jDskTop1.add(frmProduto);
                }
            }
        });

        mItemFor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!frmFornecedor.isVisible()) {
                    frmFornecedor.setVisible(true);
                    frmFornecedor.setClosable(true); // frame fechado pelo borão fechar
                    frmFornecedor.setIconifiable(true);// frame poder ser minimizado
                    frmFornecedor.setResizable(true);

                    //Centraliza a janela interna do cliente
                    frmFornecedor.setLocation((x / 2) - frmFornecedor.getWidth() / 2 - 10, (y / 2) - (frmFornecedor.getHeight() / 2) - 50);
                    jDskTop1.add(frmFornecedor);
                }
            }
        });
        
        mItemUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!frmUser.isVisible()) {
                    frmUser.setVisible(true);
                    frmUser.setClosable(true); // frame fechado pelo borão fechar
                    frmUser.setIconifiable(true);// frame poder ser minimizado
                    frmUser.setResizable(true);

                    //Centraliza a janela interna do cliente
                    frmUser.setLocation((x / 2) - frmUser.getWidth() / 2 - 10, (y / 2) - (frmUser.getHeight() / 2) - 50);
                    jDskTop1.add(frmUser);
                }
            }
        });
    }
}
