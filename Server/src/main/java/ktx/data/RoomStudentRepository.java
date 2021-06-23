package ktx.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ktx.RoomStudent;

public interface RoomStudentRepository extends CrudRepository<RoomStudent, Long>{
	@Query(value="select rs.* from room_student rs, student s where rs.student_id = s.id and rs.room_id=?1", nativeQuery = true)
	public Iterable<RoomStudent> getStudentRoom(Long id);
}
