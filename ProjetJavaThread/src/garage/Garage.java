package garage;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Garage {

	private int nbEmployee;
	private int stockVoiture = 0;
	private List<Voiture> listVoiture;
	private BlockingQueue<Employee> listeEmployeeNonDispo;
	private List<CommandeVoiture> listeCmdVoiture;
	
	public Garage(int nbEmployee) {
		this.nbEmployee = nbEmployee;
		listVoiture = new LinkedList<Voiture>();
		listeCmdVoiture = new LinkedList<CommandeVoiture>();
		listeEmployeeNonDispo = new LinkedBlockingQueue<Employee>(nbEmployee);
			
	}

	public int getNbEmployee() {
		return nbEmployee;
	}

	public void setNbEmployee(int nbEmployee) {
		this.nbEmployee = nbEmployee;
	}

	public int getStockVoiture() {
		return stockVoiture;
	}

	public void setStockVoiture(int stockVoiture) {
		this.stockVoiture = stockVoiture;
	}
	
	public void ajoutVoiture(Voiture v) {
		listVoiture.add(v);
	}
	
	public void nbreVoitureTotal() {
		for(Voiture v : listVoiture) {
			stockVoiture = stockVoiture + v.getStock();
		}
	}

	public List<Voiture> getListVoiture() {
		return listVoiture;
	}

	public void setListVoiture(List<Voiture> listVoiture) {
		this.listVoiture = listVoiture;
	}

	public BlockingQueue<Employee> getListeEmployeeNonDispo() {
		return listeEmployeeNonDispo;
	}

	public void setListeEmployeeNonDispo(BlockingQueue<Employee> listeEmployeeNonDispo) {
		this.listeEmployeeNonDispo = listeEmployeeNonDispo;
	}

	public List<CommandeVoiture> getListeCmdvoiture() {
		return listeCmdVoiture;
	}

	public void setListeCmdvoiture(List<CommandeVoiture> listeCmdvoiture) {
		this.listeCmdVoiture = listeCmdvoiture;
	}
	
}
