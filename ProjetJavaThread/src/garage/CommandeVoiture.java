package garage;

/**
 * Cette classe sera utilis�e lorsque la r�servation d'une voiture est possible.
 *
 */
public class CommandeVoiture {

	private int numero;
	private String etat;
	private int numeroCommande;
	private Voiture voiture;
	
	/**
	 * Contructeur d'une voiture command�e
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
	 * R�cup�re le num�ro d'une voiture command�e
	 * @return numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * Initialise le num�ro d'une voiture command�e
	 * @param numero
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * R�cup�re l'�tat de la voiture command�e
	 * @return etat
	 */
	public String getEtat() {
		return etat;
	}

	/**
	 * Initialise l'�tat de la voiture command�e
	 * @param etat
	 */
	public void setEtat(String etat) {
		this.etat = etat;
	}

	/**
	 * R�cup�re le num�ro de commande
	 * @return numeroCommande
	 */
	public int getNumeroCommande() {
		return numeroCommande;
	}

	/**
	 * Initialise le num�ro de commande
	 * @param numeroCommande
	 */
	public void setNumeroCommande(int numeroCommande) {
		this.numeroCommande = numeroCommande;
	}

	/**
	 * R�cup�re la voiture
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
