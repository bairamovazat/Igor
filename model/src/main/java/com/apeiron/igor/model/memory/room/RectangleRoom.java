package com.apeiron.igor.model.memory.room;

import com.apeiron.igor.model.db.Person;
import com.apeiron.igor.model.db.User;
import com.apeiron.igor.model.memory.action.Attack;
import com.apeiron.igor.model.memory.action.ChosePerson;
import com.apeiron.igor.model.memory.event.*;
import com.apeiron.igor.model.memory.room.annotation.EventHandler;
import com.apeiron.igor.model.memory.room.annotation.Room;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Room
public class RectangleRoom extends AbstractRoom {

    public User currentUser;

    public HashMap<Long, List<Person>> userIdPersons = new HashMap<>();

    public HashMap<Long, Integer> userIdCurrentPersons = new HashMap<>();

    public RectangleRoom(RoomCallback roomCallback, List<User> userList) {
        super(roomCallback);
        this.userList = userList;
        currentUser = userList.get((int) (System.currentTimeMillis() % userList.size()));
        userList.forEach(user -> userIdCurrentPersons.put(user.getId(), 0));
    }

    @Getter
    public List<User> userList = new ArrayList<>();

    public boolean userContains(User user) {
        return userList.stream().anyMatch(e -> Objects.equals(e.getId(), user.getId()));
    }

    @EventHandler(event = ChosePerson.class)
    public void processChosePerson(ChosePerson chosePerson) {
        userIdPersons.put(chosePerson.user.id, chosePerson.getPersonList());

        userList.stream()
                .filter(user -> !user.getId().equals(chosePerson.user.getId()))
                .forEach(user -> {
                    EnemyPlayerChosePerson enemyPlayerChosePerson = EnemyPlayerChosePerson.builder()
                            .personList(chosePerson.getPersonList())
                            .user(user)
                            .build();
                    callback(enemyPlayerChosePerson);
                });
        if (userIdPersons.size() == userList.size()) {
            userList.forEach(user -> {
                StartGame startGame = new StartGame();
                startGame.setUser(user);
                callback(startGame);
            });
            nextUser();
        }

    }

    @EventHandler(event = Attack.class)
    public void processAttack(Attack attack) {
        if (!Objects.equals(attack.getUser().getId(), attack.getUser().getId())) {
            return;
        }

        userList.stream()
                .filter(user -> !user.getId().equals(attack.getUser().getId()))
                .forEach(user -> {
                    Person person = userIdPersons.get(user.id)
                            .stream()
                            .filter(e -> e.getId() == ((long)attack.getX()))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Person not found"));

                    person.setHealth(person.getHealth() - attack.getPerson().getAttack());

                    EnemyPlayerAttack enemyPlayerAttack = EnemyPlayerAttack.builder()
                            .person(person)
                            .user(user)
                            .xAbscissa(attack.getX())
                            .yOrdinate(attack.getY())
                            .build();

                    callback(enemyPlayerAttack);
                    checkEndGame();
                });
        nextUser();
    }

    private void checkEndGame() {
        for (User user : userList) {
            List<Person> alivePerson = userIdPersons.get(user.getId())
                    .stream()
                    .filter(person -> person.getHealth() > 0)
                    .collect(Collectors.toList());
            if (alivePerson.size() == 0) {
                userLose(user);
                return;
            }
        }
    }

    private void userLose(User user) {
        this.userList.stream()
                .filter(e -> !e.getId().equals(user.getId()))
                .forEach(winUser -> {
                    GameEnd gameEnd = new GameEnd();
                    gameEnd.setExperience(100);
                    gameEnd.setWin(true);
                    gameEnd.setUser(winUser);
                    callback(gameEnd);
                });

        GameEnd gameEnd = new GameEnd();
        gameEnd.setExperience(50);
        gameEnd.setWin(false);
        gameEnd.setUser(user);
        callback(gameEnd);
    }

    private void nextUser() {
        int index = userList.indexOf(currentUser);
        int nextIndex = index + 1;
        if (nextIndex >= userList.size()) {
            nextIndex = 0;
        }
        currentUser = userList.get(nextIndex);
        YourStep yourStep = new YourStep();
        yourStep.setUser(currentUser);
        yourStep.setPersonId(getCurrentPersonId(currentUser));
        callback(yourStep);
        userList.stream()
                .filter(user -> !Objects.equals(user.getId(), currentUser.getId()))
                .forEach(user -> {
                    EnemyPlayerStep enemyPlayerStep = new EnemyPlayerStep();
                    enemyPlayerStep.setUser(user);
                    callback(enemyPlayerStep);
                });
    }

    private Long getCurrentPersonId(User user) {
        Integer currentPerson = userIdCurrentPersons.get(user.getId());
        Integer result = currentPerson;
        currentPerson = currentPerson + 1;
        if(currentPerson >= userIdPersons.get(user.getId()).size()) {
            currentPerson = 0;
        }
        userIdCurrentPersons.put(user.getId(), currentPerson);
        return userIdPersons.get(user.getId()).get(result).getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    public void start() {

    }
}
