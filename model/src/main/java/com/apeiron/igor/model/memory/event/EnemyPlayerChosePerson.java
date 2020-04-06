package com.apeiron.igor.model.memory.event;

import com.apeiron.igor.model.db.Person;
import com.apeiron.igor.model.db.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnemyPlayerChosePerson implements Event {
    @Builder.Default
    public String eventName = "EnemyPlayerChosePerson";
    private List<Person> personList = new ArrayList<>();

    public User user;
}
