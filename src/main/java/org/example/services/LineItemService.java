package org.example.services;

import org.example.dto.LineItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LineItemService {
    public LineItemDto createLineItem(LineItemDto lineItemDto);
    public LineItemDto updateLineItem(Integer lineId, LineItemDto lineItemDto);
    public LineItemDto findById(Integer lineId);
    public void deleteLineItem(Integer lineId);
    public List<LineItemDto> findAllLineItems();
}
