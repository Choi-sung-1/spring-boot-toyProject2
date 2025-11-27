package com.project.toyProject2.service;


import com.project.toyProject2.domain.dto.order.OrderItemDetailDTO;

import java.util.List;

public interface OrderItemService {
    public List<OrderItemDetailDTO> findOrderItemById(Long id);
}
