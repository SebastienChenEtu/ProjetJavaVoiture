package garage;

public class Voiture {

	
	private String modele;
	private String couleur;	
	private int stock;
	private int temps;
	
	public Voiture(String modele,String couleur, int stock , int temps) {
		this.modele = modele; 
		this.stock = stock;
		this.couleur = couleur;
		this.temps = temps;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public int getTemps() {
		return temps;
	}

	public void setTemps(int temps) {
		this.temps = temps;
	}
	
}
