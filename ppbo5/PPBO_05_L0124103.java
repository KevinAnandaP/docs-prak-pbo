// Nama : Kevin Ananda Putra
// NIM  : L0124103

import java.util.Scanner;

public class PPBO_05_L0124103 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== VALIDASI DATA ===\n");
        
        System.out.print("Masukkan Tanggal Lahir (dd/mm/yyyy): ");
        String tanggal = sc.nextLine();
        validasiTanggal(tanggal);
        
        System.out.print("Masukkan Nomor HP: ");
        String hp = sc.nextLine();
        validasiHP(hp);
        
        System.out.print("Masukkan Email: ");
        String email = sc.nextLine();
        validasiEmail(email);
        
        sc.close();
    }
    
    static void validasiTanggal(String tanggal) {
        if(tanggal.length() != 10 || tanggal.charAt(2) != '/' || tanggal.charAt(5) != '/') {
            System.out.println("Tanggal Lahir Tidak Valid\n");
            return;
        }
        
        try {
            int hari = Integer.parseInt(tanggal.substring(0, 2));
            int bulan = Integer.parseInt(tanggal.substring(3, 5));
            int tahun = Integer.parseInt(tanggal.substring(6, 10));
            
            if(hari >= 1 && hari <= 31 && bulan >= 1 && bulan <= 12 && tahun >= 1000 && tahun <= 9999) {
                System.out.println("Tanggal Lahir Valid\n");
            } else {
                System.out.println("Tanggal Lahir Tidak Valid\n");
            }
        } catch(Exception e) {
            System.out.println("Tanggal Lahir Tidak Valid\n");
        }
    }
    
    static void validasiHP(String hp) {
        if(hp.startsWith("+") || hp.startsWith("00")) {
            String nomor = hp.startsWith("+") ? hp.substring(1) : hp.substring(2);
            
            boolean semuaAngka = true;
            for(int i = 0; i < nomor.length(); i++) {
                if(!Character.isDigit(nomor.charAt(i))) {
                    semuaAngka = false;
                    break;
                }
            }
            
            if(semuaAngka && nomor.length() >= 10 && nomor.length() <= 15) {
                System.out.println("Nomor HP Valid\n");
            } else {
                System.out.println("Nomor HP Tidak Valid\n");
            }
        } else {
            System.out.println("Nomor HP Tidak Valid\n");
        }
    }
    
    static void validasiEmail(String email) {
        int panjang = email.length();
        
        if(panjang < 5 || panjang > 50) {
            System.out.println("Email Tidak Valid");
            return;
        }
        
        char awal = email.charAt(0);
        if(!Character.isLetterOrDigit(awal)) {
            System.out.println("Email Tidak Valid");
            return;
        }
        
        int posisiAt = -1;
        for(int i = 0; i < panjang; i++) {
            if(email.charAt(i) == '@') {
                if(posisiAt == -1) {
                    posisiAt = i;
                } else {
                    System.out.println("Email Tidak Valid");
                    return;
                }
            }
        }
        
        if(posisiAt == -1) {
            System.out.println("Email Tidak Valid");
            return;
        }
        
        boolean adaTitik = false;
        for(int i = posisiAt + 1; i < panjang; i++) {
            if(email.charAt(i) == '.') {
                adaTitik = true;
                break;
            }
        }
        
        if(adaTitik) {
            System.out.println("Email Valid");
        } else {
            System.out.println("Email Tidak Valid");
        }
    }
}
