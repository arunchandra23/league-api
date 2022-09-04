import api from "../../apis/housePrediction";

export const sendFormData = (formData) => async (dispatch) => {
//   await api.get(
//     `url?bedRooms=${formData.bedRooms}&pin=${formData.pin}&houseType=${formData.houseType}&furnishing=${formData.furnishing}&parking=${formData.parking}&facing=${formData.facing}`
//   );
  dispatch({
    type: "SEND_FORM_DATA",
    payload: formData,
  });
};
