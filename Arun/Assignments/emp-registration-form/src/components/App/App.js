import React,{ useState} from 'react';
import Form from '../Form/Form';
import "./App.css";
import Submitted from '../Submitted/Submitted';

const App= () => {
    let [stat,setStat]=useState(0);
    let [data,setData]=useState();
    const getDetails = (details) =>{
        setStat(Object.keys(details).length);
        setData({
            'Name':details['first-name']+" "+details['last-name'],
            'Email':details['email'],
            'Contact':details['contact'],
            'Address':details['address'],
            'Position':details['position'],
            'Path':details['file-upload']
        })
        console.log(details)
    }
    return (
        <div>
            {stat===7?<Submitted displayData={data}/>:<Form passDetails={getDetails}/>}
        </div>
    );
}

export default App;