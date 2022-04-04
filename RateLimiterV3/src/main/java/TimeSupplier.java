import java.util.function.Supplier;

public class TimeSupplier implements Supplier<Long> {
    @Override
    public Long get() {
        return System.currentTimeMillis();
    }
}
