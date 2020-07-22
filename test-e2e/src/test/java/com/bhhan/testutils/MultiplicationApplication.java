package com.bhhan.testutils;

import com.bhhan.testutils.beans.*;
import com.bhhan.testutils.http.ApplicationHttpUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2020-07-22
 * Github : http://github.com/bhhan5274
 */
public class MultiplicationApplication {
    private static final String APPLICATION_BASE_URL = "http://localhost:9000/api";
    private static final String CONTEXT_ATTEMPTS = "/results";
    private static final String CONTEXT_SCORE = "/scores/";
    private static final String CONTEXT_STATS = "/stats";
    private static final String CONTEXT_USERS = "/users/";
    private static final String CONTEXT_LEADERBOARD = "/leaders";
    private static final String CONTEXT_DELETE_DATA_GAM = "/gamification/admin/delete-db";
    private static final String CONTEXT_DELETE_DATA_MULT = "/multiplication/admin/delete-db";

    private ApplicationHttpUtils httpUtils;
    private ObjectMapper objectMapper;

    public MultiplicationApplication(){
        this.httpUtils = new ApplicationHttpUtils(APPLICATION_BASE_URL);
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public AttemptResponse sendAttempt(String userAlias, int factorA, int factorB, int result){
        String attemptJson = "{\"user\":{\"alias\":\"" + userAlias + "\"}," +
                "\"multiplication\":{\"factorA\":\"" + factorA + "\",\"factorB\":\"" + factorB + "\"}," +
                "\"resultAttempt\":\"" + result + "\"}";
        final String response = httpUtils.post(CONTEXT_ATTEMPTS, attemptJson);

        try {
            return objectMapper.readValue(response, AttemptResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ScoreResponse getScoreForAttempt(long attemptId){
        final String response = httpUtils.get(CONTEXT_SCORE + attemptId);
        if(response.isEmpty()){
            return new ScoreResponse(0);
        }else{
            try {
                return objectMapper.readValue(response, ScoreResponse.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Stats getStatsForUser(long userId){
        final String response = httpUtils.get(CONTEXT_STATS + "?userId=" + userId);

        try {
            return objectMapper.readValue(response, Stats.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUser(long userId){
        final String response = httpUtils.get(CONTEXT_USERS + userId);

        try {
            return objectMapper.readValue(response, User.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<LeaderBoardPosition> getLeaderBoard(){
        final String response = httpUtils.get(CONTEXT_LEADERBOARD);

        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, LeaderBoardPosition.class);
        try {
            return objectMapper.readValue(response, collectionType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteData(){
        httpUtils.post(CONTEXT_DELETE_DATA_GAM, "");
        httpUtils.post(CONTEXT_DELETE_DATA_MULT, "");
    }
}
