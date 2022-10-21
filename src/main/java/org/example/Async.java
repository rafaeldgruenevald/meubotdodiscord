package org.example;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Async extends Wordle implements Runnable {
    Wordle wordle;
    EmbedBuilder embedWorlde = new EmbedBuilder();
    MessageReceivedEvent event;
    private int rodadaAtual = 1;
    private int rodadaFinal = 6;
    private StringBuilder mensagemWordle = new StringBuilder();
    public void setEmbedWorlde(EmbedBuilder embedWorlde) {
        this.embedWorlde = embedWorlde;
    }
    public void setWordle(Wordle wordle) {
        this.wordle = wordle;
    }
    public void setEvent(MessageReceivedEvent event) {
        this.event = event;
    }

    @Override
    public void run() {
        wordle.resetImputWordle();
        while(rodadaAtual <= rodadaFinal) {
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
            }

            embedWorlde.setDescription("Rodada: " + rodadaAtual + "/" + rodadaFinal);
            embedWorlde.clearFields();

            if(rodadaAtual == 1) {
                embedWorlde.addField("", wordle.getPalavraEmEmojis(wordle.getMaiorImputWordle()) + "\n" + wordle.wordleResposta() + "\n", false);
                mensagemWordle.append(wordle.getPalavraEmEmojis(wordle.getMaiorImputWordle())).append("\n").append(wordle.wordleResposta()).append("\n");
            } else {
                embedWorlde.addField("",mensagemWordle.toString() + wordle.getPalavraEmEmojis(wordle.getMaiorImputWordle()) + "\n" + wordle.wordleResposta() + "\n", false);
                mensagemWordle.append(wordle.getPalavraEmEmojis(wordle.getMaiorImputWordle())).append("\n").append(wordle.wordleResposta()).append("\n");
            }

            if(wordle.getMaiorImputWordle().equals(wordle.getPalavraRandom())) {
                if(rodadaAtual != 1) {
                    embedWorlde.addField("Acertou!", "Vitoria atingida em " + rodadaAtual + "ª" + " rodadas!", false);
                } else {
                    embedWorlde.addField("Acertou!", "Vitoria atingida em " + rodadaAtual + "ª" + " rodada!", false);
                }
                event.getChannel().sendMessageEmbeds(embedWorlde.build()).queue();
                wordle.resetNumeroRandom();
                rodadaAtual = 6;
            } else if(rodadaAtual == rodadaFinal) {
                embedWorlde.addField("Derrota!", "A palavra correta era " + wordle.getPalavraRandom(), false);
                event.getChannel().sendMessageEmbeds(embedWorlde.build()).queue();
            } else {
                event.getChannel().sendMessageEmbeds(embedWorlde.build()).queue();
            }

            wordle.resetImputWordle();
            rodadaAtual++;
        }
    }
}
