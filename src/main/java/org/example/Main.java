package org.example;

import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    public static void main(String[] args) throws Exception {
        JDA jda = JDABuilder.createLight(,
                        GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new Bot())
                .build();

        jda.awaitReady();

    }
}