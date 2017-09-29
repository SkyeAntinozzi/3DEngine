/**
 * Author: Skye Antinozzi
 * The heart of the 3DEngine. This is constructs and starts the engine for viewing.
 */
package lumine.engine;

import javax.swing.*;

public class Engine {

    private Screen screen;

    public Engine(){

        screen = Screen.getScreen();

        JFrame frame = new JFrame("3D Engine");
        frame.add(screen);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void start(){
        screen.start();
    }
}
