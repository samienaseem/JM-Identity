package projectjmi_id.samar.com.jm_id;
public class student {
    public String first_Name;
    public String last_Name;
    public String course;
    public String faculty;
    public String issueDate;
    public String expiryDate;
    public String pic;
    public String iid;
    public String number;
    public String email;
   // public String Active;
    public student() {

    }

    public student(String id,String fname, String lname, String ccourse, String cfaculty, String issueD, String expiryD, String email, String phone,String pic) {
        this.first_Name = fname;
        this.last_Name = lname;
        this.course = ccourse;
        this.faculty = cfaculty;
        this.issueDate = issueD;
        this.expiryDate = expiryD;
        this.iid=id;
        this.number = phone;
        this.email = email;
        this.pic=pic;
        //this.Active=Active;
    }
    public student(String pic){
        this.pic=pic;
    }

    /*public student(String first_Name, String last_Name, String course, String faculty, String issueDate, String expiryDate, String pic, String iid, String emai, String email) {
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.course = course;
        this.faculty = faculty;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.pic = pic;
        this.iid = iid;


    }*/

   /* public student(String fname, String lname) {
        this.first_name = fname;
        this.last_name = lname;
    }
*/
    /*public String getFirst_name() {
        return first_name;
    }
*/

}
