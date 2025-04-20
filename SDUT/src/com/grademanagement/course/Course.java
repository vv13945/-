package com.grademanagement.course;

/**
 * 课程实体类
 */
public class Course {
    private String courseId;
    private String courseName;
    private double credit;
    private String teacher;
    private String schedule;

    public Course(String courseId, String courseName, double credit, String teacher, String schedule) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credit = credit;
        this.teacher = teacher;
        this.schedule = schedule;
    }

    // Getter和Setter方法
    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credit=" + credit +
                ", teacher='" + teacher + '\'' +
                ", schedule='" + schedule + '\'' +
                '}';
    }
}