package almeida.murilo;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.BasicTextImage;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.*;
import com.googlecode.lanterna.gui2.menu.Menu;import com.googlecode.lanterna.gui2.menu.MenuBar;import com.googlecode.lanterna.gui2.menu.MenuItem;import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;import java.sql.SQLOutput;

import static com.googlecode.lanterna.TextCharacter.fromCharacter;

public class ArchitectApp {
    public static void main(String[] args) {

        try(Screen screen = new DefaultTerminalFactory().createScreen()) {
            TextGraphics textgh = screen.newTextGraphics();
            screen.startScreen();

            MultiWindowTextGUI gui = new MultiWindowTextGUI(screen);

            DialogWindow message = new MessageDialogBuilder()
                    .setTitle("Ronaldo")
                            .setText("Ronaldo 2")
                                .build();

            BasicWindow window = new BasicWindow("Ronaldo");
            TextBox txtBox = new TextBox();

            Button btn = new Button("Aperte aqui");
            btn.addListener((b) -> {
                System.out.println(txtBox.getText());
                window.close();
            });

            Panel pnBtnTxtBox = new Panel();
            pnBtnTxtBox.addComponent(txtBox);
            pnBtnTxtBox.addComponent(btn);

            window.setComponent(pnBtnTxtBox);

            gui.addWindowAndWait(message);
            gui.addWindow(window);
            gui.updateScreen();



            window.waitUntilClosed();


        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
