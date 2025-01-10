// BasePizza interface (Component)
interface BasePizza {
    String getDescription();
    double getCost();
}

// Concrete Class: Farmhouse Pizza
class Farmhouse implements BasePizza {
    @Override
    public String getDescription() {
        return "Farmhouse Pizza";
    }

    @Override
    public double getCost() {
        return 250.0;
    }
}

// Abstract Decorator: ToppingDecorator
abstract class ToppingDecorator implements BasePizza {
    // Has-a relationship: Decorator has a BasePizza
    protected BasePizza pizza;

    public ToppingDecorator(BasePizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription(); // Delegate description to wrapped pizza
    }

    @Override
    public double getCost() {
        return pizza.getCost(); // Delegate cost to wrapped pizza
    }
}

// Concrete Decorator: Cheese Topping
class CheeseTopping extends ToppingDecorator {
    public CheeseTopping(BasePizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Cheese"; // Add cheese description
    }

    @Override
    public double getCost() {
        return super.getCost() + 50.0; // Add cheese cost
    }
}

// Concrete Decorator: Veggie Topping
class VeggieTopping extends ToppingDecorator {
    public VeggieTopping(BasePizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Veggies"; // Add veggie description
    }

    @Override
    public double getCost() {
        return super.getCost() + 30.0; // Add veggie cost
    }
}

// Main Class to Test Decorator Pattern
public class DecoratorPatternDemo {
    public static void main(String[] args) {
        // Create a Farmhouse pizza
        BasePizza pizza = new Farmhouse();

        System.out.println("Base Pizza:");
        System.out.println("Description: " + pizza.getDescription());
        System.out.println("Cost: " + pizza.getCost() + "\n");

        // Add cheese topping
        pizza = new CheeseTopping(pizza);

        System.out.println("After Adding Cheese:");
        System.out.println("Description: " + pizza.getDescription());
        System.out.println("Cost: " + pizza.getCost() + "\n");

        // Add veggie topping
        pizza = new VeggieTopping(pizza);

        System.out.println("After Adding Veggies:");
        System.out.println("Description: " + pizza.getDescription());
        System.out.println("Cost: " + pizza.getCost());
    }
}