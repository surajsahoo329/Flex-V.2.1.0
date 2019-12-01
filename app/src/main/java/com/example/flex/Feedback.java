package com.example.flex;

public class Feedback {

    private String userId;
    private String userMail;
    private String feedback;
    private String rating;
    private String date;

    Feedback(String userId, String userMail, String rating, String feedback, String date)
    {
        this.userId = userId;
        this.userMail = userMail;
        this.rating = rating;
        this.feedback = feedback;
        this.date=date;
    }

    public String getUserId() { return userId; }

    public String getUserMail()
    {
        return userMail;
    }

    public String getFeedback()
    {
        return feedback;
    }

    public String getRating()
    {
        return rating;
    }

    public String getDate() {
        return date;
    }
}