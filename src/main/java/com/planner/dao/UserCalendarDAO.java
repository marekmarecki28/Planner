package com.planner.dao;

import com.planner.entities.UserCalendar;

public interface UserCalendarDAO {
	public UserCalendar findUserCalendar(Long calendarId);
	public void updateUserCalendar(UserCalendar calendar);
}
