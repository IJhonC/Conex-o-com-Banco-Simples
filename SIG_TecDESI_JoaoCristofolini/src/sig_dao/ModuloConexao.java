/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sig_dao;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author joao_cristofolini
 */
public class ModuloConexao {

    public static Connection conector() {
        Connection conexao = null;
        //Definição do Driver
        String driver = "com.mysql.cj.jdbc.Driver";

        //Informação do Banco de dados
        String url = "jdbc:mysql://localhost:3306/dbTecDes_CassinoOnline_JoaoCristofolini";
        String user = "root";
        String pass = "";

        //Tentatica de acesso ao banco de dados
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,user,pass);
            return conexao;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Erro na conexão do banco de dados: "+e);
            return null;
        }
    }
}
