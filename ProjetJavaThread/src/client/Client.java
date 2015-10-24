package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Client {

	public static void main(String argv[]) throws Exception {

		String nbModele;
		String request;
		String answer;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		List<String> listeModele = new LinkedList<String>();

		// Cr�ation de la socket client, demande de connexion
		Socket clientSocket = new Socket("localhost", 8080);

		// Cr�ation du flux en sortie
		PrintWriter outToServer = new PrintWriter(
				new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);

		// Creation du flux en entree
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		System.out.println(
				"Bienvenue! Voulez-vous: \n R�server (taper reserver) \n Voir votre suivi (taper suivi) \n Quitter (taper stop)");
		request = inFromUser.readLine();

		// �mission des donn�ees au serveur
		outToServer.println(request);

		while(!request.toLowerCase().equals("stop")) {
			switch(request.toLowerCase()) {

				// Processus de r�servation
				case("reserver") :
					// Lecture des donn�ees arrivant du serveur
					nbModele = inFromServer.readLine();
					int nbreVoiture = Integer.parseInt(nbModele);
					System.out.println("Voici les voitures que nous vous proposons : ");

					for(int i = 0; i < nbreVoiture; i++) {
						answer = inFromServer.readLine();
						listeModele.add(answer);
						answer = inFromServer.readLine();
						System.out.println(answer);
					}

					System.out.println("Veuillez indiquer le mod�le souhait� :");
					answer = inFromUser.readLine();
					outToServer.println(answer);
				
					boolean verifSaisie = false; 
				
					while(!verifSaisie) {
						for(int i = 0; i < listeModele.size(); i++) {
							if(answer.toLowerCase().equals(listeModele.get(i).toLowerCase())) {
								answer = inFromServer.readLine();
								System.out.println(answer);
								verifSaisie = true;
								break;
							}
						}
						if(!verifSaisie) {
							answer = inFromServer.readLine();
							System.out.println(answer + "Ressaisissez votre mod�le : ");
							System.out.println("");
							answer = inFromUser.readLine();
							outToServer.println(answer);
						}
					}
					outToServer.println(answer);
					break;

				// Processus de suivi
				case("suivi") :
					break;

				default :
					// Lecture des donn�ees arrivant du serveur
					answer = inFromServer.readLine();
					System.out.println(answer
							+ "Veuillez saisir :\n \"reserver\" pour voir la liste des voitures disponibles"
							+ "\n \"suivi\" pour visualiser le suivi de votre r�servation");
					System.out.println("");
			}
			request = inFromUser.readLine();
			// �mission des donn�ees au serveur
			outToServer.println(request);
		}
		clientSocket.close();
	}

}
