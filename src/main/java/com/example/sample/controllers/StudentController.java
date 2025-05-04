package com.example.sample.controllers;

import com.example.sample.response.ResponseDTO;
import com.example.sample.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping("/get-all-students")
    public ResponseEntity <ResponseDTO> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());

    }

}
