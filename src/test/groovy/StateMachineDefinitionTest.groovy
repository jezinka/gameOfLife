import spock.lang.Specification

class StateMachineDefinitionTest extends Specification {

    def setup() {
        StateMachineDefinition.state_machine_definition = [
                (State.ALIVE): [[event: 'to_dead', to: State.DEAD], [event: 'stable', to: State.ALIVE]],
                (State.DEAD) : [[event: 'to_alive', to: State.ALIVE]]
        ]
    }

    def "Transition no exception"() {

        when:
        StateMachineDefinition.transition(event, cell)

        then:
        noExceptionThrown()
        assert cell.state == expectedState

        where:
        cell                  | event      || expectedState
        new Cell(State.ALIVE) | 'to_dead'  || State.DEAD
        new Cell(State.ALIVE) | 'stable'   || State.ALIVE
        new Cell(State.DEAD)  | 'to_alive' || State.ALIVE
    }

    def "Transition should throw exception"() {
        when:
        StateMachineDefinition.transition('to_another_state', new Cell(State.ALIVE))

        then:
        thrown(Exception)
    }
}
