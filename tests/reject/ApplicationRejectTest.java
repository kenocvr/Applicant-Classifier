package reject;

import classifier.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationRejectTest {
    Applicant applicant;
    ReviewReport reviewReport;
    Calendar cal;
    Calendar cal2;
    Date felonyDate1;
    Date felonyDate2;

    @BeforeEach
    void initApplicant() {
        applicant = new Applicant("Carlos", "Rucker", 101, "California");
        reviewReport = new ReviewReport();
    }

    @BeforeEach
    void initFelony() {
        cal = Calendar.getInstance();
        cal2 = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal2.set(Calendar.YEAR, 2019);
        cal2.set(Calendar.MONTH, Calendar.FEBRUARY);
        cal2.set(Calendar.DAY_OF_MONTH, 13);
        felonyDate1 = cal.getTime();
        felonyDate2 = cal2.getTime();
    }

        @Test
        void testRejectWithGPA () {
            applicant.setMaxGPA(4.0);
            applicant.setGpa(2.2);
            reviewReport = Classifier.getReviewReport(applicant);
            assertEquals(ReviewType.INSTANT_REJECT, reviewReport.getReviewType());
            System.out.println("instant reject");
            System.out.println(reviewReport.getDescription());
        }

        @Test
        void testRejectWithFelonies () {
            applicant.setMaxGPA(4.0);
            applicant.setGpa(3.6);
            applicant.setSatScore(1922);
            applicant.addFelonies(new Felony(felonyDate1, "Microwaving Fish in the Break Room"));
            applicant.addFelonies(new Felony(felonyDate2, "Using Spaces - Not Tabs"));
            reviewReport = Classifier.getReviewReport(applicant);
            assertEquals(ReviewType.INSTANT_REJECT, reviewReport.getReviewType());
            System.out.println("Instant reject");
            System.out.println(reviewReport.getDescription());
        }

        @Test
        void testRejectWithName () {
            Applicant applicant = new Applicant("King", "T'Challa", 22, "California");
            applicant.setMaxGPA(4.0);
            applicant.setGpa(3.6);
            applicant.setSatScore(1922);
            reviewReport = Classifier.getReviewReport(applicant);
            assertEquals(ReviewType.INSTANT_REJECT, reviewReport.getReviewType());
            System.out.println("Instant reject the alter ego of the Black Panther: " + applicant.getFirstName() + " " + applicant.getLastName());
            System.out.println(reviewReport.getDescription());
        }

        @Test
        void testRejectWithAge () {
            Applicant applicant = new Applicant("John", "Henry", -22, "California");
            applicant.setMaxGPA(4.0);
            applicant.setGpa(3);
            applicant.setSatScore(1922);
            reviewReport = Classifier.getReviewReport(applicant);
            assertEquals(ReviewType.INSTANT_REJECT, reviewReport.getReviewType());
            System.out.println("Instant reject");
            System.out.println(reviewReport.getDescription());
        }

    }
