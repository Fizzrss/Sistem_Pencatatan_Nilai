package backend;

import java.sql.*;
import java.util.ArrayList;

public class Mahasiswa {
    private int idMahasiswa;
    private String nim;
    private String nama;
    private String prodi;
    private int semester;

    public Mahasiswa() {
    }

    public Mahasiswa(String nim, String nama, String prodi, int semester) {
        this.nim = nim;
        this.nama = nama;
        this.prodi = prodi;
        this.semester = semester;
    }

    public int getIdMahasiswa() {
        return idMahasiswa;
    }

    public void setIdMahasiswa(int idMahasiswa) {
        this.idMahasiswa = idMahasiswa;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    // ===========================
    //      GET ALL DATA
    // ===========================
    public static ArrayList<Mahasiswa> getAll() {
        ArrayList<Mahasiswa> list = new ArrayList<>();
        String sql = "SELECT * FROM mahasiswa";

        try (Connection conn = DBHelper.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Mahasiswa mhs = new Mahasiswa();
                mhs.setIdMahasiswa(rs.getInt("id_mahasiswa"));
                mhs.setNim(rs.getString("nim"));
                mhs.setNama(rs.getString("nama"));
                mhs.setProdi(rs.getString("prodi"));
                mhs.setSemester(rs.getInt("semester"));
                list.add(mhs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ===========================
    //  GET ALL WITH SEARCH
    // ===========================
    public static ArrayList<Mahasiswa> getAll(String keyword) {
        ArrayList<Mahasiswa> list = new ArrayList<>();
        String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ? OR nim LIKE ? OR prodi LIKE ?";

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String like = "%" + keyword + "%";
            ps.setString(1, like);
            ps.setString(2, like);
            ps.setString(3, like);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Mahasiswa mhs = new Mahasiswa();
                    mhs.setIdMahasiswa(rs.getInt("id_mahasiswa"));
                    mhs.setNim(rs.getString("nim"));
                    mhs.setNama(rs.getString("nama"));
                    mhs.setProdi(rs.getString("prodi"));
                    mhs.setSemester(rs.getInt("semester"));
                    list.add(mhs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ===========================
    //      GET BY ID
    // ===========================
    public static Mahasiswa getById(int id) {
        Mahasiswa mhs = null;
        String sql = "SELECT * FROM mahasiswa WHERE id_mahasiswa = ?";

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    mhs = new Mahasiswa();
                    mhs.setIdMahasiswa(rs.getInt("id_mahasiswa"));
                    mhs.setNim(rs.getString("nim"));
                    mhs.setNama(rs.getString("nama"));
                    mhs.setProdi(rs.getString("prodi"));
                    mhs.setSemester(rs.getInt("semester"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mhs;
    }

    // ===========================
    //          SAVE
    // ===========================
    public void save() {
        if (this.idMahasiswa == 0) {
            String sql = "INSERT INTO mahasiswa (nim, nama, prodi, semester) VALUES (?, ?, ?, ?)";

            try (Connection conn = DBHelper.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, this.nim);
                ps.setString(2, this.nama);
                ps.setString(3, this.prodi);
                ps.setInt(4, this.semester);
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        this.idMahasiswa = rs.getInt(1);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            String sql = "UPDATE mahasiswa SET nim=?, nama=?, prodi=?, semester=? WHERE id_mahasiswa=?";

            try (Connection conn = DBHelper.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, this.nim);
                ps.setString(2, this.nama);
                ps.setString(3, this.prodi);
                ps.setInt(4, this.semester);
                ps.setInt(5, this.idMahasiswa);
                ps.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // ===========================
    //          DELETE
    // ===========================
    public static boolean delete(int idMahasiswa) {
        String sql = "DELETE FROM mahasiswa WHERE id_mahasiswa = ?";

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idMahasiswa);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            // Jika terhubung ke tabel lain FK
            if (e.getErrorCode() == 1451) {
                System.out.println("Data mahasiswa masih digunakan di tabel lain");
            }

            e.printStackTrace();
            return false;
        }
    }
    
    public String toString(){
        return nama;
    }
}
