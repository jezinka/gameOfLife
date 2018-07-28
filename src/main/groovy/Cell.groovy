class Cell {
    State state
    int lifeLong

    Cell(State state) {
        this.state = state
        this.lifeLong = 0
    }

    int[] getCellColor() {
        int[] color
        if (this.state == State.ALIVE) {
            if (this.lifeLong == 0) {
                return [255, 0, 0]
            } else if (this.lifeLong == 1) {
                color = [0, 0, 255]
            } else {
                color = [255, 255, 0]
            }
        } else {
            color = [255, 255, 255]
        }

        return color
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
