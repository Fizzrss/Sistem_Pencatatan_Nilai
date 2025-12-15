package frontend;

import backend.MataKuliah; // Import yang benar (camelcase K besar)
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormMataKuliah extends javax.swing.JFrame {

    private int idSelected = 0;

    public FormMataKuliah() {
        initComponents();
        kosongkanForm();
        tampilkanData();
    }

    public void kosongkanForm() {
        idSelected = 0;
        txtNama.setText("");
        txtSks.setText("");
    }

    public void tampilkanData() {
        String[] kolom = {"ID", "Mata Kuliah", "SKS"};
        DefaultTableModel model = new DefaultTableModel(kolom, 0);

        String keyword = txtCari.getText();
        ArrayList<MataKuliah> list = MataKuliah.getAll(keyword);

        for (MataKuliah mk : list) {
            Object[] row = {
                mk.getIdMatakuliah(),
                mk.getNama(),
                mk.getSks()
            };
            model.addRow(row);
        }

        tblMatakuliah.setModel(model);
    }

    // --- EVENT HANDLERS ---
    
    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {
        String nama = txtNama.getText();
        String sksStr = txtSks.getText();

        if (nama.isEmpty() || sksStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nama Mata Kuliah dan SKS harus diisi!");
            return;
        }

        int sks = 0;
        try {
            sks = Integer.parseInt(sksStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "SKS harus berupa angka!");
            return;
        }

        MataKuliah mk;

        if (idSelected != 0) {
            mk = MataKuliah.getById(idSelected);
            mk.setNama(nama);
            mk.setSks(sks);
        } else {
            mk = new MataKuliah(nama, sks);
        }

        mk.save();
        tampilkanData();
        kosongkanForm();
    }

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {
        if (idSelected == 0) {
            JOptionPane.showMessageDialog(this, "Pilih data pada tabel terlebih dahulu!");
            return;
        }
        
        MataKuliah.delete(idSelected);
        tampilkanData();
        kosongkanForm();
    }

    private void btnTambahBaruActionPerformed(java.awt.event.ActionEvent evt) {
        kosongkanForm();
    }

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {
        tampilkanData();
    }

    private void tblMatakuliahMouseClicked(java.awt.event.MouseEvent evt) {
        DefaultTableModel model = (DefaultTableModel) tblMatakuliah.getModel();
        int row = tblMatakuliah.getSelectedRow();

        if (row >= 0) {
            idSelected = Integer.parseInt(model.getValueAt(row, 0).toString());
            // Kolom 1 = Nama, Kolom 2 = SKS
            txtNama.setText(model.getValueAt(row, 1).toString());
            txtSks.setText(model.getValueAt(row, 2).toString());
        }
    }

    // --- GENERATED CODE SECTION ---
    // Pastikan Variable Name di Design View:
    // txtNama, txtSks, btnSimpan, btnTambahBaru, btnHapus, btnCari, txtCari, tblMatakuliah
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        txtNama = new javax.swing.JTextField();
        txtSks = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnTambahBaru = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnCari = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        tblMatakuliah = new javax.swing.JTable();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Mata Kuliah");
        jLabel2.setText("SKS");

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnTambahBaru.setText("Tambah Baru");
        btnTambahBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahBaruActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        tblMatakuliah.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"ID", "Mata Kuliah", "SKS"}
        ));
        tblMatakuliah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMatakuliahMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMatakuliah);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNama)
                            .addComponent(txtSks)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSimpan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTambahBaru)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCari)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCari)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnTambahBaru)
                    .addComponent(btnHapus)
                    .addComponent(btnCari)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new FormMataKuliah().setVisible(true);
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambahBaru;
    private javax.swing.JTable tblMatakuliah;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtSks;
    // End of variables declaration                   
}