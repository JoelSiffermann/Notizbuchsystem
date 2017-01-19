package de.hdm.notizbuchsystem.shared.bo;

public class Notizquelle extends BusinessObject {

	private static final long serialVersionUID = 6460995603401695785L;

	private String url;
	private int notizquelleId;
	private int notizId;
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return this.url;
	}

	public int getNotizquelleId() {
		return notizquelleId;
	}

	public void setNotizquelleId(int notizquelleId) {
		this.notizquelleId = notizquelleId;
	}

	public int getNotizId() {
		return notizId;
	}

	public void setNotizId(int notizId) {
		this.notizId = notizId;
	}
}


