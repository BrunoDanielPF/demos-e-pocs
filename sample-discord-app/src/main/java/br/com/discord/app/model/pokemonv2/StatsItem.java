package br.com.discord.app.model.pokemonv2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatsItem{

	@JsonProperty("stat")
	private Stat stat;

	@JsonProperty("base_stat")
	private int baseStat;

	@JsonProperty("effort")
	private int effort;

	public Stat getStat(){
		return stat;
	}

	public int getBaseStat(){
		return baseStat;
	}

	public int getEffort(){
		return effort;
	}

	@Override
 	public String toString(){
		return 
			"StatsItem{" + 
			"stat = '" + stat + '\'' + 
			",base_stat = '" + baseStat + '\'' + 
			",effort = '" + effort + '\'' + 
			"}";
		}
}