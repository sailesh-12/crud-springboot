package com.crudapp.learn.repo;


import com.crudapp.learn.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer>{
    boolean existsByRollNoAndIsDeletedFalse(int rollNo);

    Student findByRollNoAndIsDeletedFalse(int rollNo);

    List<Student> findByIsDeletedFalse();
}
