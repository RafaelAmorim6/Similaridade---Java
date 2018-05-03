package br.edu.ifsp.leitura;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public abstract class Arquivo {

	protected String nome;
	public Map <String, Double> palavras = new HashMap();
	
	public Arquivo(String nome)
	{
		this.nome = nome;
	}
	
	public abstract void lerArquivo() throws IOException, FileNotFoundException;
	
	protected void processaPalavra(String texto)
	{
		String[] recebe = texto.split(" ");
		Double frequencia = 0.0;
		
		for(String word : recebe)
		{
			frequencia = palavras.get(word);
			
			if(frequencia == null)
			{
				frequencia = 0.0;
			}
			palavras.put(word, frequencia + 1);
		}
	}
	
	public static Arquivo criaArquivo(String nome)
	{
		if (nome.endsWith(".txt") == true)
		{
			return new ArquivoTxt(nome);				
		}
		else if (nome.endsWith(".pdf") == true)
		{
			return new ArquivoPdf(nome);				
		}
		else if (nome.endsWith(".docx") == true)
		{
			return new ArquivoDoc(nome);				
		}
		else
		
		return null;
	}
	
	public String padronizaTexto(String textoRecebido)
	{
		String sanear = textoRecebido;
		sanear = sanear.toLowerCase();
		sanear = sanear.replace(':', ' ');
		
		return sanear;
	}
	
	
}
