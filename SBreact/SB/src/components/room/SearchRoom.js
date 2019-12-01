import React, { Component } from 'react'

class SearchRoom extends Component {

    state = {
        roomname: '',
        subject: '',
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

    render() {
        return (
            <div className="container">
                <form onSubmit={this.handleSubmit} className="white">
                    <h5 className="grey-text text-darken-3">Search Rooms</h5>
                    <br/>
                    <div className="input-field">
                        <label htmlFor="roomname">Room Name</label>
                        <input type="text" id="roomname" onChange={this.handleChange}/>
                    </div>
                    <div className="input-field">
                        <label htmlFor="subject">Subject</label>
                        <input type="text" id="subject" onChange={this.handleChange}/>
                    </div>
                    <div className="input-field">
                        <button className="btn blue lighten-1 z-depth-0">Search</button>

                    </div>
                </form>
                
            </div>
        )
    }
}

export default SearchRoom