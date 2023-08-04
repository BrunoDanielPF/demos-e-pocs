package br.com.discord.app.model.pokemonv2.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sprites{

	@JsonProperty("other")
	private Other other;

	@JsonProperty("front_default")
	private String frontDefault;

	public void setOther(Other other){
		this.other = other;
	}

	public Other getOther(){
		return other;
	}

	public void setFrontDefault(String frontDefault){
		this.frontDefault = frontDefault;
	}

	public String getFrontDefault(){
		return frontDefault;
	}

	@Override
 	public String toString(){
		return 
			"Sprites{" + 
			"other = '" + other + '\'' + 
			",front_default = '" + frontDefault + '\'' + 
			"}";
		}
}