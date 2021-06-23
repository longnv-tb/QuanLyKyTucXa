package ktx;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@RequiredArgsConstructor
public class RoomStudent {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Date ngayden;
	
	@ManyToOne(targetEntity=Student.class)
	private final Student student;
	
	@ManyToOne(targetEntity=Room.class)
	private final Room room;
	
	@PrePersist
	void ngaySuDung() {
		this.ngayden = new Date();
	}
	
}
