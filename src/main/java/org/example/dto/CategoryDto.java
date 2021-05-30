package org.example.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private String title;
    private String image;
    private String description;
//    private List<ProductDto> productDto = new ArrayList<>();
}
