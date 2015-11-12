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

	/**
	 * Lancement de l'application c�t� client
	 * Main : Proposition de commandes permettant l'utilisation des fonctions propos�es
	 * - R�server
	 * - Prendre
	 * - Commander
	 * - Rendre
	 * - Suivi
	 */
	public static void main(String argv[]) throws Exception {

		String nbModele;
		String request;
		String answer;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		List<String> listeModele;

		// Creation de la socket client, demande de connexion
		Socket clientSocket = new Socket("localhost", 8080);

		// Creation du flux en sortie
		PrintWriter outToServer = new PrintWriter(
				new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);

		// Creation du flux en entree
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		System.out.println(
				"Bienvenue ! Voulez-vous: \n R�server (taper reserver) \n Voir votre suivi (taper suivi) \n Prendre une voiture en r�serve (taper prendre) \n Rendre une voiture (taper rendre) \n Voir les voitures d�j� command�es (taper commandee) \n Quitter (taper stop)");
		request = inFromUser.readLine();

		// Emission des donnees au serveur
		outToServer.println(request);

		while (!request.toLowerCase().equals("stop")) {
			switch (request.toLowerCase()) {

			case ("reserver"):

				// Lecture des donnees arrivant du serveur
				//affichage
				nbModele = inFromServer.readLine();
				int nbreVoiture = Integer.parseInt(nbModele);
				System.out.println("Voici les voitures que nous vous proposons :");
				listeModele = new LinkedList<String>();
				for (int i = 0; i < nbreVoiture; i++) {
					answer = inFromServer.readLine();
					listeModele.add(answer);
					answer = inFromServer.readLine();
					System.out.println(answer);
					
				}

				//Reservation
				System.out.println("Veuillez indiquer le mod�le souhait� :");
				answer = inFromUser.readLine();
				outToServer.println(answer);
				boolean verifSaisie = false; 
				while(!verifSaisie){	
					for(int i=0; i<listeModele.size() ; i++){
						if (answer.toLowerCase().equals(listeModele.get(i).toLowerCase())){
							answer = inFromServer.readLine();
							System.out.println(answer);
							answer = inFromServer.readLine();
							System.out.println("Voici votre num�ro de commande "+answer);
							answer = inFromServer.readLine();
							System.out.println("Voici votre mot de passe "+answer);
							verifSaisie = true;
							break;
						}
					}
					if(!verifSaisie){
						answer = inFromServer.readLine();
						System.out.println(answer + "Ressaisissez votre mod�le :");
						System.out.println("");
						answer = inFromUser.readLine();
						outToServer.println(answer);
					}
				}
				
				break;
			
			case ("prendre"):
				System.out.println("Veuillez entrer le num�ro de commande et le mot de passe :");
				System.out.println("Num�ro de commande :");
				request = inFromUser.readLine();
				outToServer.println(request);
				System.out.println("Mot de passe :");
				request = inFromUser.readLine();
				outToServer.println(request);
				answer = inFromServer.readLine();
				System.out.println(answer);
				break;
				
			case ("commandee"):
				nbModele = inFromServer.readLine();
			int nbreVoitureCmd = Integer.parseInt(nbModele);
			
				System.out.println("Voici les voitures d�j� command�es: ");
				if(nbreVoitureCmd == 0) {
					System.out.println("Aucune voiture command�e");
				}
				else {
					listeModele = new LinkedList<String>();

					for (int i=0; i<nbreVoitureCmd;i++) {
						answer = inFromServer.readLine();
						listeModele.add(answer);
						answer = inFromServer.readLine();
						System.out.println(answer);

						System.out
								.println("Veuillez indiquer le mod�le souhait� :");
						answer = inFromUser.readLine();
						outToServer.println(answer);
						boolean verifSaisie1 = false;
						while (!verifSaisie1) {
							for (int i1 = 0; i1 < listeModele.size(); i1++) {
								if (answer.toLowerCase().equals(
										listeModele.get(i1).toLowerCase())) {
									answer = inFromServer.readLine();
									System.out.println(answer);
									verifSaisie1 = true;
									break;
								}
							}
							if (!verifSaisie1) {
								answer = inFromServer.readLine();
								System.out.println(answer
										+ "Ressaisissez votre mod�le :");
								System.out.println("");
								answer = inFromUser.readLine();
								outToServer.println(answer);
							}
						}

					}
				}
				
				break;
				
			case ("rendre"):
				System.out.println("Veuillez entrer le num�ro de commande et le mot de passe :");
				System.out.println("Num�ro de commande :");
				request = inFromUser.readLine();
				outToServer.println(request);
				System.out.println("Mot de passe :");
				request = inFromUser.readLine();
				outToServer.println(request);
				answer = inFromServer.readLine();
				System.out.println(answer);
				break;

			case ("suivi"):
				System.out.println("Veuillez entrer le num�ro de commande et le mot de passe :");
				System.out.println("Num�ro de commande :");
				request = inFromUser.readLine();
				outToServer.println(request);
				System.out.println("Mot de passe :");
				request = inFromUser.readLine();
				outToServer.println(request);
				answer = inFromServer.readLine();
				System.out.println(answer);
				break;

			default:
				// Lecture des donnees arrivant du serveur
				answer = inFromServer.readLine();
				System.out.println(answer
						+ "Veuillez saisir :\n reserver pour voir la liste de voiture disponible "
						+ "\n suivi pour visualiser le suivi de votre r�servation");
				
				

				System.out.println("");
			}
			System.out.println(
					"Voulez-vous: \n R�server (taper reserver) \n Rendre une voiture (taper rendre) \n Voir votre suivi (taper suivi) \n Prendre une voiture en r�serve (taper prendre) \n Voir les voitures d�j� command�es (taper commandee) \n Quitter (taper stop)");
			request = inFromUser.readLine();
			listeModele = null;
			// Emission des donnees au serveur
			outToServer.println(request);
		}

		clientSocket.close();
	}

}
