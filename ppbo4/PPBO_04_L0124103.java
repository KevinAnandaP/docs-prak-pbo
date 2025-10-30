// Nama : Kevin Ananda Putra
// NIM  : L0124103

import java.util.Scanner;

public class PPBO_04_L0124103 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TokoKelontong warung = new TokoKelontong();
        boolean lanjut = true;
        
        System.out.println(">>> WARUNG PAK BUDI <<<");
        System.out.println();
        
        while(lanjut) {
            try {
                warung.lihatBarang();
                
                System.out.print("Nama barang yang dibeli (ketik 'batal' untuk selesai): ");
                String item = sc.nextLine();
                
                if(item.equals("batal")) {
                    lanjut = false;
                    continue;
                }
                
                assert item.length() > 0 : "Nama barang tidak boleh kosong";
                
                System.out.print("Berapa banyak: ");
                String inputJumlah = sc.nextLine();
                int qty = Integer.parseInt(inputJumlah);
                
                assert qty >= 1 : "Minimal beli 1";
                
                warung.prosesBeli(item, qty);
                System.out.println();
                
            } catch(NumberFormatException nfe) {
                System.out.println("Harus pakai angka\n");
            } catch(StokHabisException she) {
                System.out.println("Maaf: " + she.getMessage() + "\n");
            } catch(BarangTidakAdaException bga) {
                System.out.println("Maaf: " + bga.getMessage() + "\n");
            } catch(AssertionError ae) {
                System.out.println("Maaf: " + ae.getMessage() + "\n");
            } catch(Exception ex) {
                System.out.println("Ada error: " + ex.getMessage() + "\n");
            }
        }
        
        System.out.println("\n--- Nota Belanja ---");
        warung.cetakNota();
        sc.close();
    }
}

class ItemBarang {
    String namaItem;
    int hargaSatuan;
    int jumlahStok;
    
    ItemBarang(String nama, int harga, int stok) {
        namaItem = nama;
        hargaSatuan = harga;
        jumlahStok = stok;
    }
    
    String getNama() {
        return namaItem;
    }
    
    int getHarga() {
        return hargaSatuan;
    }
    
    int getStok() {
        return jumlahStok;
    }
    
    void kurangStok(int jml) throws StokHabisException {
        if(jml > jumlahStok) {
            throw new StokHabisException("Stok " + namaItem + " hanya ada " + jumlahStok);
        }
        jumlahStok = jumlahStok - jml;
    }
}

class TokoKelontong {
    ItemBarang[] daftarBarang;
    int totalHarga;
    String notaBelanja;
    
    TokoKelontong() {
        daftarBarang = new ItemBarang[5];
        daftarBarang[0] = new ItemBarang("Beras", 14500, 12);
        daftarBarang[1] = new ItemBarang("Minyak Goreng", 17500, 6);
        daftarBarang[2] = new ItemBarang("Gula Pasir", 11500, 9);
        daftarBarang[3] = new ItemBarang("Teh Celup", 7500, 20);
        daftarBarang[4] = new ItemBarang("Kopi Sachet", 9500, 15);
        totalHarga = 0;
        notaBelanja = "";
    }
    
    void lihatBarang() {
        System.out.println("--- Barang Tersedia ---");
        int i = 0;
        while(i < daftarBarang.length) {
            System.out.println((i+1) + ". " + daftarBarang[i].getNama() + 
                             " -> Rp" + daftarBarang[i].getHarga() + 
                             " [stok: " + daftarBarang[i].getStok() + "]");
            i++;
        }
        System.out.println();
    }
    
    void prosesBeli(String namaBarang, int qty) throws BarangTidakAdaException, StokHabisException {
        ItemBarang itemYgDipilih = null;
        
        for(int i=0; i<daftarBarang.length; i++) {
            if(daftarBarang[i].getNama().equalsIgnoreCase(namaBarang)) {
                itemYgDipilih = daftarBarang[i];
                break;
            }
        }
        
        if(itemYgDipilih == null) {
            throw new BarangTidakAdaException("Item '" + namaBarang + "' tidak ada di warung");
        }
        
        itemYgDipilih.kurangStok(qty);
        
        int hargaBayar = itemYgDipilih.getHarga() * qty;
        totalHarga = totalHarga + hargaBayar;
        
        notaBelanja += itemYgDipilih.getNama() + " (" + qty + "x) = Rp" + hargaBayar + "\n";
        
        System.out.println("OK! " + qty + " " + itemYgDipilih.getNama() + " masuk keranjang");
        System.out.println("Harga: Rp" + hargaBayar);
    }
    
    void cetakNota() {
        if(notaBelanja.length() == 0) {
            System.out.println("Tidak membeli apapun");
        } else {
            System.out.println(notaBelanja);
            System.out.println("------------------------");
            System.out.println("TOTAL: Rp" + totalHarga);
        }
    }
}

class StokHabisException extends Exception {
    StokHabisException(String pesan) {
        super(pesan);
    }
}

class BarangTidakAdaException extends Exception {
    BarangTidakAdaException(String pesan) {
        super(pesan);
    }
}