package com.br.aps;

import java.util.List;

public class Algoritmos {

    int menor, indiceMenor;

    public List<Integer> selectionSort(List<Integer> vetor){
        long tempoTotal;
        long endTime = 0;
        long startTime = 0;

        for (int i = 0; i < vetor.size() - 1; i++) {
            // antes de comparar, considera-se menor o valor atual do loop
            menor = vetor.get(i);
            indiceMenor = i;

            startTime = System.nanoTime();
            // compara com os outros valores do vetor
            for (int j = i + 1; j < vetor.size(); j++){
                if (vetor.get(j) < menor){
                    menor = vetor.get(j);
                    indiceMenor = j;
                }
            }

            // troca os valores menor e maior
            vetor.set(indiceMenor, vetor.get(i));
            vetor.set(i, menor);
            endTime = System.nanoTime();

        }

        // exibe os números na tela
        /*String numerosOrdenados = "Numeors Ordenados: ";
        for (int n : vetor) {
            System.out.println(numerosOrdenados + n);
        }*/
        tempoTotal = endTime - startTime;
        //System.out.println("Tempo total: " + tempoTotal);
        return vetor;
    }


}
