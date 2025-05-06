package com.example.sample.service;

import com.example.sample.dto.StudentDTO;
import com.example.sample.entity.Student;
import com.example.sample.response.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    public ResponseDTO getAllStudents();
    public ResponseDTO getStudentById(int id);
    public ResponseDTO addStudent(StudentDTO studentDTO);
    public ResponseEntity<ResponseDTO> updateStudent(int id, StudentDTO studentDTO);

    public ResponseDTO deleteStudent(int id);


}
