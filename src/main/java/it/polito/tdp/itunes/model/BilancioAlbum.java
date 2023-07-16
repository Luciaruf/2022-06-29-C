package it.polito.tdp.itunes.model;

public class BilancioAlbum implements Comparable<BilancioAlbum>{
	Album a;
	Double bilancio;
	
	public BilancioAlbum(Album a, Double bilancio) {
		super();
		this.a = a;
		this.bilancio = bilancio;
	}

	public Album getA() {
		return a;
	}

	public void setA(Album a) {
		this.a = a;
	}

	public Double getBilancio() {
		return bilancio;
	}

	public void setBilancio(Double bilancio) {
		this.bilancio = bilancio;
	}

	@Override
	public int compareTo(BilancioAlbum o) {
		return -(int) (this.bilancio-o.bilancio);
	}
	
	

}
