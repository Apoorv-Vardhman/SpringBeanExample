package com.springbeans.beans;

/**
 * @author : Apoorva Raj
 * @mailto : apoorv.vardhman@gmail.com
 * @LinkedIn : apoorv-vardhman
 **/
public class MessageWriter {
    private IMessageConverter messageConverter;

    public void writeMessage(String message){
        System.out.println(messageConverter.convert(message));
    }

    public void setMessageConverter(IMessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }
}
