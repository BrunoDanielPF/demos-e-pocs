package br.com.discord.app.model.pokemonv2.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OfficialArtwork{

	@JsonProperty("front_default")
	private String frontDefault;

	public void setFrontDefault(String frontDefault){
		this.frontDefault = frontDefault;
	}

	public String getFrontDefault(){
		return frontDefault;
	}

	@Override
 	public String toString(){
		return 
			"OfficialArtwork{" + 
			"front_default = '" + frontDefault + '\'' + 
			"}";
		}
}