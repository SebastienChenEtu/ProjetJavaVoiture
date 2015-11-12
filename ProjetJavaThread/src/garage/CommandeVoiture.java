package garage;

public class CommandeVoiture {

	private int numero;
	private String etat;
	private int numeroCommande;
	private Voiture voiture;
	
	public CommandeVoiture(int numero, Voiture voiture, int numeroCommande, String etat) {
		this.numero = numero;
		this.voiture = voiture;
		this.numeroCommande = numeroCommande;
		this.etat = etat;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public int getNumeroCommande() {
		return numeroCommande;
	}

	public void setNumeroCommande(int numeroCommande) {
		this.numeroCommande = numeroCommande;
	}

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

}
