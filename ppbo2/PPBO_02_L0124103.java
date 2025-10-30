// Nama : Kevin Ananda Putra
// NIM  : L0124103

public class PPBO_02_L0124103 {
    public static void main(String[] args) {
        Swalayan toko = new Swalayan();
        
        System.out.println("=== SISTEM SWALAYAN ===\n");
        
        toko.tampilkanBarang();
        
        System.out.println("Customer 1 datang:");
        int totalBelanja = toko.jualBarang("Beras", 2);
        int uangBayar = 50000;
        int kembalian = uangBayar - totalBelanja;
        System.out.println("Total: Rp " + totalBelanja);
        System.out.println("Bayar: Rp " + uangBayar);
        System.out.println("Kembalian: Rp " + kembalian + "\n");
        
        System.out.println("Customer 2 datang:");
        totalBelanja = toko.jualBarang("Minyak", 1);
        uangBayar = 20000;
        kembalian = uangBayar - totalBelanja;
        System.out.println("Total: Rp " + totalBelanja);
        System.out.println("Bayar: Rp " + uangBayar);  
        System.out.println("Kembalian: Rp " + kembalian + "\n");
        
        toko.restokBarang("Beras", 5);
        toko.restokBarang("Gula", 3);
        
        toko.tampilkanBarang();
        System.out.println("Total transaksi hari ini: " + Swalayan.getTotalTransaksi());
        System.out.println("Kas toko: Rp " + toko.getKas());
    }
}

class Barang {
    String nama;
    int harga;
    int stok;
    
    public Barang(String nama, int harga, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }
}

class Swalayan {
    private static int totalTransaksi = 0;
    private int kas = 100000;
    private Barang[] daftarBarang;
    private static final double PAJAK = 0.11;
    
    public Swalayan() {
        daftarBarang = new Barang[5];
        daftarBarang[0] = new Barang("Beras", 15000, 10);
        daftarBarang[1] = new Barang("Minyak", 18000, 8);
        daftarBarang[2] = new Barang("Gula", 12000, 6);
        daftarBarang[3] = new Barang("Teh", 8000, 12);
        daftarBarang[4] = new Barang("Kopi", 10000, 15);
    }
    
    public int jualBarang(String namaBarang, int jumlah) {
        for (int i = 0; i < daftarBarang.length; i++) {
            if (daftarBarang[i].nama.equals(namaBarang)) {
                if (daftarBarang[i].stok >= jumlah) {
                    int hargaTotal = daftarBarang[i].harga * jumlah;
                    int pajak = (int)(hargaTotal * PAJAK);
                    int totalBayar = hargaTotal + pajak;
                    
                    daftarBarang[i].stok -= jumlah;
                    kas += totalBayar;
                    totalTransaksi++;
                    
                    System.out.println("Membeli " + jumlah + " " + namaBarang);
                    System.out.println("Harga: Rp " + hargaTotal);
                    System.out.println("Pajak: Rp " + pajak);
                    
                    return totalBayar;
                } else {
                    System.out.println("Stok " + namaBarang + " tidak cukup!");
                    return 0;
                }
            }
        }
        System.out.println("Barang " + namaBarang + " tidak ditemukan!");
        return 0;
    }
    
    public void restokBarang(String namaBarang, int jumlah) {
        for (int i = 0; i < daftarBarang.length; i++) {
            if (daftarBarang[i].nama.equals(namaBarang)) {
                daftarBarang[i].stok += jumlah;
                System.out.println("Restok " + jumlah + " " + namaBarang);
                return;
            }
        }
    }
    
    public void tampilkanBarang() {
        System.out.println("=== DAFTAR BARANG ===");
        for (int i = 0; i < daftarBarang.length; i++) {
            System.out.println((i+1) + ". " + daftarBarang[i].nama + 
                             " - Rp " + daftarBarang[i].harga + 
                             " (Stok: " + daftarBarang[i].stok + ")");
        }
        System.out.println();
    }
    
    public static int getTotalTransaksi() {
        return totalTransaksi;
    }
    
    public int getKas() {
        return kas;
    }
}