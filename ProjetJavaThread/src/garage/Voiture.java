package garage;

/**
 * Cette classe sera utlis�e pour d�terminer les caract�ristiques d'une voiture et son temps de pr�paration.
 *
 */
public class Voiture {

	
	private String modele;
	private String couleur;	
	private int stock;
	private int temps;
	
	/**
	 * Contructeur d'une voiture
	 * @param modele
	 * @param couleur
	 * @param stock
	 * @param temps
	 */
	public Voiture(String modele,String couleur, int stock , int temps) {
		this.modele = modele; 
		this.stock = stock;
		this.couleur = couleur;
		this.temps = temps;
	}

	/**
	 * R�cup�re le mod�le d'une voiture
	 * @return modele
	 */
	public String getModele() {
		return modele;
	}

	/**
	 * Initialise le mod�le d'une voiture
	 * @param modele
	 */
	public void setModele(String modele) {
		this.modele = modele;
	}

	/**
	 * R�cup�re le stock d'une voiture
	 * @return stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * Initialise le stock d'une voiture
	 * @param stock
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * R�cup�re la couleur d'une voiture
	 * @return couleur
	 */
	public String getCouleur() {
		return couleur;
	}

	/**
	 * Initialise la couleur d'une voiture
	 * @param couleur
	 */
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	/**
	 * R�cup�re le temps de la voiture � pr�parer
	 * @return
	 */
	public int getTemps() {
		return temps;
	}

	/**
	 * Initialise le temps de la voiture � pr�parer
	 * @param temps
	 */
	public void setTemps(int temps) {
		this.temps = temps;
	}
	
}
