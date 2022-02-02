package uz.pdp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDto {

	private Integer number;
	private Integer floor;
	private Float size;
	private Integer hotelId;

}
