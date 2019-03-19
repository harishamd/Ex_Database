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
public class koneksi {
    //menyiapkan parameter SQL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; //Driver Database
    static final String DB_URL = "jdbc:mysql://localhost/DataPegawai"; //nama database
    static final String USER = "root"; //username DB
    static final String PASS = " "; //password DB
    
    //membuat objek pengelola DB
    static Connection conn;
    static Statement stmtTamu, stmtPegawai;
    static ResultSet rsTamu, rsPegawai;
    
    public static void main(String[] args){
        // melakukan koneksi ke DB
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS); //BUAT KONEKSI DB
            stmtPegawai = conn.createStatement(); //statement DB
            String sql = "SELECT * FROM Pegawai"; //Buat Query
            rsPegawai = stmtPegawai.executeQuery(sql); //eksekusi query
            rsTamu = stmtTamu.executeQuery(sql); //eksekusi query
            
            while(rsPegawai.next()){
                System.out.println("ID : "+rsPegawai.getInt("ID"));
                System.out.println("NAMA : "+rsPegawai.getString("NAMA"));
                System.out.println("JENIS KELAMIN : "+rsPegawai.getString("JENIS_KELAMIN"));
                System.out.println("JENIS PEKERJAAN : "+rsPegawai.getString("JENIS_PEKERJAAN"));
                System.out.println("GAJI : "+rsPegawai.getInt("GAJI"));
            }
            while(rsTamu.next()){
                System.out.println("ID : "+rsTamu.getInt("ID"));
                System.out.println("NAMA : "+rsTamu.getString("NAMA"));
                System.out.println("JENIS KELAMIN : "+rsTamu.getString("JENIS_KELAMIN"));
                System.out.println("JENIS PEKERJAAN : "+rsTamu.getString("JENIS_PEKERJAAN"));
                System.out.println("GAJI : "+rsTamu.getInt("GAJI"));
            }
            
            stmtTamu.close();
            stmtPegawai.close();
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
}
