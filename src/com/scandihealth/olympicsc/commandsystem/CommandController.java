package com.scandihealth.olympicsc.commandsystem;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Name("commandController")
@Scope(org.jboss.seam.ScopeType.SESSION)
public class CommandController implements Serializable {

    List<Command> commands;
    List<Command> redoList;

    public CommandController() {
        commands = new ArrayList<Command>();
        redoList = new ArrayList<Command>();
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public boolean executeCommand(Command command) {
        boolean b = command.execute();
        addCommand(command);
        return b;
    }

    public void undo() {
        if (commands.size() > 0) {
            Command command = commands.get(commands.size() - 1);
            command.undo();
            commands.remove(command);
            redoList.add(command);
        }
    }

    public void redo() {
        if (redoList.size() > 0) {
            Command command = redoList.get(redoList.size() - 1);
            command.redo();
            redoList.remove(command);
            commands.add(command);
        }
    }
}
