package com.vendorloginservice.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vendorloginservice.entity.VendorAuth;
import com.vendorloginservice.entity.VendorAuth_PK;

@Repository
public interface VendorLoginRepository extends JpaRepository<VendorAuth, VendorAuth_PK> {

	Optional<VendorAuth> findByMobile(String mobile);
	Optional<VendorAuth> findByVendorId(Long vendorId);
}
