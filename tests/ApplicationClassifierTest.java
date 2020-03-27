import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationClassifierTest {


    @Test
    void xinstanceAcceptCheck0() {
        Applicant applicant = new Applicant("John", "Henry", 22, "California");
        applicant.setMaxGPA(4.0);
        applicant.setGPA(3.68);
        applicant.setSATScore(1922);
        ReviewReport reviewReport = Classifier.getReviewReport(applicant);
        System.out.println("instant accept because:" + applicant.getGPAPercentage());
        assertEquals(ReviewType.INSTANT_ACCEPT,reviewReport.getReviewType());
        applicant.setState("New York");
        applicant.setAge(81);
        applicant.setMaxGPA(5);
        applicant.setGPA(4.5);
        applicant.setSATScore(0);
        applicant.setACTScore(28);
        reviewReport = Classifier.getReviewReport(applicant);
        assertEquals(ReviewType.INSTANT_ACCEPT,reviewReport.getReviewType());
        applicant.setSATScore(1921);
        applicant.setACTScore(28);
        reviewReport = Classifier.getReviewReport(applicant);
        assertEquals(ReviewType.INSTANT_ACCEPT,reviewReport.getReviewType());
    }
    @Test
    void instanceAcceptCheck() {
        Applicant applicant = new Applicant("John", "Henry", 22, "California");
        applicant.setMaxGPA(4.0);
        applicant.setGPA(3.6);
        applicant.setSATScore(1922);
        ReviewReport reviewReport = Classifier.getReviewReport(applicant);
        System.out.println("instant accept");
        assertEquals(ReviewType.INSTANT_ACCEPT,reviewReport.getReviewType());
        applicant.setState("New York");
        applicant.setAge(81);
        applicant.setMaxGPA(5);
        applicant.setGPA(4.5);
        applicant.setSATScore(0);
        applicant.setACTScore(28);
        reviewReport = Classifier.getReviewReport(applicant);
        assertEquals(ReviewType.INSTANT_ACCEPT,reviewReport.getReviewType());
        applicant.setSATScore(1921);
        applicant.setACTScore(28);
        reviewReport = Classifier.getReviewReport(applicant);
        assertEquals(ReviewType.INSTANT_ACCEPT,reviewReport.getReviewType());
    }

    @Test
    void instanceRejectCheckWithName() {
        Applicant applicant = new Applicant("john", "Henry", 22, "California");
        applicant.setMaxGPA(4.0);
        applicant.setGPA(3.6);
        applicant.setSATScore(1922);
        ReviewReport reviewReport = Classifier.getReviewReport(applicant);

        assertEquals(ReviewType.INSTANT_REJECT,reviewReport.getReviewType());
        applicant.setFirstName("John");
        applicant.setLastName("henry");

        assertEquals(ReviewType.INSTANT_REJECT,reviewReport.getReviewType());
        applicant.setFirstName("John");
        applicant.setLastName("HeNry");

        System.out.println("instant reject");
        System.out.println(reviewReport.getDescription());
    }

    @Test
    void instanceRejectCheckWithFelonies() {
        Applicant applicant = new Applicant("John", "Henry", 22, "California");
        applicant.setMaxGPA(4.0);
        applicant.setGPA(3.6);
        applicant.setSATScore(1922);
        applicant.setFelonies(1);
        ReviewReport reviewReport = Classifier.getReviewReport(applicant);

        assertEquals(ReviewType.INSTANT_REJECT,reviewReport.getReviewType());
        System.out.println("instant reject");
        System.out.println(reviewReport.getDescription());
    }

    @Test
    void instanceRejectCheckWithGPA() {
        Applicant applicant = new Applicant("John", "Henry", 22, "California");
        applicant.setMaxGPA(4.0);
        applicant.setGPA(2.7);
        applicant.setSATScore(1922);

        ReviewReport reviewReport = Classifier.getReviewReport(applicant);

        assertEquals(ReviewType.INSTANT_REJECT,reviewReport.getReviewType());
        System.out.println("instant reject");
        System.out.println(reviewReport.getDescription());
    }

    @Test
    void instanceRejectCheckWithAge() {
        Applicant applicant = new Applicant("John", "Henry", -22, "California");
        applicant.setMaxGPA(4.0);
        applicant.setGPA(3);
        applicant.setSATScore(1922);

        ReviewReport reviewReport = Classifier.getReviewReport(applicant);

        assertEquals(ReviewType.INSTANT_REJECT,reviewReport.getReviewType());
        System.out.println("instant reject");
        System.out.println(reviewReport.getDescription());
    }

    @Test
    void furtherReviewCheck() {
        Applicant applicant = new Applicant("John", "Henry", 22, "California");
        applicant.setMaxGPA(4.0);
        applicant.setGPA(3);
        applicant.setSATScore(1922);
        ReviewReport reviewReport = Classifier.getReviewReport(applicant);
        assertEquals(ReviewType.FURTHER_REVIEW,reviewReport.getReviewType());
        applicant.setGPA(3.7);
        applicant.setSATScore(1900);
        reviewReport = Classifier.getReviewReport(applicant);
        assertEquals(ReviewType.FURTHER_REVIEW,reviewReport.getReviewType());
        applicant.setSATScore(1917);
        applicant.setACTScore(20);
        reviewReport = Classifier.getReviewReport(applicant);
        assertEquals(ReviewType.FURTHER_REVIEW,reviewReport.getReviewType());
        applicant.setSATScore(1927);
        applicant.setACTScore(30);
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