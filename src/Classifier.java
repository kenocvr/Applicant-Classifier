public class Classifier {

    private Classifier()
    {
    }

    public static ReviewReport getReviewReport(Applicant applicant)
    {
        ReviewReport reviewReport=new ReviewReport();
        ReviewTest instantRejectCheck = instantRejectCheck(applicant);
        if(instantRejectCheck.getPassed())
        {
            reviewReport.setReviewType(ReviewType.INSTANT_REJECT);
            reviewReport.setDescription(instantRejectCheck.description);
            return reviewReport;
        }
        ReviewTest instantAcceptCheck = instantAcceptCheck(applicant);
        if (instantAcceptCheck.getPassed()) {
            reviewReport.setReviewType(ReviewType.INSTANT_ACCEPT);
            reviewReport.setDescription(instantRejectCheck.description);
            return reviewReport;
        }

        reviewReport.setReviewType(ReviewType.FURTHER_REVIEW);
        reviewReport.setDescription("");
        return reviewReport;
    }

    private static boolean nameTest(String firstName,String lastName)
    {
        if(firstName.length()==0||lastName.length()==0)
            return false;
        else if(Character.getType(firstName.charAt(0))!=Character.UPPERCASE_LETTER
                ||Character.getType(lastName.charAt(0))!=Character.UPPERCASE_LETTER)
        {
            return false;
        }
        else
        {
            for (int i = 1; i < firstName.length(); i++)
            {
                if(Character.getType(firstName.charAt(i))!=Character.LOWERCASE_LETTER)
                {
                    return false;
                }
            }
            for (int i = 1; i < lastName.length(); i++)
            {
                if(Character.getType(lastName.charAt(i))!=Character.LOWERCASE_LETTER)
                {
                    return false;
                }
            }
        }
        return true;
    }

    private static double getGPAPercentage(double GPA,double maxGPA) {
        return GPA/maxGPA;
    }

    private static ReviewTest instantAcceptCheck(Applicant applicant) {
        ReviewTest reviewTest = new ReviewTest(true);
        if(instantRejectCheck(applicant).getPassed())
        {
            reviewTest.setPassed(false);
            return reviewTest;
        }

        if (applicant.getState().toLowerCase().trim().equals("california")) {
            if (applicant.getAge() < 17 || applicant.getAge() >= 26) {
                reviewTest.setPassed(false);
                return reviewTest;
            }
        } else if (applicant.getAge() <= 80) {

            reviewTest.setPassed(false);
            return reviewTest;
        }
        if (applicant.getGPAPercentage() < 0.9) {
            reviewTest.setPassed(false);
            return reviewTest;
        }

        if (applicant.getSATScore() <= 1920 && applicant.getACTScore() <= 27) {
            reviewTest.setPassed(false);
            return reviewTest;
        }
        return reviewTest;
    }

    private static ReviewTest instantRejectCheck(Applicant applicant)
    {
        ReviewTest reviewTest =new ReviewTest(false);
        String description="";
        if (applicant.getFelonies()>0) {
            reviewTest.setPassed(true);
            description+="The applicant get "+applicant.getFelonies()+" over the past 5 year\n";
        }
        if(applicant.getGPAPercentage()<0.7)
        {
            reviewTest.setPassed(true);
            description+="GPA below 70%\n";
        }
        if(applicant.getAge()<0) {
            reviewTest.setPassed(true);
            description+="Age has negative value\n";
        }
        if (!nameTest(applicant.getFirstName(),applicant.getLastName())) {
            reviewTest.setPassed(true);
            description+="The name is not in the correct format\n";
        }

        reviewTest.setDescription(description);
        return reviewTest;
    }

    private static class ReviewTest
    {
        public String description;
        public boolean passed;

        public ReviewTest(boolean passed) {
            this.passed = passed;
            this.description="";
        }

        public String getDescription() {
            return description;
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
