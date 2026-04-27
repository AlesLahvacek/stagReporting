package pro1.reports.report5.reportDataModel;

public class DepartmentExamsStats {
    public long realizedExamsCount;
    public long[] teacherIds;

    public DepartmentExamsStats(long realizedExamsCount, long[] teacherIds) {
        this.realizedExamsCount = realizedExamsCount;
        this.teacherIds = teacherIds;
    }
}
