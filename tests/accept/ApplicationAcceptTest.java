package accept;

import classifier.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationAcceptTest {
    Applicant applicant;
    ReviewReport reviewReport;
    Calendar cal;
    Calendar cal2;
    Date felonyDate1;
    Date felonyDate2;

    @BeforeEach
    void initApplicant(){
        applicant = new Applicant("Carlos", "Rucker", 101, "California");
        reviewReport = new ReviewReport();
    }
    @BeforeEach
    void initFelony() {
        cal = Calendar.getInstance();
        cal2 = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1970);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal2.set(Calendar.YEAR, 1989);
        cal2.set(Calendar.MONTH, Calendar.FEBRUARY);
        cal2.set(Calendar.DAY_OF_MONTH, 13);
        felonyDate1 = cal.getTime();
        felonyDate2 = cal2.getTime();
    }

    @Test
    void testAcceptOctagenarianNotInCalifornia() {
        applicant.setAge(81);
        applicant.setState("New York");
        applicant.setMaxGPA(4.0);
        applicant.setGpa(3.6);
        applicant.setSatScore(1922);
        applicant.setActScore(28);
        reviewReport = Classifier.getReviewReport(applicant);
        System.out.println("instant accept: ");
        assertEquals(ReviewType.INSTANT_ACCEPT,reviewReport.getReviewType());
    }

    @Test
    void testAcceptFelonyOverFiveYearsAgo() {
            applicant.setMaxGPA(4.0);
            applicant.setGpa(3.6);
            applicant.setSatScore(1922);
            applicant.addFelonies(new Felony(felonyDate1, "Chewing Too Loudly"));
            applicant.addFelonies(new Felony(felonyDate2, "Excessive Use of Emoji"));
            reviewReport = Classifier.getReviewReport(applicant);
            assertEquals(ReviewType.INSTANT_ACCEPT, reviewReport.getReviewType());
            System.out.println("Instant reject");
            System.out.println(reviewReport.getDescription());
        }
    @Test
    void testAcceptOctagenarianInCalifornia() {
        Applicant applicant = new Applicant("Mister", "Rogers", 81, "California");
        applicant.setMaxGPA(4.0);
        applicant.setGpa(3.6);
        applicant.setSatScore(1922);
        applicant.setActScore(28);
        applicant.setState("California");
        applicant.setAge(81);
        reviewReport = Classifier.getReviewReport(applicant);
        System.out.println("instant accept");
        assertEquals(ReviewType.INSTANT_ACCEPT,reviewReport.getReviewType());
    }

    @Test
    void testAcceptLowSat() {
        applicant.setState("New York");
        applicant.setMaxGPA(4.0);
        applicant.setGpa(3.6);
        applicant.setSatScore(1700);
        applicant.setActScore(28);
        reviewReport = Classifier.getReviewReport(applicant);
        System.out.println("instant accept");
        assertEquals(ReviewType.INSTANT_ACCEPT,reviewReport.getReviewType());
    }

    @Test
    void testAccept() {
        applicant.setAge(22);
        applicant.setState("California");
        applicant.setMaxGPA(4.0);
        applicant.setGpa(3.6);
        applicant.setSatScore(1922);
        applicant.setActScore(28);
        reviewReport = Classifier.getReviewReport(applicant);
        System.out.println("instant accept");
        assertEquals(ReviewType.INSTANT_ACCEPT,reviewReport.getReviewType());
    }

}
