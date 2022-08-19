import React,{ useState} from 'react';
import { useRef } from 'react';
import "./Form.css";

const Form= ({passDetails}) => {
    let [formData,setFormData] = useState([]);
    const formRef=useRef()

    const onFormSubmit = (event) =>{
        event.preventDefault();
        setFormData(formData.splice(0,formData.length));
        let dic={}
        let i=0
        for(i=0; i<formRef.current.length-1; i++){
            let data = formRef.current[i]
            console.log(formRef.current[i])
            if(data.value===""){
                data.classList.add("border-alert");
            }else{
                data.classList.remove("border-alert");
                dic[data.id]=data.value;
            }
        }

        formData.push(dic);
        passDetails(formData[0])
    }
        return (<>
            <div className='container'>
                <div className='form-container'>
                        <h1>Employee Registration From</h1>
                    <form className='form' ref={formRef} onSubmit={(e) =>{onFormSubmit(e);}}>
                            
                        
                            <label >First-name: </label>
                            <input type="text" className='first-name' id="first-name" />
                        
                        
                            <label >Last-name: </label>
                            <input type="text" className='last-name' id="last-name" />
                        
                        
                            <label >Email: </label>
                            <input type="email" className='email' id="email" />
                        
                        
                            <label >Contact Number: </label>
                            <input type="tel" pattern="[0-9]{10}" className='contact' id="contact" />
                        
                        
                            <label >Address: </label>
                            <input type="text" className='address' id="address" />
                        
                        
                        <label >Applying for position: </label>
                            <select name='position' id='position'>
                                <option value="developer">Developer</option>
                                <option value="tester">Tester</option>
                                <option value="hr">HR</option>
                            </select>
                        
                        
                            <input type="file" id="file-upload" />
                        
                        
                        <button className='submit-btn' type="submit">Submit</button>
                    </form>
                    
                </div>
                
            </div>
        </>
    );  
}


export default Form;