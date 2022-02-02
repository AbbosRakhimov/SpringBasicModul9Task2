package uz.pdp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uz.pdp.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

	Page<Room> findAllByHotelId(Integer hoter_id, Pageable pageable);
	
	boolean existsByNumberAndHotelId(Integer number, Integer hotel_id );
}
