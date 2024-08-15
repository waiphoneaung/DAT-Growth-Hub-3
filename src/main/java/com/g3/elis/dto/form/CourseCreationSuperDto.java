package com.g3.elis.dto.form;

import java.util.List;

public class CourseCreationSuperDto 
{
	private CourseDto courseDto;
	private List<CourseModuleDto> courseModuleDtoList;
	private List<CourseMaterialDto> courseMaterialDtoList;
	private List<CourseAssignmentDto> courseAssignmentDtoList;
	private List<QuestionDto> questionDtoList;
	private List<AnswerDto> answerDtoList;
	private InputFileDto inputFileDto;
	private Integer courseId;
	private String action;
	
	public CourseCreationSuperDto(){
		
	}

	public CourseDto getCourseDto() {
		return courseDto;
	}

	public void setCourseDto(CourseDto courseDto) {
		this.courseDto = courseDto;
	}

	public List<CourseModuleDto> getCourseModuleDtoList() {
		return courseModuleDtoList;
	}

	public void setCourseModuleDtoList(List<CourseModuleDto> courseModuleDtoList) {
		this.courseModuleDtoList = courseModuleDtoList;
	}

	public List<CourseMaterialDto> getCourseMaterialDtoList() {
		return courseMaterialDtoList;
	}

	public void setCourseMaterialDtoList(List<CourseMaterialDto> courseMaterialDtoList) {
		this.courseMaterialDtoList = courseMaterialDtoList;
	}

	public List<CourseAssignmentDto> getCourseAssignmentDtoList() {
		return courseAssignmentDtoList;
	}

	public void setCourseAssignmentDtoList(List<CourseAssignmentDto> courseAssignmentDtoList) {
		this.courseAssignmentDtoList = courseAssignmentDtoList;
	}

	public List<QuestionDto> getQuestionDtoList() {
		return questionDtoList;
	}

	public void setQuestionDtoList(List<QuestionDto> questionDtoList) {
		this.questionDtoList = questionDtoList;
	}

	public List<AnswerDto> getAnswerDtoList() {
		return answerDtoList;
	}

	public void setAnswerDtoList(List<AnswerDto> answerDtoList) {
		this.answerDtoList = answerDtoList;
	}

	public InputFileDto getInputFileDto() {
		return inputFileDto;
	}

	public void setInputFileDto(InputFileDto inputFileDto) {
		this.inputFileDto = inputFileDto;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public CourseCreationSuperDto(CourseDto courseDto, List<CourseModuleDto> courseModuleDtoList,
			List<CourseMaterialDto> courseMaterialDtoList, List<CourseAssignmentDto> courseAssignmentDtoList,
			List<QuestionDto> questionDtoList, List<AnswerDto> answerDtoList, InputFileDto inputFileDto,
			Integer courseId, String action) {
		super();
		this.courseDto = courseDto;
		this.courseModuleDtoList = courseModuleDtoList;
		this.courseMaterialDtoList = courseMaterialDtoList;
		this.courseAssignmentDtoList = courseAssignmentDtoList;
		this.questionDtoList = questionDtoList;
		this.answerDtoList = answerDtoList;
		this.inputFileDto = inputFileDto;
		this.courseId = courseId;
		this.action = action;
	}


}
