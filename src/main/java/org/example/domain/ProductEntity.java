package org.example.domain;

import lombok.*;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class ProductEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (unique = true)
    private String name;
    @Column
    private String image;
    @Column (nullable = false)
    private Integer quantity;
    @Column (nullable = false)
    private Double price;
    @Column
    private String description;
    @OneToOne
    private CategoryEntity categoryEntity;

}
