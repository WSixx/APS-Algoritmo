package com.br.aps.screens;

import com.br.aps.*;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainScreen extends JFrame{
    private JButton btnOpenFile;
    private JPanel panel1;
    private JButton btnList;
    private JScrollPane scrollPane;
    private JButton btnPesquisar;
    private JTextField indiceTextField;
    private JButton btnSelectionSort;
    private JLabel lblResultado;
    private JTable jTable;
    private JButton btnInsertion;
    private JButton btnBubble;
    static OpenExcel openExcel = new OpenExcel();
    public static List<Integer> listFromSelect = new ArrayList<>();
    BancoDAO bancoDAO;
    GetTempo getTempo = new GetTempo();

    {
        try {
            bancoDAO = new BancoDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    DefaultListModel dlm = new DefaultListModel();

    MainScreen() {
        super();
        //this.setContentPane(new MainScreen().panel1);
        this.setContentPane(this.panel1);
        //setDefaultCloseOperation = Sair da aplicacao no X button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Size do frame
        this.setSize(800, 600);
        //Redimensionamento = false
        this.setResizable(false);

        //Cria uma nova imagem a partir do caminho
        //ImageIcon img = new ImageIcon("src/icon/line-stats.png");
        //Set o icone como img
        //this.setIconImage(img.getImage());

        //Abrir a janela no centro da Tela
        this.setLocationRelativeTo(null);


        btnOpenFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int returnValue = jfc.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    System.out.println(selectedFile.getAbsolutePath());
                    try {
                        if(openExcel.OpenExcel(selectedFile.getAbsolutePath()))
                        {
                            JOptionPane.showMessageDialog(null,
                                    "Arquivo Carregado.",
                                    "Arquivo Carregado",
                                    JOptionPane.OK_OPTION);
                        }else {
                            System.out.println("Arquivo return False");
                        }

                    } catch (Exception ioException) {
                        ioException.printStackTrace();
                    }
                }

            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    bancoDAO.FillTable(jTable);
                    scrollPane.getViewport().setView(jTable);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        btnSelectionSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableModel model = buildJTable();
                jTable.setModel(model);
                scrollPane.getViewport().setView(jTable);
                System.out.println("Main tempo: " + getTempo.getTempoTotal());
                long tempototal = TimeUnit.MILLISECONDS.convert((getTempo.getTempoTotal()), TimeUnit.NANOSECONDS) ;
                lblResultado.setText("Tempo: " + tempototal + " Milisegundos");
                Timestamp dataNow = new Timestamp(System.currentTimeMillis());
                bancoDAO.salvaTempoBd("SelectionSort", tempototal, dataNow);
            }
        });

        btnBubble.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableModel model = buildJTable();
                jTable.setModel(model);
                scrollPane.getViewport().setView(jTable);
                System.out.println("Main tempo: " + getTempo.getTempoTotal());
                long tempototal = TimeUnit.MILLISECONDS.convert((getTempo.getTempoTotal()), TimeUnit.NANOSECONDS) ;
                lblResultado.setText("Tempo: " + tempototal + " Milisegundos");
                Timestamp dataNow = new Timestamp(System.currentTimeMillis());

                bancoDAO.salvaTempoBd("BubbleSort", tempototal, dataNow);

            }
        });

        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textFieldValue = indiceTextField.getText();
                try {
                    bancoDAO.selectWhere(jTable, Integer.parseInt(textFieldValue));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                scrollPane.getViewport().setView(jTable);
                try {
                    refreshlist(false);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });



        btnInsertion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!listFromSelect.isEmpty()){
                    passarToLista();
                }

                TableModel model = buildJTable();
                jTable.setModel(model);
                scrollPane.getViewport().setView(jTable);
                System.out.println("Main tempo: " + getTempo.getTempoTotal());
                long tempototal = TimeUnit.MILLISECONDS.convert((getTempo.getTempoTotal()), TimeUnit.NANOSECONDS) ;
                lblResultado.setText("Tempo: " + tempototal + " Milisegundos");
                Timestamp dataNow = new Timestamp(System.currentTimeMillis());
                bancoDAO.salvaTempoBd("InsertionSort", tempototal, dataNow);

            }
        });

    }

    private TableModel buildJTable(){
        Algoritmos algoritmos = new Algoritmos();

        Focos focos;
        DefaultTableModel model = new DefaultTableModel(new String[]{"id", "Satelite", "" +
                "Cidade", "Estado", "Dias sem chuva", "Bioma"}, 0);
        for(int p : algoritmos.insertionSort(listFromSelect, getTempo) ){
            try {
                focos = bancoDAO.selectWhere2(p);
                model.addRow(new Object[]{p, focos.getSatelite(), focos.getMunicipio(), focos.getEstado(), focos.getDiasSemChuva(), focos.getBioma()});
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return model;
    }

    void passarToLista(){
        BancoDAO bancoDAO = null;
        try {
            bancoDAO = new BancoDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            listFromSelect = bancoDAO.select();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void refreshlist(boolean isRandom) throws Exception {
        dlm.removeAllElements();
        if(isRandom){
            for(int p : listFromSelect ){
                System.out.println("ADD: " + p);
                dlm.addElement(p);
            }
        }else {
            String textFieldValue = indiceTextField.getText();
            bancoDAO.selectWhere(jTable, Integer.parseInt(textFieldValue));
            scrollPane.getViewport().setView(jTable);
        }
    }

    public static void main(String[] args) throws Exception {
       BancoDAO bancoDAO = new BancoDAO();
       MainScreen mainScreen = new  MainScreen();
       mainScreen.setVisible(true);
       listFromSelect = bancoDAO.select();
    }
}
