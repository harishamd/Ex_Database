/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_sbd;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 *
 * @author harishl
 */
public class TUBES_SBD {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; //Driver dari Java Database Connection
    static final String DB_URL = "jdbc:mysql://localhost/DataPegawai"; // Nama DB di localhost MySQL Kalian
    static final String USER = "root"; // Username DB Kalian
    static final String PASS = ""; // Pass DB Kalian
    
    //Objek untuk JSON Converter
    static ArrayList<String> data = new ArrayList<String>();
    static ArrayList<String> tamu = new ArrayList<String>();
    static PreparedStatement ps = null;
    static String path = "Pegawai.txt"; //Export file, Ekstensi bisa diubah, jadi .txt bisa, .json bisa, dll
    static String driver="com.mysql.jdbc.Driver";
    static String url="jdbc:mysql://localhost/DataPegawai";
    static String username="root";
    static String password="";
    static String query="select * from Pegawai";
    
    //Objek untuk JSON Converter
    static String pathtamu = "Tamu.txt"; //Export file, Ekstensi bisa diubah, jadi .txt bisa, .json bisa, dll
    static String urltamu="jdbc:mysql://localhost/DataPegawai";
    static String querytamu="select * from Tamu";
    

    // Menyiapkan objek yang diperlukan untuk mengelola database
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);

    //JSON Converter
    @SuppressWarnings({ "unchecked" })
    public static void dataLoad(String path) {
        JSONObject obj1 = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        conn = koneksi.getDbConnection(driver, url, username,
                password);
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<String> columnNames = new ArrayList<String>();
            if (rs != null) {
                ResultSetMetaData columns = rs.getMetaData();
                int i = 0;
                while (i < columns.getColumnCount()) {
                    i++;
                    columnNames.add(columns.getColumnName(i));
                }
                while (rs.next()) {
                    JSONObject obj = new JSONObject();
                    for (i = 0; i < columnNames.size(); i++) {
                        data.add(rs.getString(columnNames.get(i)));
                        {
                            for (int j = 0; j < data.size(); j++) {
                                if (data.get(j) != null) {
                                    obj.put(columnNames.get(i), data.get(j));
                                }else {
                                    obj.put(columnNames.get(i), "");
                                }
                            }
                        }
                    }

                    jsonArray.add(obj);
                    obj1.put("Pegawai", jsonArray);
                    FileWriter file = new FileWriter(path);
                    file.write(obj1.toJSONString());
                    file.flush();
                    file.close();
                }
                ps.close();
            } else {
                JSONObject obj2 = new JSONObject();
                obj2.put(null, null);
                jsonArray.add(obj2);
                obj1.put("Pegawai", jsonArray);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    rs.close();
                    ps.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    
    @SuppressWarnings({ "unchecked" })
    public static void dataLoadTamu(String pathtamu) {
        JSONObject obj1tamu = new JSONObject();
        JSONArray jsonArraytamu = new JSONArray();
        conn = koneksi.getDbConnection(driver, urltamu, username,
                password);
        try {
            ps = conn.prepareStatement(querytamu);
            rs = ps.executeQuery();
            ArrayList<String> columnNames = new ArrayList<String>();
            if (rs != null) {
                ResultSetMetaData columns = rs.getMetaData();
                int i = 0;
                while (i < columns.getColumnCount()) {
                    i++;
                    columnNames.add(columns.getColumnName(i));
                }
                while (rs.next()) {
                    JSONObject objtamu = new JSONObject();
                    for (i = 0; i < columnNames.size(); i++) {
                        tamu.add(rs.getString(columnNames.get(i)));
                        {
                            for (int j = 0; j < tamu.size(); j++) {
                                if (tamu.get(j) != null) {
                                    objtamu.put(columnNames.get(i), tamu.get(j));
                                }else {
                                    objtamu.put(columnNames.get(i), "");
                                }
                            }
                        }
                    }

                    jsonArraytamu.add(objtamu);
                    obj1tamu.put("Tamu", jsonArraytamu);
                    FileWriter file = new FileWriter(pathtamu);
                    file.write(obj1tamu.toJSONString());
                    file.flush();
                    file.close();
                }
                ps.close();
            } else {
                JSONObject obj2tamu = new JSONObject();
                obj2tamu.put(null, null);
                jsonArraytamu.add(obj2tamu);
                obj1tamu.put("Tamu", jsonArraytamu);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    rs.close();
                    ps.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    
    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        driver = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost/DataPegawai";
        username = "root";
        password = "";
        path = "Pegawai.txt"; //Export file, Ekstensi bisa diubah, jadi .txt bisa, .json bisa, dll
        query = "select * from Pegawai";
        
        urltamu = "jdbc:mysql://localhost/DataPegawai";
        pathtamu = "Tamu.txt"; //Export file, Ekstensi bisa diubah, jadi .txt bisa, .json bisa, dll
        querytamu = "select * from Tamu";
        
        koneksi dc = new koneksi();
        dc.getDbConnection(driver,url,username,password);
        koneksi tamu = new koneksi();
        tamu.getDbConnection(driver,urltamu,username,password);
        TUBES_SBD formatter = new TUBES_SBD();
        formatter.dataLoad(path);
        
        TUBES_SBD Tamu = new TUBES_SBD();
        Tamu.dataLoad(pathtamu);
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
                String no_telepon = rs.getString("NO_TELEPON");

                System.out.println(String.format("%d. %s -- %s --- %s --- %s.", id, nama, jenis_kelamin, alamat, no_telepon));
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
            String no_telepon = input.readLine().trim();

            // query update
            String sqll = "UPDATE tamu SET NAMA='%s', JENIS_KELAMIN='%s', ALAMAT='%s', NO_TELEPON='%s' WHERE ID=%d";
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
            String sqll = String.format("DELETE FROM Tamu WHERE ID=%d", ID); // manggil query nim
            // hapus data
            stmt.execute(sqll);
            
            System.out.println("Data Tamu Sudah Dihapus...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
   
    

