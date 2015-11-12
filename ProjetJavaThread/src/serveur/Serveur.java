package serveur;
import java.net.*;

import garage.Garage;
import garage.Voiture;

public class Serveur {
	
	/**
	 * Initialisation d'un garage et de 5 voitures avec leur stock qui sont ajoutées dans ce dernier
	 * Lancement du serveur
	 * @param argv
	 * @throws Exception
	 */
	public static void main(String argv[]) throws Exception {
		
		Garage garage = new Garage(1);
		
		Voiture bmw = new Voiture("BMW", "Blanche", 5,10000);
		Voiture peugeot = new Voiture("PEUGEOT", "Bleue", 5,5000); 
		Voiture citroen = new Voiture("CITROEN", "Rouge", 5,5000);
		Voiture ferrari = new Voiture ("FERRARI", "Rouge", 1,30000);
		Voiture toyota = new Voiture("TOYOTA", "Grise", 6,8000);
		
		garage.ajoutVoiture(bmw);
		garage.ajoutVoiture(toyota);
		garage.ajoutVoiture(peugeot);
		garage.ajoutVoiture(citroen);
		garage.ajoutVoiture(ferrari);
		
		garage.nbreVoitureTotal();
		
		// Création de la socket d'accueil au port 8080
		ServerSocket welcomeSocket = new ServerSocket(8080);
		
		while(true) {
			Socket connectionSocket = welcomeSocket.accept();
			Thread service = new ServiceAppli(connectionSocket,garage);
			service.start();
		} // Boucle et attend la connexion d'un nouveau client
	}
	
}
