import java.util.concurrent.TimeUnit;

public class SlidingWindow {
    private Integer currentRequestCount;
    private Integer previousWindowRequestCount;
    private Long currentWindowStart;


    public SlidingWindow(Integer currentRequestCount, Integer previousWindowRequestCount, Long currentWindowStart) {
        this.currentRequestCount = currentRequestCount;
        this.previousWindowRequestCount = previousWindowRequestCount;
        this.currentWindowStart = currentWindowStart;
    }

    public Integer getCurrentRequestCount() {
        return currentRequestCount;
    }

    public Integer getPreviousWindowRequestCount() {
        return previousWindowRequestCount;
    }

    public Long getCurrentWindowStart() {
        return currentWindowStart;
    }

    public void incrementRequest(){
        this.currentRequestCount += 1;
    }

    public void reset(final Integer refillRequestCounts , final Integer previousWindowRequests, final Long thisWindowStart ){
        this.currentRequestCount = refillRequestCounts;
        this.previousWindowRequestCount = previousWindowRequests;
        this.currentWindowStart = thisWindowStart;
    }
}
