package com.springbeans.beans;

/**
 * @author : Apoorva Raj
 * @mailto : apoorv.vardhman@gmail.com
 * @LinkedIn : apoorv-vardhman
 **/
public class PdfConverterImpl implements IMessageConverter{
    @Override
    public String convert(String message) {
        return "<pdf>"+message+"</pdf>";
    }
}
