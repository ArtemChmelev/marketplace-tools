package ru.chmelev.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "production")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "production_id")
    private Long productionId;

    @Column(name = "marketplace_id")
    private Long marketplaceId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "count")
    private Integer count;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "overall_cost")
    private Double overallCost;

    @Column(name = "profit_percentage")
    private Double profitPercentage;

    @Column(name = "final_price")
    private Double finalPrice;

}
