package com.project.toyProject2.service;

import com.project.toyProject2.domain.dto.OrderItemDetailDTO;
import com.project.toyProject2.domain.vo.OrderItemVO;
import com.project.toyProject2.repository.OrderItemDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemDAO orderItemDAO;
    @Override
    public List<OrderItemDetailDTO> findOrderItemById(Long id) {
        return orderItemDAO.getOrderItemByOrderId(id);
    }
}
