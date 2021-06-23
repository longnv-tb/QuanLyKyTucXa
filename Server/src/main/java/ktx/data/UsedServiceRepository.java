package ktx.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ktx.UsedService;

public interface UsedServiceRepository extends CrudRepository<UsedService, Long>{

	
	@Query(value="select us.* from used_service us, service s where student_id=?1 and us.service_ma_dich_vu = s.ma_dich_vu", nativeQuery=true)
	public Iterable<UsedService> getUsedServiceById(String ids);
}
