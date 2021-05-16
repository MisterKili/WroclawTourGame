package com.example.wroclawtourgame.model;

import java.io.Serializable;

public class TourPoint implements Serializable {

    private String name;
    private String description;
    private Cords cords;
    private String question;
    private String answer;
    private boolean answered;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Cords getCords() {
        return cords;
    }

    public void setCords(String cords) {
        this.cords = new Cords(cords);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(String sAnswered) {
        this.answered = sAnswered.equals("true");
    }

    public void markAsAnswered() {
        answered = true;
    }
}
