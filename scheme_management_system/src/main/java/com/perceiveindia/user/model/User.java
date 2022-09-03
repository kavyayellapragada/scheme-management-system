package com.perceiveindia.user.model;

public class User {
    private String role;
    private String gender;
    private String age;
    private String marital_status;
    private String category;
    private String education;
    private String employement;
    private String annual_income;
    private String scheme;

    public User() {
    }

    public User(String role, String gender, String age, String marital_status, String category,
                    String education, String employement, String annual_income, String scheme) {
        this.role = role;
        this.gender = gender;
        this.age = age;
        this.marital_status = marital_status;
        this.category = category;
        this.education = education;
        this.employement = employement;
        this.annual_income = annual_income;
        this.scheme = scheme;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) { this.role = role; }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMaritalStatus() {
        return marital_status;
    }

    public void setMaritalStatus(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEmployement() {
        return employement;
    }

    public void setEmployement(String employement) {
        this.employement = employement;
    }

    public String getAnnualIncome() {
        return annual_income;
    }

    public void setAnnualIncome(String annual_income) {
        this.annual_income = annual_income;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    @Override
    public String toString() {
        return "User{" +
                "role=" + role +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", marital_status='" + marital_status + '\'' +
                ", category='" + category + '\'' +
                ", education='" + education + '\'' +
                ", employement'=" + employement + '\'' +
                ", annual_income'=" + annual_income + '\'' +
                ", scheme'=" + scheme + '\'' +
                '}';
    }
}

