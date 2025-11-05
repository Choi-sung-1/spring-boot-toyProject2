package com.project.toyProject2.repository;

import com.project.toyProject2.domain.dto.CartListDTO;
import com.project.toyProject2.domain.vo.CartVO;
import com.project.toyProject2.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartDAO {
    private final CartMapper cartMapper;

    public void insert(CartVO cartVO) {
        cartMapper.insert(cartVO);
    }
    public CartVO select (Long memberId,Long productId){
        return cartMapper.select(memberId,productId);
    }
    public List<CartListDTO> selectAll(Long memberId) {
        return cartMapper.selectAll(memberId);
    }
    public void updateCartQuantity(Long cartId,Long cartQuantity){cartMapper.update(cartId,cartQuantity);}
    public void delete(Long cartId) {
        cartMapper.delete(cartId);
    }
    public void deleteAll(Long memberId){
        cartMapper.deleteAll(memberId);
    }
}
