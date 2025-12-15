/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend;
import backend.*;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
/**
 *
 * @author MyBook Hype AMD
 */
public class FormDosen extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormDosen.class.getName());
    private int idSelected = 0;
    /**
     * Creates new form FormDosen
     */
    public FormDosen() {
        initComponents();
        kosongkanForm();
        tampilkanData();
        
        // 1. Styling Frame / Content Pane
        // Mengatur warna latar belakang frame (konten utama) menjadi putih atau sangat terang
        getContentPane().setBackground(Color.WHITE);

        // 2. Styling Text Input (Minimalis Border dan Padding)
        Color inputBackground = new Color(245, 245, 245); // Abu-abu sangat terang
        Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);

        txtNama.setFont(inputFont);
        txtNama.setBackground(inputBackground);
        txtNama.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1), // Border abu-abu tipis
            BorderFactory.createEmptyBorder(5, 10, 5, 10))); // Padding
        
        txtNip.setFont(inputFont);
        txtNip.setBackground(inputBackground);
        txtNip.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        
        txtJabatan.setFont(inputFont);
        txtJabatan.setBackground(inputBackground);
        txtJabatan.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)));
            
        txtCari.setFont(inputFont);
        txtCari.setBackground(inputBackground);
        txtCari.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        // 3. Styling Button (Flat Style dengan Warna Aksen)
        Color primaryColor = new Color(0, 123, 255); // Biru cerah sebagai aksen
        Color secondaryColor = new Color(108, 117, 125); // Abu-abu gelap
        Color accentHoverColor = new Color(0, 86, 179); // Biru lebih gelap untuk hover (walaupun fungsionalitas hover terbatas di Swing tanpa kustomisasi)
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 12);
        
        // Fungsi untuk styling tombol (membuat tombol terlihat lebih 'flat')
        javax.swing.AbstractButton[] buttons = {buttonSimpan, buttonHapus, buttonTambah, buttonCari};
        for (javax.swing.AbstractButton btn : buttons) {
            btn.setFont(buttonFont);
            btn.setFocusPainted(false); // Hilangkan border fokus
            btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15)); // Padding yang lebih besar
            btn.setForeground(Color.WHITE); // Teks putih untuk semua tombol
        }
        
        // Atur warna spesifik
        buttonSimpan.setBackground(primaryColor);
        buttonHapus.setBackground(new Color(220, 53, 69)); // Merah untuk Hapus
        buttonTambah.setBackground(secondaryColor);
        buttonCari.setBackground(primaryColor);
        
        // 4. Styling Tabel (Header dan Tampilan Baris)
        
        // Styling Header Tabel
        JTableHeader tableHeader = tabelDosen.getTableHeader();
        tableHeader.setBackground(primaryColor); // Latar belakang header biru
        tableHeader.setForeground(Color.WHITE); // Teks header putih
        tableHeader.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tableHeader.setBorder(null);

        // Styling Sel Tabel
        tabelDosen.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabelDosen.setGridColor(new Color(230, 230, 230)); // Garis tipis abu-abu
        tabelDosen.setRowHeight(25);
        tabelDosen.setSelectionBackground(new Color(173, 216, 230)); // Biru muda untuk baris yang dipilih
        tabelDosen.setSelectionForeground(Color.BLACK);
        
        // Hapus border pada JScrollPane untuk tampilan yang lebih bersih
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
        
        // 5. Styling Label
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Nama.setFont(labelFont);
        Keterangan.setFont(labelFont);
        Keterangan1.setFont(labelFont);
        // --- Akhir Kode Styling Modern Minimalis ---
        
    }
    
    public void kosongkanForm(){
    txtNama.setText("");
    txtNip.setText("");
    txtJabatan.setText("");
    idSelected = 0;
    }
    
public void tampilkanData(){
   
    String[] namaKolom = new String[] {"id_dosen", "nip", "nama", "jabatan"}; 
    DefaultTableModel model = new DefaultTableModel();
    model.setColumnIdentifiers(namaKolom);
    String keyword = txtCari.getText();
    ArrayList<Dosen> listDosen = Dosen.getAll(keyword); 
    Object[] rowData = new Object[4]; 

    for (Dosen dos : listDosen) {
        rowData[0] = dos.getIdDosen();
        rowData[1] = dos.getNip();          // Mengambil NIP
        rowData[2] = dos.getNama();
        rowData[3] = dos.getJabatan();      // Mengambil Jabatan
        
        model.addRow(rowData);
    }
    tabelDosen.setModel(model);
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelDosen = new javax.swing.JTable();
        Nama = new javax.swing.JLabel();
        Keterangan = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtNip = new javax.swing.JTextField();
        buttonSimpan = new javax.swing.JButton();
        buttonHapus = new javax.swing.JButton();
        buttonTambah = new javax.swing.JButton();
        buttonCari = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        txtJabatan = new javax.swing.JTextField();
        Keterangan1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabelDosen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id_dosen", "nip", "nama", "jabatan"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabelDosen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelDosenMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelDosen);
        tabelDosen.getAccessibleContext().setAccessibleName("");

        Nama.setText("Nama");

        Keterangan.setText("Nip");

        txtNama.addActionListener(this::txtNamaActionPerformed);

        txtNip.addActionListener(this::txtNipActionPerformed);

        buttonSimpan.setText("Simpan");
        buttonSimpan.addActionListener(this::buttonSimpanActionPerformed);

        buttonHapus.setText("Hapus");
        buttonHapus.addActionListener(this::buttonHapusActionPerformed);

        buttonTambah.setText("Tambah");
        buttonTambah.addActionListener(this::buttonTambahActionPerformed);

        buttonCari.setText("Cari");
        buttonCari.addActionListener(this::buttonCariActionPerformed);

        txtCari.addActionListener(this::txtCariActionPerformed);

        txtJabatan.addActionListener(this::txtJabatanActionPerformed);

        Keterangan1.setText("Jabatan");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Nama)
                                    .addComponent(Keterangan)
                                    .addComponent(Keterangan1))
                                .addGap(71, 71, 71)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNip, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(340, 340, 340)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonSimpan)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonCari))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonTambah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonHapus)))))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Nama)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Keterangan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Keterangan1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(buttonSimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonTambah)
                    .addComponent(buttonHapus)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCari))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void txtNipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNipActionPerformed

    private void buttonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHapusActionPerformed

    // Panggil metode static delete() dari kelas Dosen
    boolean deleteSuccess = Dosen.delete(idSelected); // Baris ini akan bekerja

    if (deleteSuccess) {
        kosongkanForm();
        tampilkanData();
    } else {
        // Mengubah pesan error agar sesuai dengan Dosen
        JOptionPane.showMessageDialog(null, "Gagal menghapus! Terdapat mata kuliah atau data lain yang terkait dengan dosen ini.");
    }
    
    }//GEN-LAST:event_buttonHapusActionPerformed

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariActionPerformed

    private void buttonTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTambahActionPerformed
        kosongkanForm();
    }//GEN-LAST:event_buttonTambahActionPerformed

    private void buttonCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCariActionPerformed
        tampilkanData();
    }//GEN-LAST:event_buttonCariActionPerformed

    private void tabelDosenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelDosenMouseClicked
        
        DefaultTableModel model = (DefaultTableModel) tabelDosen.getModel(); 

        int rowIndex = tabelDosen.getSelectedRow();

        if (rowIndex != -1) {
    
        idSelected = (int) model.getValueAt(rowIndex, 0); 
    
        // Kolom 1 (NIP): Diisi ke txtNip
        txtNip.setText(model.getValueAt(rowIndex, 1).toString()); 
    
        // Kolom 2 (Nama): Diisi ke txtNama
        txtNama.setText(model.getValueAt(rowIndex, 2).toString()); 
    
        // Kolom 3 (Jabatan): Diisi ke txtJabatan
        txtJabatan.setText(model.getValueAt(rowIndex, 3).toString()); 
        }
    }//GEN-LAST:event_tabelDosenMouseClicked

    private void txtJabatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJabatanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJabatanActionPerformed

    private void buttonSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSimpanActionPerformed

    String nama = txtNama.getText();
    String nip = txtNip.getText();
    String jabatan = txtJabatan.getText();

    if (!(nama.isEmpty()) && !(nip.isEmpty()) && !(jabatan.isEmpty())) {
        
        Dosen dos = new Dosen(); 
        
        // Atur ID Dosen (idSelected akan bernilai 0 untuk INSERT, atau ID > 0 untuk UPDATE)
        dos.setIdDosen(idSelected); 
        
        // Atur field Dosen yang sesuai
        dos.setNip(nip); 
        dos.setNama(nama);
        dos.setJabatan(jabatan);
        
        // Panggil metode save() pada objek Dosen
        dos.save(); 

        // 4. Setelah sukses, refresh tabel dan kosongkan form
        tampilkanData();
        kosongkanForm();
    } else {
        // 5. Ubah pesan error agar sesuai dengan field Dosen
        JOptionPane.showMessageDialog(null, "Silakan isi NIP, Nama, dan Jabatan");
    }

    }//GEN-LAST:event_buttonSimpanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FormDosen().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Keterangan;
    private javax.swing.JLabel Keterangan1;
    private javax.swing.JLabel Nama;
    private javax.swing.JButton buttonCari;
    private javax.swing.JButton buttonHapus;
    private javax.swing.JButton buttonSimpan;
    private javax.swing.JButton buttonTambah;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable tabelDosen;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtJabatan;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNip;
    // End of variables declaration//GEN-END:variables
}
