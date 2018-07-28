import spock.lang.Specification

class BoardTest extends Specification {

    def "getLiveNeighbours #expectedLivingNeighbours"() {
        given:

        Board board = new Board(cells)
        print board

        when:
        int livingNeighbours = board.getLiveNeighbours(1, 1)

        then:
        livingNeighbours == expectedLivingNeighbours

        where:
        cells                                                                              || expectedLivingNeighbours
        [[new Cell(State.ALIVE)] * 3] * 3 as Cell[][]                                      || 8

        [[new Cell(State.ALIVE), new Cell(State.DEAD), new Cell(State.ALIVE)],
         [new Cell(State.DEAD), new Cell(State.DEAD), new Cell(State.DEAD)],
         [new Cell(State.ALIVE), new Cell(State.DEAD), new Cell(State.ALIVE)]] as Cell[][] || 4

        [[new Cell(State.DEAD), new Cell(State.ALIVE), new Cell(State.DEAD)],
         [new Cell(State.ALIVE), new Cell(State.DEAD), new Cell(State.ALIVE)],
         [new Cell(State.DEAD), new Cell(State.ALIVE), new Cell(State.DEAD)]] as Cell[][]  || 4

        [[new Cell(State.DEAD)] * 3] * 3 as Cell[][]                                       || 0
    }

    def "getEvent test #excpedtedEvent"() {

        setup:
        Board board = new Board(cells)

        when:
        Event event = board.getEvent(1, 1, cellState)

        then:
        event == expectedEvent

        where:
        cells                                                                            | cellState   || expectedEvent
        [[new Cell(State.ALIVE)] * 3] * 3 as Cell[][]                                    | State.ALIVE || Event.OVER_POPULATION

        [[new Cell(State.DEAD)] * 3] * 3 as Cell[][]                                     | State.ALIVE || Event.UNDER_POPULATION

        [[new Cell(State.DEAD), new Cell(State.ALIVE), new Cell(State.DEAD)],
         [new Cell(State.ALIVE), new Cell(State.DEAD), new Cell(State.ALIVE)],
         [new Cell(State.DEAD), new Cell(State.DEAD), new Cell(State.DEAD)]] as Cell[][] | State.DEAD  || Event.REPRODUCTION

        [[new Cell(State.DEAD), new Cell(State.DEAD), new Cell(State.DEAD)],
         [new Cell(State.ALIVE), new Cell(State.DEAD), new Cell(State.ALIVE)],
         [new Cell(State.DEAD), new Cell(State.DEAD), new Cell(State.DEAD)]] as Cell[][] | State.ALIVE || Event.STABLE

        [[new Cell(State.DEAD), new Cell(State.DEAD), new Cell(State.DEAD)],
         [new Cell(State.ALIVE), new Cell(State.DEAD), new Cell(State.ALIVE)],
         [new Cell(State.DEAD), new Cell(State.DEAD), new Cell(State.DEAD)]] as Cell[][] | State.DEAD  || Event.STABLE
    }

}


