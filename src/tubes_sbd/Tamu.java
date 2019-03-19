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
import static tubes_sbd.TUBES_SBD.JDBC_DRIVER;
import static tubes_sbd.TUBES_SBD.input;
//import static tubes_sbd.TUBES_SBD.rs;

/**
 *
 * @author harishl
 */
public class Tamu {
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
        System.out.println("1. Insert Nama Tamu");
        System.out.println("2. Show Data Tamu");
        System.out.println("3. Edit Data Tamu");
        System.out.println("4. Delete Data Tamu");
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
                    insertTamu();
                    break;
                case 2:
                    showData();
                    break;
                case 3:
                    updateTamu();
                    break;
                case 4:
                    deleteTamu();
                    break;
                default:
                    System.out.println("Pilihan yang dimasukan salah!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void showData() {
        String sql = "SELECT * FROM Tamu"; // manggil db "mahasiswa" dan bisa diganti sesuai nama db kalian

        try {
            rs = stmt.executeQuery(sql);
            
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
            String sql = "INSERT INTO Tamu (ID, NAMA, JENIS_KELAMIN, ALAMAT, NO_TELEPON) VALUE('%s', '%s', '%s', '%s', '%s')";
            sql = String.format(sql, id, nama, jenis_kelamin, alamat, no_telepon);

            // simpan data
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
            String sql = "UPDATE Pegawai SET NAMA='%s', JENIS_KELAMIN='%s', ALAMAT='%s', NO_TELEPON'%d' WHERE ID=%d";
            sql = String.format(sql, nama, jenis_kelamin, alamat, no_telepon, id);

            // update data pegawai
            stmt.execute(sql);
            
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
            String sql = String.format("DELETE FROM Pegawai WHERE ID=%d", ID); // manggil query nim
            // hapus data
            stmt.execute(sql);
            
            System.out.println("Data Pegawai Sudah Dihapus...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

