package com.planner.dao;

import com.planner.entities.UserCalendar;

public interface UserCalendarDAO {
	public UserCalendar findUserCalendar(Long calendarId, Long employeeId);
	public void updateUserCalendar(UserCalendar userCalendar);
	public void createUserCalendar(UserCalendar userCalendar);
}
