import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.*;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BunParamsTest {
    private final String name;
    private final float price;

    public BunParamsTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1}")
    public static Object[][] getBunData(){
        return new Object[][]{
                {"GreyBun", 1.0f},
                {"BlackBun", 2.5f},
                {"BlackBun2", 0.9f},
                {"Черная булка", 1.1f},
                {"1233123", 0.0f},
                {"№@@@@@@@", 0f},
                {"BlackBun", -1f},
                {"BlackBun", -1.0f},
                {"BlackBun", -0.5f},
                {"BlackBun", -1.5f},
                {"BlackBun", -2.5f},
                {"BlackBun", -Float.MAX_VALUE},
                {"BlackBun", Float.MAX_VALUE},
        };
    }

    @Test
    public void checkCorrectBunPriceIsReturned(){
        Bun bun = new Bun (name, price);
        float result = bun.getPrice();
        assertEquals("Ожидается другое значение",price, result, 0);
    }
}