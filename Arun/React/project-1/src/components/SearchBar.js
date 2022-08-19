import React,{useState} from "react";

const SearchBar=({label,setSearchTerm})=>{
    const [term,setTerm]=useState('')
    const onFormSubmit = (e)=>{
        e.preventDefault();
        setSearchTerm(term);
        
    }
    return (<div>
        <form className="keyword-form" onSubmit={(e)=>{onFormSubmit(e)}}>
            <label>{label}</label>
            <input type="text" value={term} onChange={(e)=>{setTerm(e.target.value)}}/>
            <button type="submit">Submit</button>
        </form>
    </div>);
};

SearchBar.defaultProps={
    label:"Give a label",
    setSearchTerm:"pass a finction to set search term"
};

export default SearchBar;

