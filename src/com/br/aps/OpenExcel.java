package com.br.aps;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class OpenExcel {
    private static final String fileName = "C:/teste/focos.xls";
    Focos focos = new Focos();
    BancoDAO bancoDAO;

    {
        try {
            bancoDAO = new BancoDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean OpenExcel(String absolutePath) throws IOException {

        List<Focos> listaNumbers = new ArrayList<>();

        try {
            FileInputStream arquivo = new FileInputStream(new File(
                    absolutePath));

            HSSFWorkbook workbook = new HSSFWorkbook(arquivo);
            HSSFSheet sheetFocos = workbook.getSheetAt(0);

            for (Row row : sheetFocos) {
                Iterator<Cell> cellIterator = row.cellIterator();

                listaNumbers.add(focos);
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getColumnIndex()) {
                        case 0 -> focos.setIndex((int) cell.getNumericCellValue());
                        //case 1 -> focos.setData(cell.getLocalDateTimeCellValue());
                        case 2 -> focos.setSatelite(cell.getStringCellValue());
                        //case 3 -> focos.setPais(cell.getStringCellValue());
                        case 4 -> focos.setEstado(cell.getStringCellValue());
                        case 5 -> focos.setMunicipio(cell.getStringCellValue());
                        case 6 -> focos.setBioma(cell.getStringCellValue());
                        //case 7 -> focos.setLat(cell.getNumericCellValue());
                        //case 8 -> focos.setLon(cell.getNumericCellValue());
                        //case 9 -> focos.setRegiao(cell.getStringCellValue());
                        case 10 -> focos.setDiasSemChuva((int) cell.getNumericCellValue());
                    }
                }
                bancoDAO.salvaBd(focos);
            }
            arquivo.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Arquivo Excel não encontrado!");
            return false;
        }

        if (listaNumbers.size() == 0) {
            System.out.println("Nenhum dado encontrado!");
            return false;
        } else {
            System.out.println("FROM EXCEL");
            System.out.println("Número total de ocorrências: " + listaNumbers.size());
            return true;

        }

    }
}
