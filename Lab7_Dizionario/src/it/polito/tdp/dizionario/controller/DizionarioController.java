package it.polito.tdp.dizionario.controller;

import it.polito.tdp.model.GrafoModel;

import java.util.List;
import java.net.URL;
import java.util.ResourceBundle;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class DizionarioController {
	
	private GrafoModel model = new GrafoModel();
	private SimpleGraph<String,DefaultEdge> grafo = new SimpleGraph<String,DefaultEdge>(DefaultEdge.class);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtLettere;

    @FXML
    private TextField txtCerca;

    @FXML
    private Button btnGrafo;

    @FXML
    private Button btnVicini;

    @FXML
    private Button btnConnessi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;
    
    public void setModel(GrafoModel model){
    	this.model = model;
    }
    

    @FXML
    void doConnessi(ActionEvent event) {
    	
    	String connessi = "";
    	for(String s : model.visitaProfondita(grafo, txtCerca.getText()))
    		connessi = connessi + s + " ";
    	txtResult.setText("Dal vertice " + txtCerca.getText() + " posso arrivare a: " + connessi);

    }

    @FXML
    void doGrafo(ActionEvent event) {
    	  	
    	int dimensione = Integer.parseInt(txtLettere.getText());
    	if(dimensione!= txtCerca.getText().length()){
    		txtResult.setDisable(false);
    		txtResult.setText("Parola e dimensione non corrispondono.");
    		return;
    	}
    	List<String> lista = model.trovaParole(dimensione);
    	grafo = model.generaGrafo(lista);
    	
    	String vertici = "";
    	String edges = "";
    	for(String s : grafo.vertexSet()){
    		vertici = vertici + s + "\n";
    	}
    	for(DefaultEdge e : grafo.edgeSet()){
    		edges = edges + e + "\n";
    	}
    	
    	txtResult.setText("VERTICI:\n" + vertici + "EDGES:\n" + edges);
    	    	
    	btnVicini.setDisable(false);
    	btnConnessi.setDisable(false);
    	txtResult.setDisable(false);

    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	txtLettere.clear();
    	txtCerca.clear();
    	txtResult.clear();
    	txtResult.setDisable(true);
    	btnVicini.setDisable(true);
        btnConnessi.setDisable(true);

    }

    @FXML
    void doVicini(ActionEvent event) {
    	
    	String vicini = "";
    	for(String s : Graphs.neighborListOf(grafo, txtCerca.getText()))
    		vicini = vicini + s + " ";
    	txtResult.setText("I vicini di "+ txtCerca.getText() + " sono " + vicini);
    	
    }

    @FXML
    void initialize() {
        assert txtLettere != null : "fx:id=\"txtLettere\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert txtCerca != null : "fx:id=\"txtCerca\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert btnGrafo != null : "fx:id=\"btnGrafo\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert btnVicini != null : "fx:id=\"btnVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert btnConnessi != null : "fx:id=\"btnConnessi\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Dizionario.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Dizionario.fxml'.";
        
        btnVicini.setDisable(true);
        btnConnessi.setDisable(true);
        txtResult.setDisable(true);

    }
}
