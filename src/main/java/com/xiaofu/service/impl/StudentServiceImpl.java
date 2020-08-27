package com.xiaofu.service.impl;

import com.xiaofu.dao.StudentDao;
import com.xiaofu.domain.dto.Student;
import com.xiaofu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author jinchengll
 * @Date 2020/8/27 3:19 下午
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao studentDao;

    @Override
    public int deleteStudent(Integer id) {
        return studentDao.deleteByPrimaryKey(id);
    }

    @Override
    public int addStudent(Student student) {
        return studentDao.insert(student);
    }

    @Override
    public int addStudentSelective(Student student) {
        return studentDao.insertSelective(student);
    }

    @Override
    public Student getStudent(Integer id) {
        return studentDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateStudentSelective(Student student) {
        return studentDao.updateByPrimaryKeySelective(student);
    }

    @Override
    public int updateStudent(Student student) {
        return studentDao.updateByPrimaryKey(student);
    }
}
