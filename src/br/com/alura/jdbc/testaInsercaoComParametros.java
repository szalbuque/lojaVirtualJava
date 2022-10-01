package br.com.alura.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//vai usar o connection.prepareStatement ao invés do connection.createStatement, para evitar o SQL Injection

public class testaInsercaoComParametros {

	public static void main(String[] args) throws SQLException {
		//Apesar de haver um erro na string abaixo, o prepareStatement vai contornar o erro antes de rodar o sql
		String nome = "Luminária";
		String descricao = "Luminária de LED";
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.recuperarConexao();
		PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?,?)",Statement.RETURN_GENERATED_KEYS);
		adicionaRegistro(nome, descricao, stm);
		stm.close();
		connection.close();

	}

	private static void adicionaRegistro(String nome, String descricao, PreparedStatement stm) throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, descricao);
		stm.execute();

		ResultSet rst = stm.getGeneratedKeys();
		while(rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("O id criado foi: " + id);
		}
		rst.close();
	}

}
