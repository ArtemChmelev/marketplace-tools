package ru.chmelev.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.chmelev.enums.MarketplaceTypes;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "marketplace")
public class Marketplace {

    @ManyToMany(mappedBy = "marketplaces")
    List<User> users;

    @Id
    @Column(name = "marketplace_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private MarketplaceTypes marketplaceTypes;

    @Column(name = "phone_number")
    private String marketplacePhoneNumber;

    @Column(name = "email")
    private String marketplaceEmail;

    @Column(name = "inn")
    private String marketplaceInn;

    @Column(name = "commission")
    private Double commission;

    @Column(name = "is_work")
    private boolean work;
}
