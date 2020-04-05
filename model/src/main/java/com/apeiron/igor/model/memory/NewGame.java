package com.apeiron.igor.model.memory;

import com.apeiron.igor.model.db.Person;
import com.apeiron.igor.model.db.User;
import com.apeiron.igor.model.memory.map.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewGame {
    private String id;
    private Date createDate;
    private User enemyPlayer;
    private List<Person> personList;
    private Map gameMap;
}
