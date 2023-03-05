package pl.sda.orange2.lambda;

@FunctionalInterface
public interface CoffeeMaker {
    String prepare(int water, String coffeeType);
}
