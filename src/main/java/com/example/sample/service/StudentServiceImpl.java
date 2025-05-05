package com.example.sample.service;

import com.example.sample.dto.StudentDTO;
import com.example.sample.entity.Student;
import com.example.sample.repo.StudentRepo;
import com.example.sample.response.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
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
                        .status(404)
                        .message("No students found")
                        .data(null)
                        .build();
            }
            return ResponseDTO.builder()
                    .status(200)
                    .message("All students fetched successfully")
                    .data(students)
                    .build();
        } catch (DataAccessException e) {
            return ResponseDTO.builder()
                    .status(500)
                    .message("Database error occurred while fetching students: " + e.getMessage())
                    .data(null)
                    .build();
        } catch (Exception e) {
            return ResponseDTO.builder()
                    .status(500)
                    .message("An unexpected error occurred: " + e.getMessage())
                    .data(null)
                    .build();
        }

    }


    @Override
    public ResponseDTO getStudentById(int id) {

        return null;
    }

    @Override
    public ResponseDTO addStudent(StudentDTO studentDTO) {
        try {
            if (studentRepository.existsByPhone(studentDTO.getPhone())) {
                return ResponseDTO.builder()
                        .status(400)
                        .message("Phone number already exists")
                        .data(null)
                        .build();
            }
            if (studentRepository.existsByEmail(studentDTO.getEmail())) {
                return ResponseDTO.builder()
                        .status(400)
                        .message("Email address already exists")
                        .data(null)
                        .build();
            }
            Student student = Student.builder()
                    .name(studentDTO.getName())
                    .age(studentDTO.getAge())
                    .address(studentDTO.getAddress())
                    .email(studentDTO.getEmail())
                    .phone(studentDTO.getPhone())
                    .build();

            studentRepository.save(student);
            return ResponseDTO.builder()
                    .status(201)
                    .message("Student added successfully")
                    .data(student)
                    .build();
        } catch (DataAccessException e) {
            return ResponseDTO.builder()
                    .status(500)
                    .message("Database error occurred while adding the student: " + e.getMessage())
                    .data(null)
                    .build();
        } catch (Exception e) {
            return ResponseDTO.builder()
                    .status(500)
                    .message("An unexpected error occurred: " + e.getMessage())
                    .data(null)
                    .build();
        }
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
