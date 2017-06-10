/**
 * Copyright(C) 2017 Luvina Software Company
 * manageuser.entities, 2017/05/05, DOVANDUNG
 */
package manageuser.entities;

import java.util.List;

/**
 * YearMonthDay
 * @author dovandung
 *
 */
public class YearMonthDay {
	private int year;
	private int month;
	private int day;

	public YearMonthDay(List<Integer> list) {
		super();
		if (list != null){
			this.year = list.get(0);
			this.month = list.get(1);
			this.day = list.get(2);
		}
	}

	/**
	 * @param birthdayYear
	 * @param birthdayMonth
	 * @param birthdayDay
	 */
	public YearMonthDay(int birthdayYear, int birthdayMonth, int birthdayDay) {
		super();
		this.year = birthdayYear;
		this.month = birthdayMonth;
		this.day = birthdayDay;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

}
