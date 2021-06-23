package ktx.web.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ktx.Student;
import ktx.data.StudentRepository;

@RestController
@RequestMapping(path = "/ktx", produces="application/json")
@CrossOrigin(origins="*")
public class StudentController {

	private StudentRepository studentRepo;
	
	@Autowired
	EntityLinks entityLinks;
	public StudentController(StudentRepository studentRepo) {
		this.studentRepo = studentRepo;
	}
	
	@PostMapping(consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Student postStudent(@RequestBody Student student) {
		return studentRepo.save(student);
	}
	
	@GetMapping("/students")
	public Iterable<Student> getAllStudents(){
		return studentRepo.findAll();
	}
	
	@GetMapping("/student/search/{id}")
	public Student studentById(@PathVariable("id") Long id) {
		Optional<Student> optStudent = studentRepo.findById(id);
		if(optStudent.isPresent()) {
			return optStudent.get();
		}
		return null;
	}
	
	@DeleteMapping("/student/delete/{id}")
	public void deleteStudent(@PathVariable("id") Long id) {
	try {
		studentRepo.deleteById(id);
	} catch (EmptyResultDataAccessException e) {}
	}
	
	@PutMapping("/student/{msv}")
	public Student putStudent(@RequestBody Student student) {
		return studentRepo.save(student);
	}
	
	@GetMapping("/student/searchByMsv/{MSV}")
	public Iterable<Student> searchByMSV(@PathVariable("MSV") String MSV){
		return studentRepo.findByMSV(MSV);
	}
	
	@GetMapping("/student/getFreeStudent")
	public Iterable<Student> getFreeStudent(){
		return studentRepo.findFreeStudent();
	}
	
}
