package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {

}
