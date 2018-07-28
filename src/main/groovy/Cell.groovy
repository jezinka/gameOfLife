class Cell {
    State state
    int lifeLong

    Cell(State state) {
        this.state = state
        this.lifeLong = 0
    }

    @Override
    String toString() {
        String color
        if (this.state == State.ALIVE) {
            if (this.lifeLong == 0) {
                color = "[31m"
            } else if (this.lifeLong == 1) {
                color = "[34m"
            } else {
                color = "[33m"
            }
        } else {
            color = "[39m"
        }

        return "${27 as char}${color}${this.state}"
    }
}

enum State {
    ALIVE, DEAD

    @Override
    String toString() {
        return this == ALIVE ? '*' : '.'
    }
}
