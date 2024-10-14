package com.plazoleta.plazoleta.domain.usecase;

import com.plazoleta.plazoleta.domain.api.IOrderServicePort;
import com.plazoleta.plazoleta.domain.enums.OrderSortBy;
import com.plazoleta.plazoleta.domain.enums.OrderStatus;
import com.plazoleta.plazoleta.domain.exception.*;
import com.plazoleta.plazoleta.domain.model.Order;
import com.plazoleta.plazoleta.domain.model.external.Employee;
import com.plazoleta.plazoleta.domain.model.external.User;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationCustom;
import com.plazoleta.plazoleta.domain.model.pagination.PaginationParams;
import com.plazoleta.plazoleta.domain.spi.IMessagerConnectionPort;
import com.plazoleta.plazoleta.domain.spi.IOrderPersistencePort;
import com.plazoleta.plazoleta.domain.spi.IUserAuthenticationPort;
import com.plazoleta.plazoleta.domain.spi.IUserConnectionPort;
import com.plazoleta.plazoleta.domain.usecase.validator.OrderUseCaseValidator;
import com.plazoleta.plazoleta.domain.util.Messages;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Random;

import static com.plazoleta.plazoleta.domain.util.Constants.RECLAIM_CODE_LENGTH;

@RequiredArgsConstructor
public class OrderUseCase implements IOrderServicePort {
    private final OrderUseCaseValidator orderUseCaseValidator;
    private final IOrderPersistencePort orderPersistencePort;
    private final IUserAuthenticationPort userAuthenticationPort;
    private final IUserConnectionPort userConnectionPort;
    private final IMessagerConnectionPort messagerConnectionPort;


    @Override
    public void createOrder(Order order) {
        Long customerId = userAuthenticationPort.getAuthenticatedUserId();
        orderUseCaseValidator.validateCreateOrder(order, customerId);
        order.setStatus(OrderStatus.PENDING);
        order.setCustomerId(customerId);
        orderPersistencePort.saveOrder(order);
    }

    @Override
    public PaginationCustom<Order> listOrdersByEmployeeRestaurant(OrderStatus orderStatus, PaginationParams<OrderSortBy> paginationParams) {
        Employee employee = getAuthenticatedEmployee();
        PaginationCustom<Order> paginationCustom = orderPersistencePort.findOrdersByRestaurantId(orderStatus, employee.getRestaurantId(), paginationParams);
        return paginationCustom;
    }

    @Override
    public void assignEmployeeToOrder(Long orderId) {
        Order order = orderPersistencePort.findOrderById(orderId).orElseThrow(OrderNotFoundException::new);
        Employee employee = getAuthenticatedEmployee();
        validateOrderStatusNotPending(order);
        validateIfEmployeeCanWorksInThatOrder(order, employee);

        orderPersistencePort.updateOrderEmployeeAssigned(order.getId(), employee.getId());
    }

    @Override
    public void notifyClientOrderIsReady(Long orderId) {
        Order order = orderPersistencePort.findOrderById(orderId).orElseThrow(OrderNotFoundException::new);
        if(order.getStatus() != OrderStatus.PREPARING){
            throw new OrderNotPreparingException();
        }

        User user = userAuthenticationPort.getAuthenticatedUser();

        Integer reclaimCode = generateReclaimCode();
        orderPersistencePort.updateOrderStatus(order.getId(), OrderStatus.READY);
        orderPersistencePort.updateOrderReclaimCode(order.getId(), reclaimCode);

        String message = Messages.messageOrderReady(order, user, reclaimCode);
        messagerConnectionPort.sendNotifySMSOrderReady(user.getPhoneNumber(), message);
    }



    @Override
    public void deliverOrder(Long orderId, Integer reclaimCode) {
        Order order = orderPersistencePort.findOrderById(orderId).orElseThrow(OrderNotFoundException::new);
        validateOrderNotDelivered(order);
        validateOrderStatusIsReady(order);
        validateReclaimCode(order, reclaimCode);

        orderPersistencePort.updateOrderStatus(order.getId(), OrderStatus.DELIVERED);
    }


    @Override
    public void cancelOrder(Long orderId) {
        Order order = orderPersistencePort.findOrderById(orderId).orElseThrow(OrderNotFoundException::new);
        validateIfClientIsOwnerOfOrder(order);
        validateOrderIsNotCanceledYet(order);
        validateOrderIsPending(order);

        orderPersistencePort.updateOrderStatus(orderId, OrderStatus.CANCELED);
    }

    private void validateIfClientIsOwnerOfOrder(Order order){
        Long clientId = userAuthenticationPort.getAuthenticatedUserId();
        if(order.getCustomerId() != clientId){
            throw new UnauthorizedAccessException();
        }
    }

    private void validateOrderIsNotCanceledYet(Order order){
        if(order.getStatus() == OrderStatus.CANCELED){
            throw new OrderAlreadyCanceled();
        }
    }
    private void validateOrderIsPending(Order order){
        if(order.getStatus() != OrderStatus.PENDING){
            throw new OrderNotCancelableException();
        }
    }


    private void validateOrderNotDelivered(Order order){
        if(order.getStatus() == OrderStatus.DELIVERED){
            throw new OrderAlreadyDelivered();
        }
    }

    private void validateReclaimCode(Order order, Integer reclaimCode) {
        if (String.valueOf(reclaimCode).length() != RECLAIM_CODE_LENGTH) {
            throw new OrderReclaimCodeInvalidException();
        }

        if (!order.getReclaimCode().equals(reclaimCode)) {
            throw new OrderReclaimCodeNotMatchesException();
        }
    }


    public Integer generateReclaimCode() {
        Random random = new Random();
        int min = (int) Math.pow(10, RECLAIM_CODE_LENGTH - 1);
        int max = (int) Math.pow(10, RECLAIM_CODE_LENGTH) - 1;
        return min + random.nextInt(max - min + 1);
    }

    private static void validateOrderStatusIsReady(Order order) {
        if(order.getStatus() != OrderStatus.READY){
            throw new OrderNotReadyException();
        }
    }

    private static void validateOrderStatusNotPending(Order order) {
        if(order.getStatus() != OrderStatus.PENDING){
            throw new OrderNotPendingException();
        }
    }

    private static void validateIfEmployeeCanWorksInThatOrder(Order order, Employee employee) {
        if(!Objects.equals(order.getRestaurant().getId(), employee.getRestaurantId())){
            throw new UnauthorizedAccessException();
        }
    }

    private Employee getAuthenticatedEmployee(){
        Long userId = userAuthenticationPort.getAuthenticatedUserId();
        return userConnectionPort.findEmployeeByUserId(userId).orElseThrow();
    }


}
