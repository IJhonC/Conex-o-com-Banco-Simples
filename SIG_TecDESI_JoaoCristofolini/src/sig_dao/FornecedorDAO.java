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
import sig_classes.Fornecedor;

/**
 *
 * @author joao_cristofolini
 */
public class FornecedorDAO {

    Connection conexao = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    ArrayList<Fornecedor> listaFor = new ArrayList<>();

    // Métodos C-R-U-D
    public int salvarFornecedor(Fornecedor forn) {			// C - CREATE
        conexao = ModuloConexao.conector();
        int status;
        try {
            stm = conexao.prepareStatement("insert into fornecedor values(null,?,?,?,?)");
            stm.setString(1, forn.getEmpresa());
            stm.setString(2, forn.getContato());
            stm.setString(3, forn.getFone());
            stm.setString(4, forn.getEmail());

            status = stm.executeUpdate();
            return status;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "FornecedorDAO(salvar fornecedor) - Erro: " + e);
            return e.getErrorCode();
        }
    }

    public ArrayList<Fornecedor> listarFornecedor() {		// R - RESTORE
        conexao = ModuloConexao.conector();
        String sql = "select * from fornecedor";
        try {
            stm = conexao.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Fornecedor forn = new Fornecedor();
                forn.setCod(rs.getInt("codigo"));
                forn.setEmpresa(rs.getString("empresa"));
                forn.setContato(rs.getString("contato"));
                forn.setFone(rs.getString("fone"));
                forn.setEmail(rs.getString("email"));
                listaFor.add(forn);
            }
            System.out.println("Fornecedores carregados com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "FornecedorDAO(listarFornecedor - Erro: +)" + e);
        }
        return listaFor;
    }

    public void alteraFornecedor(Fornecedor forn) {			// U - UPDATE
        conexao = ModuloConexao.conector();
        try {
            stm = conexao.prepareStatement("update fornecedor set empresa =?, contato=?,"
                    + " fone = ?, email = ? where codigo = ?");
            stm.setString(1, forn.getEmpresa());
            stm.setString(2, forn.getContato());
            stm.setString(3, forn.getFone());
            stm.setString(4, forn.getEmail());
            stm.setInt(5, forn.getCod());
            stm.execute();
            stm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "FornecedorDAO(alterarFornecedor) - Erro: " + e);
        }
    }

    public void excluirFornecedor(int cod) {				// D - DELETE
        conexao = ModuloConexao.conector();
        try {
            stm = conexao.prepareStatement("delete from fornecedor where codigo = ?");
            stm.setInt(1, cod);
            stm.execute();
            stm.close();
            JOptionPane.showMessageDialog(null, "Registro Excluido");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "fornecedorDAO (excluirfornecedor) - Erro: " + e);
        }
    }
}
