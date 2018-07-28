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

    static void transition(event, Cell cell) {
        List state = state_machine_definition[cell.state]
        def transition = state.find({ it.event == event })
        if (transition) {
            cell.state = transition.to
        } else {
            print("invalid event $event ignored for state $cell.state on subject $cell")
        }
    }
}

enum Event {
    UNDER_POPULATION, OVER_POPULATION, REPRODUCTION, STABLE
}
