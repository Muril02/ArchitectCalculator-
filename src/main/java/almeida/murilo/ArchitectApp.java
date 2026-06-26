package almeida.murilo;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialog;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialogBuilder;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

import static com.googlecode.lanterna.TextCharacter.fromCharacter;

public class ArchitectApp {
    public static void main(String[] args) {

        try(Screen screen = new DefaultTerminalFactory().createScreen()) {
            TextGraphics textgh = screen.newTextGraphics();
            screen.startScreen();

            screen.setCharacter(10, 20, fromCharacter('2')[0]);

            textgh.setBackgroundColor(TextColor.ANSI.MAGENTA_BRIGHT);
            textgh.setForegroundColor(TextColor.ANSI.MAGENTA_BRIGHT);
            textgh.drawLine(0, 0, 10, 0, '/');
            textgh.drawRectangle(new TerminalPosition(0,0), new TerminalSize(10,10), '|');
            textgh.drawLine(11, 0, 11, 10, '|');
            textgh.drawLine(12, 10, 13, 10, '|');
            screen.refresh();

            MultiWindowTextGUI gui = new MultiWindowTextGUI(screen);

            MessageDialog message = new MessageDialogBuilder()
                    .setTitle("Ronaldo")
                            .setText("Ola sou o milior")
                                    .build();
            gui.addWindow(message);

            TextInputDialog box = new TextInputDialogBuilder().setTitle("Digite aqui").build();
            gui.addWindow(box);
            gui.updateScreen();

            if(screen.readInput().getKeyType() != KeyType.Escape){
                screen.readInput();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
