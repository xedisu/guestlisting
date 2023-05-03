import java.util.Objects;

class Guest {

    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;

    public Guest(String lastName, String firstName, String email, String phoneNumber) {
        for (char c : lastName.toCharArray()) {
            if (Character.isDigit(c)) {
                System.out.println("wrong input for last name");
                return;
            }
        }
        for (char c : firstName.toCharArray()) {
            if (Character.isDigit(c)) {
                System.out.println("wrong input for first name");
                return;
            }
        }
        for (char c : phoneNumber.toCharArray()) {
            if (!Character.isDigit(c)) {
                System.out.println("wrong input for phone number");
                return;
            }
        }

        if (!email.contains("@")) {
            System.out.println("wrong input for email");
            return;
        }

        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Guest guest = (Guest) o;
        return Objects.equals(lastName, guest.lastName) && Objects.equals(firstName, guest.firstName)
                && Objects.equals(email, guest.email) && Objects.equals(phoneNumber, guest.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, email, phoneNumber);
    }

    @Override
    public String toString() {
        return "LastName: " + lastName + ", FirstName:" + firstName + ", Email: " + email + ", PhoneNumber: " + phoneNumber;
    }

    public String fullName() {
        return lastName + " " + firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}