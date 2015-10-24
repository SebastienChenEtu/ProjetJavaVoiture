package garage;

import java.util.LinkedList;
import java.util.List;

public class Garage {

	private int nbEmployee;
	private int stockVoiture = 0;
	private List<Voiture> listVoiture;
	
	public Garage(int nbEmployee){
		this.nbEmployee = nbEmployee;
		listVoiture = new LinkedList<Voiture>();
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
	
	public void ajoutVoiture(Voiture v){
		listVoiture.add(v);
	}
	
	public void nbreVoitureTotal(){
		for(Voiture v : listVoiture){
			stockVoiture = stockVoiture + v.getStock();
		}
	}

	public List<Voiture> getListVoiture() {
		return listVoiture;
	}

	public void setListVoiture(List<Voiture> listVoiture) {
		this.listVoiture = listVoiture;
	}
	
}
