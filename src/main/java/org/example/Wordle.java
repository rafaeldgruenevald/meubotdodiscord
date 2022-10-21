package org.example;

import java.util.*;

public class Wordle extends BaseDePalavrasOuLetras {
    private GeradorNumeroRandom numeroRand = new GeradorNumeroRandom();
    private HashMap<String, Integer> imputWordle = new HashMap<>();
    public int tamanhoPalavraRandom() {
        return (getPalavrasWordle(numeroRand.getNumeroRandom())).length();
    }
    public String getPalavraRandom() {
        return getPalavrasWordle(numeroRand.getNumeroRandom());
    }
    public String getPalavraEmEmojis(String palavra) {
        setLetrasEmEmojis();
        char[] palavraRand = palavra.toCharArray();
        StringBuilder palavraEmEmojis = new StringBuilder();

        for(int i = 0; i < tamanhoPalavraRandom(); i++) {
            palavraEmEmojis.append(getEmojidaLetra(palavraRand[i])).append(" ");
        }

        return palavraEmEmojis.toString();
    }

    public String wordleResposta() {
        char[] inputUser = getMaiorImputWordle().toCharArray();
        char[] respostaCorreta = getPalavraRandom().toCharArray();
        StringBuilder wordleRespostaUser = new StringBuilder();

        ArrayList<Character> listRespostaCorreta = new ArrayList<>();

        for(int x = 0; x < 5; x++) {
            listRespostaCorreta.add(respostaCorreta[x]);
        }

        int tamanhoDasPalavras = 5;
        for(int i = 0; i < tamanhoDasPalavras; i++) {
            if(inputUser[i] == respostaCorreta[i]) {
                wordleRespostaUser.append(getCoresDasRespostas("verde")).append(" ");
            } else if(listRespostaCorreta.contains(inputUser[i])) {
                wordleRespostaUser.append(getCoresDasRespostas("amarelo")).append(" ");
            } else {
                wordleRespostaUser.append(getCoresDasRespostas("cinza")).append(" ");

            }
        }
        return wordleRespostaUser.toString();
    }

    public void setImputWordle(String palavra, int quantidadeDeVezes) {
        imputWordle.put(palavra, quantidadeDeVezes);
    }
    public boolean possuiEsseImputWordle(String palavra) {
        return imputWordle.containsKey(palavra);
    }

    public Integer getImputWordle(String palavra) {
        return imputWordle.get(palavra);
    }

    public void resetImputWordle() {
        imputWordle.clear();
    }

    public void resetNumeroRandom() {
        numeroRand.resetNumeroRandom();
    }

    public String getMaiorImputWordle() {
        String[] keysImputs = imputWordle.keySet().toArray(new String[0]);
        int maiorValor = 0;
        String maiorKey = "";
        for(int i = 0; i < imputWordle.size(); i++) {
            if(imputWordle.get(keysImputs[i]) >= maiorValor) {
                maiorKey = keysImputs[i];
                maiorValor = imputWordle.get(keysImputs[i]);
            }
        }
        return maiorKey;
    }
}
