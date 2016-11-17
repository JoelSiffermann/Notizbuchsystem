package de.hdm.notizbuchsystem.shared.bo;

public class Nutzer extends BusinessObject{

	private static final long serialVersionUID = 4281079191720764987L;
	
	private String vorname;

	private String name;
	
	private String email;
	
	public void setVorname(String vorname){
		this.vorname = vorname;
	}
	
	public String getVorname(){
		return this.vorname;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}

	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return this.email;
	}
}
