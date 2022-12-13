import { Paper } from "@material-ui/core";
import React from "react";
import ImageUploader from "../buttons/ImageUploader";

const UploadPage = () => {
  return (<>  
  <Paper elevation={3}>
    Look at what we have here:
  </Paper>
  
  <ImageUploader onImageUpload={uploadImage}/>
  </>);
}

function uploadImage(image) {
  // post data via rest api consumer client to be built
  console.log("file length: " + image.length);
}

export default UploadPage;