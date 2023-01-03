package com.mrdelicious.minitankswot;

public class CustomSpinner {
    String spinnerText;
    int spinnerImage;

    public CustomSpinner(String spinnerText, int spinnerImage) {
        this.spinnerText = spinnerText;
        this.spinnerImage = spinnerImage;
    }

    public String getSpinnerText() {return spinnerText;}
    public int getSpinnerImage() {return spinnerImage;}
}
