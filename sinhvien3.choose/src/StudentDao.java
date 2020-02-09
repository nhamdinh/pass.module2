import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentDao {
    private static final String STUDENT_FILE_NAME = "student.txt";
    private static final String SORT_FILE_NAME = "sortByGpa.txt";


    public void writeDataStudent(List<Student> listStudent) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(new File(STUDENT_FILE_NAME));
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listStudent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeOutputStream(fos);
            closeOutputStream(oos);
        }
    }

    public void writeByGpa(List<Student> listStudent) {

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(SORT_FILE_NAME);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String line = "";
            for (Student student : listStudent) {
                line = student.getId() +
                        "; " + student.getCode() +
                        "; " + student.getName() +
                        "; " + student.getEmail() +
                        "; " + student.getGpa() +
                        "\n";

                bufferedWriter.write(line);
                bufferedWriter.flush();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public List<Student> read() {


        List<Student> studentList = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(new File(STUDENT_FILE_NAME));
            ois = new ObjectInputStream(fis);
            studentList = (List<Student>) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeInputStream(fis);
            closeInputStream(ois);
        }
        return studentList;
    }

    private void closeInputStream(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeOutputStream(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}