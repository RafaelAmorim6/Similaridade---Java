package br.edu.ifsp.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConexaoMySQL {

	public static String status = "Não conectou...";
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/SIMILARIDADE?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static final String username = "root"; // nome de um usuário de seu BD
	static final String password = "";
	
	
	// Método de Conexão//
	public static java.sql.Connection getConexaoMySQL() throws Exception 
	{
		Connection connection = null; 
		Class.forName(JDBC_DRIVER);

		// Configurando a nossa conexão com um banco de dados//
		connection = DriverManager.getConnection(DB_URL, username, password);
		// Testa sua conexão//
		if (connection != null){status = ("STATUS--->Conectado com sucesso!");} 
		else{status = ("STATUS--->Não foi possivel realizar conexão");}
		return connection;
	}
	// Método que fecha sua conexão//
	public static boolean FecharConexao() throws Exception 
	{
		try 
		{
			ConexaoMySQL.getConexaoMySQL().close();
			return true;
		}
		catch (SQLException e) 
		{
			return false;
		}
	}
	// Método que reinicia sua conexão//
	public static java.sql.Connection ReiniciarConexao() throws Exception 
	{
		FecharConexao();
		return ConexaoMySQL.getConexaoMySQL();
	}
	//
	
	public static void insertArquivos(String caminhoArq) throws Exception
	{
		Class.forName(JDBC_DRIVER);
		Connection connection = DriverManager.getConnection(DB_URL, username, password);
		
		Statement stmt = connection.createStatement();
		caminhoArq = caminhoArq.replace('\\', '/');
		String sql = "INSERT INTO `similaridade`.`arquivo`(`nome`)" + "VALUES('"+caminhoArq+"')";
		stmt.execute(sql);
		
		stmt.close();
	}
	
	public static void insereMapa(String palavra) throws Exception
	{
		//INSERT INTO `similaridade`.`arquivo`(`idarquivo`,`nome`)VALUES(<{idarquivo: }>,<{nome: }>);
		Class.forName(JDBC_DRIVER);
		Connection connection = DriverManager.getConnection(DB_URL, username, password);
		
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO `similaridade`.`palavra`(`palavra`) VALUES(?)");
		stmt.setString(1, palavra);
		stmt.executeUpdate();
		
		stmt.close();
	}
	
	
	public static int consultaIdArquivo(String nomeArquivo) throws Exception
	{
		//INSERT INTO `similaridade`.`arquivo`(`idarquivo`,`nome`)VALUES(<{idarquivo: }>,<{nome: }>);
				Class.forName(JDBC_DRIVER);
				Connection connection = DriverManager.getConnection(DB_URL, username, password);
				System.out.println("Arquivo a ser consultado: " + nomeArquivo);
				PreparedStatement stmt = connection.prepareStatement("SELECT IDARQUIVO from `similaridade`.`arquivo` where NOME = ?");
				stmt.setString(1, nomeArquivo.replace("\\", "/"));
				ResultSet retorno = stmt.executeQuery();
				int codigoArquivo = 0;
				while(retorno.next())
				{
					codigoArquivo = retorno.getInt(1);
				}
				stmt.close();
				
				System.out.println("Codigo retornado do banco: "+ codigoArquivo);
				return codigoArquivo;
	}
	
	public static int consultaIdPalavra(String nomePalavra) throws Exception
	{
		//INSERT INTO `similaridade`.`arquivo`(`idarquivo`,`nome`)VALUES(<{idarquivo: }>,<{nome: }>);
				Class.forName(JDBC_DRIVER);
				Connection connection = DriverManager.getConnection(DB_URL, username, password);
				System.out.println("Palavra a ser consultada: " + nomePalavra);
				PreparedStatement stmt = connection.prepareStatement("SELECT IDpalavra from `similaridade`.`palavra` where PALAVRA = ?");
				stmt.setString(1, nomePalavra.replace("\\", "/"));
				ResultSet retorno = stmt.executeQuery();
				int codigoArquivo = 0;
				while(retorno.next())
				{
					codigoArquivo = retorno.getInt(1);
				}
				stmt.close();
				
				System.out.println("Codigo retornado do banco: "+ codigoArquivo);
				return codigoArquivo;
	}
	
	
	public static void insertTFIDF(int arquivo, int palavra, double tfidf) throws Exception
	{
		Class.forName(JDBC_DRIVER);
		Connection connection = DriverManager.getConnection(DB_URL, username, password);
		
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO `similaridade`.`arquivo_palavra`(`fk_arquivo`,`fk_palavra`,`tfidf`) VALUES(?,?,?)");
		stmt.setInt(1, arquivo);
		stmt.setInt(2, palavra);
		stmt.setDouble(3, tfidf);
		stmt.executeUpdate();
		
		stmt.close();
	}
}