import { useState } from "react"

/*Takes the event from an input and changes the state accordingly (should be called in OnChange)*/
export default function useStateEvent(initialValue) {

    const [state, setState] = useState(initialValue)

    const modifiedSetState = (event) => {
        setState({ ...state, [event.target.name]: event.target.value })
    }

    return [state, modifiedSetState]

}