package amusemeu.companyReportingSystem.web.dto;

public class UserRegistrationDto {
    private String userName;
    private String lastName;
    private String email;
    private String department;
    private String password;


    public UserRegistrationDto(String userName, String lastName, String email, String department, String password) {
        this.userName = userName;
        this.lastName = lastName;
        this.department = department;
        this.password = password;
    }

    public UserRegistrationDto() {
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
