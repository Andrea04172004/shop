package org.example.dto;

import lombok.*;
import org.example.domain.LineItemEntity;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDto {
    private Integer id;
    private List<LineItemDto> lineItemDto = new ArrayList<>();
}
