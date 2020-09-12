package com.br.aps;

import java.util.List;

public class Algoritmos {

    int menor, indiceMenor;

    public void selectionSort(List<Integer> vetor){
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

        // exibe os números na tela
        String numerosOrdenados = "Numeors Ordenados: ";
        for (int n : vetor) {
            System.out.println(numerosOrdenados + n);
        }
    }


}
