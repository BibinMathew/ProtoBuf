package com.cwbm.protobuf;

//import com.cwbm.generated.model;

import com.cwbm.generated.model.Person;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;

public class PersonDemo {

    public static void main(String[] args) throws IOException {
      /* Person sam = Person.newBuilder()
                .setName("SAM")
                .setAge(10).build();
*/

        Path path = Paths.get("sam.ser");
       byte[] allBytes= Files.readAllBytes(path);
       Person sam = Person.parseFrom(allBytes);

     //   Files.write(path,sam.toByteArray());

      System.out.println(sam);

    }



}
