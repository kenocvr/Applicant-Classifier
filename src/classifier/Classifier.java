package classifier;

import java.util.ArrayList;
import java.util.Date;


public class Classifier {

    private Classifier()
    {
    }

    public static ReviewReport getReviewReport(Applicant applicant)
    {
        ReviewReport reviewReport=new ReviewReport();
        ReviewTest reviewTest = instantRejectCheck(applicant);
        if(reviewTest.getPassed())
        {
            reviewReport.setReviewType(ReviewType.INSTANT_REJECT);
            reviewReport.setDescription(reviewTest.description);
            return reviewReport;
        }
        reviewTest = instantAcceptCheck(applicant);
        if (reviewTest.getPassed()) {
            reviewReport.setReviewType(ReviewType.INSTANT_ACCEPT);
            reviewReport.setDescription(reviewTest.description);
            return reviewReport;
        }

        reviewReport.setReviewType(ReviewType.FURTHER_REVIEW);
        reviewReport.setDescription("");
        return reviewReport;
    }

    private static boolean nameTest(String firstName, String lastName)
    {
        if(!firstName.equals(firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase())){
            return false;
        }
        return lastName.equals(lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase());
    }


    private static ReviewTest instantAcceptCheck(Applicant applicant) {
        ReviewTest reviewTest = new ReviewTest(true);
        if(instantRejectCheck(applicant).getPassed()){
            reviewTest.setPassed(false);
            return reviewTest;
        }

        if (applicant.getState().toLowerCase().trim().equals("california")) {
            if (applicant.getAge() <= 17){
                reviewTest.setPassed(false);
                return reviewTest;
            }
            if (applicant.getAge() > 26 && applicant.getAge() < 80){
                reviewTest.setPassed(false);
                return reviewTest;
            }

        } else if (applicant.getAge() < 80) {

            reviewTest.setPassed(false);
            return reviewTest;
        }

        if (applicant.getGPAPercentage() < 0.9) {
            reviewTest.setPassed(false);
            return reviewTest;
        }
        if (applicant.getSatScore() !=null && applicant.getSatScore() >= 1920)  {
                reviewTest.setPassed(true);
                return reviewTest;
        }

        if (applicant.getActScore() != null && applicant.getActScore() >= 27) {
                reviewTest.setPassed(true);
                return reviewTest;
        }

        return reviewTest;
    }

    private static ArrayList<Felony> getValidFelony(ArrayList<Felony> felonies)
    {
        ArrayList<Felony> result = new ArrayList<>();
        Date currDate = new Date();
        for (Felony felony : felonies) {
            if (Utility.getDiffYears(currDate, felony.getDate()) <= 5)
                result.add(felony);
        }
        return result;
    }

    private static ReviewTest instantRejectCheck(Applicant applicant)
    {
        ReviewTest reviewTest = new ReviewTest(false);
        StringBuilder descriptionBuilder = new StringBuilder("");
        ArrayList<Felony> validFelony = getValidFelony((ArrayList<Felony>) applicant.getFelonies());
        if (!validFelony.isEmpty()) {
            reviewTest.setPassed(true);
            descriptionBuilder.append("The applicant has ").append(validFelony.size()).append(" felonies over the past 5 year\nFelonies are:\n");
            for (Felony felony : validFelony) {
                descriptionBuilder.append(felony.getDescription()).append(" -> ").append(felony.getDate().toString()).append("\n");
            }
        }
        if(applicant.getGPAPercentage() < 0.7)
        {
            reviewTest.setPassed(true);
            descriptionBuilder.append("GPA below 70%\n").append(" ")
                    .append(applicant.getGPAPercentage());
        }
        if(applicant.getAge() < 0) {
            reviewTest.setPassed(true);
            descriptionBuilder.append("Age has negative value\n").append(" ")
                    .append(applicant.getAge());
        }
        if (!nameTest(applicant.getFirstName(),applicant.getLastName())) {
            reviewTest.setPassed(true);
            descriptionBuilder.append("The name is not in the correct format\n").append(" ")
                    .append(applicant.getFirstName()).append(" ")
                    .append(applicant.getLastName());
        }

        reviewTest.setDescription(descriptionBuilder.toString());
        return reviewTest;
    }

    private static class ReviewTest
    {
         String description;
         boolean passed;

        public ReviewTest(boolean passed) {
            this.passed = passed;
            this.description = "";
        }


        public void setDescription(String description) {
            this.description = description;
        }

        public boolean getPassed() {
            return passed;
        }

        public void setPassed(boolean passed) {
            this.passed = passed;
        }
    }
}
