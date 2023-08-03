package br.com.discord.app.model.pokemonv2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sprites{

	@JsonProperty("front_default")
	private String frontDefault;

	public String getFrontDefault(){
		return frontDefault;
	}

	@Override
 	public String toString(){
		return 
			"Sprites{" + 
			"front_default = '" + frontDefault + '\'' + 
			"}";
		}
}