import com.algo.Algo;
import com.board.BoardHandler;
import com.maze.MazeGenerator;
import com.window.Frame;
import com.window.Panel;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int height = 32;
        int width = 60;
        int offset = 24;
        BoardHandler boardHandler = new BoardHandler(offset, height, width);
        Frame frame = new Frame();
        Panel panel = new Panel(width, height, boardHandler);
        frame.add(panel);
        MazeGenerator mazeGenerator = new MazeGenerator(panel);
        mazeGenerator.generateMaze(boardHandler.getBlockAt(0,0));
        Algo algo = new Algo(panel);
        algo.findPath(boardHandler.getBlockAt(0,0), boardHandler.getBlockAt(width - 1, height - 1));
    }
}
