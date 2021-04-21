package org.example.services;

import org.example.domain.LineItemEntity;
import org.example.dto.LineItemDto;
import org.example.repositories.LineItemRepository;
import org.example.utils.BusinessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineItemServiceImpl implements LineItemService {
    @Autowired
    private LineItemRepository lineItemRepository;
    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public LineItemDto createLineItem(LineItemDto lineItemDto) {
        LineItemEntity lineItemEntity = businessMapper.convertToLineItemEntity(lineItemDto);
        lineItemRepository.save(lineItemEntity);
        return businessMapper.convertToLineItemDto(lineItemEntity);
    }

    @Override
    public LineItemDto updateLineItem(Integer lineId, LineItemDto lineItemDto) {
        return null;
    }

    @Override
    public LineItemDto findById(Integer lineId) {
        return businessMapper.convertToLineItemDto(lineItemRepository.findById(lineId).orElseThrow());
    }

    @Override
    public void deleteLineItem(Integer lineId) {
        lineItemRepository.deleteById(lineId);
    }

    @Override
    public List<LineItemDto> findAllLineItems() {
        return businessMapper.convertCollectionToListGen(lineItemRepository.findAll(), businessMapper.toLineItemDto);
    }
}
