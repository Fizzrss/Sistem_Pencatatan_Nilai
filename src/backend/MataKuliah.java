package backend;

import java.sql.*;
import java.util.ArrayList;

public class MataKuliah {
    private int idMatakuliah;
    private String nama;
    private int sks;

    // --- Konstruktor ---
    public MataKuliah() {
    }

    public MataKuliah(String nama, int sks) {
        this.nama = nama;
        this.sks = sks;
    }

    // --- Getter dan Setter ---
    public int getIdMatakuliah() {
        return idMatakuliah;
    }

    public void setIdMatakuliah(int idMatakuliah) {
        this.idMatakuliah = idMatakuliah;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }

    // --- Method Helper (CRUD) ---

    public static MataKuliah getById(int id) {
        MataKuliah mk = null;
        String sql = "SELECT * FROM matakuliah WHERE id_matakuliah = ?";

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    mk = new MataKuliah();
                    mk.setIdMatakuliah(rs.getInt("id_matakuliah"));
                    mk.setNama(rs.getString("nama"));
                    mk.setSks(rs.getInt("sks"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mk;
    }

    public static ArrayList<MataKuliah> getAll() {
        ArrayList<MataKuliah> list = new ArrayList<>();
        String sql = "SELECT * FROM matakuliah";

        try (Connection conn = DBHelper.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                MataKuliah mk = new MataKuliah();
                mk.setIdMatakuliah(rs.getInt("id_matakuliah"));
                mk.setNama(rs.getString("nama"));
                mk.setSks(rs.getInt("sks"));
                list.add(mk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<MataKuliah> getAll(String keyword) {
        ArrayList<MataKuliah> list = new ArrayList<>();
        String sql = "SELECT * FROM matakuliah WHERE nama LIKE ?";

        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, "%" + keyword + "%");
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MataKuliah mk = new MataKuliah();
                    mk.setIdMatakuliah(rs.getInt("id_matakuliah"));
                    mk.setNama(rs.getString("nama"));
                    mk.setSks(rs.getInt("sks"));
                    list.add(mk);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void save() {
        if (this.idMatakuliah == 0) {
            // Insert
            String sql = "INSERT INTO matakuliah (nama, sks) VALUES (?, ?)";
            
            try (Connection conn = DBHelper.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                
                ps.setString(1, this.nama);
                ps.setInt(2, this.sks);
                ps.executeUpdate();
                
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        this.idMatakuliah = rs.getInt(1);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Update
            String sql = "UPDATE matakuliah SET nama=?, sks=? WHERE id_matakuliah=?";
            
            try (Connection conn = DBHelper.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                
                ps.setString(1, this.nama);
                ps.setInt(2, this.sks);
                ps.setInt(3, this.idMatakuliah);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean delete(int id) {
        String sql = "DELETE FROM matakuliah WHERE id_matakuliah = ?";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public String toString(){
        return nama;
    }
}