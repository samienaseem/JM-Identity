package jm_id.com.samar.projectjmi_id.jm_id_gaurds;

public class student {

    public String first_Name;
    public String last_Name;
    public String course;
    public String faculty;
    public String issueDate;
    public String expiryDate;
    public  String pic;

    public student() {

    }

    public student(String fname, String lname, String ccourse, String cfaculty, String issueD, String expiryD) {
        this.first_Name = fname;
        this.last_Name = lname;
        this.course = ccourse;
        this.faculty = cfaculty;
        this.issueDate = issueD;
        this.expiryDate = expiryD;
    }

    public student(String fname, String lname) {
        this.first_Name = fname;
        this.last_Name = lname;
    }






}

