package serveur;
import java.net.*;

import garage.Garage;
import garage.Voiture;

public class Serveur {
	
	
	public static void main(String argv[]) throws Exception {
		
		
		// Creation de la socket d'accueil au port 8080
	
		Garage garage = new Garage(1);
		
		Voiture bmw = new Voiture("bobo","bmw","blanc",5);
		Voiture peugeot = new Voiture("bibi","peugeot","bleu",5); 
		Voiture citroen = new Voiture("baba","citroen","rouge",5);
		Voiture ferrari = new Voiture ("beau","ferrari","rouge",1);
		Voiture bmw2 = new Voiture("plop","bmw","gris",6);
		
		garage.ajoutVoiture(bmw);
		garage.ajoutVoiture(bmw2);
		garage.ajoutVoiture(peugeot);
		garage.ajoutVoiture(citroen);
		garage.ajoutVoiture(ferrari);
		
		garage.nbreVoitureTotal();
		
		
		
		ServerSocket welcomeSocket = new ServerSocket(8080);
		

		while(true) {
			Socket connectionSocket = welcomeSocket.accept();
			Thread service = new ServiceAppli(connectionSocket,garage);
			service.start();
		} // boucle et attend la connexion d'un nouveau client
	}
	
}
