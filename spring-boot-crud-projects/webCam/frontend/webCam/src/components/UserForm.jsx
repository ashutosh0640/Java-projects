import React, { useState } from 'react';

const UserForm = () => {
  const [user, setUser] = useState({
    firstName: '',
    middleName: '',
    lastName: '',
    mobileNo: '',
    email: '',
    city: '',
    state: '',
    profileImage: null,
  });
  const [cameraOpen, setCameraOpen] = useState(false);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setUser((prevUser) => ({ ...prevUser, [name]: value }));
  };

  const handleImageUpload = (e) => {
    const file = e.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        setUser((prevUser) => ({ ...prevUser, profileImage: reader.result }));
      };
      reader.readAsDataURL(file);
    }
  };

  const handleCaptureImage = (image) => {
    setUser((prevUser) => ({ ...prevUser, profileImage: image }));
    setCameraOpen(false);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const response = await fetch('http://localhost:8080/users/save', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(user),
    });

    if (response.ok) {
      alert('User saved successfully!');
      setUser({
        firstName: '',
        middleName: '',
        lastName: '',
        mobileNo: '',
        email: '',
        city: '',
        state: '',
        profileImage: null,
      });
    } else {
      alert('Error saving user.');
    }
  };

  return (
    <div>
      <h1>Register User</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label for="firstName">First Name:</label>
          <input
            type="text"
            id='firstName'
            name="firstName"
            value={user.firstName}
            onChange={handleInputChange}
            required
          />
        </div>
        <div>
          <label>Middle Name:</label>
          <input
            type="text"
            name="middleName"
            value={user.middleName}
            onChange={handleInputChange}
          />
        </div>
        <div>
          <label>Last Name:</label>
          <input
            type="text"
            name="lastName"
            value={user.lastName}
            onChange={handleInputChange}
            required
          />
        </div>
        <div>
          <label for="mobile">Mobile No:</label>
          <input
            type="text"
            id='mobile'
            name="mobileNo"
            value={user.mobileNo}
            onChange={handleInputChange}
            required
          />
        </div>
        <div>
          <label>Email:</label>
          <input
            type="email"
            name="email"
            value={user.email}
            onChange={handleInputChange}
            required
          />
        </div>
        <div>
          <label>City:</label>
          <input
            type="text"
            name="city"
            value={user.city}
            onChange={handleInputChange}
          />
        </div>
        <div>
          <label>State:</label>
          <input
            type="text"
            name="state"
            value={user.state}
            onChange={handleInputChange}
          />
        </div>
        <div>
          <label>Profile Image:</label>
          <div>
            <input
              type="file"
              accept="image/*"
              onChange={handleImageUpload}
            />
          </div>
          <div>
            <button type="button" onClick={() => setCameraOpen(true)}>
              Capture Image
            </button>
          </div>
        </div>
        {cameraOpen && (
          <Camera
            onCapture={(image) => handleCaptureImage(image)}
            onClose={() => setCameraOpen(false)}
          />
        )}
        {user.profileImage && (
          <div>
            <p>Selected Image:</p>
            <img
              src={user.profileImage}
              alt="Profile Preview"
              style={{ width: '100px', height: '100px' }}
            />
          </div>
        )}
        <button type="submit">Save User</button>
      </form>
    </div>
  );
};

// Camera component for capturing images
const Camera = ({ onCapture, onClose }) => {
  const [stream, setStream] = useState(null);
  const videoRef = React.useRef();

  React.useEffect(() => {
    navigator.mediaDevices
      .getUserMedia({ video: true })
      .then((stream) => {
        setStream(stream);
        if (videoRef.current) {
          videoRef.current.srcObject = stream;
        }
      })
      .catch((err) => {
        console.error('Error accessing camera:', err);
      });

    return () => {
      if (stream) {
        stream.getTracks().forEach((track) => track.stop());
      }
    };
  }, []);

  const captureImage = () => {
    const canvas = document.createElement('canvas');
    canvas.width = videoRef.current.videoWidth;
    canvas.height = videoRef.current.videoHeight;
    canvas.getContext('2d').drawImage(videoRef.current, 0, 0);
    const image = canvas.toDataURL('image/png');
    onCapture(image);
  };

  return (
    <div>
      <video ref={videoRef} autoPlay />
      <div>
        <button onClick={captureImage}>Capture</button>
        <button onClick={onClose}>Close</button>
      </div>
    </div>
  );
};

export default UserForm;