import React, { Component } from 'react'
import ReactTable from 'react-table'

class RoomList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            username:props.username,
            rooms:[],
            columns:["Room ID", "Name", "Subject", "Location", "Description", "Admin"]
        }
    }

    fillTable (username) {
        this.getAllRoomsJoinedByUser(username).then(result => this.setState({rooms:result}));
    }

    async getAllRoomsJoinedByUser (username) {
        this.setState({isFetching:true})
        try {
            let res = await fetch('http://localhost:8080/api/studybud/' + username + '/dashboard', {
                method: 'GET',
                mode: 'cors'
            });

            let result = await res.json();
            return this.renderTable(result);
        } catch (e) {
            alert('Could not obtain rooms.');
        }
    };

    renderTable() {
        return (<div>
                    <ReactTable
                        data={this.rooms}
                        columns={this.columns}
                        defaultPageSize = {2}
                        pageSizeOptions = {[2,4, 6]}
                    />
                </div>
        );
    }

    render (props) {
        this.fillTable(this.username);
        return (
            <div>
                {this.getAllRoomsJoinedByUser}
            </div>
        );
    }
}
export default RoomList