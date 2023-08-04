package br.com.discord.app.model.pokemonv2.base;

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

	public void setStat(Stat stat){
		this.stat = stat;
	}

	public Stat getStat(){
		return stat;
	}

	public void setBaseStat(int baseStat){
		this.baseStat = baseStat;
	}

	public int getBaseStat(){
		return baseStat;
	}

	public void setEffort(int effort){
		this.effort = effort;
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