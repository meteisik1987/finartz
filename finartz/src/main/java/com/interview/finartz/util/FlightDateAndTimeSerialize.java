package com.interview.finartz.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class FlightDateAndTimeSerialize extends JsonSerializer<Date>  {
	
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat(
            "dd-MM-yyyy HH:mm");

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
	    String dateString = dateFormat.format(value);
	    gen.writeStartObject();
	    gen.writeStringField("dateAndTime",dateString);
	    gen.writeEndObject();
	}

}
