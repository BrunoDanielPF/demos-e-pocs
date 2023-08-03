package br.com.discord.app;

import br.com.discord.app.model.pokemonv2.ResponsePokemon;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;

public class Bot extends ListenerAdapter {
    public static void main(String[] args) {

        JDA jda = JDABuilder.createDefault("token do portal discord developer")
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
        String pokemonId = event.getOption("pokemon").getAsString();
        final String PREFIX = "https://pokeapi.co/api/v2/";
        Client client = ClientBuilder.newClient();
        WebTarget baseTarget = client.target(PREFIX);
        WebTarget pokemonTarget = baseTarget.path("pokemon").path(pokemonId);

        Response respPokemon = pokemonTarget.request(MediaType.APPLICATION_JSON).get();
        ResponsePokemon response = respPokemon.readEntity(ResponsePokemon.class); // objeto que captura somente as habilidades
// =========================================================
        //TODO criar um novo bot que retorna a api do pokemon em json no discord
//        Gson jsonObj = new GsonBuilder().setPrettyPrinting().create();
//        String jsonResponse = jsonObj.toJson(response);
//        String content = "```json".concat("\n") + jsonResponse + "```"; // formata em json para ficar com cor no discord
//        event.reply(content).queue();
// =========================================================
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Informações do Pokemon: " + response.getName(), null);
        eb.setColor(new Color(173, 11, 255));
        eb.setDescription("teste descrição");
        response.getStats().forEach(statsItem -> eb.addField(statsItem.getStat().getName(), String.valueOf(statsItem.getBaseStat()), false));
//        eb.addField("Experiencia base do pokemon", String.valueOf(response.getBaseExperience()), false);
//        eb.addField("Habilidades do pokemon", String.valueOf(response.getAbilities()), false);
        eb.addBlankField(false);
        eb.setAuthor("Autor: Vonvernice");
        eb.setFooter("pokemon pokedex index " + pokemonId);
        eb.setImage(response.getSprites().getFrontDefault());
        eb.setThumbnail(response.getSprites().getFrontDefault());

        event.replyEmbeds(eb.build()).queue();
        client.close();
    }

    static class EmbedChannel extends Commands {


    }

    public void say(SlashCommandInteractionEvent event, String content) {
        event.reply(content).queue();
    }
}
