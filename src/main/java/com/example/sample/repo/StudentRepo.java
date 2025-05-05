package com.example.sample.repo;

import com.example.sample.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);

}
