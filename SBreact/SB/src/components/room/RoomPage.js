import React from 'react'


const RoomPage = (props) => {
    const id = props.match.params.id;
    return(
        <div className="container section project-details">
            <div className="card z-depth-0">
                <div className="card-content">
                    <span className="card-title">Room Name - {id}</span>
                    <p>Values</p>
                </div>
                <div className="card-action grey lighten-4 grey-text">
                    <div>Created by user</div>
                    <div>28th September</div>
                </div>
            </div>
        </div>

    )
}

export default RoomPage
