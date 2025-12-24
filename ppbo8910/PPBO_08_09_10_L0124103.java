import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

interface Perawatan {
    void servis();
    void gantiOli();
}

abstract class Kendaraan {
    private String noPlat, merk;
    private int tahun;

    public Kendaraan(String noPlat, String merk, int tahun) {
        this.noPlat = noPlat;
        this.merk = merk;
        this.tahun = tahun;
    }

    public String getNoPlat() {
        return noPlat;
    }
    public String getMerk() {
        return merk;
    }
    public int getTahun() {
        return tahun;
    }

    public abstract void nyalakanMesin();

    public void tampilkanInfo() {
        System.out.println("No Plat: " + noPlat + ", Merk: " + merk + ", Tahun: " + tahun);
    }
}

class Mobil extends Kendaraan implements Perawatan {
    private int pintu;

    public Mobil(String noPlat, String merk, int tahun, int pintu) {
        super(noPlat, merk, tahun);
        this.pintu = pintu;
    }

    public void nyalakanMesin() {
        System.out.println("Mobil " + getMerk() + " menyala.");
    }
    public void servis() {
        System.out.println("Servis mobil " + getMerk());
    }
    public void gantiOli() {
        System.out.println("Ganti oli mobil " + getMerk());
    }
    
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jenis: Mobil, Pintu: " + pintu);
    }
}

class Motor extends Kendaraan implements Perawatan {
    private String jenis;

    public Motor(String noPlat, String merk, int tahun, String jenis) {
        super(noPlat, merk, tahun);
        this.jenis = jenis;
    }

    public void nyalakanMesin() {
        System.out.println("Motor " + getMerk() + " menyala.");
    }
    public void servis() {
        System.out.println("Servis motor " + getMerk());
    }
    public void gantiOli() {
        System.out.println("Ganti oli motor " + getMerk());
    }

    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jenis: Motor, Tipe: " + jenis);
    }
}

class Truk extends Kendaraan implements Perawatan {
    private double muatan;

    public Truk(String noPlat, String merk, int tahun, double muatan) {
        super(noPlat, merk, tahun);
        this.muatan = muatan;
    }

    public void nyalakanMesin() {
        System.out.println("Truk " + getMerk() + " menyala.");
    }
    public void servis() {
        System.out.println("Servis truk " + getMerk());
    }
    public void gantiOli() {
        System.out.println("Ganti oli truk " + getMerk());
    }

    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Jenis: Truk, Muatan: " + muatan + " ton");
    }
}

class SimulasiJalan extends Thread {
    private String nama;
    private int speed;

    public SimulasiJalan(String nama, int speed) {
        this.nama = nama;
        this.speed = speed;
    }

    public void run() {
        System.out.println(nama + " jalan, speed: " + speed + " km/jam.");
        for (int i = 1; i <= 5; i++) {
            try {
                System.out.println(nama + " melaju... " + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
        System.out.println(nama + " sampai.");
    }
}

public class PPBO_08_09_10_L0124103 {
    static ArrayList<Kendaraan> list = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Tambah\n2. Tampil\n3. Simulasi\n4. Keluar");
            System.out.print("Pilih: ");
            int p = 0;
            try {
                p = Integer.parseInt(sc.nextLine());
            } catch(Exception e) {}

            if (p == 4) {
                break;
            }
            if (p == 1) {
                tambah();
            }
            else if (p == 2) {
                tampil();
            }
            else if (p == 3) {
                simulasi();
            }
        }
    }

    static void tambah() {
        System.out.print("\n1.Mobil \n2.Motor \n3.Truk \nPilih: ");
        int j = Integer.parseInt(sc.nextLine());
        System.out.print("Plat: "); String p = sc.nextLine();
        System.out.print("Merk: "); String m = sc.nextLine();
        System.out.print("Tahun: "); int t = Integer.parseInt(sc.nextLine());

        if (j == 1) {
            System.out.print("Pintu: ");
            list.add(new Mobil(p, m, t, Integer.parseInt(sc.nextLine())));
        } else if (j == 2) {
            System.out.print("Tipe: ");
            list.add(new Motor(p, m, t, sc.nextLine()));
        } else if (j == 3) {
            System.out.print("Muatan: ");
            list.add(new Truk(p, m, t, Double.parseDouble(sc.nextLine())));
        }
    }

    static void tampil() {
        for (Kendaraan k : list) {
            k.nyalakanMesin();
            if (k instanceof Perawatan) ((Perawatan) k).servis();
            k.tampilkanInfo();
        }
    }

    static void simulasi() {
        Random r = new Random();
        for (Kendaraan k : list) {
            new SimulasiJalan(k.getMerk(), 40 + r.nextInt(80)).start();
        }
    }
}
