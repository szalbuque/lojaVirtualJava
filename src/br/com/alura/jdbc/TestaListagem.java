package br.com.alura.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaListagem {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory conFact = new ConnectionFactory();
		Connection con = conFact.recuperarConexao();

		PreparedStatement stm = con.prepareStatement("SELECT * FROM PRODUTO");
		stm.execute();

		ResultSet rst = stm.getResultSet();
		while(rst.next()) {
			int id = rst.getInt("id");
			String nome = rst.getString("nome");
			String descricao = rst.getString("descricao");
			System.out.println(id);
			System.out.println(nome);
			System.out.println(descricao);
		}
	}

}
