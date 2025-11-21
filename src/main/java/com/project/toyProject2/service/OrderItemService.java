package com.project.toyProject2.service;


import com.project.toyProject2.domain.dto.OrderItemDetailDTO;
import com.project.toyProject2.domain.vo.OrderItemVO;

import java.util.List;

public interface OrderItemService {
    public List<OrderItemDetailDTO> findOrderItemById(Long id);
}
