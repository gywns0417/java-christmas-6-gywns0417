package christmas;

import christmas.controller.EventPlannerController;

public class Application {
    public static void main(String[] args) {
        EventPlannerFactory factory = new EventPlannerFactory();
        EventPlannerController controller = factory.createEventPlannerController();
        controller.run();
    }
}
