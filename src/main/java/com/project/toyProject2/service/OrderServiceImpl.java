package com.project.toyProject2.service;

import com.project.toyProject2.domain.dto.CartListDTO;
import com.project.toyProject2.domain.dto.OrderPaymentDTO;
import com.project.toyProject2.domain.dto.OrderRequestDTO;
import com.project.toyProject2.domain.vo.MemberVO;
import com.project.toyProject2.domain.vo.OrderItemVO;
import com.project.toyProject2.domain.vo.OrderVO;
import com.project.toyProject2.repository.CartDAO;
import com.project.toyProject2.repository.MemberDAO;
import com.project.toyProject2.repository.OrderDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CartDAO cartDAO;
    private final MemberDAO memberDAO;
    private final OrderDAO orderDAO;
    @Override
    public OrderPaymentDTO orderPaymentPage(MemberVO member) {
        OrderPaymentDTO orderPaymentDTO = new OrderPaymentDTO();
        orderPaymentDTO.setMemberName(member.getMemberName());
        orderPaymentDTO.setMemberLoginId(member.getMemberLoginId());
        orderPaymentDTO.setMemberPhone(member.getMemberPhone());
        orderPaymentDTO.setCartLists(cartDAO.selectAll(member.getMemberId()));
        orderPaymentDTO.calcTotalPrice();
        return orderPaymentDTO;
    }

    @Override
    public void saveOrder(OrderRequestDTO orderRequestDTO) {
        MemberVO member = memberDAO.selectMemberById(orderRequestDTO.getMemberLoginId()).get();
        List<CartListDTO> cartList = cartDAO.selectAll(member.getMemberId());
        OrderVO orderVO = new OrderVO();

        if (!cartList.isEmpty()) {
            int cartSize = cartList.size()-1;
            orderVO.setOrderSummary(cartList.get(0).getProductName() + "외 " + cartSize + "건");
        }
        orderVO.setMemberId(member.getMemberId());
        orderVO.setOrderDate(LocalDateTime.now());
//      직접 결제 api를 사용하지않기때문에 정적으로 넣어줌
        orderVO.setOrderStatus("배송 준비중");
        orderVO.setOrderTotalProductPrice(orderRequestDTO.getTotalProductPrice());
        orderVO.setOrderDeliveryPrice(orderRequestDTO.getDeliveryPrice());
        orderVO.setOrderTotalPayment(orderRequestDTO.getTotalPayment());
//      직접 결제 api를 사용하지않기때문에 정적으로 넣어줌
        orderVO.setOrderPaymentMethod("계좌이체");
        orderVO.setOrderAddress(orderRequestDTO.getOrderAddress());
        orderVO.setOrderDetailAddress(orderRequestDTO.getOrderDetailAddress());
        orderVO.setOrderMemo(orderRequestDTO.getOrderMemo());

        orderDAO.insertOrder(orderVO);

        OrderItemVO orderItemVO = new OrderItemVO();
        orderItemVO.setOrderId(orderVO.getOrderId());
//        orderItem테이블 추가
        for (CartListDTO cartListDTO : cartList) {

        }

    }

    @Override
    public List<OrderVO> orderList() {
        return orderDAO.selectOrderList();
    }

    @Override
    public OrderVO findOrder(Long orderId) {
        return orderDAO.selectOrderById(orderId);
    }
}
