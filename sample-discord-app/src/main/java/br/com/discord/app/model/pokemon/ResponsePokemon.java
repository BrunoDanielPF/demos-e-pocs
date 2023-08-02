package br.com.discord.app.model.pokemon;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsePokemon{

	@JsonProperty("abilities")
	private List<AbilitiesItem> abilities;

	@JsonProperty("base_experience")
	private int baseExperience;

	public void setAbilities(List<AbilitiesItem> abilities){
		this.abilities = abilities;
	}

	public List<AbilitiesItem> getAbilities(){
		return abilities;
	}

	public void setBaseExperience(int baseExperience){
		this.baseExperience = baseExperience;
	}

	public int getBaseExperience(){
		return baseExperience;
	}

	@Override
 	public String toString(){
		return 
			"ResponsePokemon{" + 
			"abilities = '" + abilities + '\'' + 
			",base_experience = '" + baseExperience + '\'' + 
			"}";
		}
}