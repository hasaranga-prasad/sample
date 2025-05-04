package com.example.sample.service;

import com.example.sample.entity.Student;
import com.example.sample.repo.StudentRepo;
import com.example.sample.response.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepository;

    @Override
    public ResponseDTO getAllStudents() {
        try {

            List<Student> students = studentRepository.findAll();
            if (students.isEmpty()) {
                return ResponseDTO.builder()
                        .status("error")
                        .message("No students found")
                        .data(null)
                        .build();
            }
            return ResponseDTO.builder()
                    .status("success")
                    .message("All students fetched successfully")
                    .data(students)
                    .build();
        } catch (Exception e) {
            return ResponseDTO.builder()
                    .status("error")
                    .message("An error occurred while fetching students: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }


    @Override
    public ResponseDTO getStudentById(int id) {

        return null;
    }

    @Override
    public ResponseDTO addStudent(String name, int age) {

        return null;
    }

    @Override
    public ResponseDTO updateStudent(int id, String name, int age) {

        return null;
    }

    @Override
    public ResponseDTO deleteStudent(int id) {

        return null;
    }
}
