import React, { useRef, useState } from "react";
import Webcam from "react-webcam";
import axios from "axios";

const CameraCapture = () => {
  const webcamRef = useRef(null);
  const [image, setImage] = useState(null);

  const capturePhoto = () => {
    const imageSrc = webcamRef.current.getScreenshot();
    setImage(imageSrc);
  };

  const savePhoto = async () => {
    if (!image) return alert("No photo captured!");

    try {
      await axios.post("http://localhost:8080/api/photos", {
        image: image.split(",")[1], // Remove data URL header
      });
      alert("Photo saved successfully!");
    } catch (error) {
      console.error("Error saving photo:", error);
      alert("Failed to save photo.");
    }
  };

  return (
    <div>
      <h1>Camera Capture</h1>
      <Webcam
        audio={false}
        ref={webcamRef}
        screenshotFormat="image/jpeg"
        width={400}
        height={300}
      />
      <div>
        <button onClick={capturePhoto}>Capture Photo</button>
        <button onClick={savePhoto}>Save Photo</button>
      </div>
      {image && (
        <div>
          <h2>Preview</h2>
          <img src={image} alt="Captured" width={200} />
        </div>
      )}
    </div>
  );
};

export default CameraCapture;
