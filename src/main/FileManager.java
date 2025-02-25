package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static main.StudentRegister.brProgram;
import static main.StudentRegister.register;
import static main.StudentRegister.students;

/**
 *
 * @author Angelo
 */
public class FileManager {

    public static void initializaArrayList() {
        try {
            FileReader fr = new FileReader(register);
            BufferedReader brFile = new BufferedReader(fr);

            try {
                for (String line = brFile.readLine(); line != null; line = brFile.readLine()) {
                    String[] array = line.split(";");

                    Student student = new Student(array[0], array[1], Integer.valueOf(array[2]), array[3], array[4]);
                    students.add(student);
                }
            } catch (IOException ex) {
                System.err.println("Ha ocurrido un error: " + ex.getMessage());
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Ha ocurrido un error: " + ex.getMessage());
        }
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
            opc = Integer.valueOf(brProgram.readLine());
        } catch (IOException | NumberFormatException ex) {
            System.err.println("Ha ocurrido un error: " + ex.getMessage());
        }
        return opc;
    }

    public static void newStudent() {
        System.out.println(students.get(1).getDni());
        try {
            System.out.print("\nIndique el nombre del Alumno: ");
            String name = brProgram.readLine();
            System.out.print("Indique el apellido del Alumno: ");
            String surname = brProgram.readLine();
            System.out.print("Indique la edad del Alumno: ");
            int age = Integer.valueOf(brProgram.readLine());
            System.out.print("Indique el curso del Alumno: ");
            String grade = brProgram.readLine();

            boolean correctDNI;
            String dni;

            do {
                System.out.print("Indique el DNI del Alumno: ");
                dni = brProgram.readLine();

                correctDNI = true;

                for (int i = 0; i < students.size(); i++) {
                    if (students.get(i).getDni().equals(dni)) {
                        correctDNI = false;
                        System.out.println("El DNI ya existe. Por favor, introduzca un nuevo DNI.");
                        break;
                    }
                }

            } while (!correctDNI);
            
            Student student = new Student(name, surname, age, grade, dni);

            System.out.println("Alumno creado correctamente.\n");
            students.add(student);
            overWriteRegister();
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error: " + ex.getMessage());
        }
    }

    public static void overWriteRegister() {
        try {
            FileWriter fw = new FileWriter(register, false);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < students.size(); i++) {
                bw.write(students.get(i).toString() + "\n");
            }
            bw.close();
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error: " + ex.getMessage());
        }
    }

    public static void removeStudent() {
        showRegister();
        try {
            System.out.println("Indique el DNI del Alumno que quiere eliminar: ");
            String dni = brProgram.readLine();

            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getDni().equalsIgnoreCase(dni)) {
                    students.remove(students.get(i));
                }
            }
            overWriteRegister();
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error: " + ex.getMessage());
        }
    }

    public static void showRegister() {
        try {
            FileReader fr = new FileReader(register);
            BufferedReader brFile = new BufferedReader(fr);
            
            System.out.println("\n");
            for (String line = brFile.readLine(); line != null; line = brFile.readLine()) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error: " + ex.getMessage());
        }

    }

    public static void showStudentByDNI() {
        try {
            System.out.println("\nIndique el DNI del Alumno que quiere buscar: ");
            String dni = brProgram.readLine();

            FileReader fr = new FileReader(register);
            BufferedReader brFile = new BufferedReader(fr);

            for (String line = brFile.readLine(); line != null; line = brFile.readLine()) {
                if (line.contains(dni)) {
                    System.out.println(line);
                }
            }
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un error: " + ex.getMessage());
        }
    }
}
