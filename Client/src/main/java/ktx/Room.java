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

@Entity
@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE,force=true)
@RequiredArgsConstructor
public class Room {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull(message = "So phong phai duoc nhap")
	private final String soPhong;
	
	@NotNull(message="Loai phong phai duoc nhap")
	private final String loaiPhong;
	
	@NotNull(message="Don gia phai duoc nhap")
	private final Float donGia;
	
	@NotNull(message="So nguoi toi da phai duoc nhap")
	private final Integer soNguoiToiDa;
}
