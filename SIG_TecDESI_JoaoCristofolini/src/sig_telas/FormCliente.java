package sig_telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import sig_classes.Cliente;
import sig_dao.ClienteDAO;
import sig_dao.ModuloConexao;

public class FormCliente extends JInternalFrame {

    JTable jTblCli = new JTable();
    JScrollPane scrlTbCli = new JScrollPane(jTblCli);
    public static ArrayList<Cliente> listaCliente;

    Connection conexao = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    ClienteDAO cliDAO = new ClienteDAO();

    public FormCliente() {
        super();
        this.setSize(800, 500);
        this.setResizable(false);
        this.setTitle("Cadastro de Clientes");
        this.setLayout(null);

        initComponentes();
    }

    public void tblCliente() {    //Atualiza os dados da tabela Cliente
        DefaultTableModel modeloCli = new DefaultTableModel(new Object[]{
            "Código",
            "Nome",
            "Telefone",
            "E-mail",
            "Endereço"}, 0);
        for (int i = 0; i < listaCliente.size(); i++) {
            Object linhaCli[] = new Object[]{
                listaCliente.get(i).getCod(),
                listaCliente.get(i).getNome(),
                listaCliente.get(i).getFone(),
                listaCliente.get(i).getEmail(),
                listaCliente.get(i).getEnde()};
            modeloCli.addRow(linhaCli);
        }
        jTblCli.setModel(modeloCli);
        jTblCli.getColumnModel().getColumn(0).setPreferredWidth(80);
        jTblCli.getColumnModel().getColumn(1).setPreferredWidth(220);
        jTblCli.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTblCli.getColumnModel().getColumn(3).setPreferredWidth(80);
        jTblCli.getColumnModel().getColumn(4).setPreferredWidth(80);
    }

    //CRUD - Restore
    public void loadCli() {
        listaCliente.clear();
        try {
            listaCliente = cliDAO.listarCliente();
            tblCliente();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "FormCliente(loadCli) - Erro: " + e);
        }
    }

    private void initComponentes() {
        conexao = ModuloConexao.conector();
        listaCliente = new ArrayList();

        JLabel lblCodCli = new JLabel("Código: ");
        lblCodCli.setBounds(10, 35, 50, 25);

        JTextField jtxtCodCli = new JTextField();
        jtxtCodCli.setBounds(10, 60, 100, 25);

        JLabel lblNomCli = new JLabel("Nome: ");
        lblNomCli.setBounds(10, 95, 50, 25);

        JTextField jtxtNomCli = new JTextField();
        jtxtNomCli.setBounds(10, 120, 270, 25);

        JLabel lblFonCli = new JLabel("Fone: ");
        lblFonCli.setBounds(10, 155, 50, 25);

        JTextField jtxtFonCli = new JTextField();
        jtxtFonCli.setBounds(10, 180, 270, 25);

        JLabel lblMailCli = new JLabel("Email: ");
        lblMailCli.setBounds(10, 215, 50, 25);

        JTextField jtxtMailCli = new JTextField();
        jtxtMailCli.setBounds(10, 240, 270, 25);

        JLabel lblEndeCli = new JLabel("Endereço: ");
        lblEndeCli.setBounds(10, 275, 100, 25);

        JTextArea jtxtEndeCli = new JTextArea();
        jtxtEndeCli.setBounds(10, 300, 270, 100);
        Border gray = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
        jtxtEndeCli.setBorder(gray);

        scrlTbCli.setBounds(300, 60, 460, 340);
        scrlTbCli.setBorder(gray);

        loadCli();

        JButton btnNewCli = new JButton("Novo");
        btnNewCli.setBounds(20, 420, 130, 30);
        this.add(btnNewCli);

        JButton btnEdtCli = new JButton("Editar");
        btnEdtCli.setBounds(170, 420, 130, 30);
        this.add(btnEdtCli);

        JButton btnDelCli = new JButton("Excluir");
        btnDelCli.setBounds(320, 420, 130, 30);
        this.add(btnDelCli);

        JButton btnEscCli = new JButton("Cancelar");
        btnEscCli.setBounds(470, 420, 130, 30);
        this.add(btnEscCli);

        JButton btnSavCli = new JButton("Salvar");
        btnSavCli.setBounds(620, 420, 130, 30);
        this.add(btnSavCli);

        //CRUD - Create
        btnSavCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cli = new Cliente();
                cli.setNome(jtxtNomCli.getText());
                cli.setFone(jtxtFonCli.getText());
                cli.setEmail(jtxtMailCli.getText());
                cli.setEnde(jtxtEndeCli.getText());

                if (cliDAO.salvarCliente(cli) == 1) {
                    JOptionPane.showMessageDialog(null, "Dados gravados com sucesso na tabela clientes.");
                }
                loadCli();
            }
        });
        //Evento Botão Editar
        btnEdtCli.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                Cliente cli = new Cliente();

                cli.setCod(Integer.parseInt(jtxtCodCli.getText()));
                cli.setNome(jtxtNomCli.getText());
                cli.setFone(jtxtFonCli.getText());
                cli.setEmail(jtxtMailCli.getText());
                cli.setEnde(jtxtEndeCli.getText());

                cliDAO.alteraCliente(cli);
                loadCli();

            }
        });

        btnDelCli.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                int codCli;

                codCli = Integer.parseInt(jtxtCodCli.getText());

                cliDAO.excluirCliente(codCli);
                loadCli();
                jtxtCodCli.setText("");
                jtxtNomCli.setText("");
                jtxtFonCli.setText("");
                jtxtMailCli.setText("");
                jtxtEndeCli.setText("");
            }
        });

        btnNewCli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jtxtCodCli.setText("");
                jtxtNomCli.setText("");
                jtxtFonCli.setText("");
                jtxtMailCli.setText("");
                jtxtEndeCli.setText("");
            }
        });

        //------ Tratamento de eventos do Mouse do Cliente -------------------------------
        jTblCli.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int index = jTblCli.getSelectedRow();
                if (index >= 0 && index < listaCliente.size()) {
                    Cliente cli = listaCliente.get(index);
                    jtxtCodCli.setText(String.valueOf(cli.getCod()));
                    jtxtNomCli.setText(cli.getNome());
                    jtxtFonCli.setText(cli.getFone());
                    jtxtMailCli.setText(cli.getEmail());
                    jtxtEndeCli.setText(cli.getEnde());
                }

            }

            public void mousePressed(MouseEvent e) {
                //JOptionPane.showMessageDialog(null, "Mouse Pressionado!!!");
            }

            public void mouseReleased(MouseEvent e) {
                //JOptionPane.showMessageDialog(null, "Mouse Liberado!!!");
            }

            public void mouseEntered(MouseEvent e) {
                //JOptionPane.showMessageDialog(null, "Mouse Entrou!!!");
            }

            public void mouseExited(MouseEvent e) {
                //JOptionPane.showMessageDialog(null, "Mouse Saiu!!!");
            }
        });

        add(jtxtCodCli);
        add(lblCodCli);
        add(lblNomCli);
        add(jtxtNomCli);
        add(lblFonCli);
        add(jtxtFonCli);
        add(lblMailCli);
        add(jtxtMailCli);
        add(lblEndeCli);
        add(jtxtEndeCli);
        add(scrlTbCli);

        repaint();
    }
}
