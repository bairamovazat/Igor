package com.apeiron.igor.service;

import com.apeiron.igor.model.GameFactory;
import com.apeiron.igor.model.GameFactoryImpl;
import com.apeiron.igor.model.config.PrincipalImpl;
import com.apeiron.igor.model.db.User;
import com.apeiron.igor.model.memory.NewGame;
import com.apeiron.igor.model.memory.action.Attack;
import com.apeiron.igor.model.memory.action.ChosePerson;
import com.apeiron.igor.model.memory.event.Event;
import com.apeiron.igor.model.memory.room.RectangleRoom;
import com.apeiron.igor.model.xap.GameInvite;
import com.apeiron.igor.repository.UserRepository;
import com.apeiron.igor.repository.impl.RoomRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private RoomRepositoryImpl roomRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserRepository userRepository;

    private GameFactory gameFactory = new GameFactoryImpl();

    @Override
    public void chosePerson(ChosePerson msg, PrincipalImpl principal, String gameId) {
        msg.setUser(principal.getUser());
        roomRepository.getById(gameId).processActon(msg);
    }

    @Override
    public void sendAttack(Attack msg, PrincipalImpl principal, String gameId) {
        msg.setUser(principal.getUser());
        roomRepository.getById(gameId).processActon(msg);
    }

    @Override
    public void startRoom(GameInvite gameInvite) {
        User invitedUser = userRepository.findOneById(gameInvite.getInvited())
                .orElseThrow(() -> new IllegalArgumentException("Пользователь (invited) не найден"));
        User initiatorUser = userRepository.findOneById(gameInvite.getInitiator())
                .orElseThrow(() -> new IllegalArgumentException("Пользователь (initiator) не найден"));

        RectangleRoom rectangleRoom = new RectangleRoom(this::callBack, Arrays.asList(invitedUser, initiatorUser));
        roomRepository.save(rectangleRoom);
        NewGame invitedUserNewGame = gameFactory.createGame(rectangleRoom, initiatorUser);
        NewGame initiatorUserNewGame = gameFactory.createGame(rectangleRoom, invitedUser);
        simpMessagingTemplate.convertAndSendToUser(invitedUser.getLogin(), "/queue/game/new-game", invitedUserNewGame);
        simpMessagingTemplate.convertAndSendToUser(initiatorUser.getLogin(), "/queue/game/new-game", initiatorUserNewGame);
        rectangleRoom.start();
    }

    public void callBack(Event event, String roomId) {
        simpMessagingTemplate.convertAndSendToUser(event.getUser().getLogin(), "/queue/game/" + event.getEventName() + "/" + roomId, event);
    }
}
