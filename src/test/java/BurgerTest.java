import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Mockito;
import praktikum.*;
import static org.junit.Assert.*;

public class BurgerTest {
    Burger burger;
    Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "ingredientName", 1f);
    Ingredient secondIngredient = new Ingredient(IngredientType.FILLING, "secondIngredientName", 1f);

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Mock
    Bun bun;

    @Test
    public void checkBunIsSet() {
        burger.setBuns(bun);
        Bun result = burger.bun;
        assertEquals("Значение переменной bun не соответствует", bun, result);
    }

    @Test
    public void checkIngredientIsAdded() {
        burger.addIngredient(ingredient);
        Boolean result = burger.ingredients.contains(ingredient);
        assertEquals("Добавленный ингредиент не был добавлен", true, result);
    }

    @Test
    public void checkIngredientIsRemoved() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        Boolean result = burger.ingredients.contains(ingredient);
        assertEquals("Добавленный ингредиент не был удалён", false, result);
    }

    @Test
    public void checkIngredientIsMoved() {
        burger.addIngredient(secondIngredient);
        burger.addIngredient(ingredient);
        burger.moveIngredient(0, 1);
        Ingredient result = burger.ingredients.get(0);
        assertEquals("ингредиент не был перемещён на нужный индекс", ingredient, result);
    }

    @Test
    public void checkCorrectPriceIsReturned() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);
        Mockito.when(bun.getPrice()).thenReturn(0.5f);
        float result = burger.getPrice();
        assertEquals(3f, result,0);
    }

    @Test
    public void checkCorrectReceiptIsReturned() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);
        Mockito.when(bun.getName()).thenReturn("bunName");
        Mockito.when(bun.getPrice()).thenReturn(0.5f);
        String result = burger.getReceipt();
        String expectedResult = String.format(
                "(==== %s ====)%n"
                + "= %s %s =%n"
                + "= %s %s =%n"
                + "(==== %s ====)%n"
                +  "%nPrice: %f%n",
                "bunName", "sauce","ingredientName", "filling","secondIngredientName", "bunName", 3f);
        assertEquals(expectedResult, result);
    }
}