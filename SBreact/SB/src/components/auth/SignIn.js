import React, { Component } from 'react'
import UserStore from '../../stores/UserStore'

class SignIn extends Component {

    constructor(props){
        super(props);
        this.state = {
            username: '',
            password: '',
            buttonDisabled: false
        }
    }

    

    handleChange = (e) => {
        this.setState({
            /* targets the element that is actually changing */
            [e.target.id]: e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        console.log(this.state);
    }

    async resetForm() {
        this.setState ({
            username: '',
            password: '',
            buttonDisabled: false
        })
    }

    async doLogin() {


        if(!this.state.username){
            return;
        }
        if(!this.state.password){
            return;
        }

        this.setState({
            buttonDisabled: true
        })

        try {

            let res = await fetch('http://localhost:8080/api/studybud/login', {
                method: 'POST',
                mode:'cors',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'               
                },
                body: JSON.stringify({
                    username: this.state.username,
                    password: this.state.password
                })
            });

            let result = await res.json();

            if (result === 1){
                UserStore.isLoggedIn = true;
                UserStore.username = result.username;
            }
            else if (result === 0){
                this.resetForm();
                alert(result);
            }
        }

        catch(e) {
            console.log(e);
            this.resetForm();
        }
    }

    async componentDidMount() {

        try {
          let res = await fetch('localhost:8080/api/studybud/isLoggedIn', {
            method: 'get',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
             },
              body: JSON.stringify({
                  username: UserStore.username,
              })
          });

          let result = await res.json();
    
          if (result && result.success) {
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

    render() {
        return (
            <div className="container">
                <form onSubmit={this.handleSubmit} className="white">
                    <h5 className="grey-text text-darken-3">Sign In</h5>
                    <br/>
                    <div className="input-field">
                        <label htmlFor="username">Username</label>
                        <input type="text" id="username" onChange={this.handleChange}/>
                    </div>
                    <div className="input-field">
                        <label htmlFor="password">Password</label>
                        <input type="password" id="password" onChange={this.handleChange}/>
                    </div>
                    <div className="input-field">
                        <button 
                        disabled={this.state.buttonDisabled}
                        onClick={() => this.doLogin()}
                        className="btn blue lighten-1 z-depth-0">Login</button>

                    </div>
                </form>
                
            </div>
        )
    }
}

export default SignIn
