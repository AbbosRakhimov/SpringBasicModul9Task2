package uz.pdp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uz.pdp.entity.Hotel;
import uz.pdp.entity.Room;
import uz.pdp.payload.RoomDto;
import uz.pdp.repository.HotelRepository;
import uz.pdp.repository.RoomRepository;

@RestController
@RequestMapping(value = "/room")
public class RoomController {

	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	RoomRepository roomRepository;
	
	@GetMapping
	public List<Room> getAllRoomList(){
//		Pageable pageable = PageRequest.of(page, 5);
//		Page<Room> roPage = roomRepository.findAll(pageable);
		return roomRepository.findAll();
	}
	@GetMapping("/{id}")
	public Room getRoom(@PathVariable Integer id) {
		Optional<Room> rOptional= roomRepository.findById(id);
		if(rOptional.isPresent()) {
			return rOptional.get();
		}
		return new Room();
	}
	@GetMapping("/byHotelId/{hotelId}")
	public Page<Room> getRommPage(@PathVariable Integer hotelId, @RequestParam int page){
		Pageable pageable = PageRequest.of(page, 5);
		
		Page<Room> page2= roomRepository.findAllByHotelId(hotelId, pageable);
		
		return page2;
	}
	@PostMapping
	public String saveRooom(@RequestBody RoomDto roomDto) {
		if(roomRepository.existsByNumberAndHotelId(roomDto.getNumber(), roomDto.getHotelId()))
			return "room in this hotel already exists";
		Optional<Hotel> hOptional = hotelRepository.findById(roomDto.getHotelId());
		if(!hOptional.isPresent())
			return "Hotel not found";
		Room room = new Room(roomDto.getNumber(),roomDto.getFloor(), roomDto.getSize(), hOptional.get());
		roomRepository.save(room);
		
		return " Successfuly saved";
	}
	
	@PutMapping("/{id}")
	public String editedRoom(@PathVariable Integer id, @RequestBody RoomDto roomDto) {
//		if(roomRepository.existsByNumberAndHotel(roomDto.getNumber(), roomDto.getHotelId()))
//			return "room in this hotel already exists";
		Optional<Room> rOptional = roomRepository.findById(id);
		Optional<Hotel> hOptional = hotelRepository.findById(roomDto.getHotelId());
		
		if(!rOptional.isPresent() || !hOptional.isPresent())
			return " Room or Hotel not found";
		Room room = rOptional.get();
		room.setFloor(roomDto.getFloor());
		room.setHotel(hOptional.get());
		room.setNumber(roomDto.getNumber());
		room.setSize(roomDto.getSize());
		roomRepository.save(room);
		
		return "Successfuly edited";
	}
	@DeleteMapping(value = "/{id}")
	public String deletRoom(@PathVariable Integer id) {
		Optional< Room> rOptional = roomRepository.findById(id);
		if(rOptional.isPresent()) {
			roomRepository.deleteById(id);
			return " deleted";
		}
		return "Room not found";
	}
}
