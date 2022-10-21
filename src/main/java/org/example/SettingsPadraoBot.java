package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class SettingsPadraoBot {
    private ArrayList<String> chatsLiberados = new ArrayList<>(Arrays.asList("geral", "bate-papo-do-anderson", "comandos"));
    private final String[] comandosPadroes = {"prefixo", "criarComando", "ajuda", "excluir", "comandosCriados", "adicionarCanal", "removerCanal", "wordle"};

    public void removerDosChatsLiberados(String chatParaRemover) {
        chatsLiberados.remove(chatParaRemover);
    }

    public void adicionarChatsLiberados(String chatParaAdicionar) {
        chatsLiberados.add(chatParaAdicionar);
    }

    public String getChatsLiberados(int x) {
        return chatsLiberados.get(x);
    }

    public int tamanhoComandosPadroes() {
        return comandosPadroes.length;
    }

    public int tamanhoChatsLiberados() {
        return chatsLiberados.size();
    }

    public boolean chatsLiberadoContem(String temp) {
        return chatsLiberados.contains(temp);
    }

    public String[] getComandosPadroes() {
        return comandosPadroes;
    }
}
