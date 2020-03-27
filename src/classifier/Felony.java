package classifier;

import java.util.Date;

public class Felony {
    private Date date;
    private String description;

    public Felony(Date date, String description) {
        this.date = date;
        this.description = description;
    }

    public Date getDate() {
        return date;
    }


    public String getDescription() {
        return description;
    }

}

