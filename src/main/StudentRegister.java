package main;

import filemanager.FileManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Angelo
 */
public class StudentRegister {

    public static BufferedReader brProgram = new BufferedReader(new InputStreamReader(System.in));
    public static ArrayList<Student> students = new ArrayList<>();
    public static File register = new File(System.getProperty("user.dir") + File.separator + "registro.txt");
    
    public static void main(String[] args) {
        if (!register.exists()) {
            try {
                register.createNewFile();
            } catch (IOException ex) {
                FileManager.showError(ex);
            }
        }
        
        FileManager.initializaArrayList();
        int opc;
        do {
            opc = FileManager.menu();
            switch (opc) {
                case 1:
                    FileManager.newStudent();
                    break;
                case 2:
                    FileManager.showRegister();
                    break;
                case 3:
                    FileManager.removeStudent();
                    break;
                case 4:
                    FileManager.showStudentByDNI();
                    break;
                case 5:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no disponible\n");
            }
        } while (opc != 5);
    }
}
