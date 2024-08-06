package cm;

public class Gate {
    private Integer gateId;
    private String location;

    public Gate(Integer gateId, String location) {
        if (gateId == null || gateId < 0) {
            throw new IllegalArgumentException("Gate ID must be a non-negative integer");
        }
        if (location == null || location.isEmpty()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }

        this.gateId = gateId;
        this.location = location;
    }
    public Integer getGateId() {
        return gateId;
    }

    public void setGateId(Integer gateId) {
        if (gateId == null || gateId < 0) {
            throw new IllegalArgumentException("Gate ID must be a non-negative integer");
        }
        this.gateId = gateId;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if (location == null || location.isEmpty()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }
        this.location = location;
    } 
}
