package com.example.sample.controllers;

import com.example.sample.dto.StudentDTO;
import com.example.sample.response.ResponseDTO;
import com.example.sample.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/get-all-students")
    public ResponseEntity<ResponseDTO> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());

    }

    @PostMapping("/add-student")
    public ResponseEntity<ResponseDTO> addStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.addStudent(studentDTO));
    }

    @GetMapping("/get-student-by-id/{id}")
    public ResponseEntity<ResponseDTO> getStudentById(@PathVariable @Positive(message = "ID must be a positive number")  int id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }
    @PutMapping("/update-student/{id}")
    public ResponseEntity<ResponseDTO> updateStudent(@PathVariable @Positive(message = "ID must be a positive number") int id, @Valid @RequestBody StudentDTO studentDTO) {
        return studentService.updateStudent(id, studentDTO);
    }



    @DeleteMapping("/delete-student/{id}")
    public ResponseEntity<ResponseDTO> deleteStudent(@PathVariable @Positive(message = "ID must be a positive number")  int id) {
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }
}
