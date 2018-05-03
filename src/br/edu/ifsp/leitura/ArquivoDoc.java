package br.edu.ifsp.leitura;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class ArquivoDoc extends Arquivo{

	public ArquivoDoc(String nome) {
		super(nome);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void lerArquivo() throws IOException, FileNotFoundException
	{
		 XWPFDocument docx = new XWPFDocument(new FileInputStream(nome));
	      
	     XWPFWordExtractor documento = new XWPFWordExtractor(docx);
	     String extracao = documento.getText();
	     this.processaPalavra(extracao);
	     System.out.println(extracao);
	     documento.close();
	}

}
