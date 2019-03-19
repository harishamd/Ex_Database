/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes_sbd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import tubes_sbd.TUBES_SBD.*;
import tubes_sbd.Tamu.*;

/**
 *
 * @author harishl
 */
public class Menu {
    
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);
    
    public static void main(String[] args) {
    System.out.println("\n========= MENU =========");
        System.out.println("1. Pegawai");
        System.out.println("2. Tamu");
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
                    TUBES_SBD tubes = new TUBES_SBD();
                    tubes.getClass(TUBES_SBD.showMenu());
                    break;
                case 2:
                    Tamu tamu = new Tamu();
                    tamu.getClass(Tamu.showMenu());
                    break;
                default:
                    System.out.println("Pilihan yang dimasukan salah!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
