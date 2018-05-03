package br.edu.ifsp.controle;

//Pacotes---------
import br.edu.ifsp. parametro.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

import br.edu.ifsp. banco.*;
import br.edu.ifsp. busca.*;
import br.edu.ifsp. calculo.*;
import br.edu.ifsp. leitura.*;
import br.edu.ifsp. web.*;
//---------------------


@SuppressWarnings("unused")
public class Main {

	private static ArrayList<Pasta> pastas;
	private static ArrayList<Arquivo> arquivos = new ArrayList<>();
	private static Map <String, Double> totalPalavras = new HashMap();
	
	public static void main(String[] args) throws Exception
	{
		//1. Inicializa a classe com o caminho do arquivo de caminhos
		Parametro p = new Parametro("C:\\Users\\VStudio\\Desktop\\Exportar\\Similaridade\\parametro.txt");
		//2. Executa funcao que captura as informacoes
		p.lerParametro();
		//3. Para cada caminho, cria um objeto Pasta
		Main.pastas = p.getPastas();
		//4.Para cada pasta, busca os arquivos
		for (Pasta pasta : Main.pastas) 
		{
			pasta.obterArquivos();
		}
		//Para cada pasta, adiciona os arquivos encontrados na lista de arquivos principal
		for (Pasta pasta : Main.pastas) 
		{
			for(String arq : pasta.getArquivos())
			{
				System.out.println(arq);
				Arquivo arquivo = Arquivo.criaArquivo(arq);
				arquivos.add(arquivo);
				arquivo.lerArquivo();
			}
		}
		//para cada arquivo, faz a leitura das palavras da key set e adiciona ou incrementa no Map Principal
		for (Arquivo arquivo : Main.arquivos)
		{
			for (String palavra : arquivo.palavras.keySet())
			{
				Double frequencia = Main.totalPalavras.get(palavra);
				if(frequencia == null)
				{
					frequencia = 0.0;
				}
				Main.totalPalavras.put(palavra, frequencia + 1);
			}
		}
		
		//Faz o cálculo do peso IDF de cada palavra, calculando o total de arquivos dividido pelo numero de ocorrencias em arquivos
		for (String printar : Main.totalPalavras.keySet())
		{
			System.out.println("Antes: " + printar + " " + Main.totalPalavras.get(printar));
			
			double totalArquivos = Main.arquivos.size();
			double valorKey = Main.totalPalavras.get(printar);
			Main.totalPalavras.put(printar, Math.log10(totalArquivos / valorKey));
			
			System.out.println("Depois: " + printar + " " + Main.totalPalavras.get(printar));
		}
		
		//dentro de cada arquivo faz o cálculo da frequencia da palavra * peso IDF geral
		for (Arquivo TF : Main.arquivos)
		{
			for(String word : TF.palavras.keySet())
			{
				System.out.println("Antes: " + word + " " + TF.palavras.get(word));
				
				double valorLocal = TF.palavras.get(word);
				double valorGeral = Main.totalPalavras.get(word);
				TF.palavras.put(word, (Math.log10(valorLocal)+1) * valorGeral);
				
				System.out.println("Depois: " + word + " " + TF.palavras.get(word));
			}
		}//fim-for
		
		ConexaoMySQL.getConexaoMySQL();
		 
		System.out.println(ConexaoMySQL.statusConection());
	}//fim-main
}
