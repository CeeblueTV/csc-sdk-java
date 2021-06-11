package com.ceeblue.streamingcloud.sdk.streams.push.models.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = OutputDeserializer.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OutputParent {

}
