import model.GamePackage.Game;

public class Launcher {

    public static void main(String[] args) {

        if (args.length > 0) {

            if (args[0].equals("-t"))
            System.out.println("Testing mode");
            Game game = new Game(800, 540, args[0]);
            game.start();
        }else{
            System.out.println("Distribution mode");
        }
    }
}
