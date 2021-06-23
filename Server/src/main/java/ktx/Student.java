package ktx;
import javax.validation.constraints.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE,force=true)
@RequiredArgsConstructor
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@NotNull(message = "Student code is required")
	private final String msv;
	
	@NotNull(message = "Ten is required")
	private final String ten;
	
	@NotNull(message = "Identity Card is required")
	private final String cmt;
	
	@NotNull(message ="Date of birth is required")
	private final String ngaySinh;
	
	@NotNull(message = "Class is required")
	private final String lop;
	
	private final String queQuan;
}
