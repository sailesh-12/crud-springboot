package com.crudapp.learn.service;

import com.crudapp.learn.entity.Student;
import com.crudapp.learn.repo.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepo studentRepo;
    public  StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }
    public void createStudent(Student student){
        //business logic
        student.setIsDeleted(false);
        studentRepo.save(student);
    }

    public List<Student> getStudents() {
        return studentRepo.findByIsDeletedFalse();
    }
    public Student getStudentByrollNo(int rollNo){
        boolean isExists=studentRepo.existsByRollNoAndIsDeletedFalse(rollNo);
        if(isExists){
            return studentRepo.getReferenceById(rollNo);
        }
        return null;
    }

    public Student updateStudentDetails(Integer rollNo, Student student) {
        Student existing=studentRepo.findByRollNoAndIsDeletedFalse(rollNo);
        if(existing!=null){
            existing.setName(student.getName());
            existing.setAge(student.getAge());
            existing.setAddress(student.getAddress());
            existing.setEmail(student.getEmail());
            existing.setSubject(student.getSubject());
            existing.setIsDeleted(false);
            return  studentRepo.save(existing);
        }
        return null;
    }

    public Boolean deleteStudent(Integer rollNo) {
        Student student=studentRepo.findById(rollNo).orElse(null);
        if(student!=null){
            studentRepo.delete(student);
            return true;
        }
        return false;
    }

    public Boolean softDeleteStudent(Integer rollNo) {
        Student existing=studentRepo.findByRollNoAndIsDeletedFalse(rollNo);
        if(existing!=null){
            existing.setIsDeleted(true);
            studentRepo.save(existing);
            return true;
        }
        return false;
    }
}
