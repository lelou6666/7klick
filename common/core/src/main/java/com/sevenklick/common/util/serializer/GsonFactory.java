package com.sevenklick.common.util.serializer;


import com.google.gson.*;
import com.sevenklick.common.util.helpers.CirrusDateUtil;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by piepet on 2013-12-15.
 */
public class GsonFactory {
    public static Gson gsonInstance(){
    GsonBuilder builder = new GsonBuilder();
    // Register an adapter to manage the date types as long values
    builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

            SimpleDateFormat dateFormat = CirrusDateUtil.getLocalizedDateFormatForContext();
            if(dateFormat==null){
                dateFormat=CirrusDateUtil.getLocalizedDateFormat(Locale.getDefault().getCountry(),Locale.getDefault().getLanguage());
            }
            Date date=null;
            try {
                date= dateFormat.parse(json.getAsJsonPrimitive().getAsString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        }
    });
    Gson gson = builder.create();
        return gson;
    }
}
