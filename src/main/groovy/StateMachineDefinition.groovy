class StateMachineDefinition {

    static Map<State, List> state_machine_definition = [
            (State.ALIVE): [
                    [event: Event.OVER_POPULATION, to: State.DEAD],
                    [event: Event.UNDER_POPULATION, to: State.DEAD],
                    [event: Event.STABLE, to: State.ALIVE],
            ],
            (State.DEAD) : [
                    [event: Event.REPRODUCTION, to: State.ALIVE],
                    [event: Event.STABLE, to: State.DEAD]
            ]
    ]

    static State transition(event, State state) {
        List states = state_machine_definition[state]
        def transition = states.find({ it.event == event })
        if (transition) {
            return transition.to
        }
        throw new Exception("invalid event $event ignored for state $state")
    }
}

enum Event {
    UNDER_POPULATION, OVER_POPULATION, REPRODUCTION, STABLE
}
