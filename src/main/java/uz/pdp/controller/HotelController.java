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
import uz.pdp.repository.HotelRepository;

@RestController
@RequestMapping(value = "/hotel")
public class HotelController {

	@Autowired
	HotelRepository hotelRepository;
	
	@GetMapping
	public List<Hotel> getHotelList(){
//		Pageable pageable = PageRequest.of(page, 5);
//		Page<Hotel>hotelPage= hotelRepository.findAll(pageable);
		
		return hotelRepository.findAll();
	}

	@GetMapping(value = "/{id}")
	public Hotel getHotel(@PathVariable Integer id) {
		Optional<Hotel> hOptional = hotelRepository.findById(id);
		if (hOptional.isPresent()) {
			return hOptional.get();
		}
		return new Hotel();
	}
	@PostMapping
	public String saveHotel(@RequestBody Hotel hotel) {
		if(hotelRepository.existsByName(hotel.getName()))
			return "Hotel already exist";
		Hotel hotel2 = new Hotel(hotel.getName());
		hotelRepository.save(hotel2);
		
		return "Hotel Successfuly saved";
	}
	@PutMapping(value = "/{id}")
	public String editHotel(@PathVariable Integer id, @RequestBody Hotel hotel) {
		if(hotelRepository.existsByName(hotel.getName()))
			return "Hotel already exist";
		Optional<Hotel> hOptional = hotelRepository.findById(id);
		if(hOptional.isPresent()) {
			Hotel hotel2 = hOptional.get();
			hotel2.setName(hotel.getName());
			hotelRepository.save(hotel2);
			
			return " Successfully edited";
		}
		return "Hotel not found";
	}
	@DeleteMapping(value = "/{id}")
	public String delteHotel(@PathVariable Integer id) {
		Optional<Hotel> hOptional = hotelRepository.findById(id);
		if(hOptional.isPresent()) {
			hotelRepository.deleteById(id);
			return "Successfully deleted";
		}
		return "Hotel not found";
	}
}
