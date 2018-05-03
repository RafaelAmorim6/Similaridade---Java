package br.edu.ifsp.parametro;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Parametro 
{

	//vars
	private String caminho; // caminho para leitura do arquivo - recebe da controller
	private ArrayList<Pasta> pastas = new ArrayList<>(); //recebe cada endereco com os tipos
	
	//Construtor
	public Parametro(String pathFolder)
	{
		this.caminho = pathFolder;
	}
	
	//1. Recebe caminho do arquivo
	//2. Realiza a leitura de cada linha
	//3. Captura os caminhos e respectivas extensoes
	//4. Retorna as informacoes capturadas
	
	public ArrayList<Pasta> getPastas() {
		return pastas;
	}

	public void lerParametro() throws FileNotFoundException
	{
		
		File arquivo = new File(caminho);
		
		Scanner bf = new Scanner(arquivo); //buffer do texto
	
		while (bf.hasNextLine())
		{
			String linha = bf.nextLine();
			if (linha.length() == 0) {
				continue;
			}
			String[] recebe = linha.split(";");
			
			String[] ext = recebe[1].split(",");
			Pasta tempPasta = new Pasta(recebe[0],ext);
			pastas.add(tempPasta);
		}
		
		bf.close();
	}
		
		
}
	

