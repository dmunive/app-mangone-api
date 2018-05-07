package com.goone.mangone.api.rest.response;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ResponseSerializer extends StdSerializer<GenericResponse> {

	public ResponseSerializer() {
		this(null);
	}

	public ResponseSerializer(Class<GenericResponse> t) {
		super(t);
	}

	@Override
	public void serialize(GenericResponse gr, JsonGenerator jg, SerializerProvider sp) throws IOException {
		jg.writeStartObject();

		StatusResponse status = gr.getStatus();

		if (status != null) {
			jg.writeFieldName(GenericResponse.STATUS);
			jg.writeString(gr.getStatus().getValue());
		}

		if (status == StatusResponse.SUCCESS) {
			if (gr.getData() != null) {
				jg.writeFieldName(GenericResponse.MESSAGE);
				jg.writeObject(gr.getMessage());
				jg.writeFieldName(GenericResponse.DATA);
				jg.writeObject(gr.getData());
			}else{
				jg.writeFieldName(GenericResponse.MESSAGE);
				jg.writeObject(gr.getMessage());
				jg.writeFieldName(GenericResponse.DATA);
				jg.writeObject(null);
			}
		}

		if (status == StatusResponse.FAIL) {
			if (gr.getData() != null) {
				jg.writeFieldName(GenericResponse.DATA);
				jg.writeObject(gr.getData());
			}
		}

		if (status == StatusResponse.ERROR) {
			if (gr.getMessage() != null) {
				jg.writeFieldName(GenericResponse.MESSAGE);
				jg.writeString(gr.getMessage());
			}
		}
		jg.writeEndObject();
	}
}
