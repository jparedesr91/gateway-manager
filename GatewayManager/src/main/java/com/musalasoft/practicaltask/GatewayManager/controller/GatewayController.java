package com.musalasoft.practicaltask.GatewayManager.controller;

import com.musalasoft.practicaltask.GatewayManager.dto.GatewayDto;
import com.musalasoft.practicaltask.GatewayManager.dto.PeripheralDto;
import com.musalasoft.practicaltask.GatewayManager.exceptions.DataAlreadyExistException;
import com.musalasoft.practicaltask.GatewayManager.exceptions.DataNotFoundException;
import com.musalasoft.practicaltask.GatewayManager.service.IDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/gateways")
public class GatewayController {

    @Autowired
    private IDataService dataService;

    @RequestMapping(value = "/gateway/pagination/{limit}/{offset}", method = RequestMethod.GET)
    public ResponseEntity<List<GatewayDto>> getGatewayDto(@PathVariable("limit") int limit, @PathVariable("offset") int offset) {
        return ResponseEntity.accepted().body(dataService.findAll(limit,offset));
    }

    @RequestMapping(value = "/gateway", method = RequestMethod.POST)
    public ResponseEntity<GatewayDto> addGateway(@Valid @RequestBody GatewayDto gatewayDto) throws DataAlreadyExistException {
            return ResponseEntity.accepted().body(dataService.addGateway(gatewayDto));
    }

    @RequestMapping(value = "/peripheral/{serialNumber}", method = RequestMethod.POST)
    public ResponseEntity<GatewayDto> addPeripherals(@PathVariable String serialNumber,@Valid @RequestBody List<PeripheralDto> peripheralDtos) throws DataNotFoundException {
        return ResponseEntity.accepted().body(dataService.addPeripheral(serialNumber,peripheralDtos));
    }

    @RequestMapping(value = "/gateway/{serialNumber}", method = RequestMethod.GET)
    public ResponseEntity<GatewayDto> findGateway(@PathVariable String serialNumber) throws DataNotFoundException {
        return ResponseEntity.accepted().body(dataService.findBySerialNumber(serialNumber));
    }

    @RequestMapping(value = "/peripheral/{uid}", method = RequestMethod.GET)
    public ResponseEntity<PeripheralDto> findPeripheral(@PathVariable long uid) throws DataNotFoundException {
        return ResponseEntity.accepted().body(dataService.findByUid(uid));
    }

    @RequestMapping(value = "/peripheral/{uid}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removePeripheral(@PathVariable long uid) throws DataNotFoundException {
        dataService.removePeripheral(uid);
        return ResponseEntity.ok().body("Peripheral deleted successfully");
    }
}
