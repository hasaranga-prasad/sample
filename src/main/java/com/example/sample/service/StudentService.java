package com.example.sample.service;

import com.example.sample.dto.StudentDTO;
import com.example.sample.entity.Student;
import com.example.sample.response.ResponseDTO;

public interface StudentService {
    public ResponseDTO getAllStudents();
    public ResponseDTO getStudentById(int id);
    public ResponseDTO addStudent(StudentDTO studentDTO);
    public ResponseDTO updateStudent(int id, String name, int age);
    public ResponseDTO deleteStudent(int id);


}
