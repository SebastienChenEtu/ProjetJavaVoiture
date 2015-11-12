package garage;

/**
 * Cette classe sera utilisée lorsque la réservation d'une voiture est possible.
 *
 */
public class CommandeVoiture {

	private int numero;
	private String etat;
	private int numeroCommande;
	private Voiture voiture;
	
	/**
	 * Contructeur d'une voiture commandée
	 * @param numero
	 * @param voiture
	 * @param numeroCommande
	 * @param etat
	 */
	public CommandeVoiture(int numero, Voiture voiture, int numeroCommande, String etat) {
		this.numero = numero;
		this.voiture = voiture;
		this.numeroCommande = numeroCommande;
		this.etat = etat;
	}

	/**
	 * Récupère le numéro d'une voiture commandée
	 * @return numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Initialise le numéro d'une voiture commandée
	 * @param numero
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * Récupère l'état de la voiture commandée
	 * @return etat
	 */
	public String getEtat() {
		return etat;
	}

	/**
	 * Initialise l'état de la voiture commandée
	 * @param etat
	 */
	public void setEtat(String etat) {
		this.etat = etat;
	}

	/**
	 * Récupère le numéro de commande
	 * @return numeroCommande
	 */
	public int getNumeroCommande() {
		return numeroCommande;
	}

	/**
	 * Initialise le numéro de commande
	 * @param numeroCommande
	 */
	public void setNumeroCommande(int numeroCommande) {
		this.numeroCommande = numeroCommande;
	}

	/**
	 * Récupère la voiture
	 * @return voiture
	 */
	public Voiture getVoiture() {
		return voiture;
	}

	/**
	 * Initialise la voiture
	 * @param voiture
	 */
	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

}
