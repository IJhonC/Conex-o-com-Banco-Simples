/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sig_dao;

import sig_classes.Cliente;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author joao_cristofolini
 */
public class ClienteDAO {

    Connection conexao = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    ArrayList<Cliente> listaCli = new ArrayList<>();

    // Métodos C-R-U-D
    public int salvarCliente(Cliente cli) {			// C - CREATE
        conexao = ModuloConexao.conector();
        int status;
        try {
            stm = conexao.prepareStatement("insert into cliente values(null,?,?,?,?)");
            stm.setString(1, cli.getNome());
            stm.setString(2, cli.getFone());
            stm.setString(3, cli.getEmail());
            stm.setString(4, cli.getEnde());

            status = stm.executeUpdate();
            return status;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ClienteDAO(salvar cliente) - Erro: " + e);
            return e.getErrorCode();
        }
    }

    public ArrayList<Cliente> listarCliente() {		// R - RESTORE
        conexao = ModuloConexao.conector();
        String sql = "select * from cliente";
        try {
            stm = conexao.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setCod(rs.getInt("codigo"));
                cli.setNome(rs.getString("nome"));
                cli.setFone(rs.getString("fone"));
                cli.setEmail(rs.getString("email"));
                cli.setEnde(rs.getString("ende"));
                listaCli.add(cli);
            }
            System.out.println("Clientes carregados com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "CLienteDAO(listarCliente - Erro: +)" + e);
        }
        return listaCli;
    }

    public void alteraCliente(Cliente cli) {			// U - UPDATE
        conexao = ModuloConexao.conector();
        try {
            stm = conexao.prepareStatement("update Cliente set nome =?, fone=?,"
                    + " email = ?, ende = ? where codigo = ?");
            stm.setString(1, cli.getNome());
            stm.setString(2, cli.getFone());
            stm.setString(3, cli.getEmail());
            stm.setString(4, cli.getEnde());
            stm.setInt(5, cli.getCod());
            stm.execute();
            stm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ClienteDAO(alterarCliente) - Erro: " + e);
        }
    }

    public void excluirCliente(int cod) {				// D - DELETE
        conexao = ModuloConexao.conector();
        try {
            stm = conexao.prepareStatement("delete from cliente where codigo = ?");
            stm.setInt(1, cod);
            stm.execute();
            stm.close();
            JOptionPane.showMessageDialog(null, "Registro Excluido");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ClienteDAO (excluircliente) - Erro: " + e);
        }
    }
}
