package serveur;
import java.io.*;
import java.net.*;

public class Serveur {
	
	public static void main(String argv[]) throws Exception {

		// Creation de la socket d'accueil au port 8080
		ServerSocket welcomeSocket = new ServerSocket(8080);

		while(true) {
			Socket connectionSocket = welcomeSocket.accept();
			Thread service = new ServiceAppli(connectionSocket);
			service.start();
		} // boucle et attend la connexion d'un nouveau client
	}
	
}
