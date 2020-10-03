package com.br.aps.classes;

import java.util.List;

public class Algoritmos {

    int menor, indiceMenor;
    long endTime = 0;
    long startTime = 0;

    public List<Integer> selectionSort(List<Integer> vetor, GetTempo getTempo){
        long endTime = 0;
        long startTime = 0;
        startTime = System.nanoTime();
        for (int i = 0; i < vetor.size() - 1; i++) {
            // antes de comparar, considera-se menor o valor atual do loop
            menor = vetor.get(i);
            indiceMenor = i;


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


        }
        endTime = System.nanoTime();

        getTempo.contagem(startTime, endTime);
        return vetor;
    }

    public List<Integer> insertionSort(List<Integer> vetor, GetTempo getTempo) {
        long endTime = 0;
        long startTime = 0;
        startTime = System.nanoTime();

        for (int i = 0; i < vetor.size(); i++) {
            int atual = vetor.get(i);
            int j = i - 1;
            while (j >= 0 && vetor.get(j) >= atual) {
                vetor.set(j + 1, vetor.get(j));
                j--;
            }
            vetor.set(j + 1, atual);;
        }
        endTime = System.nanoTime();

        getTempo.contagem(startTime, endTime);

        return vetor;
    }

    public List<Integer> bubbleSort(List<Integer> vetor, GetTempo getTempo) {
        long endTime = 0;
        long startTime = 0;
        startTime = System.nanoTime();

        for (int i = vetor.size(); i >= 1; i--) {
            for (int j = 1; j < i; j++) {
                if (vetor.get(j - 1) > vetor.get(j)) {
                    int aux = vetor.get(j);
                    vetor.set(j, vetor.get(j - 1));
                    vetor.set(j - 1, aux);
                }
            }
        }
        endTime = System.nanoTime();

        getTempo.contagem(startTime, endTime);

        return vetor;
    }

}
