import org.junit.Test;
import praktikum.*;
import static org.junit.Assert.*;

public class BunNameTest {

    @Test
    public void checkCorrectBunNameIsReturned(){
        Bun bun = new Bun ("BunName", 1.5f);
        String result = bun.getName();
        assertEquals("Ожидается другое значение ", "BunName", result);
    }

}