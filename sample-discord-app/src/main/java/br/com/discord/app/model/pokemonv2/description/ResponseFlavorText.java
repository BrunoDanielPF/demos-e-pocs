package br.com.discord.app.model.pokemonv2.description;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseFlavorText{

	@JsonProperty("flavor_text_entries")
	private List<FlavorTextEntriesItem> flavorTextEntries;

	public void setFlavorTextEntries(List<FlavorTextEntriesItem> flavorTextEntries){
		this.flavorTextEntries = flavorTextEntries;
	}

	public List<FlavorTextEntriesItem> getFlavorTextEntries(){
		return flavorTextEntries;
	}

	@Override
 	public String toString(){
		return 
			"ResponseFlavorText{" + 
			"flavor_text_entries = '" + flavorTextEntries + '\'' + 
			"}";
		}
}