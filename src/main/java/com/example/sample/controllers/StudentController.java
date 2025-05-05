package com.example.sample.controllers;

import com.example.sample.dto.StudentDTO;
import com.example.sample.response.ResponseDTO;
import com.example.sample.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping("/get-all-students")
    public ResponseEntity <ResponseDTO> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());

    }
    @PostMapping("/add-student")
    public ResponseEntity<ResponseDTO> addStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.addStudent(studentDTO));
    }

}
