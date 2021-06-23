package ktx.data;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ktx.Student;
public interface StudentRepository extends CrudRepository<Student, Long>{

	@Query(value="select * from student s where s.msv=:MSV", nativeQuery = true)
	public Iterable<Student> findByMSV(@Param("MSV") String MSV);
	
	@Query(value="select * from student s where s.id not in (select s.id from student s, room_student rs where s.id = rs.student_id)", nativeQuery = true)
	public Iterable<Student> findFreeStudent();
}
