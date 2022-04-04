import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class SlidingWindowRateLimiterTest {

    @Test
    public void slidingWin_rl()
    {

        RateLimiterConfiguration rateLimiterConfiguration = new RateLimiterConfiguration( 5, TimeUnit.MILLISECONDS,3 );
        RateLimiter rateLimiter = new SlidingWindowRateLimiter(rateLimiterConfiguration , new MockTimeSupplier());
        Customer customer = new Customer("A");

        //Boolean res = rateLimiter.allow(customer);
        //Assertions.assertEquals(false, res);

        List<Boolean> response =  new ArrayList<>();
        response.add(rateLimiter.allow(customer));
        response.add(rateLimiter.allow(customer));
        response.add(rateLimiter.allow(customer));
        response.add(rateLimiter.allow(customer));
        response.add(rateLimiter.allow(customer));
        response.add(rateLimiter.allow(customer));
        response.add(rateLimiter.allow(customer));
        response.add(rateLimiter.allow(customer));
        response.add(rateLimiter.allow(customer));
        response.add(rateLimiter.allow(customer));


        System.out.println(response);
    }

}