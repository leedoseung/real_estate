package com.duvis.realestate.repository;

import com.duvis.realestate.domain.entity.EstateRealAptTrx;
import com.duvis.realestate.domain.entity.EstateRealAptTrxKeys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealEstateTrxAptRepository extends JpaRepository<EstateRealAptTrx, EstateRealAptTrxKeys> {
}
