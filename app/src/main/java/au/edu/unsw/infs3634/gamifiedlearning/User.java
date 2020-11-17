package au.edu.unsw.infs3634.gamifiedlearning;

public class User {
    public String Name, Email, PhoneNumber;
    public int SIScore, FGSScore;

    public User()

    {
    }


    public User(String fullName, String Email, String PhoneNumber, int  SIScore, int FGSScore) {
        this.Name = fullName;
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
        this.SIScore = SIScore;
        this.FGSScore = FGSScore;
    }
}
