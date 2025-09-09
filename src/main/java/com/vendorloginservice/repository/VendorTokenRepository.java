package com.vendorloginservice.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.vendorloginservice.entity.VendorTokens;
import jakarta.transaction.Transactional;

@Repository
public interface VendorTokenRepository extends JpaRepository<VendorTokens, String> {

	Optional<VendorTokens> findByVendorId(long vendorId);
	@Modifying
	@Transactional
	@Query("UPDATE VendorTokens t " +
	       "SET t.isActive = 'N' " +
	       "WHERE t.vendorId = :vendorId " +
	       "AND t.deviceName = :deviceName " +
	       "AND t.isActive = 'Y'")
	void deactivateTokensForDevice(@Param("vendorId") Long custId,
	                               @Param("deviceName") String deviceName);
	
	// if you only want the latest active token
	VendorTokens findTopByVendorIdAndIsActiveOrderByIssuedAtDesc(Long vendorId, String isActive);
	Optional<VendorTokens> findByAccessTokenAndIsActive(String accessToken, String string);
}
