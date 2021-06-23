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

	@NotNull(message = "So phong is required")
	private final String soPhong;
	
	@NotNull(message = "Loai phong is required")
	private final String loaiPhong;
	
	@NotNull(message = "Don gia is required")
	private final Float donGia;
	
	@NotNull(message ="So nguoi toi da is required")
	private final Integer soNguoiToiDa;

}
