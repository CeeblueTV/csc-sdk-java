package com.ceeblue.streamingcloud.sdk.streams.output.models.connection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = HttpConnectionDeserializer.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Connection {

}
