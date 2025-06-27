package com.vendorloginservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendorloginservice.entity.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long>{

}
