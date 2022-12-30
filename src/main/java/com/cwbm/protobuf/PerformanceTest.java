package com.cwbm.protobuf;

import com.cwbm.generated.model.Person;
import com.cwbm.json.JPerson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;

public class PerformanceTest {

    public static void main(String[] args) {


        JPerson json = new JPerson();
        json.setName("sam");
        json.setAge(10);

        ObjectMapper mapper = new ObjectMapper();

        Runnable runnable1 = () -> {
            try {
                byte[] bytes = mapper.writeValueAsBytes(json);
                JPerson person2 = mapper.readValue(bytes,JPerson.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };





        Person sam = Person.newBuilder()
                .setName("sam")
                .setAge(10).build();


        Runnable runnable2 = () ->{

            try {
                byte[] bytes = sam.toByteArray();
                Person sam2 = Person.parseFrom(bytes);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };


        runPerformanceTest( runnable1 , "JSON");
        runPerformanceTest( runnable2 , "PROTO");


    }

    private static void runPerformanceTest ( Runnable runnable, String method){

        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            runnable.run();
        }
        long time2 = System.currentTimeMillis();
        System.out.println("Method : " +  method + " took :" + (time2-time1) );


    }


}


