import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class SlidingWindowRateLimiter implements RateLimiter {

    private final Integer maxAllowedRequest;
    private final Long timeWindowWidth;
    private final Map<Customer,SlidingWindow> customerSlidingWindowMap;
    private final Supplier<Long> timeSupplier;



    public SlidingWindowRateLimiter(RateLimiterConfiguration configuration, Supplier<Long> timeSupplier) {
        this.maxAllowedRequest = configuration.getMaxAllowedRequests();
        this.timeWindowWidth = TimeUnit.MILLISECONDS.convert(configuration.getTimeWindow(),configuration.getTimeUnit());
        this.customerSlidingWindowMap = new HashMap<>();
        this.timeSupplier = timeSupplier;
    }

    @Override
    public Boolean allow(Customer customer) {
        Long thisRequestsTimeStamp = timeSupplier.get();

        SlidingWindow customerWindow = customerSlidingWindowMap.getOrDefault(customer,new SlidingWindow(0,maxAllowedRequest,thisRequestsTimeStamp));

        if( thisRequestsTimeStamp - customerWindow.getCurrentWindowStart() < timeWindowWidth ){
            Long expectedCount  = customerWindow.getPreviousWindowRequestCount() * (( thisRequestsTimeStamp - customerWindow.getCurrentWindowStart() ) / timeWindowWidth)  + customerWindow.getCurrentRequestCount();
            if( expectedCount < maxAllowedRequest ){
                customerWindow.incrementRequest();
                customerSlidingWindowMap.put(customer,customerWindow);
                return true;
            }
        }
        else{
            customerWindow.reset(
                    0,
                    customerWindow.getCurrentRequestCount(),
                    customerWindow.getCurrentWindowStart()+timeWindowWidth );
            customerWindow.incrementRequest();
            customerSlidingWindowMap.put(customer,customerWindow);
            return true;
        }
        return false;
    }
}
