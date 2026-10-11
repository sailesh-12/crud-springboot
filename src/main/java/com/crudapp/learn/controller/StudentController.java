package com.crudapp.learn.controller;

import com.crudapp.learn.entity.Student;
import com.crudapp.learn.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private  final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/health")
    public Map<String,String> getHealth(){
        return Map.of("Message","Success server is healthy");
    }
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Student student){
       Student existing=studentService.getStudentByrollNo(student.getRollNo());
       if(existing!=null){
           return  ResponseEntity.badRequest().body("Student already exists");
       }
       studentService.createStudent(student);
       return  ResponseEntity.status(201).body("Student created successfully");
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students=studentService.getStudents();
        if(students.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }
    @GetMapping("/find")
    public ResponseEntity<Student> getStudentById(@RequestParam Integer rollNo){
        Student response=studentService.getStudentByrollNo(rollNo);
        if(response==null){
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(200).body(response);
    }
    @PutMapping("/update/{rollNo}")
    public ResponseEntity<String> update(@PathVariable Integer rollNo, @RequestBody Student student){
        Student response=studentService.updateStudentDetails(rollNo,student);
        if(response==null){
            return ResponseEntity.status(404).body("Student not found");
        }
        return ResponseEntity.status(200).body("Student updated successfully");
    }
    @DeleteMapping("/delete/{rollNo}")
    public ResponseEntity<String> delete(@PathVariable Integer rollNo){
        Boolean isDeleted=studentService.deleteStudent(rollNo);
        if(isDeleted){
            return ResponseEntity.status(202).body("Deleted student successfully");
        }
        return ResponseEntity.status(404).body("Deleted student not found");
    }
    @PatchMapping("/soft-delete/{rollNo}")
    public ResponseEntity<String> softDelete(@PathVariable Integer rollNo){
        Boolean isDeleted=studentService.softDeleteStudent(rollNo);
        if(isDeleted){
            return ResponseEntity.status(202).body("Deleted student successfully");
        }
        return ResponseEntity.status(404).body("Deleted student not found");
    }

}
