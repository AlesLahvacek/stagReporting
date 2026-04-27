package pro1.reports.report4;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.ThesisList;
import pro1.reports.report4.reportDataModel.ThesisDuration;

import java.time.temporal.ChronoUnit;

public class ThesisDurationReporting {
    public static ThesisDuration[] GetReport(DataSource dataSource, String katedra, String[] years) {
        ThesisDuration[] result = new ThesisDuration[years.length];
        for (int i = 0; i < years.length; i++) {
            String year = years[i];
            var json = dataSource.getKvalifikacniPrace(year, katedra);
            var thesisList = new Gson().fromJson(json, ThesisList.class);

            long total = 0;
            long count = 0;
            for (var thesis : thesisList.items) {
                if (thesis.assignmentDate != null && thesis.assignmentDate.isValid()
                        && thesis.submissionDate != null && thesis.submissionDate.isValid()) {
                    long days = ChronoUnit.DAYS.between(
                            thesis.assignmentDate.toLocalDate(),
                            thesis.submissionDate.toLocalDate());
                    total += days;
                    count++;
                }
            }

            long avg = count > 0 ? Math.round((double) total / count) : 0;
            result[i] = new ThesisDuration(year, avg);
        }
        return result;
    }
}
