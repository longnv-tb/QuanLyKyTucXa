package ktx.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ktx.Room;

public interface RoomRepository extends CrudRepository<Room, Long>{
	@Query(value="select * from room r where r.so_phong like %?1%", nativeQuery = true)
	public Iterable<Room> findBySoPhong(String soPhong);
	
//	@Query(value="select * from room where room.id in(select room.id )")
//	public Iterable<Room> findFreeRoom(String sophong);
}
