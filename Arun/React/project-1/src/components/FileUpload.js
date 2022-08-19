import React, { useRef } from "react";
import { useState } from "react";
import axios from "axios";

const FileUpload = ({uploadUrl}) => {
  const FileRef = useRef();
  // const [selectedFile,setSelectedFile]=useState(null);
  const [loaded, setLoaded] = useState(0);
  const handleFormSubmit = (event) => {
    event.preventDefault();
    // console.log(selectedFile.name)
    const data = new FormData();
    data.append(
      "file",
      FileRef.current.files[0],
      FileRef.current.files[0].name
    );
    axios
      .post(uploadUrl, data, {
        onUploadProgress: (ProgressEvent) => {
          setLoaded((ProgressEvent.loaded / ProgressEvent.total) * 100);
        },
      })
      .then((res) => {
        console.log(res.statusText);
      });
  };
  return (
    <>
      <form
        onSubmit={(event) => {
          handleFormSubmit(event);
        }}
      >
        <input
          ref={FileRef}
          accept="video/mp4,video/x-m4v,video/*"
          type="file"
          id="myFile"
          name="filename"
        />
        <button type="submit">Upload</button>
      </form>
      <h3>{loaded === 0 ? "" : `Uploaded ${Math.round(loaded)}%`}</h3>
    </>
  );
};

export default FileUpload;
