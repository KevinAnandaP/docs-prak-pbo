// Nama : Kevin Ananda Putra
// NIM  : L0124103

import java.util.ArrayList;
import java.util.Scanner;

public class PPBO_03_L0124103 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> hewan = new ArrayList<>();
        ArrayList<String> tanaman = new ArrayList<>();
        
        System.out.println("=== Aplikasi Pencatatan Hewan dan Tanaman ===");
        
        while (true) {
            System.out.print("Masukkan data, atau ketik 'exit' untuk berhenti: ");
            String data = input.nextLine();
            
            if (data.equalsIgnoreCase("exit")) {
                break;
            }
            
            String hasil = CheckInput(data);
            
            if (hasil.equals("Hewan")) {
                AddHewan(hewan, data);
                System.out.println("Data hewan berhasil ditambahkan");
            } else if (hasil.equals("Tanaman")) {
                AddTanaman(tanaman, data);
                System.out.println("Data tanaman berhasil ditambahkan");
            } else {
                System.out.println("Input tidak valid! Harus diawali 'Hewan' atau 'Tanaman'");
            }
        }
        
        System.out.println("\n=== Menu Tampilan ===");
        System.out.println("1. Tampilkan Daftar Hewan");
        System.out.println("2. Tampilkan Daftar Tanaman");
        System.out.println("3. Tampilkan Semua");
        System.out.print("Pilih menu (1-3): ");
        int pilihan = input.nextInt();
        
        if (pilihan == 1) {
            PrintHewan(hewan);
        } else if (pilihan == 2) {
            PrintTanaman(tanaman);
        } else if (pilihan == 3) {
            PrintHewan(hewan);
            PrintTanaman(tanaman);
        } else {
            System.out.println("Pilihan tidak valid!");
        }
        
        input.close();
    }
    
    public static String CheckInput(String input) {
        if (input.startsWith("Hewan")) {
            return "Hewan";
        } else if (input.startsWith("Tanaman")) {
            return "Tanaman";
        } else {
            return "Invalid";
        }
    }
    
    public static void AddHewan(ArrayList<String> hewan, String input) {
        if (input.startsWith("Hewan")) {
            hewan.add(input);
        }
    }
    
    public static void AddTanaman(ArrayList<String> tanaman, String input) {
        if (input.startsWith("Tanaman")) {
            tanaman.add(input);
        }
    }
    
    public static void PrintHewan(ArrayList<String> hewan) {
        System.out.println("\n=== Daftar Hewan ===");
        if (hewan.isEmpty()) {
            System.out.println("Belum ada data hewan");
        } else {
            for (int i = 0; i < hewan.size(); i++) {
                System.out.println((i+1) + ". " + hewan.get(i));
            }
        }
    }
    
    public static void PrintTanaman(ArrayList<String> tanaman) {
        System.out.println("\n=== Daftar Tanaman ===");
        if (tanaman.isEmpty()) {
            System.out.println("Belum ada data tanaman");
        } else {
            for (int i = 0; i < tanaman.size(); i++) {
                System.out.println((i+1) + ". " + tanaman.get(i));
            }
        }
    }
}