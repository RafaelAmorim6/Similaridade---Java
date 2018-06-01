package br.edu.ifsp.calcular;

import java.util.Map;
import java.util.HashMap;
import java.math.*;

public class Calculo
{
	
	
	public double calculaSimilaridade(Map <String, Double> mapa1, Map <String, Double> mapa2) 
	{
		
		  double comprimento1 = 0.0;
		  double comprimento2 = 0.0;
		  double prodInterno = 0.0;
		  
		  for (String key : mapa1.keySet())
		  {
		  		double temp = mapa1.get(key);
		  		comprimento1 = comprimento1 + (temp * temp); 
		  }
		  comprimento1 = Math.sqrt(comprimento1);
		  
		  for (String key : mapa2.keySet())
		  {
		  		double temp = mapa2.get(key);
		  		comprimento2 = comprimento2 + (temp * temp); 
		  }
		  comprimento2 = Math.sqrt(comprimento2);
		  
		  for(String key : mapa1.keySet())
		  {
		  		if(mapa2.get(key)!= null)
		  		{
		  			double temp1 = mapa1.get(key);
		  			double temp2 = mapa2.get(key);
		  
		  			prodInterno = prodInterno + (temp1*temp2);
		  		}
		  
		  }	
		  
		  double cosseno = prodInterno /(comprimento1 * comprimento2);
		  System.out.println("Valor cosseno: " + cosseno);
		
		  return cosseno;
	}

}
