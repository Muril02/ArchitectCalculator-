package almeida.murilo.Calculos;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Calculos {

    public static BigDecimal calcTelhados(BigDecimal inc, BigDecimal larTelhado){
        return inc.multiply(larTelhado).divide(BigDecimal.valueOf(100));
    }

    public static BigDecimal calcEspelhos(BigDecimal altEspelho, BigDecimal altura){
        return altura.divide(altEspelho);
    }


}
