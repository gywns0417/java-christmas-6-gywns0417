package christmas.service;

import christmas.domain.date.VisitDate;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DateService {
    public VisitDate getDateInput(Supplier<String> inputSupplier, Runnable messagePrinter,
                                  Consumer<String> errorMessagePrinter) {
        while (true) {
            try {
                messagePrinter.run();
                return new VisitDate(inputSupplier.get());
            } catch (IllegalArgumentException e) {
                errorMessagePrinter.accept(e.getMessage());
            }
        }
    }
}
