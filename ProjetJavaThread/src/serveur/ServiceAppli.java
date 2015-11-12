package serveur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Random;

import garage.CommandeVoiture;
import garage.Employee;
import garage.Garage;
import garage.Voiture;

public class ServiceAppli extends Thread {

	private boolean verife;
	private String request;
	private String nbVoiture;
	private Socket connectionSocket;
	private static Garage garage;
	public static int numeroCommande = 1;

	/**
	 * Contructeur du service
	 * @param socket
	 * @param garage
	 */
	public ServiceAppli(Socket socket, Garage garage) {
		connectionSocket = socket;
		this.garage = garage;
	}

	/**
	 * Serveur appelle ce service permettant traiter les demandes de l'utilisateur
	 */
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
				switch(request.toLowerCase()) {

				case("reserver") :
					afficherListVoitureServer(outToClient);
				reserver(inFromClient,outToClient,garage);
				break;
				
				case("commandee") :
					afficherListVoitureServer(outToClient);
				reserver(inFromClient,outToClient,garage);
				break;

				case("prendre"):
					String commandePrendre = inFromClient.readLine();
				String mdpPrendre = inFromClient.readLine();
				verife = false;
				for(CommandeVoiture cmd : garage.getListeCmdvoiture()){
					if( Integer.toString(cmd.getNumeroCommande()).equals(commandePrendre)){
						if(Integer.toString(cmd.getNumero()).equals(mdpPrendre) && cmd.getEtat().equals("pret")){		
							request = "voiture sortie";
							cmd.setEtat("sortie");
							verife = true;
							break;
						}
					}
				}
				if(!verife){
					request = "saisie incorect";
				}
				outToClient.println(request);
				break;

				case("rendre"):
					String commandeRendu = inFromClient.readLine();
				String mdpRendu = inFromClient.readLine();
				verife = false;
				for(CommandeVoiture cmd : garage.getListeCmdvoiture()){
					if( Integer.toString(cmd.getNumeroCommande()).equals(commandeRendu)){
						if(Integer.toString(cmd.getNumero()).equals(mdpRendu) && cmd.getEtat().equals("sortie")){		
							request = "voiture rendu";
							verife = false;
							for(int i=0;i<garage.getListVoiture().size();i++){
								if(garage.getListVoiture().get(i).getModele().equals(cmd.getVoiture().getModele())){
									garage.getListVoiture().get(i).setStock(garage.getListVoiture().get(i).getStock() + 1);
									verife = true;
									garage.getListeCmdvoiture().remove(cmd);
									break;
								}
							}
							if(!verife){
								garage.ajoutVoiture(cmd.getVoiture());
								garage.getListVoiture().get(garage.getListVoiture().size()-1).setStock(garage.getListVoiture().get(garage.getListVoiture().size()-1).getStock() + 1);
								garage.getListeCmdvoiture().remove(cmd);
							}

							verife = true;
							break;
						}
					}
				}
				if(!verife){
					request = "saisie incorect";
				}
				outToClient.println(request);
				break;

				case("suivi") :
					String commandeSuivi = inFromClient.readLine();
				String mdpSuivi = inFromClient.readLine();
				verife = false;
				for(CommandeVoiture cmd : garage.getListeCmdvoiture()){
					if( Integer.toString(cmd.getNumeroCommande()).equals(commandeSuivi)){
						if(Integer.toString(cmd.getNumero()).equals(mdpSuivi)){		
							request = cmd.getEtat();
							verife = true;
							break;
						}
					}
				}
				if(!verife){
					request = "Saisie incorrecte";
				}
				outToClient.println(request);
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
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Affiche la liste des voitures disponibles au client
	 * @param outToClient
	 */
	public void afficherListVoitureServer(PrintWriter outToClient){
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
	}

	/**
	 * Méthode de réservation d'une voiture
	 * @param inFromClient
	 * @param outToClient
	 * @param garage
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void reserver(BufferedReader inFromClient, PrintWriter outToClient,Garage garage) throws IOException, InterruptedException{
		request = inFromClient.readLine();
		boolean verifSaisie = false;
		while(!verifSaisie) {
			//synchronized(garage){
			for(int i = 0; i < garage.getListVoiture().size();i++) {
				if(request.toLowerCase().equals(garage.getListVoiture().get(i).getModele().toLowerCase())) {
					synchronized(garage.getListVoiture().get(i)){
						verifSaisie = true;
						if(garage.getListVoiture().get(i).getStock()!=0){
							request = "Voiture réservée.";
							garage.getListVoiture().get(i).setStock(garage.getListVoiture().get(i).getStock()-1);

							Random rnd = new Random();
							int nombre = rnd.nextInt(30000);
							CommandeVoiture commandeVoiture = new CommandeVoiture(nombre,garage.getListVoiture().get(i),numeroCommande,"nonDispo" );
							Employee employee = new Employee(nombre,commandeVoiture,garage);
							garage.getListeEmployeeNonDispo().put(employee);
							garage.getListeCmdvoiture().add(commandeVoiture);
							// manque paassage de l'employee pour stocker les voiture reserver
							if(garage.getListVoiture().get(i).getStock() == 0)
								garage.getListVoiture().remove(i);
							outToClient.println(request);
							outToClient.println(Integer.toString(numeroCommande));
							outToClient.println(Integer.toString(nombre));
							numeroCommande++;
							employee.start();
						}else{
							request = "Voiture plus disponible";
							outToClient.println(request);
							request = inFromClient.readLine();	
						}
						break;
					}
				}
			}
			if(!verifSaisie) {
				request = "Saisie incorrect.";
				outToClient.println(request);
				request = inFromClient.readLine();	
			}
			//}
		}


	}


}
