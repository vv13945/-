package com.grademanagement.course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程管理类
 * 负责课程的增删改查操作
 */
public class CourseManager {
    private Map<String, Course> courses;
    private static final int MAX_COURSES = 200;

    public CourseManager() {
        this.courses = new HashMap<>();
    }

    /**
     * 添加新课程
     * @param courseId 课程编号
     * @param courseName 课程名称
     * @param credit 学分
     * @param teacher 授课教师
     * @param schedule 上课时间
     * @return 添加是否成功
     */
    public boolean addCourse(String courseId, String courseName,
                             double credit, String teacher, String schedule) {
        if (courses.size() >= MAX_COURSES) {
            System.out.println("已达到最大课程数量限制！");
            return false;
        }

        if (courses.containsKey(courseId)) {
            System.out.println("该课程编号已存在！");
            return false;
        }

        Course newCourse = new Course(courseId, courseName, credit, teacher, schedule);
        courses.put(courseId, newCourse);
        System.out.println("课程添加成功：" + newCourse);
        return true;
    }

    /**
     * 删除课程
     * @param courseId 课程编号
     * @return 删除是否成功
     */
    public boolean removeCourse(String courseId) {
        if (!courses.containsKey(courseId)) {
            System.out.println("该课程编号不存在！");
            return false;
        }

        Course removed = courses.remove(courseId);
        System.out.println("课程删除成功：" + removed);
        return true;
    }

    /**
     * 更新课程信息
     * @param courseId 课程编号
     * @param courseName 课程名称
     * @param credit 学分
     * @param teacher 授课教师
     * @param schedule 上课时间
     * @return 更新是否成功
     */
    public boolean updateCourse(String courseId, String courseName,
                                double credit, String teacher, String schedule) {
        if (!courses.containsKey(courseId)) {
            System.out.println("该课程编号不存在！");
            return false;
        }

        Course course = courses.get(courseId);
        course.setCourseName(courseName);
        course.setCredit(credit);
        course.setTeacher(teacher);
        course.setSchedule(schedule);
        System.out.println("课程信息更新成功：" + course);
        return true;
    }

    /**
     * 查询课程信息
     * @param courseId 课程编号
     * @return 课程对象，不存在返回null
     */
    public Course getCourse(String courseId) {
        return courses.get(courseId);
    }

    /**
     * 获取所有课程列表
     * @return 课程列表
     */
    public List<Course> getAllCourses() {
        return new ArrayList<>(courses.values());
    }

    /**
     * 按教师筛选课程
     * @param teacher 教师姓名
     * @return 该教师教授的课程列表
     */
    public List<Course> getCoursesByTeacher(String teacher) {
        List<Course> result = new ArrayList<>();
        for (Course course : courses.values()) {
            if (course.getTeacher().equals(teacher)) {
                result.add(course);
            }
        }
        return result;
    }

    /**
     * 课程信息统计
     */
    public void displayStatistics() {
        System.out.println("===== 课程信息统计 =====");
        System.out.println("总课程数: " + courses.size());

        Map<String, Integer> teacherCourseCount = new HashMap<>();
        double totalCredits = 0;

        for (Course course : courses.values()) {
            teacherCourseCount.put(course.getTeacher(),
                    teacherCourseCount.getOrDefault(course.getTeacher(), 0) + 1);
            totalCredits += course.getCredit();
        }

        System.out.println("\n按教师分布:");
        teacherCourseCount.forEach((tchr, count) ->
                System.out.println(tchr + ": " + count + "门课程"));

        System.out.println("\n学分统计:");
        System.out.println("平均每门课程学分: " + (totalCredits / courses.size()));
    }

    /**
     * 课程类
     */
    public static class Course {
        private String courseId;
        private String courseName;
        private double credit;
        private String teacher;
        private String schedule;

        public Course(String courseId, String courseName, double credit,
                      String teacher, String schedule) {
            this.courseId = courseId;
            this.courseName = courseName;
            this.credit = credit;
            this.teacher = teacher;
            this.schedule = schedule;
        }

        // Getter和Setter方法
        public String getCourseId() { return courseId; }
        public String getCourseName() { return courseName; }
        public void setCourseName(String courseName) { this.courseName = courseName; }
        public double getCredit() { return credit; }
        public void setCredit(double credit) { this.credit = credit; }
        public String getTeacher() { return teacher; }
        public void setTeacher(String teacher) { this.teacher = teacher; }
        public String getSchedule() { return schedule; }
        public void setSchedule(String schedule) { this.schedule = schedule; }

        @Override
        public String toString() {
            return String.format("课程编号: %s, 名称: %s, 学分: %.1f, 教师: %s, 时间: %s",
                    courseId, courseName, credit, teacher, schedule);
        }
    }
}