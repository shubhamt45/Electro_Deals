package com.Electronics.store.Electronics_goods.Store.services;

import com.Electronics.store.Electronics_goods.Store.dtos.CreateOrderRequest;
import com.Electronics.store.Electronics_goods.Store.dtos.OrderDto;
import com.Electronics.store.Electronics_goods.Store.dtos.OrderUpdateRequest;
import com.Electronics.store.Electronics_goods.Store.dtos.PageableResponse;

import java.util.List;

public interface OrderService {

    OrderDto getOrder(String orderId);

    //create order
    OrderDto createOrder(CreateOrderRequest orderDto);

    //remove order
    void removeOrder(String orderId);

    //get orders of user
    List<OrderDto> getOrdersOfUser(String userId);

    //get orders
    PageableResponse<OrderDto> getOrders(int pageNumber, int pageSize, String sortBy, String sortDir);

    OrderDto updateOrder(String orderId, OrderUpdateRequest request);
    OrderDto updateOrder(String orderId, OrderDto request);

    //order methods(logic) related to order
}
