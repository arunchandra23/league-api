import React,{useState} from "react";
import { useEffect } from "react";
import axios from "axios";
import SearchBar from "./SearchBar";
import FileUpload from "./FileUpload";




let url="http://a1a3-34-86-151-150.ngrok.io"



const App=()=>{
    const [searchTerm,setSearchTerm]=useState('');
    const [status,setStatus]=useState('');
    useEffect(() => {
        var CancelToken = axios.CancelToken;
        var source = CancelToken.source();
        const downloadVideo=async()=>{
            setStatus(`Downloading videos for keyword "${searchTerm}"`);
            const downlodStatus=await axios.get(`${url}/downloadVideo?key=${searchTerm}`,{
                cancelToken: source.token,
                onDownloadProgress(progress) {
                    console.log('download progress:', progress);
                }
            });
            setStatus(downlodStatus.data.status);
        }
        if(searchTerm!==''){
            downloadVideo();
        }
        return ()=>{
            console.log("Hello")
            source.cancel('Operation canceled by the user.');
        };
    }, [searchTerm]);
    return (<>
    <div className="search-bar">
        <SearchBar setSearchTerm={setSearchTerm} label="Enter a keyword"/>
    </div>
        <h2>{status}</h2>
    <div className="file-upload">
        <FileUpload uploadUrl={`${url}/upload`}/>
    </div>
    </>);
};

export default App;