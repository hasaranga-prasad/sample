package com.example.sample.service;
import com.example.sample.dto.StudentDTO;
import com.example.sample.entity.Student;
import com.example.sample.exception.EmailAlreadyExistsException;
import com.example.sample.exception.PhoneNumberAlreadyExistsException;
import com.example.sample.exception.StudentNotFoundException;
import com.example.sample.repo.StudentRepo;
import com.example.sample.response.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepository;

    @Override
    public ResponseDTO getAllStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            throw new StudentNotFoundException("No students found");
        }

        return ResponseDTO.builder()
                .status(200)
                .message("All students fetched successfully")
                .data(students)
                .build();
    }



    @Override
    public ResponseDTO getStudentById(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));

        return ResponseDTO.builder()
                .status(200)
                .message("Student fetched successfully")
                .data(student)
                .build();
    }


    @Override
    public ResponseDTO addStudent(StudentDTO studentDTO) {

        if (studentRepository.existsByPhone(studentDTO.getPhone())) {
            throw new PhoneNumberAlreadyExistsException("Phone number already exists");
        }


        if (studentRepository.existsByEmail(studentDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email address already exists");
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
    }

    @Override
    public ResponseEntity<ResponseDTO> updateStudent(int id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id).
                orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));

        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());
        student.setAddress(studentDTO.getAddress());
        student.setEmail(studentDTO.getEmail());
        student.setPhone(studentDTO.getPhone());
        studentRepository.save(student);
        ResponseDTO response = ResponseDTO.builder()
                .status(200)
                .message("Student updated successfully")
                .data(student)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @Override
    public ResponseDTO deleteStudent(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
        studentRepository.delete(student);
        return ResponseDTO.builder()
                .status(200)
                .message("Student deleted successfully")
                .data(null)
                .build();
    }

}
