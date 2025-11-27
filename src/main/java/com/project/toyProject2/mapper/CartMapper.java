package com.project.toyProject2.mapper;

import com.project.toyProject2.domain.dto.cart.CartListDTO;
import com.project.toyProject2.domain.vo.CartVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    public void insert(CartVO cartVO);
    public CartVO select(Long memberId,Long productId);
    public void update(Long cartId,Long cartQuantity);
    public List<CartListDTO> selectAll(Long memberId);
    public void delete(Long cartId);
    public void deleteAll(Long memberId);
}
