const sendFormDataReducer=(state={},action)=> {
    switch (action.type) {
        case 'SEND_FORM_DATA':
            console.log(action.payload);
            return action.payload
    
        default:
            return state;
    }
    
};
export default sendFormDataReducer;