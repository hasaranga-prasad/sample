package com.example.sample.service;

import com.example.sample.response.ResponseDTO;

public interface StudentService {
    public ResponseDTO getAllStudents();
    public ResponseDTO getStudentById(int id);
    public ResponseDTO addStudent(String name, int age);
    public ResponseDTO updateStudent(int id, String name, int age);
    public ResponseDTO deleteStudent(int id);


}
