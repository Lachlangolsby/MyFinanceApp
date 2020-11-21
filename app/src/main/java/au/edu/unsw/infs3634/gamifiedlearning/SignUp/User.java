package au.edu.unsw.infs3634.gamifiedlearning.SignUp;

public class User {
    // decalring user variables
    public String Name, Email, PhoneNumber;
    public String SIScore;
    public String FGSScore;

    // creating empty constructor
    public User() {

    }

    // creating constructor
    public User(String fullName, String Email, String PhoneNumber, String SIScore, String FGSScore) {
        this.Name = fullName;
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
        this.SIScore = SIScore;
        this.FGSScore = FGSScore;
    }
}
