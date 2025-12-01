package com.project.toyProject2.service;

import com.project.toyProject2.domain.dto.cart.CartListDTO;
import com.project.toyProject2.domain.dto.order.OrderPaymentDTO;
import com.project.toyProject2.domain.dto.order.OrderRequestDTO;
import com.project.toyProject2.domain.vo.MemberVO;
import com.project.toyProject2.domain.vo.OrderItemVO;
import com.project.toyProject2.domain.vo.OrderVO;
import com.project.toyProject2.domain.vo.ProductVO;
import com.project.toyProject2.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CartDAO cartDAO;
    private final MemberDAO memberDAO;
    private final OrderDAO orderDAO;
    private final OrderItemDAO orderItemDAO;
    private final ProductDAO productDAO;
    @Override
    public OrderPaymentDTO orderPaymentPage(MemberVO member) {
        OrderPaymentDTO orderPaymentDTO = new OrderPaymentDTO();
        orderPaymentDTO.setMemberName(member.getMemberName());
        orderPaymentDTO.setMemberLoginId(member.getMemberLoginId());
        orderPaymentDTO.setMemberPhone(member.getMemberPhone());

        Map<Long,CartListDTO> map = new LinkedHashMap<>();
        for (CartListDTO cartListDTO : cartDAO.selectAll(member.getMemberId())) {
            if (!map.containsKey(cartListDTO.getProductId())){
                map.put(cartListDTO.getProductId(), cartListDTO);
            }
        }
        orderPaymentDTO.setCartLists(new ArrayList<>(map.values()));
        orderPaymentDTO.calcTotalPrice();
        return orderPaymentDTO;
    }

    @Override
    public void saveOrder(OrderRequestDTO orderRequestDTO) {
        MemberVO member = memberDAO.selectMemberById(orderRequestDTO.getMemberLoginId()).get();
        List<CartListDTO> cartList = cartDAO.selectAll(member.getMemberId());
        Map<Long,CartListDTO> map = new LinkedHashMap<>();
        for (CartListDTO cartListDTO : cartList) {
            if (!map.containsKey(cartListDTO.getProductId())){
                map.put(cartListDTO.getProductId(), cartListDTO);
            }
        }
        cartList = new ArrayList<>(map.values());

        OrderVO orderVO = new OrderVO();

        if (!cartList.isEmpty()) {
            if (cartList.size() != 1) {
                int cartSize = cartList.size() - 1;
                orderVO.setOrderSummary(cartList.get(0).getProductName() + " 외 " + cartSize + "건");
            }else{
                orderVO.setOrderSummary(cartList.get(0).getProductName());
            }
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


        for (CartListDTO cartListDTO : cartList) {
            OrderItemVO orderItemVO = new OrderItemVO();
            orderItemVO.setOrderId(orderVO.getOrderId());
            orderItemVO.setOrderProductId(cartListDTO.getProductId());
            orderItemVO.setOrderItemQuantity(cartListDTO.getCartQuantity());
            orderItemDAO.insertOrderItem(orderItemVO);
            ProductVO product = productDAO.selectProductById(cartListDTO.getProductId());
//            제품 재고 업데이트
            productDAO.updateProduct(product.getProductId(), product.getProductStock()-cartListDTO.getCartQuantity());
        }
        cartDAO.deleteAll(member.getMemberId());
    }

    @Override
    public List<OrderVO> orderList(Long memberId) {
        return orderDAO.selectOrderList(memberId);
    }

    @Override
    public OrderVO findOrder(Long orderId) {
        return orderDAO.selectOrderById(orderId);
    }
}
