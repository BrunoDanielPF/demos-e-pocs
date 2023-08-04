package br.com.discord.app.model.pokemonv2.description;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FlavorTextEntriesItem{

	@JsonProperty("language")
	private Language language;

	@JsonProperty("version")
	private Version version;

	@JsonProperty("flavor_text")
	private String flavorText;

	public void setLanguage(Language language){
		this.language = language;
	}

	public Language getLanguage(){
		return language;
	}

	public void setVersion(Version version){
		this.version = version;
	}

	public Version getVersion(){
		return version;
	}

	public void setFlavorText(String flavorText){
		this.flavorText = flavorText;
	}

	public String getFlavorText(){
		return flavorText;
	}

	@Override
 	public String toString(){
		return 
			"FlavorTextEntriesItem{" + 
			"language = '" + language + '\'' + 
			",version = '" + version + '\'' + 
			",flavor_text = '" + flavorText + '\'' + 
			"}";
		}
}