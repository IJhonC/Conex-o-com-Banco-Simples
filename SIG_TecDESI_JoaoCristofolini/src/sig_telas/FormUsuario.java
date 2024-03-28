/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sig_telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
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
import sig_classes.Usuario;
import sig_dao.UsuarioDAO;
import sig_dao.ModuloConexao;

/**
 *
 * @author joao_cristofolini
 */
public class FormUsuario extends JInternalFrame {

    JTable jTblUser = new JTable();
    JScrollPane scrlTbUser = new JScrollPane(jTblUser);
    public static ArrayList<Usuario> listaUser;

    Connection conexao = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    UsuarioDAO userDAO = new UsuarioDAO();

    public FormUsuario() {
        super();
        this.setSize(800, 500);
        this.setResizable(false);
        this.setTitle("Cadastro de Usuário");
        this.setLayout(null);

        initComponentes();
    }

    public void tblUsuario() {    //Atualiza os dados da tabela Usuario
        DefaultTableModel modeloUser = new DefaultTableModel(new Object[]{
            "Código",
            "Nome",
            "Senha",
            "Tipo",
            "Privilégio"}, 0);
        for (int i = 0; i < listaUser.size(); i++) {
            Object linhaUser[] = new Object[]{
                listaUser.get(i).getIduser(),
                listaUser.get(i).getNmUser(),
                listaUser.get(i).getPwUser(),
                listaUser.get(i).getTpuser(),
                listaUser.get(i).getPvUser()};
            modeloUser.addRow(linhaUser);
        }
        jTblUser.setModel(modeloUser);
        jTblUser.getColumnModel().getColumn(0).setPreferredWidth(80);
        jTblUser.getColumnModel().getColumn(1).setPreferredWidth(220);
        jTblUser.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTblUser.getColumnModel().getColumn(3).setPreferredWidth(80);
        jTblUser.getColumnModel().getColumn(4).setPreferredWidth(80);
    }

    //CRUD - Restore
    public void loadUser() {
        listaUser.clear();
        try {
            listaUser = userDAO.listarUsuario();
            tblUsuario();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "FormUsuario(loadUser) - Erro: " + e);
        }
    }

    private void initComponentes() {
        conexao = ModuloConexao.conector();
        listaUser = new ArrayList();

        JLabel lblCodUser = new JLabel("Código: ");
        lblCodUser.setBounds(10, 35, 50, 25);

        JTextField jtxtCodUser = new JTextField();
        jtxtCodUser.setBounds(10, 60, 100, 25);

        JLabel lblNomUser = new JLabel("Nome: ");
        lblNomUser.setBounds(10, 95, 50, 25);

        JTextField jtxtNomUser = new JTextField();
        jtxtNomUser.setBounds(10, 120, 270, 25);

        JLabel lblPwUser = new JLabel("Senha: ");
        lblPwUser.setBounds(10, 155, 50, 25);

        JTextField jtxtPwUser = new JTextField();
        jtxtPwUser.setBounds(10, 180, 270, 25);

        JLabel lblTpUser = new JLabel("Tipo: ");
        lblTpUser.setBounds(10, 215, 50, 25);

        JTextField jtxtTpUser = new JTextField();
        jtxtTpUser.setBounds(10, 240, 270, 25);

        JLabel lblPvUser = new JLabel("Privilégio: ");
        lblPvUser.setBounds(10, 275, 100, 25);

        JTextArea jtxtPvUser = new JTextArea();
        jtxtPvUser.setBounds(10, 300, 270, 100);

        Border gray = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
        jtxtPvUser.setBorder(gray);

        scrlTbUser.setBounds(300, 60, 460, 340);
        scrlTbUser.setBorder(gray);

        loadUser();

        JButton btnNewUser = new JButton("Novo");
        btnNewUser.setBounds(20, 420, 130, 30);
        this.add(btnNewUser);

        JButton btnEdtUser = new JButton("Editar");
        btnEdtUser.setBounds(170, 420, 130, 30);
        this.add(btnEdtUser);

        JButton btnDelUser = new JButton("Excluir");
        btnDelUser.setBounds(320, 420, 130, 30);
        this.add(btnDelUser);

        JButton btnEscUser = new JButton("Cancelar");
        btnEscUser.setBounds(470, 420, 130, 30);
        this.add(btnEscUser);

        JButton btnSavUser = new JButton("Salvar");
        btnSavUser.setBounds(620, 420, 130, 30);
        this.add(btnSavUser);

        //CRUD - Create
        btnSavUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario user = new Usuario();
                user.setNmUser(jtxtNomUser.getText());
                user.setPwUser(jtxtPwUser.getText());
                user.setTpuser(jtxtTpUser.getText());
                user.setPvUser(jtxtPvUser.getText().charAt(0));

                if (userDAO.salvarUsuario(user) == 1) {
                    JOptionPane.showMessageDialog(null, "Dados gravados com sucesso na tabela usuario.");
                }
                loadUser();
            }
        });
        //Evento Botão Editar
        btnEdtUser.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                Usuario user = new Usuario();

                user.setIduser(Integer.parseInt(jtxtCodUser.getText()));
                user.setNmUser(jtxtNomUser.getText());
                user.setPwUser(jtxtPwUser.getText());
                user.setTpuser(jtxtTpUser.getText());
                user.setPvUser(jtxtPvUser.getText().charAt(0));

                userDAO.alteraUsuario(user);
                loadUser();

            }
        });
        btnDelUser.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                int codUser;

                codUser = Integer.parseInt(jtxtCodUser.getText());

                userDAO.excluirUsuario(codUser);
                loadUser();
                jtxtCodUser.setText("");
                jtxtNomUser.setText("");
                jtxtPwUser.setText("");
                jtxtTpUser.setText("");
                jtxtPvUser.setText("");
            }
        });

        btnNewUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jtxtCodUser.setText("");
                jtxtNomUser.setText("");
                jtxtPwUser.setText("");
                jtxtTpUser.setText("");
                jtxtPvUser.setText("");
            }
        });

        //------ Tratamento de eventos do Mouse do Usuário -------------------------------
        jTblUser.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int index = jTblUser.getSelectedRow();
                if (index >= 0 && index < listaUser.size()) {
                    Usuario user = listaUser.get(index);
                    jtxtCodUser.setText(String.valueOf(user.getIduser()));
                    jtxtNomUser.setText(user.getNmUser());
                    jtxtPwUser.setText(user.getPwUser());
                    jtxtTpUser.setText(user.getTpuser());
                    jtxtPvUser.setText(Character.toString(user.getPvUser()));
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

        add(jtxtCodUser);
        add(lblCodUser);
        add(lblNomUser);
        add(jtxtNomUser);
        add(lblPwUser);
        add(jtxtPwUser);
        add(lblTpUser);
        add(jtxtTpUser);
        add(lblPvUser);
        add(jtxtPvUser);
        add(scrlTbUser);

        repaint();
    }
}
