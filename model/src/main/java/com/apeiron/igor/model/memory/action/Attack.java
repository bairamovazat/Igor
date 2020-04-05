package com.apeiron.igor.model.memory.action;

import com.apeiron.igor.model.db.Person;
import com.apeiron.igor.model.db.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attack implements Acton {
    public String actionName = "Attack";
    public Integer xAbscissa;
    public Integer yOrdinate;
    public Person person;

    public User user;

}
