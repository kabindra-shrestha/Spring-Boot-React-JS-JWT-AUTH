package com.springbootreactjsjwtauth.entity.common;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "item_code")
public class ItemCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "item_code_id")
    private long id;

    @NotNull
    @NotEmpty(message = "*Please provide a item code key")
    @Column(name = "item_code_secret_key")
    private String secretKey;

    @NotNull
    @NotEmpty(message = "*Please provide a item code key")
    @Column(name = "item_code_view_key")
    private String viewKey;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "item_code_created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT/* , pattern = "yyyy-MM-dd hh:mm:ss" */)
    private Date createdat;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "item_code_modified_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT/* , pattern = "yyyy-MM-dd hh:mm:ss" */)
    private Date modifiedat;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "lot_id")
    @JsonBackReference(value = "lot_id")
    private Lot lotid;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user_id")
    private Users userid;

    public ItemCode() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getViewKey() {
        return viewKey;
    }

    public void setViewKey(String viewKey) {
        this.viewKey = viewKey;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public Date getModifiedat() {
        return modifiedat;
    }

    public void setModifiedat(Date modifiedat) {
        this.modifiedat = modifiedat;
    }

    public Lot getLotid() {
        return lotid;
    }

    public void setLotid(Lot lotid) {
        this.lotid = lotid;
    }

    public Users getUserid() {
        return userid;
    }

    public void setUserid(Users userid) {
        this.userid = userid;
    }

}
