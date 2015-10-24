package garage;

public class Voiture {

	private String immatriculation;
	private String modele;
	private String couleur;
	private String etat;
	private int nbCommande;
	
	private int stock;
	
	public Voiture(String modele,String couleur, int stock) {
		this.modele = modele; 
		this.stock = stock;
		this.couleur = couleur;
	}
	
	public Voiture(String immatriculation, String modele, String couleur, int commande) {
		this.immatriculation = immatriculation;
		this.modele = modele;
		this.couleur = couleur;
		this.nbCommande = commande;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
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

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public int getNbCommande() {
		return nbCommande;
	}

	public void setNbCommande(int nbCommande) {
		this.nbCommande = nbCommande;
	}
	
}
