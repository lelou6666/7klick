package com.sevenklick.common.core.helpers;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by pierre.petersson on 09/06/2014.
 */
@Component
public class CalculatorUtil {
    public BigDecimal add(BigDecimal source, BigDecimal value ){
        BigDecimal decimalValue=BigDecimal.ZERO;
        if(source==null) {
            source = new BigDecimal(0);
        }
            if (value == null) {
                decimalValue=source.add(BigDecimal.ZERO);
            } else {
                decimalValue= source.add(value);
            }

        return  decimalValue;
    }
    public BigDecimal subtract(BigDecimal source, BigDecimal value ){
        BigDecimal decimalValue=BigDecimal.ZERO;
        if(source==null) {
            source = new BigDecimal(0);
        }
            if (value == null) {
                decimalValue=source.subtract(BigDecimal.ZERO);
            } else {
                decimalValue= source.subtract(value);
            }

        return  decimalValue;
    }
    public BigInteger add(BigInteger source, BigInteger value ){
        BigInteger intValue=BigInteger.ZERO;
        if(source==null) {
            source = BigInteger.ZERO;
        }
        if (value == null) {
            intValue=source.add(BigInteger.ZERO);
        } else {
            intValue= source.add(value);
        }

        return  intValue;
    }
    public BigInteger subtract(BigInteger source, BigInteger value ){
        BigInteger intValue=BigInteger.ZERO;
        if(source==null) {
            source = BigInteger.ZERO;
        }
        if (value == null) {
            intValue=source.subtract(BigInteger.ZERO);
        } else {
            intValue= source.subtract(value);
        }

        return  intValue;
    }

}
