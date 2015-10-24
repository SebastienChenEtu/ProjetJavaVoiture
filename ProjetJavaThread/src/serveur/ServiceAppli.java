package serveur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import org.omg.CORBA.Request;

import garage.Garage;

public class ServiceAppli extends Thread {
	
	String request;
	Socket connectionSocket;
	private static Garage garage;


	public ServiceAppli(Socket socket,Garage garage) {
		connectionSocket = socket;
		this.garage = garage;
	}

	public void run() {
		// Creation du flux en entree attache a la socket
		BufferedReader inFromClient;
		try {
			inFromClient = new BufferedReader(
					new InputStreamReader(
							connectionSocket.getInputStream()));
			// Creation du flux en sortie attache a la socket
			PrintWriter outToClient = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									connectionSocket.getOutputStream())),true);

			request = inFromClient.readLine();
			
			while(!request.toLowerCase().equals("stop")) {
				// Attente d'une demande de connexion sur la socket d'accueil
				switch (request.toLowerCase()){
				
				case ("reserver"):
					// processus de reservation
				break;
				
				case ("suivi"):
					//processus de suivi
				break;
				
				default: 
					request = "Saisi incorrect. Veuillez saisir :/n Reserver pour voir la liste de voiture disponible /n Suivi pour visualiser le suivi de votre réservation";
					// Emission des donnees au client
					outToClient.println(request);
				}					

				// Lecture des donnees arrivant du client
				request = inFromClient.readLine();
			}

			connectionSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
