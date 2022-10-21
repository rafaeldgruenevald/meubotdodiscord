package org.example;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bot extends ListenerAdapter {
    String prefixo = "!"; // prefixo default do bot
    PartesDaMensagem partesDaMensagem = new PartesDaMensagem();
    CriarComandosBot criarComandos = new CriarComandosBot();
    HashMap<String, String> comandosCriados = new HashMap<>();
    SettingsPadraoBot setsPadrao = new SettingsPadraoBot();
    Wordle wordleGame = new Wordle();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        String txtMensagemRecebida = event.getMessage().getContentRaw();
        EmbedBuilder embedComandosInvalidos = new EmbedBuilder();
        EmbedBuilder embedComandosValidos = new EmbedBuilder();

        Async async = new Async();
        Thread asyncWordle = new Thread(async);

        for (int j = 0; j < setsPadrao.tamanhoChatsLiberados(); j++) {
            if (txtMensagemRecebida.startsWith(prefixo) && event.getChannel().getName().equals(setsPadrao.getChatsLiberados(j))) {
                partesDaMensagem.setMensagemSplitada(txtMensagemRecebida.split(" ", 2)); // divide mensagens enviadas em 2.
                if (partesDaMensagem.tamanhoMensagemSplitada() > 1) {
                    partesDaMensagem.setRestoMensagem(partesDaMensagem.getPartesDaMensagemSplitada(1)); // coloca toda a mensagem sem o prefixo em uma ‘string’.
                } else {
                    partesDaMensagem.setRestoMensagem("");
                }
                partesDaMensagem.setComando(partesDaMensagem.getPartesDaMensagemSplitada(0).replace(prefixo, "")); // coloca só o comando em uma ‘string’.

                switch (partesDaMensagem.getComando()) {
                    case "prefixo": // troca o prefixo do bot
                        prefixo = partesDaMensagem.getRestoMensagem();
                        embedComandosValidos.addField("Novo prefixo!", "Seu prefixo agora é " + partesDaMensagem.getRestoMensagem(), false);
                        embedComandosValidos.setColor(Color.GREEN);
                        event.getChannel().sendMessageEmbeds(embedComandosValidos.build()).queue();
                        break;
                    case "adicionarCanal":
                        if (setsPadrao.chatsLiberadoContem(partesDaMensagem.getRestoMensagem())) {
                            embedComandosInvalidos.addField("Comando Inválido!", "Chat já foi adicionado a lista de chats " +
                                    "em que comandos estão habilitados.", false);
                            embedComandosInvalidos.setColor(Color.RED);
                            event.getChannel().sendMessageEmbeds(embedComandosInvalidos.build()).queue();
                        } else {
                            embedComandosValidos.addField("Chat Adicionado!", "O chat " + partesDaMensagem.getRestoMensagem() + " foi adicionado a lista de chats " +
                                    "em que comandos estão habilitados.", false);
                            embedComandosValidos.setColor(Color.GREEN);
                            event.getChannel().sendMessageEmbeds(embedComandosValidos.build()).queue();
                            setsPadrao.adicionarChatsLiberados(partesDaMensagem.getRestoMensagem());
                        }
                        break;
                    case "removerCanal":
                        if (setsPadrao.chatsLiberadoContem(partesDaMensagem.getRestoMensagem())) {
                            setsPadrao.removerDosChatsLiberados(partesDaMensagem.getRestoMensagem());
                            embedComandosValidos.addField("Chat Removido da Lista!", "O chat " + partesDaMensagem.restoMensagem + " foi removido da lista de" +
                                    " chats em que comandos estão habilitados.", false);
                            embedComandosValidos.setColor(Color.GREEN);
                            event.getChannel().sendMessageEmbeds(embedComandosValidos.build()).queue();
                        } else {
                            embedComandosInvalidos.addField("Comando Inválido!", "O chat " + partesDaMensagem.restoMensagem + " não existe ou já foi excluido" +
                                    " da lista em que comandos estão habilitados.", false);
                            embedComandosInvalidos.setColor(Color.RED);
                            event.getChannel().sendMessageEmbeds(embedComandosInvalidos.build()).queue();
                        }
                        break;
                    case "criarComando":
                        String padraoCriarComando = "^[A-Za-z0-9]+ \\|.+\\| [0-9]+$"; // Expressão regular, cria um padrão que deve ser seguido.
                        Pattern padrao = Pattern.compile(padraoCriarComando); // Coloco a expressão regular em um Pattern.
                        Matcher verificacao = padrao.matcher(partesDaMensagem.getRestoMensagem()); // Crio um Matcher que vai ver se restoMensagem é == a padraoCriarComando
                        if (verificacao.matches()) {
                            criarComandos.setInfoCriarComando(partesDaMensagem.getRestoMensagem(), prefixo);
                            comandosCriados.put(criarComandos.getChamarComando(), criarComandos.getTxtParaComandoFalar());

                            EmbedBuilder comandoNovoCriado = new EmbedBuilder();
                            comandoNovoCriado.addField("Comando Novo Criado!", "O comando " + criarComandos.getChamarComando() + " foi criado!", false);
                            comandoNovoCriado.setColor(Color.GREEN);
                            event.getChannel().sendMessageEmbeds(comandoNovoCriado.build()).queue();

                        } else {
                            embedComandosInvalidos.addField("criarComandos foi incializado de maneira errada ou contem charcter inválido!",
                                    "A maneira correta seria: " + "!criarComando chamarComando |frase para o bot falar| xxxx (Numero de vezes que o bot ira repetir a mensagen)",
                                    false);
                            embedComandosInvalidos.setColor(Color.RED);
                            event.getChannel().sendMessageEmbeds(embedComandosInvalidos.build()).queue();
                        }
                        break;
                    case "excluir":
                        if (comandosCriados.containsKey(partesDaMensagem.getRestoMensagem())) {
                            comandosCriados.remove(partesDaMensagem.getRestoMensagem());
                            criarComandos.removerChamadasDeComandos(partesDaMensagem.getRestoMensagem());

                            embedComandosValidos.addField("Comando Removido!", "O comando " + partesDaMensagem.getRestoMensagem() + " foi excluido.", false);
                            embedComandosValidos.setColor(Color.GREEN);
                            event.getChannel().sendMessageEmbeds(embedComandosValidos.build()).queue();
                        } else {
                            for (int i = 0; i < setsPadrao.tamanhoComandosPadroes(); i++) {
                                if (partesDaMensagem.getRestoMensagem().equals(setsPadrao.getChatsLiberados(i))) {
                                    embedComandosInvalidos.addField("Comando Inválido!", "Você não pode excluir um comando padrão do bot.", false);
                                    embedComandosInvalidos.setColor(Color.RED);
                                    event.getChannel().sendMessageEmbeds(embedComandosInvalidos.build()).queue();
                                    break;
                                }
                            }
                            embedComandosInvalidos.addField("Comando Inválido", "Você tentou excluir um comando que não existe ou já foi excluido.", false);
                            embedComandosInvalidos.setColor(Color.RED);
                            event.getChannel().sendMessageEmbeds(embedComandosInvalidos.build()).queue();
                        }
                        break;
                    case "comandosCriados":
                        EmbedBuilder embedAjudaComandosCriados = new EmbedBuilder();
                        embedAjudaComandosCriados.setTitle("Comandos Personalizados Do Bot");
                        embedAjudaComandosCriados.setDescription("Estes são os comandos criados por esse servidor: ");
                        if (comandosCriados.size() == 0) {
                            embedComandosInvalidos.addField("Comando Inválido!", "Você não possui nenhum comando personalizado ainda.", false);
                            embedComandosInvalidos.setColor(Color.RED);
                            event.getChannel().sendMessageEmbeds(embedComandosInvalidos.build()).queue();
                        } else {
                            for (int i = 0; i < comandosCriados.size(); i++) {
                                embedAjudaComandosCriados.addField(prefixo + criarComandos.getChamadasDeComandos(i), comandosCriados.get(criarComandos.getChamadasDeComandos(i)), false);
                            }
                            embedAjudaComandosCriados.setColor(Color.GREEN);
                            event.getChannel().sendMessageEmbeds(embedAjudaComandosCriados.build()).queue();
                        }
                        break;
                    case "ajuda": // embed de comandos
                        Ajuda ajuda = new Ajuda();
                        ajuda.setAjuda();
                        EmbedBuilder embedAjuda = new EmbedBuilder();
                        embedAjuda.setTitle("Comandos do Bot", null);
                        embedAjuda.setDescription("Estes são os comandos disponiveis:");
                        for (int i = 0; i < setsPadrao.tamanhoComandosPadroes(); i++) {
                            embedAjuda.addField(prefixo + ajuda.getAjudaChamarComandosPadroes(i), ajuda.getAjudaComandosPadroes(i), false);
                        }
                        embedAjuda.setColor(Color.GREEN);
                        event.getChannel().sendMessageEmbeds(embedAjuda.build()).queue();
                        break;
                    case "wordle":
                        if (event.getChannel().getName().equals("wordle")) {

                            EmbedBuilder embedWordle = new EmbedBuilder();
                            embedWordle.setTitle("Wordle!", null);
                            embedWordle.setDescription("O Wordle que você já conhece chegou ao discord.");

                            async.setEmbedWorlde(embedWordle);
                            async.setWordle(wordleGame);
                            async.setEvent(event);

                            embedWordle.addField("", wordleGame.blankWordleStart(), false);
                            embedWordle.setColor(Color.GREEN);
                            event.getChannel().sendMessageEmbeds(embedWordle.build()).queue();

                            System.out.println("A palavra é " + wordleGame.getPalavraRandom());
                            asyncWordle.start();

                        } else {
                            embedComandosInvalidos.addField("Comando Inválido!", "Você não possui um canal nomeado wordle ou ele ainda não foi adicionado a lista" +
                                    " de canais liberados.", false);
                            embedComandosInvalidos.setColor(Color.RED);
                            event.getChannel().sendMessageEmbeds(embedComandosInvalidos.build()).queue();
                        }
                        break;
                    default:
                        if (comandosCriados.containsKey(partesDaMensagem.getComando())) {
                            for (int z = 0; z < Integer.parseInt(criarComandos.getRepeticao()); z++) {
                                event.getChannel().sendMessage(comandosCriados.get(partesDaMensagem.getComando())).queue();
                            }
                        } else {
                            embedComandosInvalidos.addField("Comando inválido!", "O comando " + partesDaMensagem.getComando() + " que você tentou utilizar não existe", false);
                            embedComandosInvalidos.setColor(Color.RED);
                            event.getChannel().sendMessageEmbeds(embedComandosInvalidos.build()).queue();
                        }
                        break;
                }
            }
        }
        if (event.getChannel().getName().equals("wordle") && !event.getMessage().getAuthor().isBot() && !event.getMessage().getContentRaw().contains("!")) { // Checa se a mensagem vem do canal wordle e n eh nem do bot nem eh a chamada do comando
            String palavraEnviada = event.getMessage().getContentRaw().toLowerCase();
            Pattern padraoDaPalavraEnviada = Pattern.compile("[A-Za-z]{5}");
            Matcher verificar = padraoDaPalavraEnviada.matcher(palavraEnviada);

            if (verificar.matches()) {
                if (!wordleGame.possuiEsseImputWordle(palavraEnviada)) {
                    wordleGame.setImputWordle(palavraEnviada, 1);
                } else {
                    wordleGame.setImputWordle(palavraEnviada, wordleGame.getImputWordle(palavraEnviada) + 1);
                }
                event.getMessage().addReaction(Emoji.fromUnicode("U+2705")).queue();
            } else {
                event.getMessage().addReaction(Emoji.fromUnicode("U+274E")).queue();
            }
        }
    }

}

