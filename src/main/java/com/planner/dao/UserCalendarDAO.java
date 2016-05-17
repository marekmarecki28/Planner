package com.planner.dao;

import java.util.List;

import com.planner.entities.UserCalendar;

public interface UserCalendarDAO {
	public UserCalendar findUserCalendar(Long calendarId, Long employeeId, Long userCalendarId);
	public void updateUserCalendar(UserCalendar userCalendar);
	public void createUserCalendar(UserCalendar userCalendar);
	public List<UserCalendar> getUserWorkHoursWeekly(Integer week, Long employeeId);
	public void deleteUserCalendarById(Long id);
	public void deleteUserCalendarEmployee(Long calendarId, Long employeeId);
	public void deleteUserCalendarEmployee(Long calendarId, Long employeeId, Long userCalendarId);
}
