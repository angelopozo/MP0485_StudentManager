package filemanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import main.Student;
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

                    Student student = new Student(array[0], array[1], Integer.parseInt(array[2]), array[3], array[4]);
                    students.add(student);
                }
            } catch (IOException ex) {
                showError(ex);
            }
        } catch (FileNotFoundException ex) {
            showError(ex);
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
            opc = Integer.parseInt(brProgram.readLine());

        } catch (IOException | NumberFormatException ex) {
            showError(ex);
        }
        return opc;
    }

    public static void newStudent() {
        try {
            System.out.print("\nIndique el nombre del Alumno: ");
            String name = brProgram.readLine();
            System.out.print("Indique el apellido del Alumno: ");
            String surname = brProgram.readLine();

            boolean correctAge = false;
            int age = 0;

            do {
                try {
                    System.out.print("Indique la edad del Alumno: ");
                    age = Integer.parseInt(brProgram.readLine());

                    correctAge = true;
                } catch (IOException | NumberFormatException ex) {
                    showError(ex);
                }
            } while (!correctAge);

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
            showError(ex);
        }
    }

    public static void overWriteRegister() {
        try {
            FileWriter fw = new FileWriter(register, false);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < students.size(); i++) {
                bw.write(students.get(i).toString() + "\n");
            }
            bw.flush();
            bw.close();
        } catch (IOException ex) {
            showError(ex);
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
            showError(ex);
        }
    }

    public static void showRegister() {
        try {
            FileReader fr = new FileReader(register);
            BufferedReader brFile = new BufferedReader(fr);

            for (String line = brFile.readLine(); line != null; line = brFile.readLine()) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            showError(ex);
        }
    }

    public static void showStudentByDNI() {
        try {
            System.out.print("\nIndique el DNI del Alumno que quiere buscar: ");
            String dni = brProgram.readLine();

            FileReader fr = new FileReader(register);
            BufferedReader brFile = new BufferedReader(fr);

            for (String line = brFile.readLine(); line != null; line = brFile.readLine()) {
                if (line.contains(dni)) {
                    System.out.println(line);
                }
            }
        } catch (IOException ex) {
            showError(ex);
        }
    }

    public static void showError(Exception ex) {
        System.err.println("Ha ocurrido un error: " + ex.getMessage() + "\n");
    }
}
