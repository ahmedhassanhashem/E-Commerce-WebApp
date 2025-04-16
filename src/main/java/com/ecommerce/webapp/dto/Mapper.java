package com.ecommerce.webapp.dto;

import com.ecommerce.webapp.entities.Cart;
import com.ecommerce.webapp.entities.CartItem;
import com.ecommerce.webapp.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class Mapper {


    public static CartDTO mapToDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(cart.getCartId());
        cartDTO.setTotalPrice(cart.getTotalPrice());

        List<CartItemDTO> itemDTOs = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            CartItemDTO itemDTO = new CartItemDTO();
            itemDTO.setId(item.getId());
            itemDTO.setQuantity(item.getQuantity());

            Product product = item.getProduct();
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductId(product.getProductId());
            productDTO.setName(product.getName());
            productDTO.setPrice(product.getPrice());
            productDTO.setImage(product.getImage());
            productDTO.setCategory(product.getCategory().name()); // Assuming category is an enum or similar

            itemDTO.setProduct(productDTO);
            itemDTOs.add(itemDTO);
        }
        cartDTO.setItems(itemDTOs);

        return cartDTO;
    }


}
