package main;

import filemanager.FileManager;

/**
 *
 * @author Angelo
 */
public class StudentRegister {
    
    public static void main(String[] args) {
        FileManager.createFile();
        FileManager.initializeInformationStudent();

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
                    System.out.println("Opción no disponible. Vuelve a intentarlo otra vez.\n");
            }
        } while (opc != 5);
    }
    
}
