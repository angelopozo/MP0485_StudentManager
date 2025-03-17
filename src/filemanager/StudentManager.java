package filemanager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import main.Student;

/**
 *
 * @author Angelo
 */
public class StudentManager {

    static File register = new File(System.getProperty("user.dir") + File.separator + "registro.txt");
    public static BufferedReader brProgram = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Student> students = new ArrayList<>();

    public static void createFile() {
        if (!register.exists()) {
            try {
                register.createNewFile();
            } catch (IOException ex) {
                StudentManager.showError(ex);
            }
        }
    }

    public static boolean isEmpty() {
        try {
            FileReader fr = new FileReader(register);
            BufferedReader brFile = new BufferedReader(fr);

            for (String line = brFile.readLine(); line != null; line = brFile.readLine()) {
                return false;
            }

        } catch (IOException ex) {
            showError(ex);
        }
        return true;
    }

    public static void initializeInformationStudent() {
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
        if (!isEmpty()) {
            showRegister();
            try {
                System.out.print("Indique el DNI del Alumno que quiere eliminar: ");
                String dni = brProgram.readLine();

                for (int i = 0; i < students.size(); i++) {
                    if (students.get(i).getDni().equalsIgnoreCase(dni)) {
                        students.remove(students.get(i));
                    }
                }

                overWriteRegister();
                System.out.println("El Alumno ha sido eliminado correctamente.");
            } catch (IOException ex) {
                showError(ex);
            }
        } else {
            System.out.println("No hay ningún alumno en el registro.\n");
        }
    }

    public static void showRegister() {
        if (!isEmpty()) {
            String linea = "<html>\n";
            linea += "<div>\n";
            for (Student s : students) {
                linea += "<p>Nombre: " + s.getName() + " " + s.getSurname() + "</p>\n";
                linea += "<p>Edad: " + s.getAge() + "</p>\n";
                linea += "<p>DNI: " + s.getDni() + "</p>\n";
                linea += "<p>Curso: " + s.getGrade() + "</p>\n\n";
            }
            linea += "</div>";
            linea += "\n</html>";
            System.out.println(linea);
        } else {
            System.out.println("No hay ningún alumno en el registro.\n");
        }
    }

    public static void showStudentByDNI() {
        if (!isEmpty()) {
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
        } else {
            System.out.println("No hay ningún alumno en el registro.\n");
        }
    }

    public static void showError(Exception ex) {
        System.err.println("Ha ocurrido un error: " + ex.getMessage() + "\n");
    }
}
