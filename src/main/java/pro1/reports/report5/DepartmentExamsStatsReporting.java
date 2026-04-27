package pro1.reports.report5;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.ExamsList;
import pro1.reports.report5.reportDataModel.DepartmentExamsStats;

import java.util.TreeSet;

public class DepartmentExamsStatsReporting {
	public static DepartmentExamsStats GetReport(DataSource dataSource, String katedra) {
		var examsJson = dataSource.getTerminyZkousek2(katedra);
		var examsList = new Gson().fromJson(examsJson, ExamsList.class);

		long realizedExamsCount = 0;
		TreeSet<Long> uniqueTeacherIds = new TreeSet<>();

		for (var exam : examsList.items) {
			if (exam.studentsCount != null) {
				try {
					if (Integer.parseInt(exam.studentsCount) > 0) {
						realizedExamsCount++;
					}
				} catch (NumberFormatException ignored) {
					// Invalid occupancy values are ignored.
				}
			}

			if (exam.teacherId != null) {
				uniqueTeacherIds.add(exam.teacherId);
			}
		}

		long[] teacherIds = uniqueTeacherIds.stream().mapToLong(Long::longValue).toArray();
		return new DepartmentExamsStats(realizedExamsCount, teacherIds);
	}

}
