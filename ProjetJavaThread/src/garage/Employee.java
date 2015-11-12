package garage;

/**
 * Cette classe sera utilis�e lorsqu'il y aura une commande de voiture.
 *
 */
public class Employee extends Thread {

	private int temps;
	private CommandeVoiture commandeVoiture;
	private static Garage garage;
	
	/**
	 * Contructeur d'un employ�
	 * @param temps
	 * @param commandeVoiture
	 * @param garage
	 */
	public Employee(int temps, CommandeVoiture commandeVoiture, Garage garage) {
		this.temps = temps;
		this.commandeVoiture = commandeVoiture;
		this.garage = garage;
	}
	
	/**
	 * Lancement de la pr�paration de la commande
	 */
	public void run() {
		try {
			prepareCommande();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * M�thode de pr�paration de la commande
	 * @throws InterruptedException
	 */
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
