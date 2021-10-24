import { useState } from "react";

/*
Example usage:

const forceUpdate = useForceUpdate();
const myFunction = () => { 
    forceUpdate()
}

will force the react component to update itself
*/
export default function useForceUpdate() {

    const setValue = useState(0)[1]
    return () => setValue(value => value + 1);

}