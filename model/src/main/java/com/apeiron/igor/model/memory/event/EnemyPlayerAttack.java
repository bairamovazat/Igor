package com.apeiron.igor.model.memory.event;

import com.apeiron.igor.model.db.Person;
import com.apeiron.igor.model.db.User;
import com.apeiron.igor.model.memory.action.Acton;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnemyPlayerAttack implements Event {
    public String actionName = "EnemyPlayerAttack";
    public Integer xAbscissa;
    public Integer yOrdinate;
    public Person person;

    public User user;
}
