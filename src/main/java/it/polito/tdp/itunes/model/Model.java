package it.polito.tdp.itunes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.itunes.db.ItunesDAO;

public class Model {
	
	ItunesDAO dao;
	Graph<Album,DefaultWeightedEdge> graph;
	
	public Model() {
		this.dao = new ItunesDAO();
		this.graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	}
	
	public Graph creaGrafo(Integer prezzo) {
		this.graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.graph, this.dao.getVertices(prezzo));
		
		for(Album a : this.graph.vertexSet()) {
			for(Album a2 : this.graph.vertexSet()) {
				if(!a.equals(a2)) {
					
					
					if(a.getPrezzo()-a2.getPrezzo()!=0) {
						Graphs.addEdge(this.graph, a, a2, Math.abs(a.getPrezzo()-a2.getPrezzo()));
					}
					
				}
			}
		}
		
		
		return this.graph;
	}
	
	
	public List<BilancioAlbum> getAdiacenti(Album root){
		List<Album>successori = new ArrayList<>(Graphs.neighborListOf(this.graph, root));
		
		
		List<BilancioAlbum> bilanciosuccessori = new ArrayList<>();
		
		for(Album a: successori) {
			bilanciosuccessori.add(new BilancioAlbum(a,getBilancio(a)));
		}
		
		Collections.sort(bilanciosuccessori);
		
		return bilanciosuccessori;
	}
	
	public Double getBilancio(Album a) {
		Double bilancio = 0.0;
		List<Double> getBilanci = new ArrayList<>();
		
		
		for(DefaultWeightedEdge e : this.graph.edgesOf(a)) {
			bilancio += this.graph.getEdgeWeight(e);
			getBilanci.add(bilancio);
		}
		
		
		
		return bilancio/getBilanci.size();
	}
	
}
