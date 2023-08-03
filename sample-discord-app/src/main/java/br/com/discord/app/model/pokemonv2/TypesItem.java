package br.com.discord.app.model.pokemonv2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TypesItem{

	@JsonProperty("slot")
	private int slot;

	@JsonProperty("type")
	private Type type;

	public int getSlot(){
		return slot;
	}

	public Type getType(){
		return type;
	}

	@Override
 	public String toString(){
		return 
			"TypesItem{" + 
			"slot = '" + slot + '\'' + 
			",type = '" + type + '\'' + 
			"}";
		}
}