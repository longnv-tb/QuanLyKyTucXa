package ktx;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@RequiredArgsConstructor
public class Guest {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull(message="Chứng minh thư không được bỏ trống")
	private final String cmt;
	
	@NotNull(message="Tên không được để trống")
	private final String ten;
	
	@NotNull(message="Ngày sinh không được để trống")
	private final String ngaySinh;
	
	@NotNull(message="Ngày đến chơi không được để trống")
	private final String ngayDenChoi;
	
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;
	
}
