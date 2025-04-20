package com.grademanagement.grade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 成绩管理类
 * 负责学生成绩的录入、查询和修改
 */
public class GradeManager {
    private Map<String, Map<String, Double>> grades; // <studentId, <courseId, grade>>
    private static final double MIN_GRADE = 0;
    private static final double MAX_GRADE = 100;

    public GradeManager() {
        this.grades = new HashMap<>();
    }

    /**
     * 录入或更新成绩
     * @param studentId 学号
     * @param courseId 课程编号
     * @param grade 成绩
     * @return 操作是否成功
     */
    public boolean recordGrade(String studentId, String courseId, double grade) {
        if (grade < MIN_GRADE || grade > MAX_GRADE) {
            System.out.println("成绩必须在" + MIN_GRADE + "到" + MAX_GRADE + "之间！");
            return false;
        }

        if (!grades.containsKey(studentId)) {
            grades.put(studentId, new HashMap<>());
        }

        grades.get(studentId).put(courseId, grade);
        System.out.printf("成绩录入成功 - 学号: %s, 课程: %s, 成绩: %.1f\n",
                studentId, courseId, grade);
        return true;
    }

    /**
     * 删除成绩记录
     * @param studentId 学号
     * @param courseId 课程编号
     * @return 删除是否成功
     */
    public boolean removeGrade(String studentId, String courseId) {
        if (!grades.containsKey(studentId) ||
                !grades.get(studentId).containsKey(courseId)) {
            System.out.println("该成绩记录不存在！");
            return false;
        }

        double removedGrade = grades.get(studentId).remove(courseId);
        System.out.printf("成绩删除成功 - 学号: %s, 课程: %s, 原成绩: %.1f\n",
                studentId, courseId, removedGrade);

        // 如果该学生没有其他成绩记录，则移除学生条目
        if (grades.get(studentId).isEmpty()) {
            grades.remove(studentId);
        }

        return true;
    }

    /**
     * 查询学生某门课程成绩
     * @param studentId 学号
     * @param courseId 课程编号
     * @return 成绩，不存在返回-1
     */
    public double getGrade(String studentId, String courseId) {
        if (!grades.containsKey(studentId) ||
                !grades.get(studentId).containsKey(courseId)) {
            return -1;
        }
        return grades.get(studentId).get(courseId);
    }

    /**
     * 查询学生所有课程成绩
     * @param studentId 学号
     * @return 成绩映射表<课程编号, 成绩>，不存在返回空map
     */
    public Map<String, Double> getStudentGrades(String studentId) {
        return grades.getOrDefault(studentId, new HashMap<>());
    }

    /**
     * 查询某课程所有学生成绩
     * @param courseId 课程编号
     * @return 成绩列表<学号, 成绩>
     */
    public Map<String, Double> getCourseGrades(String courseId) {
        Map<String, Double> result = new HashMap<>();
        for (Map.Entry<String, Map<String, Double>> entry : grades.entrySet()) {
            String studentId = entry.getKey();
            if (entry.getValue().containsKey(courseId)) {
                result.put(studentId, entry.getValue().get(courseId));
            }
        }
        return result;
    }

    /**
     * 计算学生平均成绩
     * @param studentId 学号
     * @return 平均成绩，无成绩记录返回-1
     */
    public double calculateStudentAverage(String studentId) {
        if (!grades.containsKey(studentId) || grades.get(studentId).isEmpty()) {
            return -1;
        }

        double sum = 0;
        for (double grade : grades.get(studentId).values()) {
            sum += grade;
        }
        return sum / grades.get(studentId).size();
    }

    /**
     * 计算课程平均成绩
     * @param courseId 课程编号
     * @return 平均成绩，无成绩记录返回-1
     */
    public double calculateCourseAverage(String courseId) {
        Map<String, Double> courseGrades = getCourseGrades(courseId);
        if (courseGrades.isEmpty()) {
            return -1;
        }

        double sum = 0;
        for (double grade : courseGrades.values()) {
            sum += grade;
        }
        return sum / courseGrades.size();
    }

    /**
     * 获取成绩排名
     * @param courseId 课程编号
     * @return 按成绩降序排列的学号列表
     */
    public List<String> getGradeRanking(String courseId) {
        Map<String, Double> courseGrades = getCourseGrades(courseId);
        List<Map.Entry<String, Double>> entries = new ArrayList<>(courseGrades.entrySet());

        // 按成绩降序排序
        entries.sort((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()));

        List<String> ranking = new ArrayList<>();
        for (Map.Entry<String, Double> entry : entries) {
            ranking.add(entry.getKey());
        }
        return ranking;
    }

    /**
     * 成绩统计信息
     * @param courseId 课程编号
     */
    public void displayCourseStatistics(String courseId) {
        Map<String, Double> courseGrades = getCourseGrades(courseId);
        if (courseGrades.isEmpty()) {
            System.out.println("该课程暂无成绩记录！");
            return;
        }

        double sum = 0;
        double min = MAX_GRADE;
        double max = MIN_GRADE;
        int count = 0;

        for (double grade : courseGrades.values()) {
            sum += grade;
            min = Math.min(min, grade);
            max = Math.max(max, grade);
            count++;
        }

        double average = sum / count;

        System.out.println("===== 课程成绩统计 =====");
        System.out.println("课程编号: " + courseId);
        System.out.println("平均成绩: " + String.format("%.1f", average));
        System.out.println("最高成绩: " + String.format("%.1f", max));
        System.out.println("最低成绩: " + String.format("%.1f", min));
        System.out.println("成绩分布:");

        // 成绩分段统计
        int[] ranges = new int[10]; // 0-9, 10-19, ..., 90-100
        for (double grade : courseGrades.values()) {
            int index = (int)(grade / 10);
            if (index >= 10) index = 9; // 100分放在90-100区间
            ranges[index]++;
        }

        for (int i = 0; i < ranges.length; i++) {
            int start = i * 10;
            int end = (i == 9) ? 100 : start + 9;
            System.out.printf("%d-%d分: %d人 (%.1f%%)\n",
                    start, end, ranges[i],
                    (double)ranges[i] / count * 100);
        }
    }
}