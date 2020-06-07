package com.springbootreactjsjwtauth.entity.common;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "users")
public class Users {

    public static final int EXPIRATION = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "user_id")
    private long id;

    @Column(name = "user_firstname")
    private String firstname = "";

    @Column(name = "user_lastname")
    private String lastname = "";

    /*@NotNull
    @NotEmpty(message = "*Please provide your username")*/
    @Column(name = "user_username", unique = true)
    private String username;

    @NotNull
    @Length(min = 6, message = "*Your password must have at least 6 characters")
    @NotEmpty(message = "*Please provide your password")
    @Column(name = "user_password")
    private String password;

    @NotNull
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    @Column(name = "user_email")
    private String email;

    @Column(name = "user_phone")
    private String phone = "";

    @Column(name = "user_mobile")
    private String mobile = "";

    @Column(name = "user_address")
    private String address = "";

    @Column(name = "user_image")
    private String image = "";

    @Column(name = "user_enabled")
    private boolean enabled = false;

    @Column(name = "user_token")
    private String token = "";

    @Column(name = "user_activation_token")
    private String activationToken = "";

    @Column(name = "user_activation_expiry")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT/* , pattern = "yyyy-MM-dd hh:mm:ss" */)
    private Date expiryDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "user_created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT/* , pattern = "yyyy-MM-dd hh:mm:ss" */)
    private Date createdat;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "user_modified_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT/* , pattern = "yyyy-MM-dd hh:mm:ss" */)
    private Date modifiedat;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    @JsonBackReference(value = "role_id")
    private Role roleid;

    public Users() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @JsonIgnore
    public String getActivationToken() {
        return activationToken;
    }

    @JsonProperty
    public void setActivationToken(String activationToken) {
        this.activationToken = activationToken;
    }

    @JsonIgnore
    public Date getExpiryDate() {
        return expiryDate;
    }

    @JsonProperty
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
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

    public Role getRoleid() {
        return roleid;
    }

    public void setRoleid(Role roleid) {
        this.roleid = roleid;
    }

    public Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

}
