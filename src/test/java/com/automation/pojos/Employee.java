package com.automation.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class Employee {

    @SerializedName("employee_id")
    private int employeeId;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    private String email;
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("hire_data")
    private String hireData;
    @SerializedName("job_id")
    private String jobId;
    @SerializedName("comission_pct")
    private double comissionPct;
    @SerializedName("manager_id")
    private int managerId;
    @SerializedName("department_id")
    private int departmentId;
    private List<Link> links;

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", hireData='" + hireData + '\'' +
                ", jobId='" + jobId + '\'' +
                ", comissionPct=" + comissionPct +
                ", managerId=" + managerId +
                ", departmentId=" + departmentId +
                ", links=" + links +
                '}';
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHireData() {
        return hireData;
    }

    public void setHireData(String hireData) {
        this.hireData = hireData;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public double getComissionPct() {
        return comissionPct;
    }

    public void setComissionPct(double comissionPct) {
        this.comissionPct = comissionPct;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}
