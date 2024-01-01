package com.barang.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame {
    private JPanel panelMain;
    private JTextField txtfilter;
    private JButton btnfiller;
    private JList jListBarang;
    private JTextField txtnama;
    private JTextField txtrak;
    private JTextField txtstok;
    private JButton btnsave;
    private JButton btndelete;
    private JButton btnclear;
    private JLabel lblnama;
    private JLabel lblrak;
    private JLabel lblstok;
    private JTextField txtharga;
    private JLabel lblharga;

    List<Barang> arrayListBarang = new ArrayList<>();
    DefaultListModel defaultListModel = new DefaultListModel<>();

    class Barang {
        public String nama;
        public String Kode;
        public int stok;

        public String harga;

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getKode() {
            return Kode;
        }

        public void setKode(String kode) {
            Kode = kode;
        }

        public int getStok() {
            return stok;
        }

        public void setStok(int stok) {
            this.stok = stok;
        }

        public String getHarga() {
            return harga;
        }

        public void setHarga(String harga) {
            this.harga = harga;
        }
    }

    public MainScreen(){
        super("Data Barang");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();

        btnsave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = txtnama.getText();
                String kode =  txtrak.getText();
                int stok = Integer.parseInt(txtstok.getText());
                String harga = txtharga.getText();

                Barang barang = new Barang();
                barang.setNama(nama);
                barang.setKode(kode);
                barang.setStok(stok);
                barang.setHarga(harga);

                arrayListBarang.add(barang);
                clearForm();

                fromListBarangToListModel();
            }
        });
        btnclear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        jListBarang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int index = jListBarang.getSelectedIndex();

                if (index < 0)
                    return;
                String nama= jListBarang.getSelectedValue().toString();
                for (int i = 0; i< arrayListBarang.size();i++){
                    if (arrayListBarang.get(i).getNama().equals(nama)){
                        Barang barang = arrayListBarang.get(i);
                        txtnama.setText(barang.getNama());
                        txtrak.setText(barang.getKode());
                        txtstok.setText(String.valueOf(barang.getStok()));
                        txtharga.setText(barang.getHarga());
                        break;
                    }
                }


            }
        });
        btndelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jListBarang.getSelectedIndex();

                if (index < 0)
                    return;
                String nama = jListBarang.getSelectedValue().toString();
                for(int i = 0; i < arrayListBarang.size();i++){
                    if (arrayListBarang.get(i).getNama().equals(nama)){
                        arrayListBarang.remove(i);
                    }
                }

                clearForm();
                fromListBarangToListModel();
            }
        });
        btnfiller.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String keyWord = txtfilter.getText();

                List<String> filter = new ArrayList<>();
                for (int i = 0; i < arrayListBarang.size();i++){
                    if(arrayListBarang.get(i).getNama().contains(keyWord)){
                        filter.add(arrayListBarang.get(i).getNama());
                    }
                }
                refreshListModel(filter);
            }
        });
    }

    public void clearForm(){
        txtnama.setText("");
        txtrak.setText("");
        txtstok.setText("");
        txtharga.setText("");
    }

    public void fromListBarangToListModel(){
        List<String> listnamaBarang = new ArrayList<>();

        for (int i = 0; i< arrayListBarang.size();i++){
            listnamaBarang.add(
                    arrayListBarang.get(i).getNama()
            );
        }
        refreshListModel(listnamaBarang);
    }

    public void refreshListModel(List<String> list){
        defaultListModel.clear();
        defaultListModel.addAll(list);
        jListBarang.setModel(defaultListModel);
    }

    public static void main(String[] args){
        MainScreen mainScreen=new MainScreen();
        mainScreen.setVisible(true);
    }
}
