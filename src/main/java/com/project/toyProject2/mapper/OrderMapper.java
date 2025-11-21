package com.project.toyProject2.mapper;
import com.project.toyProject2.domain.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    public void insert(OrderVO orderVO);
    public List<OrderVO> selectAll(Long memberId);
    public OrderVO selectById(Long orderId);

}
