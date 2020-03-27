package classifier;

import java.util.ArrayList;
import java.util.List;

public class Applicant {
    private String firstName;
    private String lastName;
    private int age;
    private double maxGPA;
    private double gpa;
    private String state;
    private Integer satScore;
    private Integer actScore;
    private List<Felony> felonies;



    public Applicant(String firstName, String lastName, int age, String state) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.state = state;
        felonies=new ArrayList<>();
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMaxGPA(double maxGPA) {
        this.maxGPA = maxGPA;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getSatScore() {
        return satScore;
    }

    public void setSatScore(Integer satScore) {
        this.satScore = satScore;
    }

    public Integer getActScore() {
        return actScore;
    }

    public void setActScore(Integer actScore) {
        this.actScore = actScore;
    }


    public List<Felony> getFelonies() {
        return felonies;
    }

    public void addFelonies(Felony felony)
    {
        this.felonies.add(felony);
    }

    public Double getGPAPercentage()
    {
        return gpa /maxGPA;
    }
}
