package org.example;

public class PartesDaMensagem {
    String[] mensagemSplitada;
    String restoMensagem;
    String comando;

    public void setMensagemSplitada(String[] mensagemSplitada) {
        this.mensagemSplitada = mensagemSplitada;
    }

    public void setRestoMensagem(String restoMensagem) {
        this.restoMensagem = restoMensagem;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public int tamanhoMensagemSplitada() {
        return mensagemSplitada.length;
    }

    public String getPartesDaMensagemSplitada(int x) {
        return mensagemSplitada[x];
    }

    public String getRestoMensagem() {
        return restoMensagem;
    }

    public String getComando() {
        return comando;
    }
}
