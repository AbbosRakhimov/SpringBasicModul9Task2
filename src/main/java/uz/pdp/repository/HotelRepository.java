package uz.pdp.repository;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uz.pdp.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

	boolean existsByName(String name);
}
