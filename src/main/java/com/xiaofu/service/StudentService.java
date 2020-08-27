package com.xiaofu.service;

import com.xiaofu.domain.dto.Student;

/**
 * @Author jinchengll
 * @Date 2020/8/27 3:16 下午
 */
public interface StudentService {
    int deleteStudent(Integer id);

    int addStudent(Student record);

    int addStudentSelective(Student record);

    Student getStudent(Integer id);

    int updateStudentSelective(Student record);

    int updateStudent(Student record);
}
