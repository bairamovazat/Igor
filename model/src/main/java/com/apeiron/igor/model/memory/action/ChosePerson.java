package com.apeiron.igor.model.memory.action;

import com.apeiron.igor.model.db.Person;
import com.apeiron.igor.model.db.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChosePerson implements Acton {
    @Builder.Default
    private String actionName = "ChosePerson";
    private List<Person> personList;

    public User user;
}
