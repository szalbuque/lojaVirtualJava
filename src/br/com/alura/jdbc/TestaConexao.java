package br.com.alura.jdbc;
import java.sql.Connection;
import java.sql.SQLException;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory conFact = new ConnectionFactory();
		Connection con = conFact.recuperarConexao();
		
		System.out.println("Fechando conexão!!");
		
		con.close();
	}

}
