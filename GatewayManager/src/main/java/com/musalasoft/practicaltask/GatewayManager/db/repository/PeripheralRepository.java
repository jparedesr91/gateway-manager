package com.musalasoft.practicaltask.GatewayManager.db.repository;

import com.musalasoft.practicaltask.GatewayManager.db.entity.Gateway;
import com.musalasoft.practicaltask.GatewayManager.db.entity.Peripheral;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeripheralRepository extends CrudRepository<Peripheral, Long> {
    @Query("SELECT p FROM Peripheral p LEFT JOIN FETCH p.gateway WHERE p.uid = (:uid)")
    Optional<Peripheral> findFirstByUid(long uid);
    public void deletePeripheralByUid(@Param("uid") long uid);
    @Query("SELECT p FROM Peripheral p LEFT JOIN FETCH p.gateway g WHERE g.serialNumber  = (:serialNumber)")
    Optional<List<Peripheral>> findFirstBySerialNumber(@Param("serialNumber") String serialNumber);
}

