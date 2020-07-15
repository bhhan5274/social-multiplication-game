package com.bhhan.gamification.client;

import com.bhhan.gamification.client.dto.MultiplicationResultAttempt;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * Created by hbh5274@gmail.com on 2020-07-15
 * Github : http://github.com/bhhan5274
 */
public class MultiplicationResultAttemptDeserializer extends JsonDeserializer<MultiplicationResultAttempt> {
    @Override
    public MultiplicationResultAttempt deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        final ObjectCodec codec = jsonParser.getCodec();
        final JsonNode node = codec.readTree(jsonParser);

        return MultiplicationResultAttempt.builder()
                .userAlias(node.get("user").get("alias").asText())
                .multiplicationFactorA(node.get("multiplication").get("factorA").asInt())
                .multiplicationFactorB(node.get("multiplication").get("factorB").asInt())
                .resultAttempt(node.get("resultAttempt").asInt())
                .correct(node.get("correct").asBoolean())
                .build();
    }
}
