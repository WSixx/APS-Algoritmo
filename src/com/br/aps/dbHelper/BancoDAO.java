package com.br.aps.dbHelper;

import com.br.aps.classes.Focos;
import com.br.aps.classes.Tempos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.UIManager.getInt;


public class BancoDAO {
    private Connection conector;
    List<Integer> list = new ArrayList<>();

    public BancoDAO() throws Exception {
        this.conector = new Conector().abrir();
    }

    public void salvaBd(Focos focos) {
        String sql = "INSERT INTO focos(satelite,cidade,estado,diasSemChuva,bioma) VALUES(?,?,?,?,?)";
            try {
                PreparedStatement prepState = conector.prepareStatement(sql);
                prepState.setString(1, focos.getSatelite());
                prepState.setString(2, focos.getMunicipio());
                prepState.setString(3, focos.getEstado());
                prepState.setInt(4, focos.getDiasSemChuva());
                prepState.setString(5, focos.getBioma());

                prepState.execute();
                prepState.close();

            } catch (SQLException u) {
                throw new RuntimeException(u);
            }
    }

    public void salvaTempoBd(String algoritmo, double tempo, Timestamp data) {
        String sql = "INSERT INTO tempos(algoritmo,tempo,dataNow) VALUES(?,?, ?)";
        try {
            PreparedStatement prepState = conector.prepareStatement(sql);
            prepState.setString(1, algoritmo);
            prepState.setDouble(2, tempo);
            prepState.setTimestamp(3, data);

            prepState.execute();
            prepState.close();

        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public void FillTable(JTable table) throws SQLException {
        String select = "select * from focos ORDER BY RAND()";
        PreparedStatement prepState = conector.prepareStatement(select);
        ResultSet rs = prepState.executeQuery(select);
        DefaultTableModel tableModel = new DefaultTableModel();

        //Retrieve meta data from ResultSet
        ResultSetMetaData metaData = rs.getMetaData();

        //Get number of columns from meta data
        int columnCount = metaData.getColumnCount();

        //Get all column names from meta data and add columns to table model
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
            tableModel.addColumn(metaData.getColumnLabel(columnIndex));
        }

        //Create array of Objects with size of column count from meta data
        Object[] row = new Object[columnCount];

        //Scroll through result set
        while (rs.next()){
            //Get object from column with specific index of result set to array of objects
            for (int i = 0; i < columnCount; i++){
                row[i] = rs.getObject(i+1);
            }
            //Now add row to table model with that array of objects as an argument
            tableModel.addRow(row);
        }

        //Now add that table model to your table and you are done :D
        table.setModel(tableModel);
    }

    public List<Integer> select() throws SQLException {
        String select = "select id, bioma from focos ORDER BY rand()";
        PreparedStatement prepState = conector.prepareStatement(select);
        ResultSet rs = prepState.executeQuery();

        // iterate through the java resultset
        while (rs.next())
        {
             list.add(rs.getInt("id"));
        }

        rs.close();
        return list;
    }
    public Focos selectWhere2(int id) throws SQLException {
        String select = "select id, satelite, cidade, estado, bioma, diassemchuva from focos where id=" + id;
        PreparedStatement stmt = conector.prepareStatement(select);
        ResultSet rs = stmt.executeQuery();
        Focos focos = new Focos();
        while (rs.next()) {
            focos.setIndex(rs.getInt("id"));
            focos.setSatelite(rs.getString("satelite"));
            focos.setMunicipio(rs.getString("cidade"));
            focos.setEstado(rs.getString("estado"));
            focos.setDiasSemChuva(rs.getInt("diassemchuva"));
            focos.setBioma(rs.getString("bioma"));

        }
        rs.close();
        return focos;
    }
    public void selectWhere(JTable table, int id) throws SQLException {
        String select = "select * from focos where id=" + id;
        PreparedStatement prepState = conector.prepareStatement(select);
        ResultSet rs = prepState.executeQuery(select);
        DefaultTableModel tableModel = new DefaultTableModel();

        //Retrieve meta data from ResultSet
        ResultSetMetaData metaData = rs.getMetaData();

        //Get number of columns from meta data
        int columnCount = metaData.getColumnCount();

        //Get all column names from meta data and add columns to table model
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
            tableModel.addColumn(metaData.getColumnLabel(columnIndex));
        }

        //Create array of Objects with size of column count from meta data
        Object[] row = new Object[columnCount];

        //Scroll through result set
        while (rs.next()){
            //Get object from column with specific index of result set to array of objects
            for (int i = 0; i < columnCount; i++){
                row[i] = rs.getObject(i+1);
            }
            //Now add row to table model with that array of objects as an argument
            tableModel.addRow(row);
        }

        //Now add that table model to your table and you are done :D
        table.setModel(tableModel);
    }

    public Tempos selecTempos(int id) throws SQLException {
        String select = "select id, algoritmo, tempo, dataNow from tempos where id=" + id;
        PreparedStatement stmt = conector.prepareStatement(select);
        ResultSet rs = stmt.executeQuery();
        Tempos tempos = new Tempos();
            while (rs.next()) {
                tempos.setId(rs.getInt("id"));
                tempos.setAlgoritmo(rs.getString("algoritmo"));
                tempos.setTempo(rs.getDouble("tempo"));
                tempos.setDataNow(rs.getDate("dataNow"));

            }
            rs.close();
            return tempos;
        }
    public int getCount() throws SQLException {
        String select = "SELECT COUNT(*) FROM tempos";
        PreparedStatement stmt = conector.prepareStatement(select);
        ResultSet rs = stmt.executeQuery();
        int total = 0;
        while (rs.next()) {
            total = rs.getInt(1);;
        }
        rs.close();
        System.out.println("Total: " + total);
        return total;
    }
}
