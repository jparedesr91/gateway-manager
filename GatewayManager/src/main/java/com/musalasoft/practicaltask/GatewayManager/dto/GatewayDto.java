package com.musalasoft.practicaltask.GatewayManager.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class GatewayDto {

    @NotNull(message = "Serial number is required dto")
    private String serialNumber;
    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "AddressIPv4 is required")
    @Pattern(message="Invalid IPv4 format" , regexp="\\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.|$)){4}\\b")
    private String addressIPv4;
    @Valid
    @Size(max = 10)
    private List<PeripheralDto> peripherals = new ArrayList<>();

    public GatewayDto(String serialNumber, String name, String addressIPv4, List<PeripheralDto> peripherals) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.addressIPv4 = addressIPv4;
        this.peripherals = peripherals;
    }

    public GatewayDto() {
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressIPv4() {
        return addressIPv4;
    }

    public void setAddressIPv4(String addressIPv4) {
        this.addressIPv4 = addressIPv4;
    }

    public List<PeripheralDto> getPeripherals() {
        return peripherals;
    }

    public void setPeripherals(List<PeripheralDto> peripherals) {
        this.peripherals = peripherals;
    }
}
