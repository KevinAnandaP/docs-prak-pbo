package komputer;

public class Processor {
    private String nama;
    private int core;
    private double speed;
    private boolean overclock;
    
    public Processor(String nama, int core, double speed) {
        this.nama = nama;
        this.core = core;
        this.speed = speed;
        this.overclock = false;
        System.out.println("Processor " + nama + " siap digunakan");
    }
    
    public void overclock(double speedBaru) {
        if (speedBaru > this.speed) {
            this.speed = speedBaru;
            this.overclock = true;
            System.out.println("Processor di-overclock ke " + speedBaru + " GHz");
        }
    }
    
    public void prosesData(String tugas) {
        System.out.println("Memproses: " + tugas);
        System.out.println("Menggunakan " + core + " core pada " + speed + " GHz");
    }
    
    public void tampilkanInfo() {
        System.out.println("Processor: " + nama);
        System.out.println("Core: " + core);
        System.out.println("Speed: " + speed + " GHz");
        System.out.println("Overclock: " + (overclock ? "Ya" : "Tidak"));
    }
    
    public String getNama() {
        return nama;
    }
    
    public double getSpeed() {
        return speed;
    }
}