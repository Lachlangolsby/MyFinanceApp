package au.edu.unsw.infs3634.gamifiedlearning;

public class User {
    public String Name, Email, PhoneNumber;

    public User()

    {
    }


    public User(String fullName, String Email, String PhoneNumber) {
        this.Name = fullName;
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
    }
}
