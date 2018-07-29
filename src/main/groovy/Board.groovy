import org.apache.commons.lang3.RandomUtils

class Board {

    private static final int NOT_ENOUGH = 2
    private static final int TOO_MANY = 3
    private static final int REPRODUCTORS = 3
    private static final BigDecimal DENSITY = 0.75

    Cell[][] board
    int x
    int y

    Board() {}

    Board(Cell[][] board) {
        this.board = board
        this.x = board.length
        this.y = board[0].length
    }

    Board(int x, int y) {
        this.x = x
        this.y = y

        this.board = [[new Cell(State.DEAD)] * y] * x
        randomInit()
    }

    private void randomInit() {

        ((this.x * this.y) / DENSITY).times {
            int randomX = RandomUtils.nextInt(0, this.x)
            int randomY = RandomUtils.nextInt(0, this.y)

            this.board[randomX][randomY] = new Cell(State.ALIVE)
        }
    }

    void step() {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < this.board[x].length; y++) {
                Cell cell = board[x][y]
                Event event = getEvent(x, y, cell.state)
                StateMachineDefinition.transition(event, cell)
            }
        }
    }

    private Event getEvent(int x, int y, State state) {
        int aliveNeighbours = getLiveNeighbours(x, y)

        if (state == State.ALIVE) {
            if (aliveNeighbours < NOT_ENOUGH) {
                return Event.UNDER_POPULATION
            }
            if (aliveNeighbours > TOO_MANY) {
                return Event.OVER_POPULATION
            }
        } else {
            if (aliveNeighbours == REPRODUCTORS) {
                return Event.REPRODUCTION
            }
        }
        return Event.STABLE
    }

    private int getLiveNeighbours(int x, int y) {
        int liveNeighbours = 0

        for (int i = normalizeStart(x); i <= normalizeEnd(x, this.x); i++) {
            for (int j = normalizeStart(y); j <= normalizeEnd(y, this.y); j++) {
                if ((x != i || y != j) && board[i][j].state == State.ALIVE) {
                    liveNeighbours++
                }
            }
        }
        liveNeighbours
    }

    private int normalizeStart(int i) {
        i - 1 > 0 ? i - 1 : 0
    }

    private int normalizeEnd(int i, int max) {
        i + 1 < max ? i + 1 : max - 1
    }

    int[] getCellColor(int x, int y) {
        board[x][y].getCellColor()
    }

    @Override
    String toString() {
        board.collect { row -> row.join(' ') }.join('\n') + '\n\n'
    }
}
