package com.project.toyProject2.repository;

import com.project.toyProject2.domain.dto.order.OrderItemDetailDTO;
import com.project.toyProject2.domain.vo.OrderItemVO;
import com.project.toyProject2.mapper.OrderItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderItemDAO {
    private final OrderItemMapper orderItemMapper;

    public void insertOrderItem(OrderItemVO orderItemVO) {
        orderItemMapper.insert(orderItemVO);
    }
    public List<OrderItemDetailDTO> getOrderItemByOrderId(Long orderId) {
        return orderItemMapper.select(orderId);
    }
}
