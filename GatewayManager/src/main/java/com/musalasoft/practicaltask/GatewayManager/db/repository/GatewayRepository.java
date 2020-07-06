package com.musalasoft.practicaltask.GatewayManager.db.repository;

import com.musalasoft.practicaltask.GatewayManager.db.entity.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GatewayRepository extends JpaRepository<Gateway, Long> {
    @Query("SELECT g FROM Gateway g LEFT JOIN FETCH g.peripherals WHERE g.serialNumber = (:serialNumber) order by g.id")
    public Optional<Gateway> findFirstBySerialNumber(@Param("serialNumber") String serialNumber);
    @Query("SELECT DISTINCT g FROM Gateway g LEFT JOIN FETCH g.peripherals order by g.id")
    public List<Gateway> findAl();
}

