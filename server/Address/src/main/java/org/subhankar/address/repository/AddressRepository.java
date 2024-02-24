package org.subhankar.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.subhankar.address.model.DO.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, String> {
    List<Address> findByCity(String city);

    List<Address> findByState(String state);

    List<Address> findByCountry(String country);

    List<Address> findByZipCode(String zipCode);

    @Query("SELECT a FROM Address a WHERE a.identifier = ?1")
    List<Address> findByIdentifier(String identifier);
}
