class Cell {
    State state
}

enum State {
    ALIVE, DEAD

    @Override
    String toString() {
        return this == ALIVE ? '*' : '.'
    }
}
