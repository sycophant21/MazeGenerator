import com.algo.Algo;
import com.algo.Path;
import com.board.BoardHandler;
import com.maze.MazeGenerator;
import com.maze.MazeTraverser;
import com.window.Frame;
import com.window.Panel;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int height = 17;
        int width = 32;
        int offset = 45;
        BoardHandler boardHandler = new BoardHandler(offset, height, width);
        Frame frame = new Frame();
        Path path = new Path(new ArrayList<>());
        Panel panel = new Panel(width, height, boardHandler, path);
        frame.add(panel);
        MazeGenerator mazeGenerator = new MazeGenerator(panel);
        mazeGenerator.generateMaze(boardHandler.getBlockAt(0,0));
        Algo algo = new Algo(panel, path);
        //algo.findPath(boardHandler.getBlockAt(0,0), boardHandler.getBlockAt(width - 1, height - 1));
        frame.addListeners(new MazeTraverser(boardHandler, boardHandler.getBlockAt(width - 1, height - 1), boardHandler.getBlockAt(0,0), panel));
    }
}
