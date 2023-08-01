package br.com.discord.app;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;

public class Bot extends ListenerAdapter {
    public static void main(String[] args) {
        JDA jda = JDABuilder.createDefault("MTEzNTcxNzgzNjM3NTk5ODY4NA.G0SpC2.DSmCA7ilYTP0iCrC7QpfrNejcRQStEcnUyzarQ")
                .addEventListeners(new Bot())
                .setActivity(Activity.playing("Type /ping"))
                .build();

        // Sets the global command list to the provided commands (removing all others)
        jda.updateCommands().addCommands(
                Commands.slash("ping", "teste de ping com o bot"),
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
        }
    }

    public void say(SlashCommandInteractionEvent event, String content)
    {
        event.reply(content).queue();
    }
}
