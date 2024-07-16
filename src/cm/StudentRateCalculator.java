package cm;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StudentRateCalculator extends SimpleRateCalculator {
    private final BigDecimal discountPercentage = BigDecimal.valueOf(33/100.0);
    private final BigDecimal discountThreshold = BigDecimal.valueOf(5.5);
    @Override
    public BigDecimal calculate(Period periodStay, int normalRateHours, int reducedRateHours, BigDecimal normalRate, BigDecimal reducedRate) {
        BigDecimal total = super.calculate(periodStay, normalRateHours, reducedRateHours, normalRate, reducedRate);
        if(total.compareTo(discountThreshold) > 0) {
            BigDecimal discount = total.subtract(discountThreshold).multiply(discountPercentage);
            return total.subtract(discount).setScale(2, RoundingMode.UP);
        }
        return total;
    }
}
