package org.example.dto;

import lombok.*;
import java.util.List;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private String title;
    private List<ProductDto> productDto;
}
