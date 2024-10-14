package com.plazoleta.plazoleta.infraestructure.out.feign.adapter;

import com.plazoleta.plazoleta.domain.exception.ExternalConnectionException;
import com.plazoleta.plazoleta.domain.model.external.User;
import com.plazoleta.plazoleta.domain.spi.IMessagerConnectionPort;
import com.plazoleta.plazoleta.infraestructure.out.feign.client.IMessageConnectionFeignClient;
import com.plazoleta.plazoleta.infraestructure.out.feign.model.MessageRequestDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class MessagerConnectionFeignAdapter implements IMessagerConnectionPort {

    private final IMessageConnectionFeignClient messageConnectionFeignClient;

    @Override
    public void sendNotifySMSOrderReady(String phoneNumber, String message) {
        try {
            messageConnectionFeignClient.sendMessageSMS(new MessageRequestDto(phoneNumber, message));
        }catch (Exception ex) {
            throw new ExternalConnectionException();
        }
    }
}
