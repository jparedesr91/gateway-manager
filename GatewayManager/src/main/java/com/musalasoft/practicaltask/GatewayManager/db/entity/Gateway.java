package com.musalasoft.practicaltask.GatewayManager.db.entity;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="gateway")
public class Gateway implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private long id;
    @NotNull(message = "Serial number is required")
    @Column(name="serial_number", unique = true, nullable = false)
    private String serialNumber;
    @NotNull(message = "Name is required")
    @Column(name="name", nullable = false)
    private String name;
    @NotNull(message = "AddressIPv4 is required")
    @Pattern(message="Invalid IPv4 format" , regexp="\\b((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\\.|$)){4}\\b")
    @Column(name="address_ipv4",nullable = false)
    private String addressIPv4;

    @OneToMany(
            mappedBy = "gateway",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @Valid
    @Size(max=10)
    private List<Peripheral> peripherals = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Peripheral> getPeripherals() {
        return peripherals;
    }

    public void setPeripherals(List<Peripheral> peripherals) {
        this.peripherals = peripherals;
    }

    public void addPeripheral(Peripheral peripheral) {
        peripherals.add(peripheral);
        peripheral.setGateway(this);
    }

    public void removeComment(Peripheral peripheral) {
        peripherals.remove(peripheral);
        peripheral.setGateway(null);
    }
}
