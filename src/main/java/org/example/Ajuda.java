package org.example;

import java.util.HashMap;

public class Ajuda extends SettingsPadraoBot {
    private final HashMap<String, String> ajudaComandosPadroes = new HashMap<>();

    private final String[] ajudaChamarComandosPadroes = getComandosPadroes();

    public void setAjuda() {
        ajudaComandosPadroes.put("prefixo", "Permite que você troque o prefixo.");
        ajudaComandosPadroes.put("adicionarCanal", "Adiciona canal da lista de canais em que se pode utilizar comandos.");
        ajudaComandosPadroes.put("removerCanal", "Remove canal da lista de canais em que se pode utilizar comandos.");
        ajudaComandosPadroes.put("criarComando", "Permite você criar comandos para enviar mensagens.");
        ajudaComandosPadroes.put("excluir", "Permite você excluir comandos criados.");
        ajudaComandosPadroes.put("ajuda", "Mostra todos comando do bot.");
        ajudaComandosPadroes.put("comandosCriados", "Mostra todos os comandos personalizados criados.");
        ajudaComandosPadroes.put("wordle", "O jogo Wordle dentro do discord!");
    }

    public String getAjudaComandosPadroes(int x) {
        return ajudaComandosPadroes.get(ajudaChamarComandosPadroes[x]);
    }

    public String getAjudaChamarComandosPadroes(int x) {
        return ajudaChamarComandosPadroes[x];
    }

}
