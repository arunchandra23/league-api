import React from "react";
import "./Form.css";
import { connect } from "react-redux";
import { sendFormData } from "../../Redux/actions";


const Form = ({sendFormData}) => {
  const onFormSubmit = (e) => {
    e.preventDefault();
    console.log(e);
    // console.log(e.target[3].value);
    const formData={
      bedRooms:e.target[0].value,
      pin:e.target[1].value,
      houseType:e.target[2].value,
      furnishing:e.target[3].value,
      parking:e.target[4].value,
      facing:e.target[5].value
    }
    console.log(formData);
    sendFormData(formData);


  };
  return (
    <>
    <nav className="navbar bg-light">
  <div className="container-fluid">
    <a className="navbar-brand" href="/">Hello User Welcome</a>
  </div>
</nav>
    <div className="container">
      <form
        onSubmit={(e) => {
          onFormSubmit(e);
        }}
      >
        <div className="mb-3 row">
          <label>Enter Number of bedrooms</label>
          <input required type="number" id="bedrooms-input" />
        </div>
        <div className="mb-3 row">
          <label>Enter Locality pin</label>
          <input required type="number" id="" />
        </div>
        {/* Enter House type */}
        <div className="house-type">
          <label>Enter House type:  </label>
          <select id="">
            <option value="0">Anyone</option>
            <option value="1">Bachelor</option>
            <option value="2">Company</option>
            <option value="3">Family</option>
          </select>
        </div>

        <br />
        {/* Enter choice of furniture */}
        <div className="furniture-type">
          <label>Enter choice of furnishing:  </label>

          <select id="">
            <option value="2">Semi-Furnished</option>
            <option value="0">Fully-Furnished</option>
            <option value="1">Not-Furnished</option>
          </select>
        </div>

        <br />
        {/* Type of Parking */}
        <div className="parking-type">
          <label>Type of Parking:  </label>

          <select id="">
            <option value="0">Both</option>
            <option value="1">Four-wheeler</option>
            <option value="2">None</option>
            <option value="3">Two-wheeler</option>
          </select>
        </div>

        <br />
        {/* Type of Facing */}
        <div className="facing-type">
          <label>Type of Facing:  </label>

          <select id="">
            <option value="0">East</option>
            <option value="1">North</option>
            <option value="2">North-East</option>
            <option value="3">North-West</option>
            <option value="4">South</option>
            <option value="5">South-East</option>
            <option value="6">South-West</option>
            <option value="7">West</option>
          </select>
        </div>

        <br />
        <button type="submit" className="btn btn-primary">
          Get my rent
        </button>
      </form>
    </div>
    </>
  );
};

const mapStatesToProps = (state) => {
  return {}
}

export default connect(mapStatesToProps,{sendFormData})(Form);
