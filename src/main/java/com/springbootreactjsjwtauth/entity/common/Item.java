package com.springbootreactjsjwtauth.entity.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "item_id")
    private long id;

    @NotNull
    @NotEmpty(message = "*Please provide a item name")
    @Column(name = "item_name")
    private String name;

    @NotNull(message = "*Please provide a item quantity")
    @Column(name = "item_quantity")
    private int quantity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "item_created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT/* , pattern = "yyyy-MM-dd hh:mm:ss" */)
    private Date createdat;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "item_modified_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT/* , pattern = "yyyy-MM-dd hh:mm:ss" */)
    private Date modifiedat;

    public Item() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

}
