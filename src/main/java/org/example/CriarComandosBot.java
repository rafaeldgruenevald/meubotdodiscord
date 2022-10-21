package org.example;

import java.util.ArrayList;

public class CriarComandosBot {
    private String chamarComando;
    private String txtParaComandoFalar;
    private String repeticao;

    private ArrayList<String> chamadasDeComandos = new ArrayList<>();

    public void setInfoCriarComando(String restoDaMensagem, String prefixo) {
        String[] infoCriarComando = restoDaMensagem.split("\\|", 3);
        this.chamarComando = infoCriarComando[0].replace(prefixo + "criarComando", " ").replace(" ", "");
        this.txtParaComandoFalar = infoCriarComando[1];
        this.repeticao = infoCriarComando[2].replace(" ", "");

        chamadasDeComandos.add(chamarComando);
    }


    public String getChamarComando() {
        return chamarComando;
    }

    public String getTxtParaComandoFalar() {
        return txtParaComandoFalar;
    }

    public String getRepeticao() {
        return repeticao;
    }

    public String getChamadasDeComandos(int x) {
        return chamadasDeComandos.get(x);
    }

    public void removerChamadasDeComandos(String removerElemento) {
        chamadasDeComandos.remove(removerElemento);
    }
}
