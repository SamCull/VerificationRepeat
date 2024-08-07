package cm;
import java.util.HashSet;
import java.util.Set;
public class Gate {
    private static Set<Integer> existingGateIds = new HashSet<>();
    private Integer gateId;
    private String location;

    public Gate(Integer gateId, String location) {
        if (gateId == null || gateId < 0) {
            throw new IllegalArgumentException("Gate ID must be a non-negative integer");
        }
        if (location == null || location.isEmpty()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }
        if (existingGateIds.contains(gateId)) {
            throw new IllegalArgumentException("Gate ID cannot be a duplicate ID");
        }

            this.gateId = gateId;
            this.location = location;
            existingGateIds.add(gateId);
        }
        public Integer getGateId () {
            return gateId;
        }

        public void setGateId (Integer gateId){
            if (gateId == null || gateId < 0) {
                throw new IllegalArgumentException("Gate ID must be a non-negative integer");
            }
            if (existingGateIds.contains(gateId)) {
                throw new IllegalArgumentException("Gate ID cannot be a duplicate ID");
            }
            existingGateIds.remove(this.gateId);
            this.gateId = gateId;
            existingGateIds.add(gateId);
        }
        public String getLocation () {
            return location;
        }

        public void setLocation (String location){
            if (location == null || location.isEmpty()) {
                throw new IllegalArgumentException("Location cannot be null or empty");
            }
            this.location = location;
        }

    // Method to reset the set of existing gate IDs
    public static void resetGateIds() {
        existingGateIds.clear();
    }
    }
