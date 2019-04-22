package com.galveston.services;

import com.galveston.dao.Registration;
import com.galveston.entities.Draw;
import com.galveston.entities.Event;
import com.galveston.entities.Family;
import com.galveston.entities.Prize;
import com.galveston.objectFactory.RunTimeObjectHolder;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class LoaderImpl implements Loader{


    @Override
    public void eventsFireUpTimeLoader() {
        //Adding Initial Events in Running Time.
        Event ev1 = new Event(1001L, "Sing Karaoke");
        Event ev2 = new Event(1002L, "Dance Club");
        Event ev3 = new Event(1003L, "Go Kart");
        Event ev4 = new Event(1004L, "Basketball");
        Event ev5 = new Event(1005L, "Drawing Contest");
        RunTimeObjectHolder.getInstance().events.put(ev1.getEventId(),ev1);
        RunTimeObjectHolder.getInstance().events.put(ev2.getEventId(),ev2);
        RunTimeObjectHolder.getInstance().events.put(ev3.getEventId(),ev3);
        RunTimeObjectHolder.getInstance().events.put(ev4.getEventId(),ev4);
        RunTimeObjectHolder.getInstance().events.put(ev5.getEventId(),ev5);
    }


    //This method Responsible to Read the json file and parse the data as a json object and map it to Java Object.
    @Override
    public void userFireUpTimeLoader() throws IOException {
        List<Family> users = new ArrayList<>();
        File file = new File("userDB.json");
        if(!file.exists())file.createNewFile();
        FileReader fr = new FileReader("userDB.json");
        if(!isEmpty(fr)) {
            JsonReader jsonReader = new JsonReader(new FileReader("userDB.json"));
            jsonReader.beginObject();
            jsonReader.nextName();
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                Family family = new Family();
                jsonReader.beginObject();
                jsonReader.nextName();
                    family.setUserId(jsonReader.nextLong());
                jsonReader.nextName();
                    family.setFirstName(jsonReader.nextString());
                jsonReader.nextName();
                    family.setMiddleName(jsonReader.nextString());
                jsonReader.nextName();
                    family.setLastName(jsonReader.nextString());
                jsonReader.nextName();
                    family.setUserName(jsonReader.nextString());
                jsonReader.nextName();
                    family.setPassword(jsonReader.nextString());
                jsonReader.nextName();
                    family.setCPassword(jsonReader.nextString());
                jsonReader.nextName();
                    family.setAddress(jsonReader.nextString());
                jsonReader.nextName();
                    family.setCity(jsonReader.nextString());
                jsonReader.nextName();
                    family.setState(jsonReader.nextString());
                jsonReader.nextName();
                    family.setZipCode(jsonReader.nextString());
                jsonReader.nextName();
                    family.setPhoneNumber(jsonReader.nextString());
                jsonReader.nextName();
                    family.setEmail(jsonReader.nextString());
                jsonReader.nextName();
                    family.setPoints(jsonReader.nextLong());
                jsonReader.nextName();
                jsonReader.beginArray();
                List<Draw> draws = new ArrayList<>();
                while (jsonReader.hasNext()){
                    Draw draw = new Draw();
                    jsonReader.beginObject();
                    jsonReader.nextName();
                        draw.setDrawCode(jsonReader.nextString());
                        draws.add(draw);
                    jsonReader.endObject();
                }
                //need to change to object
                draws.stream().forEach(e->family.setDraw(e));
                jsonReader.endArray();

                //need to change from id to object.
                jsonReader.nextName();
                family.setAdmin(jsonReader.nextBoolean());
                users.add(family);
                jsonReader.endObject();

            }
            //add all families from json file to factory.
            users.stream().forEach(u -> RunTimeObjectHolder.getInstance().families.put(u.getUserId(), u));
            //RunTimeObjectHolder.getInstance().families.entrySet().stream().forEach(o -> System.out.println(o.getValue()));

        }
    }

    @Override
    public void whoIsBoss(){
        Family admin = new Family("Dear","Boss","Boss","admin","password","password","Global",
                "world","world","12345","0000000000","admin@admin.com");
        admin.setAdmin(true);
        Registration.persistUserInFileAndMemory(admin);
    }

    @Override
    public void loadPrizes() {
        RunTimeObjectHolder.getInstance().prizes.add(new Prize("Teddy Bear."));
        RunTimeObjectHolder.getInstance().prizes.add(new Prize("Box Of Flower."));
        RunTimeObjectHolder.getInstance().prizes.add(new Prize("Video Game Console."));
        RunTimeObjectHolder.getInstance().prizes.add(new Prize("Lap Top."));
        RunTimeObjectHolder.getInstance().prizes.add(new Prize("TV"));
        RunTimeObjectHolder.getInstance().prizes.add(new Prize("25$ Gift Card. "));
        RunTimeObjectHolder.getInstance().prizes.add(new Prize("Toy Cars."));
    }


    boolean isEmpty(FileReader fr) throws IOException {
        BufferedReader br = new BufferedReader(fr);
        if (br.readLine() == null) {
            return true;
        }
        return false;
    }

}
