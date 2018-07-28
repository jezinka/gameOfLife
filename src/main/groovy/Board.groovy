import org.apache.commons.lang3.RandomUtils

class Board {

    Cell[][] board
    int x
    int y

    Board(int x, int y) {
        this.x = x
        this.y = y

        this.board = [[new Cell(State.DEAD)] * y] * x
        randomInit()
    }

    private void randomInit() {

        ((this.x * this.y) / 2).times {
            int randomX = RandomUtils.nextInt(0, this.x)
            int randomY = RandomUtils.nextInt(0, this.y)

            this.board[randomX][randomY] = new Cell(State.ALIVE)
        }
    }

    void step() {
        for (int x = 0; x < board.length; x++) {
            Cell[] subArray = this.board[x]
            for (int y = 0; y < subArray.length; y++) {
                Cell cell = subArray[y]
                Event event = getEvent(x, y)
                StateMachineDefinition.transition(event, cell)
            }
        }
    }

    private Event getEvent(int x, int y) {
        Cell cell = board[x][y]
        int aliveNeighbours = getLiveNeighbours(x, y)

        if (cell.state == State.ALIVE) {
            if (aliveNeighbours < 2) {
                return Event.UNDER_POPULATION
            } else if (aliveNeighbours > 3) {
                return Event.OVER_POPULATION
            }
        } else {
            if (aliveNeighbours == 3) {
                return Event.REPRODUCTION
            }
        }
        return Event.STABLE
    }

    private int getLiveNeighbours(int x, int y) {
        int liveNeighbours = 0
        for (int i = x - 1; i <= x + 1; i++) {
            if (i > 0 && i < board.length) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (i != x && j > 0 && j < board[i].length && j != y && board[i][j].state == State.ALIVE) {
                        liveNeighbours++
                    }
                }
            }
        }
        liveNeighbours
    }

    @Override
    String toString() {
        board.collect { row -> row.join(' ') }.join('\n') + '\n\n'
    }
}
