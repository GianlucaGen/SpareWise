package com.example.sparewise;

public class Part {
    private String partName;
    private String partDescription;
    private String carMake;
    private String year;
    private String price;
    private String mobileNumber;

    // Default constructor (required for Firestore)
    public Part() {
    }

    public Part(String partName, String partDescription, String carMake, String year, String price, String mobileNumber) {
        this.partName = partName;
        this.partDescription = partDescription;
        this.carMake = carMake;
        this.year = year;
        this.price = price;
        this.mobileNumber = mobileNumber;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartDescription() {
        return "Description - "+ partDescription;
    }

    public void setDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    public String getCarMake() {
        return "Make - "+ carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getYear() {
        return "Year - " + year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPrice() {
        return "Price - €" + price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMobileNumber() {
        return "Call on - "+ mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
