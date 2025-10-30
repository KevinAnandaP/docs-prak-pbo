package komputer;

public class GamingPC {
    private String nama;
    private Processor cpu;
    private Memory ram;
    private boolean nyala;
    
    public GamingPC(String nama, Processor cpu, Memory ram) {
        this.nama = nama;
        this.cpu = cpu;
        this.ram = ram;
        this.nyala = false;
        System.out.println("PC Gaming " + nama + " sudah dirakit\n");
    }
    
    public void nyalakan() {
        if (!nyala) {
            nyala = true;
            System.out.println("Menyalakan " + nama + "...");
            System.out.println("Windows loading...");
            System.out.println(nama + " siap digunakan!\n");
        }
    }
    
    public void matikan() {
        if (nyala) {
            nyala = false;
            System.out.println("Mematikan " + nama + "...");
            System.out.println("PC dimatikan\n");
        }
    }
    
    public void mainGame(String game) {
        if (nyala) {
            System.out.println("Membuka game " + game);
            ram.pakaiMemory(8);
            cpu.prosesData("Rendering " + game);
            System.out.println("Game " + game + " berjalan lancar!\n");
        } else {
            System.out.println("PC belum dinyalakan!");
        }
    }
    
    public void tampilkanInfo() {
        System.out.println("PC Gaming: " + nama);
        System.out.println("Status: " + (nyala ? "Nyala" : "Mati"));
        cpu.tampilkanInfo();
        ram.tampilkanInfo();
        System.out.println();
    }
    
    public void cekPerforma() {
        System.out.println("Cek performa PC...");
        double skor = cpu.getSpeed() * ram.getKapasitas() * 10;
        System.out.println("Skor performa: " + (int)skor);
        if (skor > 500) {
            System.out.println("Performa sangat baik!");
        } else {
            System.out.println("Performa cukup baik");
        }
        System.out.println();
    }
}