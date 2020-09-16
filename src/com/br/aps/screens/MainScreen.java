package com.br.aps.screens;

import com.br.aps.Algoritmos;
import com.br.aps.BancoDAO;
import com.br.aps.Focos;
import com.br.aps.OpenExcel;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame{
    private JButton btnStart;
    private JPanel panel1;
    private JList listData;
    private JButton btnList;
    private JScrollPane scrollPane;
    private JButton button1;
    private JTextField indiceTextField;
    private JButton ordenarButton;
    private JLabel lblResultado;
    //JFrame
    public static final JFrame frame = new JFrame();
    static OpenExcel openExcel = new OpenExcel();
    public static List<Integer> listFromSelect = new ArrayList<>();
    public static List<Integer> fromWhere = new ArrayList<>();

    DefaultListModel dlm = new DefaultListModel();


    MainScreen() {
        super();
        //this.setContentPane(new MainScreen().panel1);
        this.setContentPane(this.panel1);
        //setDefaultCloseOperation = Sair da aplicacao no X button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Size do frame
        this.setSize(600, 600);
        //Redimensionamento = false
        this.setResizable(false);

        //Cria uma nova imagem a partir do caminho
        //ImageIcon img = new ImageIcon("src/icon/line-stats.png");
        //Set o icone como img
        //this.setIconImage(img.getImage());

        //Abrir a janela no centro da Tela
        this.setLocationRelativeTo(null);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Clicou");

                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                int returnValue = jfc.showOpenDialog(null);
                // int returnValue = jfc.showSaveDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    System.out.println(selectedFile.getAbsolutePath());
                    try {
                        openExcel.OpenExcel(selectedFile.getAbsolutePath());
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
                    refreshlist(true);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                JPanel panel = new JPanel(new BorderLayout());
                listData.setModel(dlm);
                System.out.println(dlm.size());
                listData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                //listData.setSelectedIndex(0);
                listData.setVisibleRowCount(-1);
                scrollPane.getViewport().setView(listData);
            }
        });
        ordenarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Algoritmos algoritmos = new Algoritmos();
                List<Integer> teste = new ArrayList<>();
                //teste.add(algoritmos.selectionSort(listFromSelect));
                dlm.removeAllElements();

                long startTime = System.nanoTime();
                for(int p : algoritmos.selectionSort(listFromSelect) ){
                    //System.out.println("ADD: " + p);
                    dlm.addElement(p);
                }
                long endTimer = System.nanoTime();
                System.out.println("Tempo total: " + (endTimer - startTime) / 1000000);

                listData.setModel(dlm);
                System.out.println(dlm.size());
                listData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                //listData.setSelectedIndex(0);
                listData.setVisibleRowCount(-1);
                scrollPane.getViewport().setView(listData);
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    refreshlist(false);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                listData.setModel(dlm);
                System.out.println(dlm.size());
                listData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                //listData.setSelectedIndex(0);
                listData.setVisibleRowCount(-1);
                scrollPane.getViewport().setView(listData);

            }
        });

    }

    public void refreshlist(boolean isRandom) throws Exception {
        dlm.removeAllElements();
        if(isRandom){
            for(int p : listFromSelect ){
                System.out.println("ADD: " + p);
                dlm.addElement(p);
            }
        }else {
            BancoDAO bancoDAO = new BancoDAO();
            String textFieldValue = indiceTextField.getText();
            Focos focos = bancoDAO.selectWhere(Integer.parseInt(textFieldValue));
            dlm.addElement(focos.getMunicipio());
        }

    }

    public static void main(String[] args) throws Exception {
       BancoDAO bancoDAO = new BancoDAO();
       MainScreen mainScreen = new  MainScreen();
       mainScreen.setVisible(true);
       listFromSelect = bancoDAO.select();
    }
}
