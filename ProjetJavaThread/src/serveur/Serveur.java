package serveur;
import java.net.*;

import garage.Garage;
import garage.Voiture;

public class Serveur {
	
	public static void main(String argv[]) throws Exception {
		
	
		Garage garage = new Garage(1);
		
		Voiture bmw = new Voiture("BMW","blanc",5);
		Voiture peugeot = new Voiture("PEUGEOT","bleu",5); 
		Voiture citroen = new Voiture("CITROEN","rouge",5);
		Voiture ferrari = new Voiture ("FERRARI","rouge",1);
		Voiture toyota = new Voiture("TOYOTA","gris",6);
		
		garage.ajoutVoiture(bmw);
		garage.ajoutVoiture(toyota);
		garage.ajoutVoiture(peugeot);
		garage.ajoutVoiture(citroen);
		garage.ajoutVoiture(ferrari);
		
		garage.nbreVoitureTotal();
		
		// Creation de la socket d'accueil au port 8080
		ServerSocket welcomeSocket = new ServerSocket(8080);
		

		while(true) {
			Socket connectionSocket = welcomeSocket.accept();
			Thread service = new ServiceAppli(connectionSocket,garage);
			service.start();
		} // boucle et attend la connexion d'un nouveau client
	}
	
}
