package br.edu.ifsp.leitura;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArquivoTxt extends Arquivo {

	public ArquivoTxt(String nome) {
		super(nome);
		
	}

	@Override
	public void lerArquivo() throws FileNotFoundException 
	{
		
		File arquivo = new File(nome);
		
		Scanner bf = new Scanner(arquivo); //buffer do texto
		String linha = " ";
		while (bf.hasNextLine())
		{		
			linha = linha + " " + bf.nextLine();
			if (linha.length() == 0) {
				continue;
			}
			System.out.println(linha);
			
		}
		bf.close();
		this.processaPalavra(linha);
		
	}
	
	

}
