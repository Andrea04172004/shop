package org.example.dto;

import lombok.*;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LineItemDto {
    private Integer id;
    private int quantity;
    private Double lineSum;
    private ProductDto product;
}
