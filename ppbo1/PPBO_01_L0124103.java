// Nama : Kevin Ananda Putra
// NIM  : L0124103

import komputer.*;

public class PPBO_01_L0124103 {
    public static void main(String[] args) {
        System.out.println("Program Komputer Gaming\n");
        
        Processor cpu = new Processor("Intel i7", 8, 3.5);
        Memory ram = new Memory(16, "DDR4");
        GamingPC komputer = new GamingPC("Gaming Rig", cpu, ram);
        
        komputer.tampilkanInfo();
        komputer.nyalakan();
        komputer.mainGame("Valorant");
        
        cpu.overclock(4.0);
        ram.kosongkanMemory(4);
        
        komputer.cekPerforma();
        komputer.matikan();
    }
}