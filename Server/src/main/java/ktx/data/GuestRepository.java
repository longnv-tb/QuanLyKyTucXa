package ktx.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ktx.Guest;

public interface GuestRepository extends CrudRepository<Guest, Long>{

	@Query(value="SELECT * FROM guest g WHERE ten LIKE CONCAT('%',:tenKhach,'%')", nativeQuery = true)
	public Iterable<Guest> findByTen(@Param("tenKhach") String tenKhach);

}
