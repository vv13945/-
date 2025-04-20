package com.grademanagement.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学生信息管理类
 * 负责学生信息的增删改查操作
 */
public class StudentManager {
    private Map<String, Student> students;
    private static final int MAX_STUDENTS = 1000;

    public StudentManager() {
        this.students = new HashMap<>();
    }

    /**
     * 添加新学生
     * @param studentId 学号
     * @param name 姓名
     * @param age 年龄
     * @param gender 性别
     * @param department 院系
     * @return 添加是否成功
     */
    public boolean addStudent(String studentId, String name, int age,
                              String gender, String department) {
        if (students.size() >= MAX_STUDENTS) {
            System.out.println("已达到最大学生数量限制！");
            return false;
        }

        if (students.containsKey(studentId)) {
            System.out.println("该学号已存在！");
            return false;
        }

        Student newStudent = new Student(studentId, name, age, gender, department);
        students.put(studentId, newStudent);
        System.out.println("学生添加成功：" + newStudent);
        return true;
    }

    /**
     * 删除学生
     * @param studentId 学号
     * @return 删除是否成功
     */
    public boolean removeStudent(String studentId) {
        if (!students.containsKey(studentId)) {
            System.out.println("该学号不存在！");
            return false;
        }

        Student removed = students.remove(studentId);
        System.out.println("学生删除成功：" + removed);
        return true;
    }

    /**
     * 更新学生信息
     * @param studentId 学号
     * @param name 姓名
     * @param age 年龄
     * @param gender 性别
     * @param department 院系
     * @return 更新是否成功
     */
    public boolean updateStudent(String studentId, String name, int age,
                                 String gender, String department) {
        if (!students.containsKey(studentId)) {
            System.out.println("该学号不存在！");
            return false;
        }

        Student student = students.get(studentId);
        student.setName(name);
        student.setAge(age);
        student.setGender(gender);
        student.setDepartment(department);
        System.out.println("学生信息更新成功：" + student);
        return true;
    }

    /**
     * 查询学生信息
     * @param studentId 学号
     * @return 学生对象，不存在返回null
     */
    public Student getStudent(String studentId) {
        return students.get(studentId);
    }

    /**
     * 获取所有学生列表
     * @return 学生列表
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    /**
     * 按院系筛选学生
     * @param department 院系名称
     * @return 该院系学生列表
     */
    public List<Student> getStudentsByDepartment(String department) {
        List<Student> result = new ArrayList<>();
        for (Student student : students.values()) {
            if (student.getDepartment().equals(department)) {
                result.add(student);
            }
        }
        return result;
    }

    /**
     * 学生信息统计
     */
    public void displayStatistics() {
        System.out.println("===== 学生信息统计 =====");
        System.out.println("总学生数: " + students.size());

        Map<String, Integer> deptCount = new HashMap<>();
        Map<String, Integer> genderCount = new HashMap<>();

        for (Student student : students.values()) {
            deptCount.put(student.getDepartment(),
                    deptCount.getOrDefault(student.getDepartment(), 0) + 1);
            genderCount.put(student.getGender(),
                    genderCount.getOrDefault(student.getGender(), 0) + 1);
        }

        System.out.println("\n按院系分布:");
        deptCount.forEach((dept, count) ->
                System.out.println(dept + ": " + count + "人"));

        System.out.println("\n按性别分布:");
        genderCount.forEach((gender, count) ->
                System.out.println(gender + ": " + count + "人"));
    }

    /**
     * 学生类
     */
    public static class Student {
        private String studentId;
        private String name;
        private int age;
        private String gender;
        private String department;

        public Student(String studentId, String name, int age,
                       String gender, String department) {
            this.studentId = studentId;
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.department = department;
        }

        // Getter和Setter方法
        public String getStudentId() { return studentId; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
        public String getGender() { return gender; }
        public void setGender(String gender) { this.gender = gender; }
        public String getDepartment() { return department; }
        public void setDepartment(String department) { this.department = department; }

        @Override
        public String toString() {
            return String.format("学号: %s, 姓名: %s, 年龄: %d, 性别: %s, 院系: %s",
                    studentId, name, age, gender, department);
        }
    }
}