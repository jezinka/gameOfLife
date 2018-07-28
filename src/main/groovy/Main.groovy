import processing.core.PApplet

class Main extends PApplet {

    static Board board

    void settings() {
        size(500, 500)
    }

    void draw() {
        loadPixels()
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int loc = x + y * width
                pixels[loc] = color(*board.board[x][y].getCellColor())
            }
        }
        updatePixels()
        board.step()
    }


    static void main(String[] args) {

        String[] processingArgs = { "Game Of Life" }
        Main main = new Main()
        board = new Board(500, 500)
        runSketch(processingArgs, main)
    }
}


