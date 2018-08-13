package presentation.dto;

public abstract class EventDto {
	protected String idClasse;
	
	public EventDto(){
		this.idClasse = this.getClass().getSimpleName();
	}

	/**
	 * @return the idClasse
	 */
	public String getIdClasse() {
		return idClasse;
	}

	/**
	 * @param idClasse the idClasse to set
	 */
	public void setIdClasse(String idClasse) {
		this.idClasse = idClasse;
	}
	
	
}
