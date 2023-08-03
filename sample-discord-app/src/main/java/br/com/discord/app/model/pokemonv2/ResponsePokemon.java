package br.com.discord.app.model.pokemonv2;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsePokemon {

	@JsonProperty("types")
	private List<TypesItem> types;

	@JsonProperty("stats")
	private List<StatsItem> stats;

	@JsonProperty("name")
	private String name;

	@JsonProperty("weight")
	private int weight;

	@JsonProperty("sprites")
	private Sprites sprites;

	public List<TypesItem> getTypes(){
		return types;
	}

	public List<StatsItem> getStats(){
		return stats;
	}

	public String getName(){
		return name;
	}

	public int getWeight(){
		return weight;
	}

	public Sprites getSprites(){
		return sprites;
	}

	@Override
 	public String toString(){
		return 
			"ResponsePokemon{" +
			"types = '" + types + '\'' + 
			",stats = '" + stats + '\'' + 
			",name = '" + name + '\'' + 
			",weight = '" + weight + '\'' + 
			",sprites = '" + sprites + '\'' + 
			"}";
		}
}