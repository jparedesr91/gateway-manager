package com.musalasoft.practicaltask.GatewayManager.service;

import com.musalasoft.practicaltask.GatewayManager.db.ValidatorDB;
import com.musalasoft.practicaltask.GatewayManager.db.entity.Gateway;
import com.musalasoft.practicaltask.GatewayManager.db.entity.Peripheral;
import com.musalasoft.practicaltask.GatewayManager.db.repository.GatewayRepository;
import com.musalasoft.practicaltask.GatewayManager.db.repository.PeripheralRepository;
import com.musalasoft.practicaltask.GatewayManager.dto.GatewayDto;
import com.musalasoft.practicaltask.GatewayManager.dto.PeripheralDto;
import com.musalasoft.practicaltask.GatewayManager.exceptions.DataAlreadyExistException;
import com.musalasoft.practicaltask.GatewayManager.exceptions.DataNotFoundException;
import com.musalasoft.practicaltask.GatewayManager.exceptions.PeripheralSizeException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class DataService implements IDataService {

    @Autowired
    private GatewayRepository gatewayRepository;
    @Autowired
    private PeripheralRepository peripheralRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<GatewayDto> findAll(int limit, int offset) {
        Pageable pageRequest = PageRequest.of(offset, limit);
        Type listType = new TypeToken<List<GatewayDto>>(){}.getType();
        List<GatewayDto> gatewayDtoList = modelMapper.map((List<Gateway>) gatewayRepository.findAl(),listType);
        return gatewayDtoList;
    }

    @Override
    public GatewayDto addGateway(GatewayDto gatewayDto) throws DataAlreadyExistException {
        if(!gatewayRepository.findFirstBySerialNumber(gatewayDto.getSerialNumber()).isPresent()){
            Gateway gateway = modelMapper.map(gatewayDto,Gateway.class);
            ValidatorDB<Gateway> gatewayDtoValidatorDB = new ValidatorDB<>();
            gatewayDtoValidatorDB.validate(gateway);
            for(Peripheral peripheral:gateway.getPeripherals()){
                peripheral.setGateway(gateway);
            }
            return modelMapper.map(gatewayRepository.save(gateway),GatewayDto.class);
        } else {
            throw new DataAlreadyExistException("Gateway Already Exist: " + gatewayDto.getSerialNumber());
        }
    }

    @Override
    public GatewayDto addPeripheral(String serialNumber, List<PeripheralDto> peripherals) throws DataNotFoundException {
        Optional<Gateway> gatewayOptional = gatewayRepository.findFirstBySerialNumber(serialNumber);
        if(gatewayOptional.isPresent()) {
            Gateway gateway = gatewayOptional.get();
            if(gateway.getPeripherals().size()+peripherals.size()<=10){
                for (PeripheralDto peripheralDto : peripherals) {
                    gateway.addPeripheral(modelMapper.map(peripheralDto, Peripheral.class));
                }
                return modelMapper.map(gatewayRepository.save(gateway),GatewayDto.class);
            } else {
                throw new PeripheralSizeException("Peripheral size greater than 10: " + serialNumber);
            }
        } else {
            throw new DataNotFoundException("Gateway Not Found: " + serialNumber);
        }
    }

    @Override
    public GatewayDto findBySerialNumber(String serialNumber) throws DataNotFoundException {
        Optional<Gateway> gatewayOptional = gatewayRepository.findFirstBySerialNumber(serialNumber);
        if(gatewayOptional.isPresent()){
            Gateway gateway = gatewayOptional.get();
            return modelMapper.map(gateway,GatewayDto.class);
        } else {
            throw new DataNotFoundException("Gateway Not Found: " + serialNumber);
        }
    }

    @Override
    public PeripheralDto findByUid(long uid) throws DataNotFoundException {
        Optional<Peripheral> peripheralOptional = peripheralRepository.findFirstByUid(uid);
        if(peripheralOptional.isPresent()){
            Peripheral peripheral = peripheralOptional.get();
            return modelMapper.map(peripheral,PeripheralDto.class);
        } else {
            throw new DataNotFoundException("Peripheral Not Found: " + uid);
        }

    }

    @Override
    public void removePeripheral(long uid) throws DataNotFoundException {
        Optional<Peripheral> peripheralOptional = peripheralRepository.findFirstByUid(uid);
        if(peripheralOptional.isPresent()){
            Peripheral peripheral = peripheralOptional.get();
            peripheralRepository.delete(peripheral);
        } else {
            throw new DataNotFoundException("Peripheral Not Found: " + uid);
        }

    }
}
