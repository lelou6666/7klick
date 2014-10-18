package com.sevenklick.common.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sevenklick.common.util.helpers.CirrusDateUtil;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Used to serialize Java.util.Date, which is not a common JSON
 * type, so we have to create a custom serialize method;.
 *
 * @author Pierre Petersson
 */
@Component
public class JsonDateSerializer extends JsonSerializer<Date>{


    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        SimpleDateFormat dateFormat = CirrusDateUtil.getLocalizedDateFormatForContext();
        gen.writeString(dateFormat.format(date));
    }

}
