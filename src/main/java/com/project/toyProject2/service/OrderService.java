package com.project.toyProject2.service;

import com.project.toyProject2.domain.dto.order.OrderPaymentDTO;
import com.project.toyProject2.domain.dto.order.OrderRequestDTO;
import com.project.toyProject2.domain.vo.MemberVO;
import com.project.toyProject2.domain.vo.OrderVO;

import java.util.List;

public interface OrderService {
    public OrderPaymentDTO orderPaymentPage(MemberVO member);
    public void saveOrder(OrderRequestDTO orderRequestDTO);
    public List<OrderVO> orderList(Long memberId);
    public OrderVO findOrder(Long orderId);
}
