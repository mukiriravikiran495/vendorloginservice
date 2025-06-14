package com.vendorloginservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vendorloginservice.entity.VendorCredentials;
import com.vendorloginservice.entity.VendorCredentials_PK;

@Repository
public interface VendorLoginRepository extends JpaRepository<VendorCredentials, VendorCredentials_PK> {

	Optional<VendorCredentials> findByMobile(String mobile);
	
}
