import React, { Component } from 'react'
import Notifications from './Notifications'
import RoomList from '../room/RoomList'

class Dashboard extends Component {

    state = {
        isFetching: false
    }


    // componentDidMount(){

    //     fetch('localhost:8080/api/studybud/{username}/')
    //         .then(res => res.json())
    //         .then(json => {
    //             this.setState({
    //                 isLoaded:true,
    //                 items: json,
    //             })
    //         })

    // }

    render() {

        if(this.isFetching){
            return <div>Loading Dashboard...</div>;
        }
        else{
            return (
                <div className="dashboard container">
                    <div className="row">
                        <div className="col s12 m6">
                            <RoomList />
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
