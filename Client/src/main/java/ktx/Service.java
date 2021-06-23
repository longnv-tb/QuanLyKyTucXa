package ktx;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@RequiredArgsConstructor
public class Service {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long maDichVu;
	
	@NotNull(message="Tên dịch vụ không được để trống")
	private final String tenDichVu;
	
	@NotNull(message="Đơn giá không được bỏ trống")
	private final Float donGia;
}
