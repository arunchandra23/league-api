import React from 'react';
import './Submitted.css'

const Submitted=({displayData})=>{
    return (
        <div className='form-status-container'>
            <h1>Form submission success</h1>
            <div className='display-data'>    
                <b>Name:</b>
                <p>{displayData.Name}</p>
                <b>Email</b>
                <p>{displayData.Email} </p>
                <b>Contact</b>
                <p>{displayData.Contact}</p>
                <b>Address</b>
                <p>{displayData.Address}</p>
                <b>Position:</b>
                <p>{displayData.Position}</p>
                <b>File path</b>
                <p>{displayData.Path}</p>
            </div>
        </div>
    );
}

export default Submitted;