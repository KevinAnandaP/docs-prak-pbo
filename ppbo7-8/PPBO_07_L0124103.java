import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Peserta {
    private String idPeserta;
    private String nama;
    private int usia;
    private String programKursus;
    private double biayaKursus;
    
    public Peserta(String idPeserta, String nama, int usia, String programKursus, double biayaKursus) {
        this.idPeserta = idPeserta;
        this.nama = nama;
        this.usia = usia;
        this.programKursus = programKursus;
        this.biayaKursus = biayaKursus;
    }
    
    public String getIdPeserta() {
        return idPeserta;
    }
    
    public String getNama() {
        return nama;
    }
    
    public int getUsia() {
        return usia;
    }
    
    public String getProgramKursus() {
        return programKursus;
    }
    
    public double getBiayaKursus() {
        return biayaKursus;
    }
    
    public void setIdPeserta(String idPeserta) {
        this.idPeserta = idPeserta;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public void setUsia(int usia) {
        this.usia = usia;
    }
    
    public void setProgramKursus(String programKursus) {
        this.programKursus = programKursus;
    }
    
    public void setBiayaKursus(double biayaKursus) {
        this.biayaKursus = biayaKursus;
    }
    
    public void tampilkanInfo() {
        System.out.println("==========================================");
        System.out.println("ID Peserta     : " + idPeserta);
        System.out.println("Nama           : " + nama);
        System.out.println("Usia           : " + usia + " tahun");
        System.out.println("Program Kursus : " + programKursus);
        System.out.println("Biaya Kursus   : Rp " + biayaKursus);
        System.out.println("==========================================");
    }
    
    public String keFormatFile() {
        return idPeserta + "|" + nama + "|" + usia + "|" + programKursus + "|" + biayaKursus;
    }
}

public class PPBO_07_L0124103 {
    private static ArrayList<Peserta> listPeserta = new ArrayList<>();
    private static final String FILE_DATA = "data_peserta.txt";
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        bacaFile();
        
        int menu;
        do {
            tampilMenu();
            System.out.print("Masukkan pilihan: ");
            menu = input.nextInt();
            input.nextLine();
            
            System.out.println();
            
            switch (menu) {
                case 1:
                    tambahData();
                    break;
                case 2:
                    lihatData();
                    break;
                case 3:
                    simpanFile();
                    break;
                case 4:
                    bacaFile();
                    System.out.println("Data berhasil dimuat");
                    break;
                case 5:
                    System.out.println("Terima kasih");
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
            }
            
            System.out.println();
            
        } while (menu != 5);
        
        input.close();
    }
    
    private static void tampilMenu() {
        System.out.println("========================================");
        System.out.println("  SISTEM MANAJEMEN PESERTA KURSUS");
        System.out.println("========================================");
        System.out.println("1. Tambah Peserta");
        System.out.println("2. Tampilkan Semua Peserta");
        System.out.println("3. Simpan ke File");
        System.out.println("4. Muat dari File");
        System.out.println("5. Keluar");
        System.out.println("========================================");
    }
    
    private static void tambahData() {
        System.out.println("--- Tambah Peserta Baru ---");
        
        System.out.print("ID Peserta: ");
        String id = input.nextLine();
        
        System.out.print("Nama: ");
        String nama = input.nextLine();
        
        System.out.print("Usia: ");
        int usia = input.nextInt();
        input.nextLine();
        
        System.out.print("Program Kursus: ");
        String program = input.nextLine();
        
        System.out.print("Biaya Kursus: ");
        double biaya = input.nextDouble();
        input.nextLine();
        
        Peserta p = new Peserta(id, nama, usia, program, biaya);
        listPeserta.add(p);
        
        System.out.println("\nData peserta berhasil ditambahkan");
    }
    
    private static void lihatData() {
        if (listPeserta.isEmpty()) {
            System.out.println("Belum ada data peserta yang tersimpan");
            return;
        }
        
        System.out.println("--- Daftar Peserta Kursus ---");
        System.out.println("Total peserta: " + listPeserta.size());
        System.out.println();
        
        for (int i = 0; i < listPeserta.size(); i++) {
            System.out.println("Peserta " + (i + 1) + ":");
            listPeserta.get(i).tampilkanInfo();
            System.out.println();
        }
    }
    
    private static void simpanFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_DATA));
            
            for (int i = 0; i < listPeserta.size(); i++) {
                writer.write(listPeserta.get(i).keFormatFile());
                writer.newLine();
            }
            
            writer.close();
            System.out.println("Data berhasil disimpan");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data");
        }
    }
    
    private static void bacaFile() {
        File file = new File(FILE_DATA);
        
        if (!file.exists()) {
            System.out.println("Belum ada data peserta yang tersimpan");
            return;
        }
        
        listPeserta.clear();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_DATA));
            String baris;
            int total = 0;
            
            while ((baris = reader.readLine()) != null) {
                String[] data = baris.split("\\|");
                
                if (data.length == 5) {
                    String id = data[0];
                    String nama = data[1];
                    int usia = Integer.parseInt(data[2]);
                    String program = data[3];
                    double biaya = Double.parseDouble(data[4]);
                    
                    Peserta p = new Peserta(id, nama, usia, program, biaya);
                    listPeserta.add(p);
                    total++;
                }
            }
            
            reader.close();
            
            if (total == 0) {
                System.out.println("Belum ada data peserta yang tersimpan");
            } else {
                System.out.println("Berhasil memuat " + total + " data peserta");
            }
            
        } catch (IOException e) {
            System.out.println("Gagal membaca file");
        } catch (NumberFormatException e) {
            System.out.println("Format data tidak valid");
        }
    }
}