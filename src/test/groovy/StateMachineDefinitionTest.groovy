import spock.lang.Specification

class StateMachineDefinitionTest extends Specification {

    def setup() {
        StateMachineDefinition.state_machine_definition = [
                (State.ALIVE): [[event: 'to_dead', to: State.DEAD], [event: 'to_alive', to: State.ALIVE]],
                (State.DEAD) : [[event: 'to_alive', to: State.ALIVE]]
        ]
    }

    def "Transition no exception"() {

        when:
        State result = StateMachineDefinition.transition(event, state)

        then:
        noExceptionThrown()
        assert result == expectedState

        where:
        state       | event      || expectedState
        State.ALIVE | 'to_dead'  || State.DEAD
        State.ALIVE | 'to_alive' || State.ALIVE
        State.DEAD  | 'to_alive' || State.ALIVE
    }

    def "Transition should throw exception"() {
        when:
        StateMachineDefinition.transition('to_another_state', State.ALIVE)

        then:
        thrown(Exception)
    }
}
