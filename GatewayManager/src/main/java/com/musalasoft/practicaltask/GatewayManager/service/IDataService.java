package com.musalasoft.practicaltask.GatewayManager.service;
import com.musalasoft.practicaltask.GatewayManager.dto.GatewayDto;
import com.musalasoft.practicaltask.GatewayManager.dto.PeripheralDto;
import com.musalasoft.practicaltask.GatewayManager.exceptions.DataAlreadyExistException;
import com.musalasoft.practicaltask.GatewayManager.exceptions.DataNotFoundException;

import java.util.List;

public interface IDataService {
    public List<GatewayDto> findAll(int limit, int offset);
    public GatewayDto addGateway(GatewayDto gateway) throws DataAlreadyExistException;
    public GatewayDto addPeripheral(String serialNumber,List<PeripheralDto> peripherals) throws DataNotFoundException;
    public GatewayDto findBySerialNumber(String serialNumber) throws DataNotFoundException;
    public void removePeripheral(long uid) throws DataNotFoundException;
    public PeripheralDto findByUid(long uid) throws DataNotFoundException;
}
