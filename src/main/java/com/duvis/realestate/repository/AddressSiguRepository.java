package com.duvis.realestate.repository;

import com.duvis.realestate.domain.entity.KoreaAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressSiguRepository extends JpaRepository<KoreaAddress, Long> {

}
