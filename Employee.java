//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package hardwarestore;

public class Employee extends User {
    private int socialSecurityNumber;
    private float monthlySalary;

    public Employee(int id, String firstName, String lastName, int socialSecurityNumber, float monthlySalary) {
        super(id, firstName, lastName, true);
        this.socialSecurityNumber = socialSecurityNumber;
        this.monthlySalary = monthlySalary;
    }

    public int getSocialSecurityNumber() {
        return this.socialSecurityNumber;
    }

    public void setSocialSecurityNumber(int socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public float getMonthlySalary() {
        return this.monthlySalary;
    }

    public void setMonthlySalary(float monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public String getFormattedText() {
        return String.format("| %-10s| %-9s| %-12s| %-12s| SSN: %12d, Salary: %10s        |%n", "Employee", this.id, this.firstName, this.lastName, this.socialSecurityNumber, this.monthlySalary);
    }
}

