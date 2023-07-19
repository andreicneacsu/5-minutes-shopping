package com.unibuc.storeservice.repository;

import com.unibuc.storeservice.entity.Store;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("SELECT s from Store s")
    List<Store> findAll();

    @Query("SELECT s from Store s WHERE s.id = ?1")
    Optional<Store> findById(Long id);

    @Query("SELECT s from Store s WHERE s.districtNumber =?1")
    List<Store> findByDistrictNumber(Long districtNumber);
}
