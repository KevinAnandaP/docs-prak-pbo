package com.responsi;

import java.util.List;
import java.util.Scanner;

public class Responsi1_PPBO_L0124103_SC {
    
    private static Responsi1_PPBO_L0124103_SC1 dbHelper;
    private static Scanner scanner;

    public static void main(String[] args) {
        dbHelper = new Responsi1_PPBO_L0124103_SC1();
        scanner = new Scanner(System.in);
        
        System.out.println("To-Do List Application");
        
        boolean running = true;
        while (running) {
            showMenu();
            System.out.print("Pilih menu: ");
            
            String input = scanner.nextLine();
            
            switch (input) {
                case "1":
                    viewTasks();
                    break;
                case "2":
                    addTask();
                    break;
                case "3":
                    markTaskDone();
                    break;
                case "4":
                    deleteTask();
                    break;
                case "0":
                    running = false;
                    System.out.println("Jangan lupa dikerjakan");
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("=== MENU ===");
        System.out.println("1. Lihat Daftar Tugas");
        System.out.println("2. Tambah Tugas Baru");
        System.out.println("3. Tandai Tugas Selesai");
        System.out.println("4. Hapus Tugas");
        System.out.println("0. Keluar");
    }

    private static void viewTasks() {
        System.out.println("--- Daftar Tugas ---");
        List<Responsi1_PPBO_L0124103_SC2> tasks = dbHelper.getAllTasks();
        
        if (tasks.isEmpty()) {
            System.out.println("Tugas masih kosong");
        } else {
            for (Responsi1_PPBO_L0124103_SC2 task : tasks) {
                System.out.println(task.getId() + ". " + task.toString());
            }
        }
    }

    private static void addTask() {
        System.out.print("Deskripsi tugas: ");
        String desc = scanner.nextLine();
        
        if (desc.trim().isEmpty()) {
            System.out.println("Deskripsi harus diisi");
            return;
        }

        dbHelper.addTask(desc);
    }

    private static void markTaskDone() {
        viewTasks();
        System.out.print("Masukkan ID tugas: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            dbHelper.updateTaskStatus(id, true);
        } catch (NumberFormatException e) {
            System.out.println("Input harus angka");
        }
    }

    private static void deleteTask() {
        viewTasks();
        System.out.print("Masukkan ID tugas: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            
            System.out.print("(y/n): ");
            String confirmStr = scanner.nextLine();
            if (confirmStr.length() > 0) {
                char confirm = confirmStr.toLowerCase().charAt(0);
                if (confirm == 'y') {
                    dbHelper.deleteTask(id);
                } else {
                    System.out.println("Penghapusan dibatalkan");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Input harus angka");
        }
    }
}
