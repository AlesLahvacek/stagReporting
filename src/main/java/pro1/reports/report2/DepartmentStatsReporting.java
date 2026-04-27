package pro1.reports.report2;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.ActionsList;
import pro1.reports.report2.reportDataModel.DepartmentStats;

public class DepartmentStatsReporting {
    public static DepartmentStats GetReport(DataSource dataSource, String rok, String katedra) {
        var actionsListJson = dataSource.getRozvrhByKatedra(rok, katedra);
        var actionsList = new Gson().fromJson(actionsListJson, ActionsList.class);
        return new DepartmentStats(
                maxActionStudentsCount(actionsList),
                emptyActionsCount(actionsList),
                maxTeacherScore(actionsList)
        );
    }

    private static long maxActionStudentsCount(ActionsList actionsList) {
        long max = 0;
        for (var action : actionsList.items) {
            if (action.studentsCount != null && action.studentsCount > max) {
                max = action.studentsCount;
            }
        }
        return max;
    }

    private static long emptyActionsCount(ActionsList actionsList) {
        long count = 0;
        for (var action : actionsList.items) {
            if (action.studentsCount != null && action.studentsCount == 0) {
                count++;
            }
        }
        return count;
    }


    private static long maxTeacherScore(ActionsList actionsList) {
        long max = 0;
        for (var action : actionsList.items) {
            if (action.teacherId == null) {
                continue;
            }
            long score = teacherScore(action.teacherId, actionsList);
            if (score > max) {
                max = score;
            }
        }
        return max;
    }

    private static long teacherScore(long teacherId, ActionsList actionsList) {
        long score = 0;
        for (var action : actionsList.items) {
            if (action.teacherId != null && action.teacherId == teacherId && action.studentsCount != null) {
                score += action.studentsCount;
            }
        }
        return score;
    }
}
