package com.project.toyProject2.service;

import com.project.toyProject2.domain.dto.CartListDTO;
import com.project.toyProject2.domain.dto.OrderPaymentDTO;
import com.project.toyProject2.domain.vo.MemberVO;
import com.project.toyProject2.repository.CartDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CartDAO cartDAO;
    @Override
    public OrderPaymentDTO prepareCartPayment(MemberVO member) {
        List<CartListDTO> cartList = cartDAO.selectAll(member.getMemberId());

        OrderPaymentDTO orderPaymentDTO = new OrderPaymentDTO();
        orderPaymentDTO.setMemberName(member.getMemberName());
        orderPaymentDTO.setMemberLoginId(member.getMemberLoginId());
        orderPaymentDTO.setMemberPhone(member.getMemberPhone());
        orderPaymentDTO.setCartList(cartList);
        return orderPaymentDTO;
    }
}
