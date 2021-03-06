package com.apeiron.igor.model.memory.room.annotation;

import com.apeiron.igor.model.memory.action.Acton;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {
    Class<? extends Acton> event();
}
