package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import garage.Voiture;

public class Client {

	public static void main(String argv[]) throws Exception {

		String nbModele;
		String request;
		String answer;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		List<String> listeModele = new LinkedList<String>();

		// Creation de la socket client, demande de connexion
		Socket clientSocket = new Socket("localhost", 8080);

		// Creation du flux en sortie
		PrintWriter outToServer = new PrintWriter(
				new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);

		// Creation du flux en entree
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		System.out.println(
				"Bienvenue! Voulez Vous: \n Reserver (taper reserver) \n Voir votre suivi (taper suivi) \n Quitter (taper stop)");
		request = inFromUser.readLine();

		// Emission des donnees au serveur
		outToServer.println(request);

		while (!request.toLowerCase().equals("stop")) {
			switch (request.toLowerCase()) {

			case ("reserver"):
				// processus de reservation

				// Lecture des donnees arrivant du serveur
				nbModele = inFromServer.readLine();
				int nbreVoiture = Integer.parseInt(nbModele);

				System.out.println("Voici les Voitures que nous vous proposant: ");

				for (int i = 0; i < nbreVoiture; i++) {
					answer = inFromServer.readLine();
					System.out.println(answer);
					
					listeModele.add(answer);
				}

				System.out.println("Veuillez Indiquer Le Modele souhaité :");
				answer = inFromUser.readLine();
				outToServer.println(answer);
				
				
				boolean verifSaisie = false; 
				
				while(!verifSaisie){
					
					for(int i=0; i<listeModele.size() ; i++){
						if (answer == listeModele.get(i)){
							
						}
						else {
							answer = inFromUser.readLine();
							System.out.println(answer + "Ressaisissez votre modéle : ");
							System.out.println("");
							
							outToServer.println(answer);
						}
						break;
					}
					
				}
				
				
				outToServer.println(answer);
				

				break;

			case ("suivi"):
				// processus de suivi
				break;

			default:
				// Lecture des donnees arrivant du serveur
				answer = inFromServer.readLine();
				System.out.println(answer
						+ "Veuillez saisir :\n Reserver pour voir la liste de voiture disponible "
						+ "\n Suivi pour visualiser le suivi de votre réservation");
				
				

				System.out.println("");
			}
			request = inFromUser.readLine();
			// Emission des donnees au serveur
			outToServer.println(request);
		}

		clientSocket.close();
	}

}
