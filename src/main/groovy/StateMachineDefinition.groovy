class StateMachineDefinition {

    static Map<State, List> state_machine_definition = [
            (State.ALIVE): [
                    [event: Event.OVER_POPULATION, to: State.DEAD],
                    [event: Event.UNDER_POPULATION, to: State.DEAD],
                    [event: Event.STABLE, to: State.ALIVE, afterAction: 'increment'],
            ],
            (State.DEAD) : [
                    [event: Event.REPRODUCTION, to: State.ALIVE, afterAction: 'reset'],
                    [event: Event.STABLE, to: State.DEAD]
            ]
    ]

    static void transition(event, Cell cell) {
        List states = state_machine_definition[cell.state]
        def transition = states.find { it.event == event }

        if (!transition) {
            throw new Exception("invalid event $event for state ${cell.state}")
        }

        cell.state = transition.to

        if (transition.afterAction) {
            cell."${transition.afterAction}"()
        }
    }
}

enum Event {
    UNDER_POPULATION, OVER_POPULATION, REPRODUCTION, STABLE
}
