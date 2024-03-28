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
import sig_classes.Cliente;
import sig_classes.Produto;

/**
 *
 * @author joao_cristofolini
 */
public class ProdutoDAO {

    Connection conexao = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    ArrayList<Produto> listaPro = new ArrayList<>();

    // Métodos C-R-U-D
    public int salvarProduto(Produto pro) {			// C - CREATE
        conexao = ModuloConexao.conector();
        int status;
        try {
            stm = conexao.prepareStatement("insert into produto values(null,?,?,?,?)");
            stm.setString(1, pro.getDescr());
            stm.setString(2, pro.getUnidade());
            stm.setString(3, String.valueOf(pro.getQtd()));
            stm.setString(4, String.valueOf(pro.getPreco()));

            status = stm.executeUpdate();
            return status;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ProdutoDAO(salvar produto) - Erro: " + e);
            return e.getErrorCode();
        }
    }

    public ArrayList<Produto> listarProduto() {		// R - RESTORE
        conexao = ModuloConexao.conector();
        String sql = "select * from produto";
        try {
            stm = conexao.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Produto pro = new Produto();
                pro.setCod(rs.getInt("codigo"));
                pro.setDescr(rs.getString("descr"));
                pro.setUnidade(rs.getString("unidade"));
                pro.setQtd(rs.getFloat("qnt"));
                pro.setPreco(rs.getFloat("preco"));
                listaPro.add(pro);
            }
            System.out.println("Produtos carregados com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ProdutoDAO(listarProduto - Erro: +)" + e);
        }
        return listaPro;
    }

    public void alterarProduto(Produto pro) {			// U - UPDATE
        conexao = ModuloConexao.conector();
        try {
            stm = conexao.prepareStatement("update produto set descr =?, unidade=?,"
                    + " qnt = ?, preco = ? where codigo = ?");
            stm.setString(1, pro.getDescr());
            stm.setString(2, pro.getUnidade());
            stm.setFloat(3, pro.getQtd());
            stm.setFloat(4, pro.getPreco());
            stm.setInt(5, pro.getCod());
            stm.execute();
            stm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ProdutoDAO(alterarProduto) - Erro: " + e);
        }
    }

    public void excluirProduto(int cod) {				// D - DELETE
        conexao = ModuloConexao.conector();
        try {
            stm = conexao.prepareStatement("delete from produto where codigo = ?");
            stm.setInt(1, cod);
            stm.execute();
            stm.close();
            JOptionPane.showMessageDialog(null, "Registro Excluido");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ProdutoDAO (excluirProduto) - Erro: " + e);
        }
    }
}
