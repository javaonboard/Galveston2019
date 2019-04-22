package com.galveston.entities;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Event {
    private Long eventId;
    private String name;


    public Event(Long id, String name){
        this.eventId = id;
        this.name = name;
    }

}
