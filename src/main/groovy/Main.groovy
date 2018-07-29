import processing.core.PApplet

class Main extends PApplet {

    static Board board
    private static int SCALE = 2
    private static int BOARD_SIZE = 200
    private static int SCREEN_SIZE = BOARD_SIZE * SCALE

    void settings() {
        frameRate = 30
        size(SCREEN_SIZE, SCREEN_SIZE)
    }

    void draw() {
        loadPixels()
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {

                for (int i = 0; i < SCALE; i++) {
                    for (int j = 0; j < SCALE; j++) {
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
        board = new Board(BOARD_SIZE)
        runSketch(processingArgs, main)
    }
}


