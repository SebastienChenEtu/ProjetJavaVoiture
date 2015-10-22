package garage;

public class Voiture {

	private String immatriculation;
	private String modele;
	private int stock;
	
	public Voiture(String immatriculation, String modele, int stock){
		this.immatriculation = immatriculation;
		this.modele = modele;
		this.stock = stock;
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
}
