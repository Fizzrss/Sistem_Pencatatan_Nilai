package backend;

import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author kevin
 */
public class Dosen {

    private int idDosen;
    private String nip;
    private String nama;
    private String jabatan;

    // konstruktor tanpa parameter
    public Dosen() {
    }

    // konstruktor ber parameter
    public Dosen(String nip, String nama, String jabatan) {
        this.nip = nip;
        this.nama = nama;
        this.jabatan = jabatan;
    }

    public int getIdDosen() {
        return idDosen;
    }

    public void setIdDosen(int idDosen) {
        this.idDosen = idDosen;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    // methods untuk mengembalikan data
    private Dosen getDosen(ResultSet rs) throws SQLException {
        Dosen dos = new Dosen();
        dos.setIdDosen(rs.getInt("id_dosen"));
        dos.setNip(rs.getString("nip"));
        dos.setNama(rs.getString("nama"));
        dos.setJabatan(rs.getString("jabatan"));
        return dos;
    }

    // method getById
    public static Dosen getById(int id) {
        Dosen dos = new Dosen();
        String sql = "SELECT * FROM dosen WHERE id_dosen = ?";

        try (Connection conn = DBHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // instance untuk memanggil method getDosen
                    dos = new Dosen().getDosen(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dos;
    }

    // method insert
    private void insert() {
        String sql = "INSERT INTO dosen (nip, nama, jabatan) VALUES (?, ?, ?)";
        try (Connection conn = DBHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, this.nip);
            ps.setString(2, this.nama);
            ps.setString(3, this.jabatan);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    this.idDosen = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // method update
    private void update() {
        String sql = "UPDATE dosen SET nip = ?, nama = ?, jabatan = ? WHERE idDosen = ?";
        try (Connection conn = DBHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, this.nip);
            ps.setString(2, this.nama);
            ps.setString(3, this.jabatan);
            ps.setInt(4, this.idDosen);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // method save
    public void save() {
        if (this.idDosen == 0) {
            // Jika idDosen = 0, lakukan INSERT
            insert();
        } else {
            // Jika idDosen != 0, lakukan UPDATE
            update();
        }
    }

    public static ArrayList<Dosen> getAll() {
        ArrayList<Dosen> listDosen = new ArrayList<>();
        String sql = "SELECT * FROM dosen";

        try (Connection conn = DBHelper.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Dosen dos = new Dosen();
                dos.setIdDosen(rs.getInt("id_dosen"));
                dos.setNip(rs.getString("nip"));
                dos.setNama(rs.getString("nama"));
                dos.setJabatan(rs.getString("jabatan"));
                listDosen.add(dos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDosen;
    }

    // method getAll
    public static ArrayList<Dosen> getAll(String keyword) {
        ArrayList<Dosen> listDosen = new ArrayList<>();
        String sql = "SELECT * FROM dosen "
                + "WHERE nama LIKE ? OR jabatan LIKE ? OR nip LIKE ?";

        try (Connection conn = DBHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            String likeKeyword = "%" + keyword + "%";
            ps.setString(1, likeKeyword);
            ps.setString(2, likeKeyword);
            ps.setString(3, likeKeyword);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // objek dosen untuk memanggil method getDosen
                    Dosen dos = new Dosen().getDosen(rs);
                    listDosen.add(dos);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listDosen;
    }

    // method delete
    public static boolean delete(int idDosen) {
        String sql = "DELETE FROM dosen WHERE id_dosen = ?";

        // Anda bisa tambahkan logika pengecekan ketergantungan di sini jika diperlukan
        try (Connection conn = DBHelper.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idDosen);
            int affectedRows = ps.executeUpdate();

            // Mengembalikan true jika setidaknya 1 baris terhapus
            return affectedRows > 0;
        } catch (Exception e) {
            // Jika terjadi exception (misalnya karena Foreign Key constraint), dianggap gagal
            System.err.println("Error saat menghapus Dosen: " + e.getMessage());
            return false;
        }
    }
    
    public String toString(){
        return nama;
    }
}
