package com.mehmetbezci.stokuygulamasi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JTable;
import java.awt.ComponentOrientation;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StokUygulamasi extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf_urun_ismi;
	private JTextField tf_fiyat;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StokUygulamasi frame = new StokUygulamasi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StokUygulamasi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 779, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ürün İsmi :");
		lblNewLabel.setBounds(70, 43, 111, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblKategori_1 = new JLabel("Kategori :");
		lblKategori_1.setBounds(70, 101, 111, 33);
		contentPane.add(lblKategori_1);
		
		JLabel lblKategori = new JLabel("Fiyat : ");
		lblKategori.setBounds(70, 161, 60, 33);
		contentPane.add(lblKategori);
		
		tf_urun_ismi = new JTextField();
		tf_urun_ismi.setBounds(131, 49, 375, 20);
		contentPane.add(tf_urun_ismi);
		tf_urun_ismi.setColumns(10);
		
		JComboBox cb_kategori = new JComboBox();
		cb_kategori.setModel(new DefaultComboBoxModel(new String[] {"Kültür Sanat", "Yemek", "Elektronik"}));
		cb_kategori.setBounds(131, 106, 375, 22);
		contentPane.add(cb_kategori);
		
		tf_fiyat = new JTextField();
		tf_fiyat.setBounds(131, 167, 375, 20);
		contentPane.add(tf_fiyat);
		tf_fiyat.setColumns(10);
		
		JLabel mesaj_yazisi = new JLabel("");
		mesaj_yazisi.setForeground(new Color(255, 0, 0));
		mesaj_yazisi.setBounds(70, 221, 436, 33);
		contentPane.add(mesaj_yazisi);
		
		JScrollPane urun_tablosu = new JScrollPane();
		urun_tablosu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int secili_row = table.getSelectedRow();
				if(secili_row == -1) {
				tf_urun_ismi.setText(model.getValueAt(secili_row, 0).toString());
				cb_kategori.setSelectedItem(model.getValueAt(secili_row, 1).toString());
				tf_fiyat.setText(model.getValueAt(secili_row, 2).toString());
			}
		  }
		});
		urun_tablosu.setBounds(110, 265, 414, 161);
		contentPane.add(urun_tablosu);
		
		table = new JTable();
		urun_tablosu.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u00DCr\u00FCn \u0130smi", "Kategori", "Fiyat"
			}
		));
		
		JButton ekle_butonu = new JButton("Ürün Ekle");
		ekle_butonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mesaj_yazisi.setText("");
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				if(tf_urun_ismi.getText().trim().equals("")) {
					mesaj_yazisi.setText("Ürün ismi boş bırakılamaz...");
				}
				else {
					Object[] eklenecek = {tf_urun_ismi.getText(), cb_kategori.getSelectedItem().toString(),tf_fiyat.getText()};
					model.addRow(eklenecek);
				}
			}
		});
		ekle_butonu.setBounds(583, 48, 124, 23);
		contentPane.add(ekle_butonu);
		
		JButton güncelle = new JButton("Ürün Güncelle");
		güncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mesaj_yazisi.setText("");
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int secili_row = table.getSelectedRow();
				
				if(secili_row == -1) {
					if(table.getRowCount() == 0) {
						mesaj_yazisi.setText("Ürün tablosu şu anda boş...");
					}
					else {
						mesaj_yazisi.setText("Lütfen güncellenecek bir ürün seçin...");
					}
				}
				else {
					model.setValueAt(tf_urun_ismi.getText(), secili_row, 0);
					model.setValueAt(cb_kategori.getSelectedItem().toString(), secili_row, 1);
					model.setValueAt(tf_fiyat.getText(), secili_row, 2);
					
					mesaj_yazisi.setText("Ürün başarıyla güncellendi...");
				}
			}
		});
		güncelle.setBounds(583, 106, 124, 23);
		contentPane.add(güncelle);
		
		JButton sil_butonu = new JButton("Ürün Sil");
		sil_butonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int secili_row = table.getSelectedRow();
				
				if(secili_row == -1) {
					if(table.getRowCount() == 0) {
						mesaj_yazisi.setText("Ürün tablosu şu anda boş...");
					}
					else {
						mesaj_yazisi.setText("Lütfen silinecek bir ürün seçin...");
					}
				}
				else {
					model.removeRow(secili_row);
					mesaj_yazisi.setText("Ürün başarıyla silindi...");
				}
			}
		});
		sil_butonu.setBounds(583, 166, 124, 23);
		contentPane.add(sil_butonu);
	}
}
