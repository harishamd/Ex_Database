/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_sbd;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author harishl
 */
public class koneksi_tamu {
    //menyiapkan parameter SQL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; //Driver Database
    static final String DB_URL = "jdbc:mysql://localhost/DataPegawai"; //nama database
    static final String USER = "root"; //username DB
    static final String PASS = " "; //password DB
    
    //membuat objek pengelola DB
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    
    public static void main(String[] args){
        // melakukan koneksi ke DB
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS); //BUAT KONEKSI DB
            stmt = conn.createStatement(); //statement DB
            String sql = "SELECT * FROM Pegawai"; //Buat Query
            rs = stmt.executeQuery(sql); //eksekusi query
            
            while(rs.next()){
                System.out.println("ID : "+rs.getInt("ID"));
                System.out.println("NAMA : "+rs.getString("NAMA"));
                System.out.println("JENIS KELAMIN : "+rs.getString("JENIS_KELAMIN"));
                System.out.println("JENIS PEKERJAAN : "+rs.getString("JENIS_PEKERJAAN"));
                System.out.println("GAJI : "+rs.getInt("GAJI"));
            }
            stmt.close();
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
}


