package com.project.toyProject2.repository;

import com.project.toyProject2.domain.vo.OrderVO;
import com.project.toyProject2.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderDAO {
    private final OrderMapper orderMapper;

    public void insertOrder(OrderVO orderVO) {
        orderMapper.insert(orderVO);
    }
    public List<OrderVO> selectOrderList(){
        return orderMapper.selectAll();
    }
    public OrderVO selectOrderById(Long orderId) {
        return orderMapper.selectById(orderId);
    }
}
