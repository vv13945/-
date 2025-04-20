package com.grademanagement.student;

/**
 * 学生实体类
 */
public class Student {
    private String studentId;
    private String name;
    private int age;
    private String gender;
    private String department;

    public Student(String studentId, String name, int age, String gender, String department) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.department = department;
    }

    // Getter和Setter方法
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}