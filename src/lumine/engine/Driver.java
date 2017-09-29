/**
 * Author: Skye Antinozzi
 * Entry point for the 3D engine.
 */
package lumine.engine;

public class Driver {

    public static void main(String[] args){

        Engine engine = new Engine();

        Thread t = new Thread( () -> engine.start() );
        t.setName("Screen-Thread");
        t.start();

        System.out.println("Engine Started");
    }
}
