import React, { Component } from 'react'
import Notifications from './Notifications'
import RoomList from '../room/RoomList'

class Dashboard extends Component {

    constructor(props){
        super(props);
        this.state = {
            items: [],
            isLoaded: false,
        }
    }

    componentDidMount(){        

        try {
            fetch('http://localhost:8080/api/studybud/{UserStore.username}/dashboard')
            .then(res => res.json())
            .then(json => {
                this.setState({
                    isLoaded:true,
                    items: json,
                })
            })
        }
        catch(e){

        }

    }

    render() {

        var{ isLoaded, items }= this.state;

        if(!isLoaded){
            return <div>Loading Dashboard...</div>;
        }
        else{

            return (
                <div className="dashboard container">
                    <div className="row">
                        <div className="col s12 m6">
                            <RoomList/>
                            <ul>
                                {items.map(item => (
                                    <li key={item.id}>
                                        Room: {item.name} | Subject: {item.subject}
                                    </li>
                                ))};
                            </ul>
                        </div>
                        <div className="col s12 m5 offset-m1">
                            <Notifications/>
                        </div>
                    </div>
                </div>
            )

        }

        
    }
}

export default Dashboard
