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
    private String actionName;
    private List<Person> personList;

    public User user;
}
