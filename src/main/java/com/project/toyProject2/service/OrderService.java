package com.project.toyProject2.service;

import com.project.toyProject2.domain.dto.OrderPaymentDTO;
import com.project.toyProject2.domain.dto.OrderRequestDTO;
import com.project.toyProject2.domain.vo.MemberVO;
import com.project.toyProject2.domain.vo.OrderVO;

import java.util.List;

public interface OrderService {
    public OrderPaymentDTO orderPaymentPage(MemberVO member);
    public void saveOrder(OrderRequestDTO orderRequestDTO);
    public List<OrderVO> orderList(Long memberId);
    public OrderVO findOrder(Long orderId);
}
