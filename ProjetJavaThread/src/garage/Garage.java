package garage;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Cette classe sera utilisée pour stocker les employés, les voitures disponibles et les voitures commandées
 *
 */
public class Garage {

	private int nbEmployee;
	private int stockVoiture = 0;
	private List<Voiture> listVoiture;
	private BlockingQueue<Employee> listeEmployeeNonDispo;
	private List<CommandeVoiture> listeCmdVoiture;
	
	/**
	 * Contructeur d'un garage
	 * @param nbEmployee
	 */
	public Garage(int nbEmployee) {
		this.nbEmployee = nbEmployee;
		listVoiture = new LinkedList<Voiture>();
		listeCmdVoiture = new LinkedList<CommandeVoiture>();
		listeEmployeeNonDispo = new LinkedBlockingQueue<Employee>(nbEmployee);
			
	}

	/**
	 * Récupère le nombre d'employés
	 * @return nbEmployee
	 */
	public int getNbEmployee() {
		return nbEmployee;
	}

	/**
	 * Initialise le nombre d'employés
	 * @param nbEmployee
	 */
	public void setNbEmployee(int nbEmployee) {
		this.nbEmployee = nbEmployee;
	}

	/**
	 * Récupère le stock des voitures
	 * @return stockVoiture
	 */
	public int getStockVoiture() {
		return stockVoiture;
	}

	/**
	 * Initialise le stock des voitures
	 * @param stockVoiture
	 */
	public void setStockVoiture(int stockVoiture) {
		this.stockVoiture = stockVoiture;
	}
	
	/**
	 * Ajout d'une voiture dans la liste
	 * @param v
	 */
	public void ajoutVoiture(Voiture v) {
		listVoiture.add(v);
	}
	
	public void nbreVoitureTotal() {
		for(Voiture v : listVoiture) {
			stockVoiture = stockVoiture + v.getStock();
		}
	}

	/**
	 * Récupère la liste de voitures
	 * @return listVoiture
	 */
	public List<Voiture> getListVoiture() {
		return listVoiture;
	}

	/**
	 * Initialise la liste de voitures
	 * @param listVoiture
	 */
	public void setListVoiture(List<Voiture> listVoiture) {
		this.listVoiture = listVoiture;
	}

	/**
	 * Récupère la liste des employés non disponibles
	 * @return listeEmployeeNonDispo
	 */
	public BlockingQueue<Employee> getListeEmployeeNonDispo() {
		return listeEmployeeNonDispo;
	}

	/**
	 * Initialise la liste des employés non disponibles
	 * @param listeEmployeeNonDispo
	 */
	public void setListeEmployeeNonDispo(BlockingQueue<Employee> listeEmployeeNonDispo) {
		this.listeEmployeeNonDispo = listeEmployeeNonDispo;
	}

	/**
	 * Récupère la liste des voitures commandées
	 */
	public List<CommandeVoiture> getListeCmdvoiture() {
		return listeCmdVoiture;
	}

	/**
	 * Initialise la liste des voitures commandées
	 * @param listeCmdvoiture
	 */
	public void setListeCmdvoiture(List<CommandeVoiture> listeCmdvoiture) {
		this.listeCmdVoiture = listeCmdvoiture;
	}
	
}
