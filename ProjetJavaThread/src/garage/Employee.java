package garage;

public class Employee extends Thread {

	private int temps;
	private CommandeVoiture commandeVoiture;
	private static Garage garage;
	
	public Employee(int temps, CommandeVoiture commandeVoiture, Garage garage) {
		this.temps = temps;
		this.commandeVoiture = commandeVoiture;
		this.garage = garage;
	}
	
	public void run() {
		try {
			prepareCommande();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public void prepareCommande() throws InterruptedException {
		//netoyage entretien plein carburant
		int tempsPreparation = (commandeVoiture.getVoiture().getTemps() + temps)/3 ;
		for(CommandeVoiture cmdVoiture : garage.getListeCmdvoiture()){
			if(cmdVoiture.getNumeroCommande() == commandeVoiture.getNumeroCommande()){
				cmdVoiture.setEtat("nettoyage");
				Thread.sleep(tempsPreparation);
				cmdVoiture.setEtat("entretien");
				Thread.sleep(tempsPreparation);
				cmdVoiture.setEtat("carburant");
				Thread.sleep(tempsPreparation);
				cmdVoiture.setEtat("pret");
				break;
			}
		}
		garage.getListeEmployeeNonDispo().remove();
		
	}

}
