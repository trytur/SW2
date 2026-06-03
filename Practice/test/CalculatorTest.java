import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
// team member test
public class CalculatorTest {
    @Test
    void add_returnsSum() {
        Calculator calculator = new Calculator();
        assertEquals(5, calculator.add(2, 3));
    }
}
