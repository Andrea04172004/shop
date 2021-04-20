package org.example.dto;

import lombok.*;
import org.example.domain.CategoryEntity;


@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Integer id;
    private String name;
    private String image;
    private Integer quantity;
    private Double price;
    private String description;
    private CategoryDto categoryDto = new CategoryDto();
}
