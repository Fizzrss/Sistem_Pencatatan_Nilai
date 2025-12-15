/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author MyBook Hype AMD
 */
public class Nilai {

    private int id_nilai;
    private Mahasiswa mahasiswa;
    private Dosen dosen;
    private MataKuliah matakuliah;
    private int nilai_tugas;
    private int nilai_uts;
    private int nilai_uas;
    private int nilai_akhir;

    public Nilai() {
    }

    public Nilai(int id_nilai, Mahasiswa mahasiswa, Dosen dosen, MataKuliah matakuliah, int nilai_tugas, int nilai_uts, int nilai_uas, int nilai_akhir) {
        this.id_nilai = id_nilai;
        this.mahasiswa = mahasiswa;
        this.dosen = dosen;
        this.matakuliah = matakuliah;
        this.nilai_tugas = nilai_tugas;
        this.nilai_uts = nilai_uts;
        this.nilai_uas = nilai_uas;
        this.nilai_akhir = nilai_akhir;
    }

    public void setId_nilai(int id_nilai) {
        this.id_nilai = id_nilai;
    }

    public void setMahasiswa(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

    public void setDosen(Dosen dosen) {
        this.dosen = dosen;
    }

    public void setMatakuliah(MataKuliah matakuliah) {
        this.matakuliah = matakuliah;
    }

    public void setNilai_tugas(int nilai_tugas) {
        this.nilai_tugas = nilai_tugas;
    }

    public void setNilai_uts(int nilai_uts) {
        this.nilai_uts = nilai_uts;
    }

    public void setNilai_uas(int nilai_uas) {
        this.nilai_uas = nilai_uas;
    }

    public void setNilai_akhir(int nilai_akhir) {
        this.nilai_akhir = nilai_akhir;
    }

    public int getId_nilai() {
        return id_nilai;
    }

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public Dosen getDosen() {
        return dosen;
    }

    public MataKuliah getMatakuliah() {
        return matakuliah;
    }

    public int getNilai_tugas() {
        return nilai_tugas;
    }

    public int getNilai_uts() {
        return nilai_uts;
    }

    public int getNilai_uas() {
        return nilai_uas;
    }

    public int getNilai_akhir() {
        return nilai_akhir;
    }

    public static Nilai getById(int id) {
        Nilai nilai = null;
        String sql = "SELECT n.*, m.nama as nama_mhs, d.nama as nama_dosen, mk.nama as nama_mk "
                + "FROM nilai n "
                + "LEFT JOIN mahasiswa m ON n.id_mahasiswa = m.id_mahasiswa "
                + "LEFT JOIN dosen d ON n.id_dosen = d.id_dosen " 
                + "LEFT JOIN matakuliah mk ON n.id_matakuliah = mk.id_matakuliah "
                + "WHERE n.id_nilai = ?";

        try (Connection conn = DBHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                nilai = new Nilai();
                nilai.setId_nilai(rs.getInt("id_nilai"));
                nilai.setNilai_tugas(rs.getInt("nilai_tugas"));
                nilai.setNilai_uts(rs.getInt("nilai_uts"));
                nilai.setNilai_uas(rs.getInt("nilai_uas"));
                nilai.setNilai_akhir(rs.getInt("nilai_akhir"));

                Mahasiswa m = new Mahasiswa();
                m.setIdMahasiswa(rs.getInt("id_mahasiswa"));
                m.setNama(rs.getString("nama_mhs"));
                nilai.setMahasiswa(m);

                Dosen d = new Dosen();
                d.setIdDosen(rs.getInt("id_dosen"));
                d.setNama(rs.getString("nama_dosen"));
                nilai.setDosen(d);

                MataKuliah mk = new MataKuliah();
                mk.setIdMatakuliah(rs.getInt("id_matakuliah"));
                mk.setNama(rs.getString("nama_mk"));
                nilai.setMatakuliah(mk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nilai;
    }

    public static ArrayList<Nilai> getAll() {
        ArrayList<Nilai> listNilai = new ArrayList<>();
        // Perhatikan tambahan spasi di akhir setiap baris String
        String sql = "SELECT n.*, m.nama as nama_mhs, d.nama as nama_dosen, mk.nama as nama_mk "
                + "FROM nilai n "
                + "LEFT JOIN mahasiswa m ON n.id_mahasiswa = m.id_mahasiswa " 
                + "LEFT JOIN dosen d ON n.id_dosen = d.id_dosen "             
                + "LEFT JOIN matakuliah mk ON n.id_matakuliah = mk.id_matakuliah";

        try (Connection conn = DBHelper.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Nilai nilai = new Nilai();
                nilai.setId_nilai(rs.getInt("id_nilai"));
                nilai.setNilai_tugas(rs.getInt("nilai_tugas"));
                nilai.setNilai_uts(rs.getInt("nilai_uts"));
                nilai.setNilai_uas(rs.getInt("nilai_uas"));
                nilai.setNilai_akhir(rs.getInt("nilai_akhir"));

                Mahasiswa m = new Mahasiswa();
                m.setIdMahasiswa(rs.getInt("id_mahasiswa"));
                m.setNama(rs.getString("nama_mhs"));
                nilai.setMahasiswa(m);

                Dosen d = new Dosen();
                d.setIdDosen(rs.getInt("id_dosen"));
                d.setNama(rs.getString("nama_dosen"));
                nilai.setDosen(d);

                MataKuliah mk = new MataKuliah();
                mk.setIdMatakuliah(rs.getInt("id_matakuliah"));
                mk.setNama(rs.getString("nama_mk"));
                nilai.setMatakuliah(mk);

                listNilai.add(nilai);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNilai;
    }

    public static ArrayList<Nilai> getAll(String keyword) {
        ArrayList<Nilai> listNilai = new ArrayList<>();

        String sql = "SELECT n.*, m.nama as nama_mhs, d.nama as nama_dosen, mk.nama as nama_mk "
                + "FROM nilai n "
                + "LEFT JOIN mahasiswa m ON n.id_mahasiswa = m.id_mahasiswa "
                + "LEFT JOIN dosen d ON n.id_dosen = d.id_dosen "
                + "LEFT JOIN matakuliah mk ON n.id_matakuliah = mk.id_matakuliah "
                + "WHERE m.nama LIKE ? OR d.nama LIKE ? OR mk.nama LIKE ?"; 

        try (Connection conn = DBHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            String searchKeyword = "%" + keyword + "%";
            ps.setString(1, searchKeyword);
            ps.setString(2, searchKeyword);
            ps.setString(3, searchKeyword);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Nilai nilai = new Nilai();
                    nilai.setId_nilai(rs.getInt("id_nilai"));
                    nilai.setNilai_tugas(rs.getInt("nilai_tugas"));
                    nilai.setNilai_uts(rs.getInt("nilai_uts"));
                    nilai.setNilai_uas(rs.getInt("nilai_uas"));
                    nilai.setNilai_akhir(rs.getInt("nilai_akhir"));

                    Mahasiswa m = new Mahasiswa();
                    m.setIdMahasiswa(rs.getInt("id_mahasiswa"));
                    m.setNama(rs.getString("nama_mhs"));
                    nilai.setMahasiswa(m);

                    Dosen d = new Dosen();
                    d.setIdDosen(rs.getInt("id_dosen"));
                    d.setNama(rs.getString("nama_dosen"));
                    nilai.setDosen(d);

                    MataKuliah mk = new MataKuliah();
                    mk.setIdMatakuliah(rs.getInt("id_matakuliah"));
                    mk.setNama(rs.getString("nama_mk"));
                    nilai.setMatakuliah(mk);

                    listNilai.add(nilai);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNilai;
    }
    
    public void save() {
        if (getById(id_nilai) == null) {
            String sql = "INSERT INTO nilai (id_mahasiswa, id_dosen, id_matakuliah, nilai_tugas, nilai_uts, nilai_uas, nilai_akhir) VALUES(?,?,?,?,?,?,?)";
            try (Connection conn = DBHelper.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, this.mahasiswa.getIdMahasiswa());
                ps.setInt(2, this.dosen.getIdDosen());
                ps.setInt(3, this.matakuliah.getIdMatakuliah());
                ps.setInt(4, this.nilai_tugas);
                ps.setInt(5, this.nilai_uts);
                ps.setInt(6, this.nilai_uas);
                ps.setInt(7, this.nilai_akhir);
                ps.executeUpdate();
            } catch (Exception e) { e.printStackTrace(); }
        } else {
            String sql = "UPDATE nilai SET id_mahasiswa=?, id_dosen=?, id_matakuliah=?, nilai_tugas=?, nilai_uts=?, nilai_uas=?, nilai_akhir=? WHERE id_nilai=?";
            try (Connection conn = DBHelper.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                
                ps.setInt(1, this.mahasiswa.getIdMahasiswa());
                ps.setInt(2, this.dosen.getIdDosen());
                ps.setInt(3, this.matakuliah.getIdMatakuliah());
                ps.setInt(4, this.nilai_tugas);
                ps.setInt(5, this.nilai_uts);
                ps.setInt(6, this.nilai_uas);
                ps.setInt(7, this.nilai_akhir);
             
                ps.setInt(8, this.id_nilai);
                
                ps.executeUpdate();
            } catch (Exception e) { e.printStackTrace(); }
        }
    }
    
    public static void delete(int id) {
        String sql = "DELETE FROM nilai WHERE id_nilai = ?";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
