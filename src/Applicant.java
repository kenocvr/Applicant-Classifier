public class Applicant {
    private String firstName;
    private String lastName;
    private int age;
    private double maxGPA;
    private double GPA;
    private String state;
    private double SATScore;
    private double ACTScore;
    private int felonies;

    public Applicant(String firstName, String lastName, int age, String state) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.state = state;
    }

    public Applicant(String firstName, String lastName, int age, double maxGPA, double GPA, String state, double SATScore, double ACTScore, int felonies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.maxGPA = maxGPA;
        this.GPA = GPA;
        this.state = state;
        this.SATScore = SATScore;
        this.ACTScore = ACTScore;
        this.felonies = felonies;
    }

    public Applicant(String firstName, String lastName, int age, double maxGPA, double GPA, String state, double SATScore, int felonies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.maxGPA = maxGPA;
        this.GPA = GPA;
        this.state = state;
        this.SATScore = SATScore;
        this.felonies = felonies;
    }

    public Applicant(String firstName, String lastName, int age, double maxGPA, double GPA,  double ACTScore,String state, int felonies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.maxGPA = maxGPA;
        this.GPA = GPA;
        this.state = state;
        this.ACTScore = ACTScore;
        this.felonies = felonies;
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

    public double getMaxGPA() {
        return maxGPA;
    }

    public void setMaxGPA(double maxGPA) {
        this.maxGPA = maxGPA;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getSATScore() {
        return SATScore;
    }

    public void setSATScore(double SATScore) {
        this.SATScore = SATScore;
    }

    public double getACTScore() {
        return ACTScore;
    }

    public void setACTScore(double ACTScore) {
        this.ACTScore = ACTScore;
    }

    public int getFelonies() {
        return felonies;
    }

    public void setFelonies(int felonies) {
        this.felonies = felonies;
    }

    public void addFelonies(int other)
    {
        this.felonies+=other;
    }

    public double getGPAPercentage()
    {
        return GPA/maxGPA;
    }
}
