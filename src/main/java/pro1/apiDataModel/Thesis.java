package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Thesis {
    @SerializedName("datumZadani")
    public StagDate assignmentDate;

    @SerializedName("datumOdevzdani")
    public StagDate submissionDate;
}
