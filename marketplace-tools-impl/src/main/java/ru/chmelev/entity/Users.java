package ru.chmelev.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class Users {

    @ManyToMany
    @JoinTable(
            name = "users_marketplace",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "marketplace_id"))

    private List<Marketplace> marketplaces;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    long id;

    @Column(name = "user_name")
    String userName;

    @Column(name="first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "email")
    String email;

    @Column(name = "date_create")
    LocalDateTime dateCreate;

    @Column(name = "date_update")
    LocalDateTime dateUpdate;
    
}
