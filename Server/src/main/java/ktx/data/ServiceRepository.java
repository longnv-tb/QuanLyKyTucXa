package ktx.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ktx.Service;

public interface ServiceRepository extends CrudRepository<Service, Long>{
	
	@Query(value="SELECT * FROM service s WHERE ten_dich_vu LIKE CONCAT('%',:tenDichVu,'%')", nativeQuery = true)
	public Iterable<Service> findByTen(@Param("tenDichVu") String tenDichVu);
}
