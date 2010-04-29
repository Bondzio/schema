package com.scandihealth.olympicsc.commandsystem;

/**
 * Created by IntelliJ IDEA.
 * User: Martin Hylleberg
 * Date: 18-02-2010
 * Time: 23:08:03
 * To change this template use File | Settings | File Templates.
 */
public interface Command {
    public boolean execute();

    public void undo();

    public void redo();
}
