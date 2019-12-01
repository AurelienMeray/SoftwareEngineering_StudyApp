import React from 'react';
import { BrowserRouter, Switch, Route } from 'react-router-dom'
import Navbar from './components/layout/Navbar'
import Dashboard from './components/dashboard/Dashboard'
import RoomPage from './components/room/RoomPage'
import SignIn from './components/auth/SignIn'
import SignUp from './components/auth/SignUp'
import CreateRoom from './components/room/CreateRoom'
import SearchRoom from './components/room/SearchRoom'

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Navbar/>
        {/* Makes sure only one path is loaded at a time*/}
        <Switch>
          <Route exact path='/' component={Dashboard}/>
          <Route path='/room/:id' component={RoomPage}></Route>
          <Route path='/signin' component={SignIn}></Route>
          <Route path='/signup' component={SignUp}></Route>
          <Route path='/createroom' component={CreateRoom}></Route>
          <Route path='/searchrooms' component={SearchRoom}></Route>
        </Switch>
      </div>
    </BrowserRouter>
  );
}

export default App;
