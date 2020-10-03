package com.br.aps.screens;

import com.br.aps.classes.GetTempo;
import com.br.aps.classes.Tempos;
import com.br.aps.dbHelper.BancoDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Tabela {
    private JTable jTable;
    private JScrollPane scrollPanel;
    private JPanel panel2;
    BancoDAO bancoDAO;

    {
        try {
            bancoDAO = new BancoDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    GetTempo getTempo = new GetTempo();
    public static List<Integer> listFromSelect = new ArrayList<>();

    public Tabela() {
        JFrame f = new JFrame("Tempos");
        f.setContentPane(panel2);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(700,600);
        f.setResizable(false);

        //Abrir a janela no centro da Tela
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        //Cria uma nova imagem a partir do caminho
        ImageIcon img = new ImageIcon("src/icon/br.png");
        //Set o icone como img
        f.setIconImage(img.getImage());

        TableModel model = null;
        try {
            model = buildJTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        jTable.setModel(model);
        scrollPanel.getViewport().setView(jTable);

    }

    private TableModel buildJTable() throws SQLException {
        Tempos tempos;

        int total = bancoDAO.getCount();
        DefaultTableModel model = new DefaultTableModel(new String[]{"id", "algoritmo", "tempo","Data"}, 0);
        for(int i = 0; i <= total; i++){
            try {
                tempos = bancoDAO.selecTempos(i);
                model.addRow(new Object[]{i, tempos.getAlgoritmo(), tempos.getTempo(), tempos.getDataNow()});
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return model;

    }
}
