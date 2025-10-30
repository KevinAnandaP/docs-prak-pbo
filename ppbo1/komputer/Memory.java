package komputer;

public class Memory {
    private int kapasitas;
    private int terpakai;
    private String tipe;
    
    public Memory(int kapasitas, String tipe) {
        this.kapasitas = kapasitas;
        this.terpakai = 0;
        this.tipe = tipe;
        System.out.println("Memory " + tipe + " " + kapasitas + "GB terpasang");
    }
    
    public void pakaiMemory(int ukuran) {
        if (terpakai + ukuran <= kapasitas) {
            terpakai += ukuran;
            System.out.println("Menggunakan " + ukuran + "GB memory");
        } else {
            System.out.println("Memory tidak cukup!");
        }
    }
    
    public void kosongkanMemory(int ukuran) {
        if (ukuran <= terpakai) {
            terpakai -= ukuran;
            System.out.println("Membebaskan " + ukuran + "GB memory");
        } else {
            terpakai = 0;
            System.out.println("Memory dikosongkan");
        }
    }
    
    public void tampilkanInfo() {
        System.out.println("Memory: " + tipe);
        System.out.println("Kapasitas: " + kapasitas + "GB");
        System.out.println("Terpakai: " + terpakai + "GB"); 
        System.out.println("Tersisa: " + (kapasitas - terpakai) + "GB");
    }
    
    public int getKapasitas() {
        return kapasitas;
    }
    
    public String getTipe() {
        return tipe;
    }
}