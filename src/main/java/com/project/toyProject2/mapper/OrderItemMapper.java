package com.project.toyProject2.mapper;
import com.project.toyProject2.domain.dto.order.OrderItemDetailDTO;
import com.project.toyProject2.domain.vo.OrderItemVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    public void insert(OrderItemVO orderItemVO);
    public List<OrderItemDetailDTO> select(Long orderId);
}
