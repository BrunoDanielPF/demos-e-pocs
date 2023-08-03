package br.com.discord.app;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;


/**
 * Hello world!
 *
 */
public class App implements EventListener {
    // class de teste, não executar !!!
    public static void main( String[] args ) throws InterruptedException {
        JDA jdaClient = JDABuilder.createDefault("token")
                .addEventListeners(new App())
                .setActivity(Activity.of(Activity.ActivityType.STREAMING, "teste mil"))
                .build();
        jdaClient.awaitReady();
    }

    @Override
    public void onEvent(GenericEvent event) {
        if (event instanceof ReadyEvent)
            System.out.println("API is ready!");
    }
}
