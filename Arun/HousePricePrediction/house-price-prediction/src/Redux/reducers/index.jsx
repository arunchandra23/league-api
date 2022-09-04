import { combineReducers } from "redux";
import sendFormDataReducer from "./sendFormDataReducer";

export default combineReducers({
    FormData:sendFormDataReducer
});