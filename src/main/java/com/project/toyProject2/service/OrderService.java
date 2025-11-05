package com.project.toyProject2.service;

import com.project.toyProject2.domain.dto.OrderPaymentDTO;
import com.project.toyProject2.domain.vo.MemberVO;

import java.util.Optional;

public interface OrderService {

    public OrderPaymentDTO prepareCartPayment(MemberVO member);
}
