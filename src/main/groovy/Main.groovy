import processing.core.PApplet

class Main extends PApplet {

    static Board board

    private static int SCALE = 2
    private static int BOARD_SIZE = 200

    void settings() {
        frameRate = 30
        size(board.x * SCALE, board.y * SCALE)
    }

    void draw() {
        loadPixels()

        (0..<board.x).each { x ->
            (0..<board.y).each { y ->

                (0..<SCALE).each { i ->
                    (0..<SCALE).each { j ->
                        int loc = (SCALE * x + i) + (SCALE * y + j) * width
                        pixels[loc] = color(*board.getCellColor(x, y))
                    }
                }
            }
        }
        updatePixels()
        board.step()
    }


    static void main(String[] args) {
        String[] processingArgs = { "Game Of Life" }
        Main main = new Main()
        if (args.size() == 0) {
            board = new Board(BOARD_SIZE)
        } else if (args.size() < 3) {
            board = new Board(*args.collect { it as int })
        } else {
            print("Wrong number of arguments. Please provide maximum 2")
            return
        }

        runSketch(processingArgs, main)
    }
}


