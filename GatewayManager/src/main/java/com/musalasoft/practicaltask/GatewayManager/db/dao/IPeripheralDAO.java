package com.musalasoft.practicaltask.GatewayManager.db.dao;

import com.musalasoft.practicaltask.GatewayManager.db.entity.Peripheral;

import java.util.List;

public interface IPeripheralDAO {
    List<Peripheral> getPeripherals();
    Peripheral getPeripheral(int id);
    Peripheral createPeripheral(Peripheral peripheral);
    Peripheral updatePeripheral(int id,Peripheral gateway);
    boolean deletePeripheral(int id);
}
