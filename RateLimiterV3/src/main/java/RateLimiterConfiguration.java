import java.util.concurrent.TimeUnit;

public class RateLimiterConfiguration {

    private final Integer timeWindow;
    private final TimeUnit timeUnit;
    private final Integer maxAllowedRequests;

    public RateLimiterConfiguration(Integer timeWindow, TimeUnit timeUnit, Integer maxAllowedRequests) {
        this.timeWindow = timeWindow;
        this.timeUnit = timeUnit;
        this.maxAllowedRequests = maxAllowedRequests;
    }

    public Integer getTimeWindow() {
        return timeWindow;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public Integer getMaxAllowedRequests() {
        return maxAllowedRequests;
    }
}
