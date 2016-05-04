package it.polito.tdp.model;

import it.polito.tdp.db.ParolaDAO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

public class GrafoModel {
	
	//trova parole con stessa dimensione e restituisce lista
	public List<String> trovaParole(int dim){
		
		ParolaDAO dao = new ParolaDAO();
		List<String> lista = new ArrayList<String>();
		lista = dao.trovaParole(dim);
		return lista;
	}
	
	//confronta due stringhe e restituisce true se differiscono solo per una lettera
	public boolean isNeighbour(String s1, String s2){
		int sum = 0;
		char v1[] = s1.toCharArray();
		char v2[] = s2.toCharArray();
		for(int i=0; i<v1.length; i++){
			if(v1[i] != v2[i])
				sum++;
		}
			if(sum == 1)
				return true;
			else
				return false;
	}		
	
	public SimpleGraph<String, DefaultEdge> generaGrafo(List<String> lista){
		SimpleGraph<String, DefaultEdge> grafo = new SimpleGraph<String,DefaultEdge>(DefaultEdge.class);
	    //metto vertici
		for(String s : lista)
			grafo.addVertex(s);
		//ora devo creare collegamenti solo tra le parole che differiscono di una lettera : procedura ricorsiva
		recursiveEdges(lista,lista.size(),grafo);	
		return grafo;
		
	}
	//visita in profondita del grafo a partire dalla stringa start
	public List<String> visitaProfondita(SimpleGraph<String,DefaultEdge> grafo, String start){
        List<String> paroleVisitate = new LinkedList<String>();
        //iteratore del grafo 
        GraphIterator<String, DefaultEdge> visita = 
			new DepthFirstIterator<String, DefaultEdge>(grafo,start);
        while( visita.hasNext() ){
 			String c = visita.next();
 			if(c!= start) //la parola da cui parto non mi serve 
			  paroleVisitate.add(c);
		}
     return paroleVisitate;
	}
	
	//metodo ricorsivo per creare i collegamenti avendo la lista di parole
	public void recursiveEdges(List<String> lista, int passo,SimpleGraph<String,DefaultEdge> grafo){
		//cond terminazione
		if(passo == 0)
			return;
		else{
			String sVecchia = null;
			for(String s : lista){
			   if(sVecchia == null)
				   sVecchia = s;
			   else{
				   if(isNeighbour(sVecchia,s))
					   grafo.addEdge(sVecchia, s);
			   }
			}
			lista.remove(0);
			passo--;
			recursiveEdges(lista,passo,grafo);
			
		}
	}
	
	
	
}
