import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String code;
    private String name;
    private String email;
    private float gpa;

    public Student() {
    }

    public Student(int id, String code, String name,
                   String email, float gpa) {
        super();
        this.id = id;
        this.name = name;
        this.code = code;
        this.gpa = gpa;
        this.email = email;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getGpa() {
        return gpa;
    }


    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

//    public boolean setGpa(float gpa) {
//        if (gpa >= 0 && gpa <= 100) {
//            this.gpa = gpa;
//            return true;
//        } else {
//            System.out.println(" Please enter your GPA again: (from 0 to 100)");
//            return false;
//        }
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
//    @Override
//    public String toString(){
//        return "StudentCode: "+this.code ;
//    }
}