package br.com.example.metric.demo;

import org.junit.jupiter.api.Test;

public class testeLocal {

    static long converte(int dec) {
        if (dec == 0) {
            return dec;
        }else {
            long r = converte(dec / 2);
            return dec % 2 + r * 10;
        }
    }

    @Test
    public void teste() {
        System.out.println(converte(16));
    }
}
