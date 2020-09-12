package com.br.aps;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Integer> listFromSelect = new ArrayList<>();
    static Conector conector = new Conector();
    static OpenExcel openExcel = new OpenExcel();

    public static void main(String[] args) throws Exception {
        BancoDAO bancoDAO = new BancoDAO();
        //TODO - Colocar no Open Excel - conector.abrir();
        //conector.abrir();
        //openExcel.OpenExcel();
        listFromSelect = bancoDAO.select();
        for(int item: listFromSelect){
            System.out.println(item);
        }
        System.out.println("OK: " + listFromSelect.size());

        Focos focos = bancoDAO.selectWhere(273);
        System.out.println(focos.getIndex());
        System.out.println(focos.getBioma());
        System.out.println(focos.getMunicipio());
        Algoritmos algoritmos = new Algoritmos();
        algoritmos.selectionSort(listFromSelect);
    }
}
