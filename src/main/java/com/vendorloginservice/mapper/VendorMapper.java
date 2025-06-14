package com.vendorloginservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.vendorloginservice.domain.VendorAddressDTO;
import com.vendorloginservice.domain.VendorDetailsDTO;
import com.vendorloginservice.entity.VendorAddress;
import com.vendorloginservice.entity.VendorDetails;


@Mapper(componentModel = "spring")
public interface VendorMapper {

	VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    // Entity to DTO
    @Mapping(source = "vendorId", target = "vendorId")
    @Mapping(source = "v_firstName", target = "v_firstName")
    @Mapping(source = "v_lastName", target = "v_lastName")
    @Mapping(source = "v_mobile", target = "v_mobile")
    @Mapping(source = "v_email", target = "v_email")
    @Mapping(source = "vendorAddress", target = "vendorAddress")
    VendorDetailsDTO toDto(VendorDetails entity);

    @Mapping(source = "v_address_id", target = "v_address_Id")
    VendorAddressDTO toDto(VendorAddress entity);

    // DTO to Entity (optional for POST/PUT)
    @Mapping(source = "vendorId", target = "vendorId")
    @Mapping(source = "v_firstName", target = "v_firstName")
    @Mapping(source = "v_lastName", target = "v_lastName")
    @Mapping(source = "v_mobile", target = "v_mobile")
    @Mapping(source = "v_email", target = "v_email")
    @Mapping(source = "vendorAddress", target = "vendorAddress")
    VendorDetails toEntity(VendorDetailsDTO dto);

    @Mapping(source = "v_address_Id", target = "v_address_id")
    VendorAddress toEntity(VendorAddressDTO dto);

    List<VendorDetailsDTO> toDtoList(List<VendorDetails> entities);
}