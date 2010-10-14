package tkorg.idrs.core.extraction;

import tkorg.idrs.utilities.StringUtility;

public class Author {
	
	private String name;
	private String affiliation;
	private String email;
	private String web;
	
	public Author(){
		this.name = "null";
		this.affiliation = "null";
		this.email = "null";
		this.web = "null";
	}
	
	public Author(String name, String aff, String email, String web){
		
		this.name = name.trim();
		this.affiliation = aff.trim();
		this.email = email.trim();
		this.web = web.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		String newContent = StringUtility.removeNewLineCharacter(name);
		this.name = newContent.trim();
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		String newContent = StringUtility.removeNewLineCharacter(affiliation);
		this.affiliation = newContent.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		String newContent = StringUtility.removeNewLineCharacter(email);
		this.email = newContent.trim();
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		String newContent = StringUtility.removeNewLineCharacter(web);
		this.web = newContent.trim();
	}
	
	
}
