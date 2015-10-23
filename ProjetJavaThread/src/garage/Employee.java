package garage;

public class Employee extends Thread{

	private String nom;
	private String prenom;
	
	public Employee(String nom, String prenom){
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public void run(){
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
}
