package com.apeiron.igor.model;

import com.apeiron.igor.model.db.Person;
import com.apeiron.igor.model.db.PersonType;
import com.apeiron.igor.model.db.User;
import com.apeiron.igor.model.memory.NewGame;
import com.apeiron.igor.model.memory.map.RectangleMap;
import com.apeiron.igor.model.memory.room.RectangleRoom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameFactoryImpl implements GameFactory {

    @Override
    public NewGame createGame(RectangleRoom rectangleRoom, User enemyPlayer) {
        return NewGame.builder()
                .id(rectangleRoom.getId())
                .createDate(new Date())
                .enemyPlayer(enemyPlayer)
                .personList(getAllPersons())
                .gameMap(new RectangleMap())
                .yourPersons(new ArrayList<>())
                .enemyPlayerPersons(new ArrayList<>())
                .build();
    }

    private List<Person> getAllPersons() {
        List<Person> personList = new ArrayList<>();

        for (int i = 0; i < PersonType.values().length; i ++) {
            PersonType personType = PersonType.values()[i];
            personList.add(new Person((long) i, personType,  getPersonAttack(personType), getPersonHealth(personType), personType.toString()));
        }
        return personList;
    }

    private Integer getPersonHealth(PersonType personType) {
        switch (personType) {
            case tank:
                return 100;
            case priest:
                return 80;
            case wizard:
                return 75;
            case assassin:
                return 86;
            case marksman:
                return 90;
            case swords_woman:
                return 60;
            default:
                return 0;
        }
    }

    private Integer getPersonAttack(PersonType personType) {
        switch (personType) {
            case tank:
                return 20;
            case priest:
                return 25;
            case wizard:
                return 21;
            case assassin:
                return 30;
            case marksman:
                return 23;
            case swords_woman:
                return 22;
            default:
                return 0;
        }
    }
}
