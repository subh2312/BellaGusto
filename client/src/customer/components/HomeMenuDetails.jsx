import { Button, TextField, Avatar } from "@mui/material";
import React, { useState } from "react";
import EditIcon from "@mui/icons-material/Edit";
import CloseIcon from "@mui/icons-material/Close";
import CheckIcon from "@mui/icons-material/Check";
import * as Yup from "yup";
import { useFormik } from "formik";
import "./HomeMenuDetails.css";
const HomeMenuDetails = () => {
  const [isEditing, setIsEditing] = useState(false);
  const [profilePicture, setProfilePicture] = useState(null);
  const validationSchema = Yup.object().shape({
    firstName: Yup.string().required("First Name is required"),
    lastName: Yup.string().required("Last Name is required"),
    email: Yup.string().required("Email is required"),
    phone: Yup.number().required("Phone is required"),
    gender: Yup.string().required("Gender is required"),
  });
  const formik = useFormik({
    initialValues: {
      firstName: "Subhankar",
      lastName: "Mohanty",
      email: "test@test.com",
      phone: "9988776655",
      gender: "Male",
    },
    validationSchema: validationSchema,
    onSubmit: (values) => {
      const formData = {
        ...values,
        profilePicture: profilePicture,
      };
      console.log(formData);
      setIsEditing(false);
    },
  });
  const { values, handleChange, handleSubmit, errors, touched } = formik;
  const handleProfilePictureChange = (event) => {
    const file = event.target.files[0];
    if (file) {
      setProfilePicture(URL.createObjectURL(file));
    }
  };
  return (
    <div className="w-[40%]">
      <div>
        <div className="avatar-container flex justify-center">
          <Avatar
            alt="Profile Picture"
            src={profilePicture || "/path/to/default-profile-picture.jpg"}
            sx={{ width: 100, height: 100, marginBottom: 10 }}
          />
          {isEditing && (
            <label
              htmlFor="profile-picture-input"
              className="avatar-overlay cursor-pointer"
            >
              <EditIcon sx={{ color: "white" }} />
            </label>
          )}
        </div>
        <input
          type="file"
          id="profile-picture-input"
          accept="image/*"
          onChange={handleProfilePictureChange}
          style={{ display: "none" }}
        />
      </div>
      <form onSubmit={handleSubmit} className="space-y-5 w-[100%]">
        <TextField
          fullWidth
          name="firstName"
          label="First Name"
          value={values.firstName}
          onChange={handleChange}
          error={touched.firstName && Boolean(errors.firstName)}
          helperText={touched.firstName ? errors.firstName : ""}
          disabled={!isEditing}
        />
        <TextField
          name="lastName"
          label="Last Name"
          value={values.lastName}
          onChange={handleChange}
          error={touched.lastName && Boolean(errors.lastName)}
          helperText={touched.lastName ? errors.lastName : ""}
          fullWidth
          disabled={!isEditing}
        />
        <TextField
          name="email"
          label="E-Mail"
          value={values.email}
          onChange={handleChange}
          error={touched.email && Boolean(errors.email)}
          helperText={touched.email ? errors.email : ""}
          fullWidth
          disabled={!isEditing}
        />
        <TextField
          name="phone"
          label="Phone"
          value={values.phone}
          onChange={handleChange}
          error={touched.phone && Boolean(errors.phone)}
          helperText={touched.phone ? errors.phone : ""}
          fullWidth
          disabled={!isEditing}
        />
        <TextField
          name="gender"
          label="Gender"
          value={values.gender}
          onChange={handleChange}
          error={touched.gender && Boolean(errors.gender)}
          helperText={touched.gender ? errors.gender : ""}
          fullWidth
          disabled={!isEditing}
        />
        <div className="flex items-center justify-center">
          {isEditing ? (
            <div className="flex space-x-10">
              <Button variant="outlined" type="submit">
                <CheckIcon sx={{ paddingRight: "4px" }} />
                <span>Save</span>
              </Button>
              <Button
                variant="outlined"
                onClick={() => setIsEditing(!isEditing)}
              >
                <CloseIcon sx={{ paddingRight: "4px" }} />
                <span>Cancel</span>
              </Button>
            </div>
          ) : (
            <Button variant="outlined" onClick={() => setIsEditing(!isEditing)}>
              <EditIcon sx={{ paddingRight: "4px" }} />
              <span>Edit</span>
            </Button>
          )}
        </div>
      </form>
    </div>
  );
};

export default HomeMenuDetails;
