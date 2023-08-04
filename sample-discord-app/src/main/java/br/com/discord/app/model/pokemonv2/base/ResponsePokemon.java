package br.com.discord.app.model.pokemonv2.base;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsePokemon{

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

	public void setTypes(List<TypesItem> types){
		this.types = types;
	}

	public List<TypesItem> getTypes(){
		return types;
	}

	public void setStats(List<StatsItem> stats){
		this.stats = stats;
	}

	public List<StatsItem> getStats(){
		return stats;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setWeight(int weight){
		this.weight = weight;
	}

	public int getWeight(){
		return weight;
	}

	public void setSprites(Sprites sprites){
		this.sprites = sprites;
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