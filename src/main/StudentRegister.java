package main;

import filemanager.FileManager;
import static filemanager.FileManager.showError;
import java.io.IOException;

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
            opc = menu();
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
    
    public static int menu() {
        int opc = 0;
        try {
            System.out.println("--- MENÚ DEL REGISTRO DE ALUMNOS ---");
            System.out.println("1.- Agregar un nuevo alumno del registro");
            System.out.println("2.- Mostrar la lista de alumnos registrados");
            System.out.println("3.- Eliminar un alumno del registro");
            System.out.println("4.- Buscar un alumno por su DNI.");
            System.out.println("5.- Salir del programa.");

            System.out.print("Seleccione una opción: ");
            opc = Integer.parseInt(FileManager.brProgram.readLine());

        } catch (IOException | NumberFormatException ex) {
            showError(ex);
        }
        return opc;
    }
    
}
