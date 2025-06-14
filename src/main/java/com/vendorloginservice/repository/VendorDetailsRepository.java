package com.vendorloginservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendorloginservice.entity.VendorDetails;

@Repository
public interface VendorDetailsRepository extends JpaRepository<VendorDetails, Long>{

}
