package com.unibuc.cartservice.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.unibuc.cartservice.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	@Query("SELECT c FROM Cart c WHERE c.id =?1")
	Optional<Cart> findById(Long cartId);

	@Query("SELECT c FROM Cart c WHERE c.sessionId =?1")
	Optional<Cart> findBySessionId(String sessionId);

	@Query("SELECT c FROM Cart c WHERE c.customerId=?1 and c.")
	List<Cart> findByCustomerIdAndDate(Long customerId, Date date);
}
