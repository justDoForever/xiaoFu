package com.xiaofu.controller;

import com.xiaofu.domain.dto.Student;
import com.xiaofu.domain.enums.ResponseEnum;
import com.xiaofu.domain.response.ResultInfo;
import com.xiaofu.service.StudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Author jinchengll
 * @Date 2020/8/27 3:22 下午
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @ApiOperation(value = "查询学生信息", notes = "查询学生信息", produces = "application/json")
    @GetMapping("/getInfo")
    public ResultInfo<Student> getInfo(Integer id) {
        ResultInfo<Student> resultInfo = null;
        Student student = studentService.getStudent(id);
        if (student == null) {
            resultInfo = new ResultInfo<>(ResponseEnum.CUSTOM_ERROR);
        } else {
            resultInfo = new ResultInfo<>(ResponseEnum.SUCCESS, student);
        }
        return resultInfo;
    }

    @ApiOperation(value = "添加学生", notes = "添加学生", produces = "application/json")
    @PostMapping("/add")
    public ResultInfo<String> addStudent(@RequestBody Student student) {
        ResultInfo<String> resultInfo = null;
        if (student == null) {
            resultInfo = new ResultInfo<>(ResponseEnum.CUSTOM_ERROR, "参数student为null");
        } else {
            student.setCreateTime(new Date());
            student.setUpdateTime(new Date());
            int addResult = studentService.addStudent(student);
            if (addResult <= 0) {
                resultInfo = new ResultInfo<>(ResponseEnum.CUSTOM_ERROR, "增加学生信息失败");
            } else {
                resultInfo = new ResultInfo<>(ResponseEnum.SUCCESS, "增加学生信息成功");
            }
        }
        return resultInfo;
    }

    @ApiOperation(value = "删除学生", notes = "删除学生", produces = "application/json")
    @GetMapping("/delete")
    public ResultInfo<String> deleteStudent(Integer id) {
        ResultInfo<String> resultInfo = null;
        int deleteResult = studentService.deleteStudent(id);
        if (deleteResult <= 0) {
            resultInfo = new ResultInfo<>(ResponseEnum.CUSTOM_ERROR, "删除学生信息失败");
        } else {
            resultInfo = new ResultInfo<>(ResponseEnum.SUCCESS, "删除学生信息成功");
        }
        return resultInfo;
    }

    @ApiOperation(value = "更新学生信息", notes = "更新学生信息", produces = "application/json")
    @PostMapping("/update")
    public ResultInfo<String> updateStudent(@RequestBody Student student) {
        ResultInfo<String> resultInfo = null;
        if (student == null) {
            resultInfo = new ResultInfo<>(ResponseEnum.CUSTOM_ERROR, "参数student为null");
        } else {
            student.setUpdateTime(new Date());
            int addResult = studentService.updateStudentSelective(student);
            if (addResult <= 0) {
                resultInfo = new ResultInfo<>(ResponseEnum.CUSTOM_ERROR, "更新学生信息失败");
            } else {
                resultInfo = new ResultInfo<>(ResponseEnum.SUCCESS, "更新学生信息成功");
            }
        }
        return resultInfo;
    }

}
