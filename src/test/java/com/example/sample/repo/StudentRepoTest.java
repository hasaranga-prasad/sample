package com.example.sample.repo;

import com.example.sample.entity.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJdbcTest
public class StudentRepoTest {

    @Autowired
    private StudentRepo studentRepo;
    Student student;

    @BeforeEach
    void setUp() {
      student = new Student(1, "hasa", 19, "Ababalangoda", "hasa.pras@gmail.com", "0701809041");
    studentRepo.save(student);
    }

    @AfterEach
    void tearDown() {
        student= null;
        studentRepo.deleteAll();

    }
    // Test case SUCCESS
    @Test
    void testGetStudentById() {
        Optional<Student> students = studentRepo.findById(1);
        assertThat(students.isPresent()).isTrue();
        assertThat(students.get().getId()).isEqualTo(student.getId());
        assertThat(students.get().getName()).isEqualTo(student.getName());
    }

    // Test case FAILURE
    @Test
    void testGetStudentById_NotFound() {
        Optional<Student> students = studentRepo.findById(2);
        assertThat(students.isPresent()).isTrue();
        assertThat(students.get().getId()).isEqualTo(student.getId());
        assertThat(students.get().getName()).isEqualTo(student.getName());
    }

}
