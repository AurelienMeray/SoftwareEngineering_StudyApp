import React from 'react';
import { observer } from 'mobx-react'
import UserStore from  './stores/UserStore'
import { BrowserRouter, Switch, Route } from 'react-router-dom'
import Navbar from './components/layout/Navbar'
import Dashboard from './components/dashboard/Dashboard'
import RoomPage from './components/room/RoomPage'
import SignIn from './components/auth/SignIn'
import SignUp from './components/auth/SignUp'
import CreateRoom from './components/room/CreateRoom'
import SearchRoom from './components/room/SearchRoom'
import SignedOutLinks from './components/layout/SignedOutLinks'

class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
        isLoggedIn: false
    }
  }

  
   async componentDidMount() {

    try {
      

      if (UserStore.isLoggedIn) {

        // 'localhost:8080/api/studybud/{Userstore.username}/login'

      let res = await fetch('/login', {
        method: 'get',
        headers: {
          'Accept': 'application/json',
          'Content-type': 'application/json'
        }
      });

      let result = await res.json();

        UserStore.loading = false;
        UserStore.isLoggedIn = true;
        UserStore.username = result.username;
      }
      else {
        UserStore.loading = false;
        UserStore.isLoggedIn = false;
      }

    }

    catch(e) {
      UserStore.loading = false;
      UserStore.isLoggedIn = false;

    }

  }

  async doLogout() {

    try {
      let res = await fetch('/logout', {
        method: 'post',
        headers: {
          'Accept': 'application/json',
          'Content-type': 'application/json'
        }
      });

      let result = await res.json();

      if (result && result.success) {
        
        UserStore.isLoggedIn = false;
        UserStore.username = '';
      }
      else {
        UserStore.loading = false;
        UserStore.isLoggedIn = false;
      }

    }

    catch(e) {
      console.log(e)

    }

  }

  render() {

    if (UserStore.loading) {
      return(
        <div className="App">
          <div className='container'>
            Loading...
            
          </div>
        </div>
      )
    }
    else {
      if(UserStore.isLoggedIn) {
        return (
          <BrowserRouter>
            <div className="App">
              <Navbar isLoggedIn={true}/>
              {/* Makes sure only one path is loaded at a time*/}
              <Switch>
                <Route exact path='/' component={Dashboard}/>                
                <Route path='/room/:id' component={RoomPage}></Route>                
                <Route path='/createroom' component={CreateRoom}></Route>
                <Route path='/searchrooms' component={SearchRoom}></Route>
              </Switch>    
            </div>
          </BrowserRouter>
        );
      }
      else if(!UserStore.isLoggedIn){
        return(
          // return sign in page
        <BrowserRouter>
        <div className="App">
          <Navbar isLoggedIn={false}/>
          {/* Makes sure only one path is loaded at a time*/}
          <Switch>            
            <Route path='/' component={SignIn}></Route>                        
            <Route path='/signup' component={SignUp}></Route>
          </Switch>
        </div>
      </BrowserRouter>
        );
        
      }
    }

    
  }//end of render()

  
}

export default observer(App);
