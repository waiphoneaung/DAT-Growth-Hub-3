package com.g3.elis.dto.form;

public class CourseMaterialDto {

	private boolean status;
    private String title;

    private String content;
	private int index;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}


	public CourseMaterialDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CourseMaterialDto(boolean status, String title, String content, int index) {
		super();
		this.status = status;
		this.title = title;
		this.content = content;
		this.index = index;
	}

	@Override
	public String toString() {
		return "CourseMaterialDto [status=" + status + ", title=" + title + ", content=" + content + ", index=" + index
				+ "]";
	}
}


