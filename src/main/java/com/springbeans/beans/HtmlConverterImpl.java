package com.springbeans.beans;

/**
 * @author : Apoorva Raj
 * @mailto : apoorv.vardhman@gmail.com
 * @LinkedIn : apoorv-vardhman
 **/
public class HtmlConverterImpl implements IMessageConverter{
    @Override
    public String convert(String message) {
        return "<html>"+message+"</html>";
    }
}
