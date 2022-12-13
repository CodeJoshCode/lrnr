import * as React from 'react';
import Button from '@material-ui/core/Button';

export default function ImageUploader(uploaderProps) {
  const [image, setImage] = React.useState(null);

  const handleImageChange = (event) => {
    if (event.target.files) {
      setImage(event.target.files[0]);
    }
  };

  const handleImageUpload = () => {
    if (image) {
      uploaderProps.onImageUpload(image);
      setImage(null);
    }
  };

  return (
    <>
      <input type="file" onChange={handleImageChange} />
      <Button variant="contained" onClick={handleImageUpload}>
        Upload Image
      </Button>
    </>
  );
};