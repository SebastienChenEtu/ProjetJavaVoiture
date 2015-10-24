package serveur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import garage.Garage;
import garage.Voiture;

public class ServiceAppli extends Thread {

	private String nbVoiture;
	private String request;
	private Socket connectionSocket;
	private static Garage garage;

	public ServiceAppli(Socket socket, Garage garage) {
		connectionSocket = socket;
		this.garage = garage;
	}

	public void run() {
		// Création du flux en entrée attachée à la socket
		BufferedReader inFromClient;
		try {
			inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			// Creation du flux en sortie attache a la socket
			PrintWriter outToClient = new PrintWriter(
					new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream())), true);

			request = inFromClient.readLine();

			while(!request.toLowerCase().equals("stop")) {
				// Attente d'une demande de connexion sur la socket d'accueil
				switch(request.toLowerCase()) {
				
					// Processus de réservation
					case("reserver") :
						nbVoiture = Integer.toString(garage.getListVoiture().size());
						outToClient.println(nbVoiture);
						
						for(Voiture v : garage.getListVoiture()) {
							if(v.getStock() != 0) {
								request = v.getModele();
								outToClient.println(request);
								request = v.getModele() + " " + v.getCouleur();
								outToClient.println(request);
							}
						}
						
						request = inFromClient.readLine();
						boolean verifSaisie = false;
					
						while(!verifSaisie) {
							for(Voiture v : garage.getListVoiture()) {
								if(request.toLowerCase().equals(v.getModele().toLowerCase())) {
									request = "Voiture réservée.";
									outToClient.println(request);
									verifSaisie = true;
									break;
								}
							}
							if(!verifSaisie) {
								request = "Saisie incorrect.";
								// Emission des données au client
								outToClient.println(request);
								request = inFromClient.readLine();	
							}
						}
						break;

					// Processus de suivi
					case("suivi") :
						break;

					default :
						request = "Saisie incorrecte.";
						// Émission des données au client
						outToClient.println(request);
				}

				// Lecture des données arrivant du client
				request = inFromClient.readLine();
			}

			System.out.println("Programme terminé. Au revoir !");
			connectionSocket.close();

		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
