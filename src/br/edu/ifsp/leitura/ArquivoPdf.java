package br.edu.ifsp.leitura;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper; 

public class ArquivoPdf extends Arquivo{

	public ArquivoPdf(String nome) {
		super(nome);
	}
	@Override
	public void lerArquivo() throws IOException, FileNotFoundException
	{
		PDDocument document = PDDocument.load(new File(nome));
		String text = new PDFTextStripper().getText(document);
		this.processaPalavra(text);
		System.out.println("Text:" + text);
		document.close();
	}

}
