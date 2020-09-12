package com.br.aps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BancoDAO {
    private Connection conector;
    List<Integer> list = new ArrayList<>();

    public BancoDAO() throws Exception {
        this.conector = new Conector().abrir();
    }

    public void salvaBd(Focos focos) {
        String sql = "INSERT INTO focos(indice,bioma,cidade) VALUES(?,?,?)";
            try {
                PreparedStatement prepState = conector.prepareStatement(sql);

                prepState.setString(1, String.valueOf(focos.getIndex()));
                prepState.setString(2, focos.getBioma());
                prepState.setString(3, focos.getMunicipio());

                prepState.execute();
                prepState.close();

            } catch (SQLException u) {
                throw new RuntimeException(u);
            }

    }
    public List<Integer> select() throws SQLException {
        String select = "select id from focos ORDER BY rand()";

        PreparedStatement prepState = conector.prepareStatement(select);
        ResultSet rs = prepState.executeQuery();

        // iterate through the java resultset
        while (rs.next())
        {
             list.add(rs.getInt("id"));
        }
        /*for(int item: list){
            System.out.println(item);
        }*/
        rs.close();
        return list;
    }
    public Focos selectWhere(int id) throws SQLException {
        String select = "select id, bioma, cidade from focos where id=" + id;
        PreparedStatement stmt = conector.prepareStatement(select);
        ResultSet rs = stmt.executeQuery();
        Focos focos = new Focos();
        while (rs.next()) {
            focos.setIndex(rs.getInt("id"));
            focos.setBioma(rs.getString("bioma"));
            focos.setMunicipio(rs.getString("cidade"));
        }
        rs.close();
        return focos;
    }
}
