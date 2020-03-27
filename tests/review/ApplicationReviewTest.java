package review;

import classifier.Applicant;
import classifier.Classifier;
import classifier.ReviewReport;
import classifier.ReviewType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationReviewTest {
    Applicant applicant;
    ReviewReport reviewReport;
    Calendar cal;

    @BeforeEach
    void initApplicant(){
        applicant = new Applicant("Carlos", "Rucker", 101, "California");
        reviewReport = new ReviewReport();
        cal = Calendar.getInstance();

    }



    @Test
    void testReviewLowSat() {
        applicant.setMaxGPA(4.0);
        applicant.setGpa(3.6);
        applicant.setSatScore(1700);
        applicant.setActScore(28);
        reviewReport = Classifier.getReviewReport(applicant);
        assertEquals(ReviewType.INSTANT_ACCEPT,reviewReport.getReviewType());
    }


    @Test
    void testReviewCheck() {
        applicant.setMaxGPA(4.0);
        applicant.setGpa(3.0);
        applicant.setSatScore(1922);
        reviewReport = Classifier.getReviewReport(applicant);
        assertEquals(ReviewType.FURTHER_REVIEW,reviewReport.getReviewType());


        applicant.setSatScore(1917);
        applicant.setActScore(20);
        reviewReport = Classifier.getReviewReport(applicant);
        assertEquals(ReviewType.FURTHER_REVIEW,reviewReport.getReviewType());

        applicant.setSatScore(1927);
        applicant.setActScore(30);
        applicant.setAge(15);
        reviewReport = Classifier.getReviewReport(applicant);
        assertEquals(ReviewType.FURTHER_REVIEW,reviewReport.getReviewType());

        applicant.setAge(30);
        reviewReport = Classifier.getReviewReport(applicant);
        assertEquals(ReviewType.FURTHER_REVIEW,reviewReport.getReviewType());

        applicant.setState("New York");
        reviewReport = Classifier.getReviewReport(applicant);
        assertEquals(ReviewType.FURTHER_REVIEW,reviewReport.getReviewType());

        System.out.println("further review");
    }


    }
