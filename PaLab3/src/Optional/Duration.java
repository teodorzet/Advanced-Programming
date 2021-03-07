package Optional;

import java.time.LocalTime;

public class Duration {
    private LocalTime openingDuration;

    public Duration(LocalTime openingDuration) {
        this.openingDuration = openingDuration;
    }
    public LocalTime getOpeningDuration() {
        return openingDuration;
    }
}
