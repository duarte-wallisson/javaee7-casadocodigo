package org.casadocodigo.store.models;

import lombok.Getter;
import lombok.Setter;
import org.casadocodigo.store.models.validation.groups.BuyerGroup;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
public class SystemUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Email
    @NotBlank
    @Column(unique=true)
    private String email;
    @NotBlank(groups= BuyerGroup.class)
    private String firstName;
    @NotBlank(groups=BuyerGroup.class)
    private String lastName;
    @NotBlank(groups=BuyerGroup.class)
    private String socialId;
    @NotBlank(groups=BuyerGroup.class)
    private String address;
    @NotBlank(groups=BuyerGroup.class)
    private String city;
    @NotBlank(groups=BuyerGroup.class)
    private String state;
    @NotBlank(groups=BuyerGroup.class)
    private String postalCode;
    @NotBlank(groups=BuyerGroup.class)
    private String phone;
    @NotBlank(groups=BuyerGroup.class)
    private String country;
    private String password;


}
