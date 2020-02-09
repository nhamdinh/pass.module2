import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    public static Scanner scanner = new Scanner(System.in);
    private StudentDao studentDao;
    private List<Student> studentList;
    public StudentManager() {
        studentDao = new StudentDao();
        studentList = studentDao.read();
    }

    public static void showMenu() {
        System.out.println("-----------menu------------");
        System.out.println("1. Add student.");
        System.out.println("2. Edit student by id.");
        System.out.println("3. Delete student by id.");
        System.out.println("4. Sort student by gpa and write to file.txt.");
        System.out.println("5. Sort student by name.");
        System.out.println("6. Show student.");
        System.out.println("7. Show students have GPA bigger than score.");
        System.out.println("8. Show data student has wrong code.");
        System.out.println("0. exit.");
        System.out.println("---------------------------");
        System.out.print("Please choose: ");
    }

    public void add() {
        int id = (studentList.size() > 0) ? (studentList.size() + 1) : 1;
//       int id = inputId();
        System.out.println("student id = " + id);
        String code = inputCode();
        String name = inputName();
        String email = inputEmail();
        float gpa = inputGpa();

        Student student = new Student(id, code, name, email, gpa);
        studentList.add(student);
        studentDao.writeDataStudent(studentList);
    }

    public void edit(int id) {
        boolean isExisted = false;
        int size = studentList.size();
        for (int i = 0; i < size; i++) {
            if (studentList.get(i).getId() == id) {
                isExisted = true;
                studentList.get(i).setCode(inputCode());
                studentList.get(i).setName(inputName());
                studentList.get(i).setEmail(inputEmail());
                studentList.get(i).setGpa(inputGpa());

                break;
            }
        }
        if (!isExisted) {
            System.out.printf("id = %d not existed.\n", id);
        } else {
            studentDao.writeDataStudent(studentList);
        }
    }

    public void delete(int id) {
        Student student = null;
        int size = studentList.size();
        for (int i = 0; i < size; i++) {
            if (studentList.get(i).getId() == id) {
                student = studentList.get(i);
                break;
            }
        }
        if (student != null) {
            studentList.remove(student);
            studentDao.writeDataStudent(studentList);
        } else {
            System.out.printf("id = %d not existed.\n", id);
        }
    }



//    public int inputId() {
//        System.out.print("Input student id: ");
//        return scanner.nextInt();
//    }
    public int inputId() {
        System.out.print("Input student id: ");
        while (true) {
            try {

                int id = scanner.nextInt();
                return id;
            } catch (NumberFormatException ex) {
                System.out.print("invalid! Input student id again: ");
            }
        }
    }

    private String inputCode() {
        System.out.print("Input student code: ");
        return scanner.nextLine();
    }

    private String inputName() {
        System.out.print("Input student name: ");
        return scanner.nextLine();
    }

    private String inputEmail() {
        System.out.print("Input student email: ");
        return scanner.nextLine();

    }

    private float inputGpa() {
        System.out.print("Input student gpa: ");
        while (true) {
            try {
                float gpa = Float.parseFloat((scanner.nextLine()));
                if (gpa < 0.0 && gpa > 100.0) {
                    throw new NumberFormatException();
                }
                return gpa;
            } catch (NumberFormatException ex) {
                System.out.print("invalid! Input student GPA again: ");
            }
        }
    }

    public void sortStudentByName() {
        Collections.sort(studentList, new SortStudentByName());
    }

    public void sortStudentByGPA() {
        Collections.sort(studentList, new SortStudentByGPA());
        studentDao.writeByGpa(studentList);

    }

    public void show() {

        System.out.printf("%-15s%-25s%-15s%-15s%-20s%-10s\n",
                "StudentId", "StudentCode", "StudentName",
                "StudentAge", "StudentEmail", "StudentGpa");


        for (Student student : studentList) {
            System.out.format("%5d | ", student.getId());
            System.out.format("%20s | ", student.getCode());
            System.out.format("%20s | ", student.getName());
            System.out.format("%20s | ", student.getEmail());
            System.out.format("%10.1f%n", student.getGpa());

        }
    }


    public void displayStudentByGpa(float score) {
        for (Student student : studentList) {
            if (student.getGpa() > score) {
                System.out.println(student);
            }
        }

    }

    public void displayStudentByCode(String code) {
        for (Student student : studentList) {
            if (student.getCode() == code) {
                System.out.println(student);
            }
        }

    }

}