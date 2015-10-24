package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public static void main(String argv[]) throws Exception {
		
		String request;
		String answer;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

		// Creation de la socket client, demande de connexion
		Socket clientSocket = new Socket("localhost", 8080);

		// Creation du flux en sortie
		PrintWriter outToServer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())),true);

		// Creation du flux en entree
		BufferedReader inFromServer = new BufferedReader(
				new InputStreamReader(
						clientSocket.getInputStream()));
		
		// reserver ou suivi
		System.out.println("Bienvenue, Vous pouvez procéder à votre reservation de véhicule! \nSaisir ok pour commencer.");
		
		request = inFromUser.readLine();
		// Emission des donnees au serveur
		outToServer.println(request);
		
		while(!request.toLowerCase().equals("stop")) {
			switch (request.toLowerCase()){
			
			case ("reserver"):
				// processus de reservation
				
				
				// Lecture des donnees arrivant du serveur
				answer = inFromServer.readLine();
				
				
				System.out.println("Voici les Voitures que nous vous proposant : " + answer + "\n Choisissez votre voiture.");
				
				request = inFromUser.readLine();
				
				
				// Emission des donnees au serveur
				outToServer.println(request);
				
				
			break;
			
			case ("suivi"):
				//processus de suivi
			break;
			
			default: 
				// Lecture des donnees arrivant du serveur
				answer = inFromServer.readLine();
				System.out.println(answer);
				System.out.println("");
			}					
			request = inFromUser.readLine();
			// Emission des donnees au serveur
			outToServer.println(request);
		}
	
		clientSocket.close();
	}
	
}
