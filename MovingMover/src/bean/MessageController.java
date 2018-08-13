package bean;

import enumeration.MessageImportance;

public class MessageController {
	
	private MessageImportance messageImportance;
	private String contenu;
	/**
	 * @return the messageImportance
	 */
	public MessageImportance getMessageImportance() {
		return messageImportance;
	}
	/**
	 * @param messageImportance the messageImportance to set
	 */
	public void setMessageImportance(MessageImportance messageImportance) {
		this.messageImportance = messageImportance;
	}
	/**
	 * @return the contenu
	 */
	public String getContenu() {
		return contenu;
	}
	/**
	 * @param contenu the contenu to set
	 */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	
	

}
