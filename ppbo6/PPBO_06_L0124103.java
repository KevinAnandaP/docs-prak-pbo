// Nama : Kevin Ananda Putra
// NIM  : L0124103

import java.util.ArrayList;
import java.util.Scanner;

public class PPBO_06_L0124103 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== SISTEM INVENTORI PERPUSTAKAAN ===\n");
        
        Buku[] dataBuku = new Buku[3];
        dataBuku[0] = new Buku("Apip and friends", 15, 85000);
        dataBuku[1] = new Buku("The Missing Balls", 10, 95000);
        dataBuku[2] = new Buku("Darkhold", 8, 70000);
        
        System.out.println("--- DATA BUKU (ARRAY) ---");
        tampilkanBuku(dataBuku);
        
        ArrayList<Buku> koleksiBuku = new ArrayList<>();
        koleksiBuku.add(new Buku("Kimi no Nawa", 12, 80000));
        koleksiBuku.add(new Buku("The Flash", 7, 75000));
        koleksiBuku.add(new Buku("Bumi", 5, 90000));
        
        System.out.println("\n--- DATA BUKU (ARRAYLIST) ---");
        tampilkanKoleksi(koleksiBuku);
        
        boolean lanjut = true;
        while(lanjut) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Tambah Buku Baru");
            System.out.println("2. Lihat Semua Buku");
            System.out.println("3. Hitung Total Stok & Nilai");
            System.out.println("4. Urutkan Berdasarkan Harga");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            
            int pilihan = sc.nextInt();
            sc.nextLine();
            
            if(pilihan == 1) {
                System.out.print("Nama Buku: ");
                String nama = sc.nextLine();
                System.out.print("Stok: ");
                int stok = sc.nextInt();
                System.out.print("Harga: ");
                int harga = sc.nextInt();
                sc.nextLine();
                
                koleksiBuku.add(new Buku(nama, stok, harga));
                System.out.println("Buku berhasil ditambahkan!");
                
            } else if(pilihan == 2) {
                System.out.println("\n--- SEMUA BUKU ---");
                System.out.println("Dari Array:");
                tampilkanBuku(dataBuku);
                System.out.println("\nDari ArrayList:");
                tampilkanKoleksi(koleksiBuku);
                
            } else if(pilihan == 3) {
                hitungTotal(dataBuku, koleksiBuku);
                
            } else if(pilihan == 4) {
                urutkanBuku(dataBuku, koleksiBuku);
                
            } else if(pilihan == 5) {
                lanjut = false;
                System.out.println("Terima kasih!");
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }
        
        sc.close();
    }
    
    static void tampilkanBuku(Buku[] daftar) {
        if(daftar.length == 0) {
            System.out.println("Tidak ada data buku");
            return;
        }
        
        for(int i = 0; i < daftar.length; i++) {
            System.out.println((i+1) + ". " + daftar[i].nama + 
                             " | Stok: " + daftar[i].stok + 
                             " | Harga: Rp" + daftar[i].harga);
        }
    }
    
    static void tampilkanKoleksi(ArrayList<Buku> koleksi) {
        if(koleksi.isEmpty()) {
            System.out.println("Tidak ada data buku");
            return;
        }
        
        for(int i = 0; i < koleksi.size(); i++) {
            Buku b = koleksi.get(i);
            System.out.println((i+1) + ". " + b.nama + 
                             " | Stok: " + b.stok + 
                             " | Harga: Rp" + b.harga);
        }
    }
    
    static void hitungTotal(Buku[] array, ArrayList<Buku> list) {
        int totalStok = 0;
        long totalNilai = 0;
        
        for(int i = 0; i < array.length; i++) {
            totalStok += array[i].stok;
            totalNilai += (long)array[i].stok * array[i].harga;
        }
        
        for(Buku b : list) {
            totalStok += b.stok;
            totalNilai += (long)b.stok * b.harga;
        }
        
        System.out.println("\n--- TOTAL INVENTORI ---");
        System.out.println("Total Stok: " + totalStok + " buku");
        System.out.println("Total Nilai: Rp" + totalNilai);
    }
    
    static void urutkanBuku(Buku[] array, ArrayList<Buku> list) {
        ArrayList<Buku> semua = new ArrayList<>();
        
        for(int i = 0; i < array.length; i++) {
            semua.add(array[i]);
        }
        
        for(Buku b : list) {
            semua.add(b);
        }
        
        for(int i = 0; i < semua.size() - 1; i++) {
            for(int j = 0; j < semua.size() - i - 1; j++) {
                if(semua.get(j).harga > semua.get(j+1).harga) {
                    Buku temp = semua.get(j);
                    semua.set(j, semua.get(j+1));
                    semua.set(j+1, temp);
                }
            }
        }
        
        System.out.println("\n--- BUKU DIURUTKAN (TERMURAH - TERMAHAL) ---");
        for(int i = 0; i < semua.size(); i++) {
            Buku b = semua.get(i);
            System.out.println((i+1) + ". " + b.nama + 
                             " | Stok: " + b.stok + 
                             " | Harga: Rp" + b.harga);
        }
    }
}

class Buku {
    String nama;
    int stok;
    int harga;
    
    Buku(String nama, int stok, int harga) {
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
    }
}
