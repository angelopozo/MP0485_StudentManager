package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angelo
 */
public class StudentRegister {

    static BufferedReader brProgram = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Student> students = new ArrayList<>();
    static File register = new File("registro.txt");

    public static void main(String[] args) {
        try {
            register.createNewFile();
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
                        System.out.println("Opción no disponible");
                }
            } while (opc != 5);
        } catch (IOException ex) {
            Logger.getLogger(StudentRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
