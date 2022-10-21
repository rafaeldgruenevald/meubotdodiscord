package org.example;

import net.dv8tion.jda.api.entities.emoji.Emoji;

import java.util.HashMap;

public class BaseDePalavrasOuLetras {
    private String[] palavrasWordle = {"sagaz",
            "mexer",
            "termo",
            "nobre",
            "senso",
            "algoz",
            "afeto",
            "plena",
            "sutil",
            "vigor",
            "fazer",
            "assim",
            "audaz",
            "sanar",
            "inato",
            "cerne",
            "fosse",
            "ideia",
            "poder",
            "moral",
            "desde",
            "muito",
            "justo",
            "torpe",
            "honra",
            "sobre",
            "anexo",
            "etnia",
            "sonho",
            "tange",
            "amigo",
            "lapso",
            "expor",
            "haver",
            "casal",
            "tempo",
            "dengo",
            "seara",
            "pesar",
            "ardil",
            "genro",
            "posse",
            "coser",
            "causa",
            "dizer",
            "corja",
            "prole",
            "brado",
            "dever",
            "tenaz"};

    private final HashMap<Character, String> letrasEmEmojis = new HashMap<>();
    public void setLetrasEmEmojis() {
        letrasEmEmojis.put('a', ":regional_indicator_a:");
        letrasEmEmojis.put('b', ":regional_indicator_b:");
        letrasEmEmojis.put('c', ":regional_indicator_c:");
        letrasEmEmojis.put('d', ":regional_indicator_d:");
        letrasEmEmojis.put('e', ":regional_indicator_e:");
        letrasEmEmojis.put('f', ":regional_indicator_f:");
        letrasEmEmojis.put('g', ":regional_indicator_g:");
        letrasEmEmojis.put('h', ":regional_indicator_h:");
        letrasEmEmojis.put('i', ":regional_indicator_i:");
        letrasEmEmojis.put('j', ":regional_indicator_j:");
        letrasEmEmojis.put('k', ":regional_indicator_k:");
        letrasEmEmojis.put('l', ":regional_indicator_l:");
        letrasEmEmojis.put('m', ":regional_indicator_m:");
        letrasEmEmojis.put('n', ":regional_indicator_n:");
        letrasEmEmojis.put('o', ":regional_indicator_o:");
        letrasEmEmojis.put('p', ":regional_indicator_p:");
        letrasEmEmojis.put('q', ":regional_indicator_q:");
        letrasEmEmojis.put('r', ":regional_indicator_r:");
        letrasEmEmojis.put('s', ":regional_indicator_s:");
        letrasEmEmojis.put('t', ":regional_indicator_t:");
        letrasEmEmojis.put('u', ":regional_indicator_u:");
        letrasEmEmojis.put('v', ":regional_indicator_v:");
        letrasEmEmojis.put('w', ":regional_indicator_w:");
        letrasEmEmojis.put('x', ":regional_indicator_x:");
        letrasEmEmojis.put('y', ":regional_indicator_y:");
        letrasEmEmojis.put('z', ":regional_indicator_z:");
    }

    private final HashMap<String, String> coresDasRespostas = new HashMap<>();

    public void setCoresDasRespostas() {
        coresDasRespostas.put("verde", ":green_square:");
        coresDasRespostas.put("amarelo", ":yellow_square:");
        coresDasRespostas.put("cinza", ":black_large_square:");
    }

    public String blankWordleStart() {
        setCoresDasRespostas();
        StringBuilder blankWordleStart = new StringBuilder();

        for(int i = 0; i < 5; i++) {
            blankWordleStart.append(getCoresDasRespostas("cinza")).append(" ");
        }

        return blankWordleStart.toString();
    }

    public String getCoresDasRespostas(String corDesejada) {
        return coresDasRespostas.get(corDesejada);
    }
    public String getPalavrasWordle(int iterator) {
        return palavrasWordle[iterator];
    }

    public int getTamanhoPalavrasWordle() {
        return palavrasWordle.length;
    }

    public String getEmojidaLetra(char letra) {
        return letrasEmEmojis.get(letra);
    }
}
