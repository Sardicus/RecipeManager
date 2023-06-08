package org.recipe.controller.modify;

public interface Command {
    void execute();
    void undo();
}
