package uz.pdp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(uniqueConstraints =@UniqueConstraint(columnNames = {"number", "hotel_id"}))
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private Integer number;
	
	@Column(nullable = false)
	private Integer floor;
	
	@Column(nullable = false)
	private Float size;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
//	@JoinColumn(name = "hotelid")
	private Hotel hotel;

	public Room(Integer number, Integer floor, Float size, Hotel hotel) {
		super();
		this.number = number;
		this.floor = floor;
		this.size = size;
		this.hotel = hotel;
	}
	
	
}


