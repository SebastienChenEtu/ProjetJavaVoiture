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
		List<String> listeModele;

		// Creation de la socket client, demande de connexion
		Socket clientSocket = new Socket("localhost", 8080);

		// Creation du flux en sortie
		PrintWriter outToServer = new PrintWriter(
				new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);

		// Creation du flux en entree
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		System.out.println(
				"Bienvenue! Voulez Vous: \n Reserver (taper reserver) \n Voir votre suivi (taper suivi) \n prendre une voiture en reserve (taper prendre) \n rendre une voiture (taper rendre) \n Voir les voitures déjà commandées (taper commandee) \n Quitter (taper stop)");
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
				System.out.println("Voici les Voitures que nous vous proposant: ");
				listeModele = new LinkedList<String>();
				for (int i = 0; i < nbreVoiture; i++) {
					answer = inFromServer.readLine();
					listeModele.add(answer);
					answer = inFromServer.readLine();
					System.out.println(answer);
					
				}

				//Reservation
				System.out.println("Veuillez Indiquer Le Modele souhaité :");
				answer = inFromUser.readLine();
				outToServer.println(answer);
				boolean verifSaisie = false; 
				while(!verifSaisie){	
					for(int i=0; i<listeModele.size() ; i++){
						if (answer.toLowerCase().equals(listeModele.get(i).toLowerCase())){
							answer = inFromServer.readLine();
							System.out.println(answer);
							answer = inFromServer.readLine();
							System.out.println("Voici votre numero de commande "+answer);
							answer = inFromServer.readLine();
							System.out.println("Voici votre mot de passe "+answer);
							verifSaisie = true;
							break;
						}
					}
					if(!verifSaisie){
						answer = inFromServer.readLine();
						System.out.println(answer + "Ressaisissez votre modéle : ");
						System.out.println("");
						answer = inFromUser.readLine();
						outToServer.println(answer);
					}
				}
				
				break;
			
			case ("prendre"):
				System.out.println("Veuillez entrez le numéro de commande et le mot de passe:");
				System.out.println("numéro de commande :");
				request = inFromUser.readLine();
				outToServer.println(request);
				System.out.println("mot de passe :");
				request = inFromUser.readLine();
				outToServer.println(request);
				answer = inFromServer.readLine();
				System.out.println(answer);
				break;
				
			case ("commandee"):
				nbModele = inFromServer.readLine();
			int nbreVoitureCmd = Integer.parseInt(nbModele);
			
				System.out.println("Voici les Voitures déjà commandées: ");
				if(nbreVoitureCmd == 0) {
					System.out.println("Aucune voiture commandée");
				}
				else {
					listeModele = new LinkedList<String>();

					for (int i=0; i<nbreVoitureCmd;i++) {
						answer = inFromServer.readLine();
						listeModele.add(answer);
						answer = inFromServer.readLine();
						System.out.println(answer);

						System.out
								.println("Veuillez Indiquer Le Modele souhaité :");
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
										+ "Ressaisissez votre modèle : ");
								System.out.println("");
								answer = inFromUser.readLine();
								outToServer.println(answer);
							}
						}

					}
				}
				
				break;
				
			case ("rendre"):
				System.out.println("Veuillez entrez le numéro de commande et le mot de passe:");
				System.out.println("numéro de commande :");
				request = inFromUser.readLine();
				outToServer.println(request);
				System.out.println("mot de passe :");
				request = inFromUser.readLine();
				outToServer.println(request);
				answer = inFromServer.readLine();
				System.out.println(answer);
				break;

			case ("suivi"):
				System.out.println("Veuillez entrez le numéro de commande et le mot de passe:");
				System.out.println("numéro de commande :");
				request = inFromUser.readLine();
				outToServer.println(request);
				System.out.println("mot de passe :");
				request = inFromUser.readLine();
				outToServer.println(request);
				answer = inFromServer.readLine();
				System.out.println(answer);
				break;

			default:
				// Lecture des donnees arrivant du serveur
				answer = inFromServer.readLine();
				System.out.println(answer
						+ "Veuillez saisir :\n Reserver pour voir la liste de voiture disponible "
						+ "\n Suivi pour visualiser le suivi de votre réservation");
				
				

				System.out.println("");
			}
			System.out.println(
					"Voulez Vous: \n Reserver (taper reserver) \n rendre une voiture (taper rendre) \n Voir votre suivi (taper suivi) \n prendre une voiture en reserve (taper prendre) \n Voir les voitures déjà commandées (taper commandee) \n Quitter (taper stop)");
			request = inFromUser.readLine();
			listeModele = null;
			// Emission des donnees au serveur
			outToServer.println(request);
		}

		clientSocket.close();
	}

}
