package almeida.murilo;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class ArchitectApp {
    public static void main(String[] args) {

        try(Terminal terminal = new DefaultTerminalFactory().createTerminal()) {
            terminal.enterPrivateMode();

            terminal.putCharacter('A');
            terminal.putCharacter('A');
            terminal.putCharacter('A');
            terminal.putCharacter('A');
            terminal.putCharacter('A');
            terminal.putCharacter('A');
            terminal.flush();

            terminal.readInput();

            terminal.exitPrivateMode();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
