package com.apeiron.igor.model.room;

import com.apeiron.igor.model.action.Acton;
import com.apeiron.igor.model.action.ChosePersonActon;
import com.apeiron.igor.model.event.ChosePersonEvent;
import com.apeiron.igor.model.event.Event;
import com.apeiron.igor.model.room.annotation.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class AbstractRoom implements Room {
    private RoomCallback roomCallback;
    private HashMap<Class<? extends Acton>, BiConsumer<Room, Acton>> eventProcessMethodMap = new HashMap<>();
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public AbstractRoom(RoomCallback roomCallback) {
        this.roomCallback = roomCallback;
        fillEventMethods();
    }

    //TODO change to static
    private void fillEventMethods() {
        Class<? extends AbstractRoom> currentClass = this.getClass();
        List<Method> eventMethodList = Arrays.stream(currentClass.getMethods())
                .filter(e -> e.getAnnotation(EventHandler.class) != null)
                .collect(Collectors.toList());
        eventMethodList.forEach(method -> {
            EventHandler eventHandler = method.getAnnotation(EventHandler.class);
            Class<? extends Acton> actionClass = eventHandler.event();
            if (eventProcessMethodMap.containsKey(actionClass)) {
                logger.error("Action class {} is duplicated in the map on class {}", actionClass, this.getClass());
            } else {
                BiConsumer<Room, Acton> runMethod = ((room, acton) -> {
                    try {
                        method.invoke(room, acton);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        logger.error("Invoke room error", e);
                    }
                });
                eventProcessMethodMap.put(actionClass, runMethod);
            }
        });
    }

    @Override
    public <T extends Acton> void processActon(T action) {
        if (eventProcessMethodMap.containsKey(action.getClass())) {
            eventProcessMethodMap.get(action.getClass())
                    .accept(this, action);
        } else {
            logger.error("Method map does not contain class {}", action.getClass());
        }
    }

    public <T extends Event> void callback(Event event) {
        roomCallback.callback(event);
    }
}
