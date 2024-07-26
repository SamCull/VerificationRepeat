package cm;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Stay {
    private int entryGateId;
    private int exitGateId;
    private LocalDateTime entryDateTime;
    private LocalDateTime exitDateTime;
    private BigDecimal charge;
    private Integer carParkId;
    private Integer exitCarParkId;

    public Stay(int entryGateId, int exitGateId, LocalDateTime entryDateTime, LocalDateTime exitDateTime, BigDecimal charge) {
        if (entryDateTime == null || exitDateTime == null) {
            throw new NullPointerException("Entry or exit date time cannot be null");
        }
        if (entryDateTime.isAfter(exitDateTime)) {
            throw new IllegalArgumentException("Entry date time must be before exit date time");
        }
        if (charge.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Charge cannot be negative");
        }

        this.entryGateId = entryGateId;
        this.exitGateId = exitGateId;
        this.entryDateTime = entryDateTime;
        this.exitDateTime = exitDateTime;
        this.charge = charge;
    }
}