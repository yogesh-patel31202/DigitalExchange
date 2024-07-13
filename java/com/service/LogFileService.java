package com.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import javax.servlet.ServletConfig;

public class LogFileService {
	public static String getEndDates(String formate, int mark) {

		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		String date1 = null, date2 = null;
		System.out.println(mark);
		SimpleDateFormat dateFormat = new SimpleDateFormat(formate);

		switch (mark) {

		case 1: {
			// ------ get today's date

			cal.set(Calendar.DATE, cal.get(Calendar.DATE));
			date1 = new SimpleDateFormat(formate).format(cal.getTime());
			System.out.println(new SimpleDateFormat(formate).format(cal
					.getTime()));
			cal2.set(Calendar.DATE, cal2.get(Calendar.DATE));
			date2 = new SimpleDateFormat(formate).format(cal2.getTime());
			System.out.println(new SimpleDateFormat(formate).format(cal2
					.getTime()));

			break;
		}
		case 2: {
			// ------ get yesterday's date
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1);
			date1 = new SimpleDateFormat(formate).format(cal.getTime());

			cal2.set(Calendar.DATE, cal2.get(Calendar.DATE) - 1);
			date2 = new SimpleDateFormat(formate).format(cal2.getTime());

			break;
		}
		case 3: {
			// ------ get this week end points
			int dayVal = cal.get(Calendar.DAY_OF_WEEK);
			System.out.println(dayVal);

			/*
			 * System.out.println("a "+ Calendar.DATE); System.out.println("b "+
			 * cal.get(Calendar.DATE)); System.out.println("c "+
			 * (cal.get(Calendar.DATE) - (dayVal - 1)));
			 * System.out.println("d1 "+ cal2.get(Calendar.DATE));
			 * System.out.println("d "+ (cal2.get(Calendar.DATE) - (dayVal -
			 * 7))); System.out.println("f "+ cal.getTime());
			 * System.out.println("g "+ cal2.getTime());
			 */

			cal.set(Calendar.DATE, cal.get(Calendar.DATE) - (dayVal - 1));
			date1 = new SimpleDateFormat(formate).format(cal.getTime());

			cal2.set(Calendar.DATE, cal2.get(Calendar.DATE) - (dayVal - 7));
			date2 = new SimpleDateFormat(formate).format(cal2.getTime());

			break;
		}
		case 4: {
			// ------ get this week's end points
			int dayVal = cal.get(Calendar.DAY_OF_WEEK);
			System.out.println(dayVal);
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) - (7 + dayVal - 1));
			date1 = new SimpleDateFormat(formate).format(cal.getTime());

			cal2.set(Calendar.DATE, cal2.get(Calendar.DATE) - (dayVal));
			date2 = new SimpleDateFormat(formate).format(cal2.getTime());

			break;
		}

		case 5: {
			// ------ get this Month's end points

			cal.set(Calendar.DATE, 1);
			// cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
			date1 = new SimpleDateFormat(formate).format(cal.getTime());

			// cal2.set(Calendar.MONTH, cal2.get(Calendar.MONTH) - 1);
			cal2.set(Calendar.DATE,
					cal2.getActualMaximum(Calendar.DAY_OF_MONTH));

			date2 = new SimpleDateFormat(formate).format(cal2.getTime());

			break;
		}

		case 6: {
			// ------ get this week end points

			cal.set(Calendar.DATE, 1);
			cal.set(Calendar.MONTH, 0);
			date1 = new SimpleDateFormat(formate).format(cal.getTime());

			cal2.set(Calendar.DATE, 31);
			cal2.set(Calendar.MONTH, 11);
			date2 = new SimpleDateFormat(formate).format(cal2.getTime());

			break;
		}
		case 7: {
			// ------ get this Month's end points

			cal2.setTime(new Date()); // Set to current date
			cal2.set(Calendar.DAY_OF_MONTH, cal2.getActualMaximum(Calendar.DAY_OF_MONTH));

			// Set the start date to the first day of the month, one year ago
			cal.setTime(new Date()); // Set to current date
			cal.add(Calendar.YEAR, -1);
			cal.set(Calendar.DAY_OF_MONTH, 1);

			date1 = dateFormat.format(cal.getTime());
			date2 = dateFormat.format(cal2.getTime());

			break;
		}
		case 8: {
		    // ------ get start and end points for January 1 to March 31 of the current year

		    cal.setTime(new Date()); // Set to current date

		    // Set the start date to January 1 of the current year
		    cal.set(Calendar.MONTH, Calendar.JANUARY);
		    cal.set(Calendar.DAY_OF_MONTH, 1);
		    Date startDate = cal.getTime();

		    // Set the end date to March 31 of the current year
		    cal.set(Calendar.MONTH, Calendar.MARCH);
		    cal.set(Calendar.DAY_OF_MONTH, 31);
		    Date endDate = cal.getTime();

		    date1 = dateFormat.format(startDate);
		    date2 = dateFormat.format(endDate);

		    break;
		}
		
		case 9: {
		    // ------ get start and end points for April 1 to June 30 of the current year

		    cal.setTime(new Date()); // Set to current date

		    // Set the start date to April 1 of the current year
		    cal.set(Calendar.MONTH, Calendar.APRIL);
		    cal.set(Calendar.DAY_OF_MONTH, 1);
		    Date startDate = cal.getTime();

		    // Set the end date to June 30 of the current year
		    cal.set(Calendar.MONTH, Calendar.JUNE);
		    cal.set(Calendar.DAY_OF_MONTH, 30);
		    Date endDate = cal.getTime();

		    date1 = dateFormat.format(startDate);
		    date2 = dateFormat.format(endDate);

		    break;
		}
		
		case 10: {
		    // ------ get start and end points for April 1 to June 30 of the current year

		    cal.setTime(new Date()); // Set to current date

		    // Set the start date to April 1 of the current year
		    cal.set(Calendar.MONTH, Calendar.JULY);
		    cal.set(Calendar.DAY_OF_MONTH, 1);
		    Date startDate = cal.getTime();

		    // Set the end date to June 30 of the current year
		    cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		    cal.set(Calendar.DAY_OF_MONTH, 30);
		    Date endDate = cal.getTime();

		    date1 = dateFormat.format(startDate);
		    date2 = dateFormat.format(endDate);

		    break;
		}
		
		case 11: {
		    // ------ get start and end points for April 1 to June 30 of the current year

		    cal.setTime(new Date()); // Set to current date

		    // Set the start date to April 1 of the current year
		    cal.set(Calendar.MONTH, Calendar.OCTOBER);
		    cal.set(Calendar.DAY_OF_MONTH, 1);
		    Date startDate = cal.getTime();

		    // Set the end date to June 30 of the current year
		    cal.set(Calendar.MONTH, Calendar.DECEMBER);
		    cal.set(Calendar.DAY_OF_MONTH, 31);
		    Date endDate = cal.getTime();

		    date1 = dateFormat.format(startDate);
		    date2 = dateFormat.format(endDate);

		    break;
		}


		default:
			break;
		}

		return date1 + "," + date2;
	}
	
	public static String changeFormate(String date, String currentFormate,
			String reqFormate) {

		try {
			// we may write simple date format on place of dateFormate
			DateFormat srcDf = new SimpleDateFormat(currentFormate);
			// parse the date string into Date object
			Date dat = srcDf.parse(date);
			DateFormat destDf = new SimpleDateFormat(reqFormate);
			// format the date into another format
			date = destDf.format(dat);
			//System.out.println("Converted date is : " + date);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String changeFormateOfCurrentDate(String formate) {

		Calendar cal = Calendar.getInstance();
		// Date dat = new Date();
		// DateFormat destDf = new SimpleDateFormat(formate);

		SimpleDateFormat destDf = new SimpleDateFormat(formate);

		// format the date into another format
		// System.out.println("Converted date is : " +
		// destDf.format(cal.getTime()));
		String result = destDf.format(cal.getTime());
		return result;
	}
	
	@SuppressWarnings("resource")
	public static void catchLogFile(Exception e, ServletConfig config)
			throws IOException {
		String date1 = changeFormateOfCurrentDate("yyyy-MM-dd");
		String time = changeFormateOfCurrentDate("HH:mm:ss");
		e.printStackTrace();
		File file = new File(config.getServletContext().getRealPath("/")
				+ "catchLogs/SqlCatchLogFile" + date1 + ".txt"); // Your file

		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fos = new FileOutputStream(file, true);
		PrintStream ps = new PrintStream(fos);
		System.setOut(ps);
		new Scanner(System.in);
		System.out.print(" [" + date1 + " " + time + "] ");
		e.printStackTrace(System.out);
		fos.close();
	}
	
	public static String previousDateString(String dateString)
			throws ParseException {
		// Create a date formatter using your format string
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		// Parse the given date string into a Date object.
		// Note: This can throw a ParseException.
		Date myDate = dateFormat.parse(dateString);

		// Use the Calendar class to subtract one day
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(myDate);
		/* calendar.add(Calendar.DAY_OF_YEAR, -1); */
		calendar.add(Calendar.DAY_OF_YEAR, -1);

		// Use the date formatter to produce a formatted date string
		Date previousDate = calendar.getTime();
		String result = dateFormat.format(previousDate);

		return result;
	}

}
