package br.com.discord.app.model.pokemonv2.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Other{

	@JsonProperty("official-artwork")
	private OfficialArtwork officialArtwork;

	public void setOfficialArtwork(OfficialArtwork officialArtwork){
		this.officialArtwork = officialArtwork;
	}

	public OfficialArtwork getOfficialArtwork(){
		return officialArtwork;
	}

	@Override
 	public String toString(){
		return 
			"Other{" + 
			"official-artwork = '" + officialArtwork + '\'' + 
			"}";
		}
}