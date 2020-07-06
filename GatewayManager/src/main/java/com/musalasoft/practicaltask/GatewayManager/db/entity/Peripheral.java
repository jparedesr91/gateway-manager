package com.musalasoft.practicaltask.GatewayManager.db.entity;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="peripheral")
public class Peripheral implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private long id;
    @NotNull(message = "UID is required")
    @Column(name="uid", unique = true,  nullable = false)
    private long uid;
    @NotNull(message = "Vendor is required")
    @Column(name="vendor", nullable = false)
    private String vendor;
    @NotNull(message = "Created is required")
    @Column(name="created", nullable = false)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate created;
    @NotNull(message = "Status is required")
    @Column(name="status", nullable = false)
    private EStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="gateway_id", nullable=false)
    private Gateway gateway;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }
}
