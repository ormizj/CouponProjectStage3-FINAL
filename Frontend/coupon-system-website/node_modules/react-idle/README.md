# React Idle

![logo](./logo.png)

What?
-----

Notifies your app when the user is idle.

Why?
----

When the user is idle you can do things like preload some code-split bundles, download images that haven't been scrolled to, etc. Also useful to automatically log them out of a sensitive website.

Installation
------------

```bash
npm install react-idle
# or
yarn add react-idle
```

And then import it:

```js
// using es modules
import Idle from 'react-idle'

// common.js
const Idle = require('react-idle').default

// AMD
// I've forgotten but it should work.
```

Or use script tags and globals.

```html
<script src="https://unpkg.com/react-idle"></script>
```

And then grab it off the global like so:

```js
const Idle = ReactIdle.default
```


How?
----

```render-babel
// import { Idle } from 'react-idle'
const { default: Idle } = ReactIdle

ReactDOM.render((
  <Idle
    onChange={({ idle }) => console.log({ idle })}
    render={({ idle }) =>
      <h1>
        {idle
          ? "You are idle."
          : "Stop doing stuff for 1 second."
        }
      </h1>
    }
  />
), DOM_NODE)
```

Props
-----

### `render`

Whatever you'd like to render in response to changes in user activity.

```render-babel
// import { Idle } from 'react-idle'
const { default: Idle } = ReactIdle

ReactDOM.render((
  <Idle render={({ idle }) =>
    <h1>
      {idle
        ? "*whistles*"
        : "Woah what now?"
      }
    </h1>
  }/>
), DOM_NODE)
```

### `timeout`

How long before notifying that the user is idle in milliseconds.

```render-babel
// import { Idle } from 'react-idle'
const { default: Idle } = ReactIdle

ReactDOM.render((
  <Idle
    timeout={2000}
    render={({ idle }) =>
      <h1>
        {idle
          ? "You are idle."
          : "Stop doing stuff for 2 seconds."
        }
      </h1>
    }
  />
), DOM_NODE)
```

### `onChange`

Called whenever the user's activity state changes, a great time to change the owner component's state, or to kick off some imperative work like pre-fetching code-split bundles or images.

```render-babel
// import { Idle } from 'react-idle'
const { default: Idle } = ReactIdle

class App extends React.Component {
  state = {
    cornifyLoaded: false
  }

  preloadCornify = () => {
    const script = document.createElement('script')
    script.onload = () => this.setState({ cornifyLoaded: true })
    script.src = '//www.cornify.com/js/cornify.js'
    document.body.appendChild(script)
  }

  cornify = () => {
    window.cornify_add()
  }

  render() {
    return (
      <div>
        {this.state.cornifyLoaded === false && (
          <Idle onChange={({ idle}) => {
            if (idle) {
              this.preloadCornify()
            }
          }}/>
        )}

        <button
          disabled={!this.state.cornifyLoaded}
          onClick={this.cornify}
        >Make Some Happiness</button>
      </div>
    )
  }
}

ReactDOM.render(<App/>, DOM_NODE)
```

### `events`

The window events to listen for activity, defaults to `[ "mousemove", "mousedown", "keydown", "touchstart", "scroll" ]`.


### `defaultIdle`

You can start out as idle by passing `<Idle defaultIdle={true}/>`, by default it is false--assumes that loading the page initially is a user interation.


Legal
-----

Released under MIT license.

Copyright &copy; 2017-present React Training LLC
