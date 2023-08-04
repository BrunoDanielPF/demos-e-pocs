package br.com.discord.app;

import br.com.discord.app.model.pokemonv2.base.ResponsePokemon;
import br.com.discord.app.model.pokemonv2.description.ResponseFlavorText;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;

public class Bot extends ListenerAdapter {
    public static void main(String[] args) {

        JDA jda = JDABuilder.createDefault("")
                .addEventListeners(new Bot())
                .setActivity(Activity.playing("com sua mae"))
                .build();

        // Sets the global command list to the provided commands (removing all others)
        jda.updateCommands().addCommands(
                Commands.slash("ping", "teste de ping com o bot"),
                Commands.slash("pokemon", "escolhe o pokemon para ter a resposta da api")
                        .addOption(STRING, "pokemon", "escolha um pokemon para ter informações atraves do ID", true),
                Commands.slash("say", "Faz o bot dizer o que você diz")
                        .addOption(STRING, "conteudo", "O que o bot deve dizer", true)
        ).queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        switch (event.getName()) {
            case "ping":
                long time = System.currentTimeMillis();
                event.reply("Pong!").setEphemeral(true)
                        .flatMap(v ->
                                event.getHook().editOriginalFormat("Pong: %d ms", System.currentTimeMillis() - time)
                        ).queue();
                break;
            case "say":
                say(event, event.getOption("conteudo").getAsString());
                break;
            case "pokemon":
                getPokemon(event);
        }
    }

    private void getPokemon(SlashCommandInteractionEvent event) {
        AtomicReference<String> descriptionPt = new AtomicReference<>("");
        final String pokemonId = event.getOption("pokemon").getAsString();
        final String PREFIX = "https://pokeapi.co/api/v2/";
        Client client = ClientBuilder.newClient();
        ResponsePokemon response = getResponsePokemon(pokemonId, PREFIX, client);
//        getDescriptionPokemon(descriptionPt, pokemonId, client);
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Informações base do Pokemon: " + formatName(response.getName()), null);
        eb.setColor(new Color(173, 11, 255));
        eb.setDescription(descriptionPt.get());
        response.getStats().forEach(statsItem -> eb.addField(formatName(statsItem.getStat().getName()), String.valueOf(statsItem.getBaseStat()), true));
        response.getTypes().forEach(type -> eb.addField("Tipo: ", formatName(type.getType().getName()), true));
        eb.addBlankField(false);
        eb.setAuthor("Autor: Vonvernice");
        eb.setFooter("pokemon pokedex index " + pokemonId);
        eb.setImage(response.getSprites().getOther().getOfficialArtwork().getFrontDefault());
        eb.setThumbnail(response.getSprites().getFrontDefault());

        event.replyEmbeds(eb.build()).queue();
        client.close();
    }

    private static ResponsePokemon getResponsePokemon(String pokemonId, String PREFIX, Client client) {
        WebTarget baseTarget = client.target(PREFIX);
        WebTarget pokemonTarget = baseTarget.path("pokemon").path(pokemonId);

        Response respPokemon = pokemonTarget.request(MediaType.APPLICATION_JSON).get();
        ResponsePokemon response = respPokemon.readEntity(ResponsePokemon.class);
        return response;
    }

    private static void getDescriptionPokemon(AtomicReference<String> descriptionPt, String pokemonId, Client client) {
        //TODO para habilitar a dependencia de traducao e necessario comprar licenca no google cloud plataform
        WebTarget speciesWebtarget = client.target("https://pokeapi.co/api/v2/pokemon-species/").path(pokemonId);
        Response responseSpeciesWebtarget = speciesWebtarget.request(MediaType.APPLICATION_JSON).get();
        ResponseFlavorText responseFlavorText = responseSpeciesWebtarget.readEntity(ResponseFlavorText.class);
        responseFlavorText.getFlavorTextEntries().forEach(text -> {
            if(text.getLanguage().getName().equals("en")){
                Translate translate = TranslateOptions.getDefaultInstance().getService();
                Translation translation = translate.translate(text.getLanguage().getName(), Translate.TranslateOption.sourceLanguage("en"), Translate.TranslateOption.targetLanguage("pt"));
                descriptionPt.set(translation.getTranslatedText());
            }
        });
    }

    private static String formatName(String name) {
        return StringUtils.capitalize(name);
    }

    public void say(SlashCommandInteractionEvent event, String content) {
        event.reply(content).queue();
    }
}
