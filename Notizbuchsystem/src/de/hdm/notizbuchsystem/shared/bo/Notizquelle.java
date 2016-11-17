package de.hdm.notizbuchsystem.shared.bo;

public class Notizquelle extends BusinessObject {

	private static final long serialVersionUID = 6460995603401695785L;

	private String url;
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return this.url;
	}
}


