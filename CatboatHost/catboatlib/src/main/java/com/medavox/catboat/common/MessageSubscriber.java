package com.medavox.catboat.common;

/**
 * @author Adam Howard
 * @date 22/04/2017
 */

public interface MessageSubscriber {
    void onMessage(Message m);
}
