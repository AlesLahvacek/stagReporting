package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Action
{
	@SerializedName("obsazeni")
	public Long studentsCount;

	@SerializedName("ucitIdno")
	public Long teacherId;

	@SerializedName("denZkr")
	public String dayAbbr;
}
