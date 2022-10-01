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
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.recuperarConexao();
		//Assumimos a responsabilidade por fazer o commit das transações
		connection.setAutoCommit(false);
		
		try {
			PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?,?)",Statement.RETURN_GENERATED_KEYS);
			adicionaRegistro("Projetor", "Datashow", stm);
			adicionaRegistro("Tela", "Tela de projeção", stm);
			connection.commit();
			stm.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Rollback executado");
			connection.rollback();
		}
		

	}

	private static void adicionaRegistro(String nome, String descricao, PreparedStatement stm) throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, descricao);
		// abaixo, um teste de lançamento de exceção
		if (nome.equals("Tela")) {
			throw new RuntimeException("Não foi possível adicionar o produto");
		}
		stm.execute();

		ResultSet rst = stm.getGeneratedKeys();
		while(rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("O id criado foi: " + id);
		}
		rst.close();
	}

}
