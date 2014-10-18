package com.sevenklick.common.util.helpers;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by lars.vateman on 2014-03-26.
 */
public class CirrusDateUtil {
    private static final DateFormat DF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    /*
     * Hidden constructor
     */
    private CirrusDateUtil() {
    }

    public static Date getFirstDateInPreviousMonth() {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.set(Calendar.DATE, 1);
        aCalendar.add(Calendar.DAY_OF_MONTH, -1);
        aCalendar.set(Calendar.DATE, 1);
        return aCalendar.getTime();

    }
    public static Date getLastDateInPreviousMonth(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        return  cal.getTime();
    }
    /**
     * Get current floor date
     * @return
     */
    public static Date getFloor() {
        return addDays(0, true, null);
    }

    /**
     * Get floor date +- days from current date
     * @param daysToAdd
     * @return
     */
    public static Date getFloor(int daysToAdd) {
        return addDays(daysToAdd, true, null);
    }

    /**
     * Get floor date +- days from a Date
     * @param daysToAdd
     * @param date
     * @return
     */
    public static Date getFloor(int daysToAdd, Date date) {
        return addDays(daysToAdd, true, date);
    }

    /**
     * Add -+ days to current date
     * @param daysToAdd
     * @return
     */
    public static Date addDays(int daysToAdd) {
        return addDays(daysToAdd, false, null);
    }

    /**
     * Add +- days to current date and optionally floor it
     * @param daysToAdd
     * @param floorIt
     * @return
     */
    public static Date addDays(int daysToAdd, boolean floorIt) {
        return addDays(daysToAdd, floorIt, null);
    }

    /**
     * Add +- days to a Date and optionally floor it
     * @param daysToAdd
     * @param floorIt
     * @param date
     * @return
     */
    public static Date addDays(int daysToAdd, boolean floorIt, Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        if (daysToAdd != 0) {
            cal.add(Calendar.DATE, daysToAdd);
        }
        if (floorIt) {
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
        }
        return new Date(cal.getTime().getTime());
    }

    /**
     * Convert a XMLGregorianCalendar to a Date
     * @param xmlGregorianCalendar
     * @return
     */
    public static Date convert(XMLGregorianCalendar xmlGregorianCalendar) {
        return xmlGregorianCalendar == null ? null : new Date(xmlGregorianCalendar.toGregorianCalendar().getTime().getTime());
    }

    /**
     * Convert a Date to a XMLGregorianCalendar
     * @param date
     * @return
     * @throws javax.xml.datatype.DatatypeConfigurationException
     */
    public static XMLGregorianCalendar convert(Date date) throws DatatypeConfigurationException {
        if (date == null) {
            return null;
        }
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
    }

    /**
     * Get current Date as ISO-8601 string
     * @return
     */
    public static String getIso8601() {
        return toIso8601(new Date());
    }

    /**
     * Convert a Date to ISO-8601 string
     * @param date
     * @return
     */
    public static String toIso8601(Date date) {
        return (date == null) ? null : DF.format(date);
    }

    public static SimpleDateFormat getLocalizedDateFormatForContext(){
        return getLocalizedDateFormat(ContextHandler.get().getLangaugeCode(),ContextHandler.get().getCountryCode());
    }
    public static SimpleDateFormat getLocalizedDateFormatForContext(int style){
        return getLocalizedDateFormat(ContextHandler.get().getLangaugeCode(),ContextHandler.get().getCountryCode(), style);
    }

    private static SimpleDateFormat getLocalizedDateFormat(Locale locale, int style){
        DateFormat localeDateFormat= DateFormat.getDateInstance(style,locale);
        SimpleDateFormat simpleDateFormat =null;
        if (localeDateFormat instanceof SimpleDateFormat)
        {
            simpleDateFormat = (SimpleDateFormat)localeDateFormat;
        }
        return simpleDateFormat;

    }
    public static SimpleDateFormat getLocalizedDateFormat(String languageCode, String countryCode){
        Locale locale = new Locale(languageCode, countryCode);
        return getLocalizedDateFormat(locale, DateFormat.SHORT);
    }
    public static SimpleDateFormat getLocalizedDateFormat(String languageCode, String countryCode, int style){
        Locale locale = new Locale(languageCode, countryCode);
        return getLocalizedDateFormat(locale, style);
    }
    public static String getLocalizedPattern(SimpleDateFormat simpleDateFormat){
        return simpleDateFormat.toLocalizedPattern();
    }

}
