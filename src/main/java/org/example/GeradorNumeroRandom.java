package org.example;
import java.util.Random;

public class GeradorNumeroRandom extends BaseDePalavrasOuLetras {
    Random random = new Random();
    private int numeroMax = getTamanhoPalavrasWordle();
    private int numeroRandom = random.nextInt(getNumeroMax());

    public int getNumeroMax() {
        return numeroMax;
    }

    public int getNumeroRandom() {
        return numeroRandom;
    }

    public void resetNumeroRandom() {
        numeroRandom = random.nextInt(getNumeroMax());
    }
}
