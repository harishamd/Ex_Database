/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_sbd;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import static tubes_sbd.Tamu.input;
import static tubes_sbd.Tamu.rs;

/**
 *
 * @author harishl
 */
public class TUBES_SBD {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; //Driver dari Java Database Connection
    static final String DB_URL = "jdbc:mysql://localhost/DataPegawai"; // Nama DB di localhost MySQL Kalian
    static final String USER = "root"; // Username DB Kalian
    static final String PASS = ""; // Pass DB Kalian

    // Menyiapkan objek yang diperlukan untuk mengelola database
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // register driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
           

            while (!conn.isClosed()) {
                showMenu();
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    static void showMenu() {
        System.out.println("\n========= SELAMAT DATANG =========");
        System.out.println("1. Insert Nama Pegawai");
        System.out.println("2. Insert Nama Tamu");
        System.out.println("3. Show Data Pegawai");
        System.out.println("4. Show Data Tamu");
        System.out.println("5. Edit Data Pegawai");
        System.out.println("6. Edit Data Tamu");
        System.out.println("7. Delete Data Pegawai");
        System.out.println("8. Delete Data Tamu");
        System.out.println("0. Keluar Aplikasi");
        System.out.println("");
        System.out.print("Masukan Pilihan> ");

        try {
            int pilihan = Integer.parseInt(input.readLine());

            switch (pilihan) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    insertPegawai();
                    break;
                case 2:
                    insertTamu();
                    break;
                case 3:
                    showDataPegawai();
                    break;
                case 4:
                    showDataTamu();
                    break;
                case 5:
                    updatePegawai();
                    break;
                case 6:
                    updateTamu();
                    break;
                case 7:
                    deletePegawai();
                    break;
                case 8:
                    deleteTamu();
                    break;
                default:
                    System.out.println("Pilihan yang dimasukan salah!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void showDataPegawai() {
        String sql = "SELECT * FROM Pegawai"; // manggil db "mahasiswa" dan bisa diganti sesuai nama db kalian

        try {
            rs = stmt.executeQuery(sql);
            
            System.out.println("+--------------------------------+");
            System.out.println("|          DATA PEGAWAI          |");
            System.out.println("+--------------------------------+");

            System.out.println("ID -- NAMA -- JENIS_KELAMIN -- JENIS_PEKERJAAN -- GAJI");
            System.out.println("");
            
            while (rs.next()) {
                int id = rs.getInt("ID");
                String nama = rs.getString("NAMA");
                String jenis_kelamin = rs.getString("JENIS_KELAMIN");
                String jenis_pekerjaan = rs.getString("JENIS_PEKERJAAN");
                int gaji = rs.getInt("GAJI");

                System.out.println(String.format("%d. %s -- %s --- %s --- %d.", id, nama, jenis_kelamin, jenis_pekerjaan, gaji));
                // &d = decimal || &s = String
                // kalo kalian ingin IO data pastikan tersambung dengan %s (string) / &d (int)
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    static void showDataTamu() {
        String sqll = "SELECT * FROM Tamu"; // manggil db "mahasiswa" dan bisa diganti sesuai nama db kalian

        try {
            rs = stmt.executeQuery(sqll);
            
            System.out.println("+--------------------------------+");
            System.out.println("|           DATA TAMU            |");
            System.out.println("+--------------------------------+");

            System.out.println("ID -- NAMA -- JENIS_KELAMIN -- ALAMAT -- NO_TELEPON");
            System.out.println("");
            
            while (rs.next()) {
                int id = rs.getInt("ID");
                String nama = rs.getString("NAMA");
                String jenis_kelamin = rs.getString("JENIS_KELAMIN");
                String alamat = rs.getString("ALAMAT");
                int no_telepon = rs.getInt("NO_TELEPON");

                System.out.println(String.format("%d. %s -- %s --- %s --- %d.", id, nama, jenis_kelamin, alamat, no_telepon));
                // &d = decimal || &s = String
                // kalo kalian ingin IO data pastikan tersambung dengan %s (string) / &d (int)
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    

    static void insertPegawai() {
        try {
            // ambil input dari user
            System.out.print("ID: ");
            String id = input.readLine().trim();
            System.out.print("NAMA: ");
            String nama = input.readLine().trim();
            System.out.print("JENIS_KELAMIN: ");
            String jenis_kelamin = input.readLine().trim();
            System.out.print("JENIS_PEKERJAAN: ");
            String jenis_pekerjaan = input.readLine().trim();
            System.out.print("GAJI: ");
            String gaji = input.readLine().trim();
 
            // query simpan
            String sql = "INSERT INTO Pegawai (ID, NAMA, JENIS_KELAMIN, JENIS_PEKERJAAN, GAJI) VALUE('%s', '%s', '%s', '%s', '%s')";
            sql = String.format(sql, id, nama, jenis_kelamin, jenis_pekerjaan, gaji);

            // simpan data
            stmt.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    static void insertTamu() {
        try {
            // ambil input dari user
            System.out.print("ID: ");
            String id = input.readLine().trim();
            System.out.print("NAMA: ");
            String nama = input.readLine().trim();
            System.out.print("JENIS_KELAMIN: ");
            String jenis_kelamin = input.readLine().trim();
            System.out.print("ALAMAT: ");
            String alamat = input.readLine().trim();
            System.out.print("NO_TELEPON: ");
            String no_telepon = input.readLine().trim();
 
            // query simpan
            String sqll = "INSERT INTO Tamu (ID, NAMA, JENIS_KELAMIN, ALAMAT, NO_TELEPON) VALUE('%s', '%s', '%s', '%s', '%s')";
            sqll = String.format(sqll, id, nama, jenis_kelamin, alamat, no_telepon);

            // simpan data
            stmt.execute(sqll);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void updatePegawai() {
        try {
            
            // ambil input dari user
            System.out.print("ID yang akan diedit: ");
            int id = Integer.parseInt(input.readLine());
            System.out.print("NAMA: ");
            String nama = input.readLine().trim();
            System.out.print("JENIS_KELAMIN: ");
            String jenis_kelamin = input.readLine().trim();
            System.out.print("JENIS_PEKERJAAN: ");
            String jenis_pekerjaan = input.readLine().trim();
            System.out.print("GAJI: ");
            int gaji = Integer.parseInt(input.readLine());

            // query update
            String sql = "UPDATE Pegawai SET NAMA='%s', JENIS_KELAMIN='%s', JENIS_PEKERJAAN='%s', GAJI='%d' WHERE ID=%d";
            sql = String.format(sql, nama, jenis_kelamin, jenis_pekerjaan, gaji, id);

            // update data pegawai
            stmt.execute(sql);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void updateTamu() {
        try {
            
            // ambil input dari user
            System.out.print("ID yang akan diedit: ");
            int id = Integer.parseInt(input.readLine());
            System.out.print("NAMA: ");
            String nama = input.readLine().trim();
            System.out.print("JENIS_KELAMIN: ");
            String jenis_kelamin = input.readLine().trim();
            System.out.print("ALAMAT: ");
            String alamat = input.readLine().trim();
            System.out.print("NO_TELEPON: ");
            int no_telepon = Integer.parseInt(input.readLine());

            // query update
            String sqll = "UPDATE tamu SET NAMA='%s', JENIS_KELAMIN='%s', ALAMAT='%s', NO_TELEPON'%d' WHERE ID=%d";
            sqll = String.format(sqll, nama, jenis_kelamin, alamat, no_telepon, id);

            // update data tamu
            stmt.execute(sqll);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void deletePegawai() {
        try {
            
            // ambil input dari user
            System.out.print("ID yang mau dihapus: ");
            int ID = Integer.parseInt(input.readLine());
            
            // buat query hapus
            String sql = String.format("DELETE FROM Pegawai WHERE ID=%d", ID); // manggil query nim
            // hapus data
            stmt.execute(sql);
            
            System.out.println("Data Pegawai Sudah Dihapus...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void deleteTamu() {
        try {
            
            // ambil input dari user
            System.out.print("ID yang mau dihapus: ");
            int ID = Integer.parseInt(input.readLine());
            
            // buat query hapus
            String sqll = String.format("DELETE FROM Pegawai WHERE ID=%d", ID); // manggil query nim
            // hapus data
            stmt.execute(sqll);
            
            System.out.println("Data Pegawai Sudah Dihapus...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
   
    

