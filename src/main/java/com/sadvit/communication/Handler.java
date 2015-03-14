package com.sadvit.communication;

/**
 * Lambda events support
 */
@FunctionalInterface
public interface Handler<T extends Event> {

    /**
     * Обработка конкретного события
     */
    public void handle(T event);

}
