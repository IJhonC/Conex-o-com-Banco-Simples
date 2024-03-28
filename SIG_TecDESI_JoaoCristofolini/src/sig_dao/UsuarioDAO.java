/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sig_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sig_classes.Usuario;

/**
 *
 * @author joao_cristofolini
 */
public class UsuarioDAO {

    Connection conexao = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    ArrayList<Usuario> listaUser = new ArrayList<>();

    // Métodos C-R-U-D
    public int salvarUsuario(Usuario user) {			// C - CREATE
        conexao = ModuloConexao.conector();
        int status;
        try {
            stm = conexao.prepareStatement("insert into tbuser values(null,?,?,?,?)");
            stm.setString(1, user.getNmUser());
            stm.setString(2, user.getPwUser());
            stm.setString(3, user.getTpuser());
            stm.setString(4, Character.toString(user.getPvUser()));

            status = stm.executeUpdate();
            return status;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO(salvar usuario) - Erro: " + e);
            return e.getErrorCode();
        }
    }

    public ArrayList<Usuario> listarUsuario() {		// R - RESTORE
        conexao = ModuloConexao.conector();
        String sql = "select * from tbuser";
        try {
            stm = conexao.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setIduser(rs.getInt("id_user"));
                user.setNmUser(rs.getString("nm_user"));
                user.setPwUser(rs.getString("pw_user"));
                user.setTpuser(rs.getString("tp_user"));
                user.setPvUser(rs.getString("pv_user").charAt(0));
                listaUser.add(user);
            }
            System.out.println("Usuários carregados com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO(listarUsuario - Erro: +)" + e);
        }
        return listaUser;
    }

    public void alteraUsuario(Usuario user) {			// U - UPDATE
        conexao = ModuloConexao.conector();
        try {
            stm = conexao.prepareStatement("update tbuser set nm_user =?, pw_user=?,"
                    + " tp_user = ?, pv_user = ? where id_user = ?");
            stm.setString(1, user.getNmUser());
            stm.setString(2, user.getPwUser());
            stm.setString(3, user.getTpuser());
            stm.setString(4, Character.toString(user.getPvUser()));
            stm.setInt(5, user.getIduser());
            stm.execute();
            stm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO(alterarUsuario) - Erro: " + e);
        }
    }

    public void excluirUsuario(int cod) {				// D - DELETE
        conexao = ModuloConexao.conector();
        try {
            stm = conexao.prepareStatement("delete from tbuser where id_user = ?");
            stm.setInt(1, cod);
            stm.execute();
            stm.close();
            JOptionPane.showMessageDialog(null, "Registro Excluido");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO (excluirUsuario) - Erro: " + e);
        }
    }
}
