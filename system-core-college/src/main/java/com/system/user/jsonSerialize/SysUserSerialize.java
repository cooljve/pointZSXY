package com.system.user.jsonSerialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.system.user.entity.SysUser;

import java.io.IOException;


public class SysUserSerialize extends JsonSerializer<SysUser> {
    public void serialize(SysUser value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getId().longValue());
        jgen.writeStringField("username", value.getUsername());
//	    jgen.writeStringField("truename", value.getTruename());
        jgen.writeStringField("code", value.getCode());
        jgen.writeStringField("sex", value.getSex() + "");
        jgen.writeStringField("tel", value.getTel());
        jgen.writeEndObject();
    }
}
