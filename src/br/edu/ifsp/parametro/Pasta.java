package br.edu.ifsp.parametro;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Pasta {

	private String caminho;
	private String[] extensoes;
	private ArrayList<String> arquivos = new ArrayList<String>();

	public ArrayList<String> getArquivos() {
		return arquivos;
	}
	public Pasta(String caminho, String[] extensoes)
	{
		this.caminho = caminho;
		this.extensoes = extensoes;
	}
	//---------------------------------------------------------------------------
	public void obterArquivos()
	{
		List<File> fila = new ArrayList<>();
		fila.add(new File(this.caminho));
		
		while(fila.size()>0)
		{
			File pai = fila.get(0);
			File []filhos = pai.listFiles();
			if (filhos == null)
			{
				continue;
			}
			for(File filho: filhos)
			{
				if(filho.isFile())
				{
					System.out.println(filho.getAbsolutePath());
					for (String extensao : this.extensoes) 
					{
						if (filho.getName().endsWith(extensao)) 
						{
							this.arquivos.add(filho.getAbsolutePath());
							break;
						}//fim-if
					}//fim-for
				}//fim-if
				else 
				{
				 System.out.println(filho.getAbsolutePath());
				 fila.add(filho);	
				}//fim-else
			}//fim-for
			
			fila.remove(0);
			
		}//fim-while
	}//fim-obterArquivos()-------------------------------------------------------

	public String getCaminho() {return caminho;}
	public String[] getExtensoes() {return extensoes;}

}
