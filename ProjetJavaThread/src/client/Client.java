package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import garage.Voiture;

public class Client {

	public static void main(String argv[]) throws Exception {

		String nbVoiture;
		String request;
		String answer;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

		// Creation de la socket client, demande de connexion
		Socket clientSocket = new Socket("localhost", 8080);

		// Creation du flux en sortie
		PrintWriter outToServer = new PrintWriter(
				new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);

		// Creation du flux en entree
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		// reserver ou suivi
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
				nbVoiture = inFromServer.readLine();
				int nbreVoiture = Integer.parseInt(nbVoiture);

				System.out.println("Voici les Voitures que nous vous proposant: ");

				request = "envoi";
				for (int i = 0; i <= nbreVoiture; i++) {
                 	outToServer.print(request);
                 	System.out.println(request);
					answer = inFromUser.readLine();
					System.out.println(answer);
				}

				break;

			case ("suivi"):
				// processus de suivi
				break;

			default:
				// Lecture des donnees arrivant du serveur
				answer = inFromServer.readLine();
				System.out.println(answer
						+ "Veuillez saisir :\n Reserver pour voir la liste de voiture disponible \n Suivi pour visualiser le suivi de votre réservation");

				System.out.println("");
			}
			request = inFromUser.readLine();
			// Emission des donnees au serveur
			outToServer.println(request);
		}

		clientSocket.close();
	}

}
