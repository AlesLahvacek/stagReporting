package pro1.apiDataModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StagDate {
    public String value;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.M.yyyy");

    public boolean isValid() {
        if (value == null || value.isBlank()) return false;
        try {
            LocalDate.parse(value.trim(), FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public LocalDate toLocalDate() {
        return LocalDate.parse(value.trim(), FORMATTER);
    }
}
