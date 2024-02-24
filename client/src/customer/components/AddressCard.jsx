import React from "react";
import HomeIcon from "@mui/icons-material/Home";
import { Button } from "@mui/material";
const AddressCard = ({ address, selectAddress, modalOpen, modalClose }) => {
  const handleSelectAddress = (selectedAddress) => {
    selectAddress(selectedAddress);
  };

  const openAddressModal = () => {
    modalOpen();
  };
  return (
    <div className="flex space-x-5 lg:w-64 m-5 p-5">
      <HomeIcon />
      <div className="space-y-3 text-gray-500 ">
        <h1 className="font-semibold text-lg text-white">{address.title}</h1>
        {address.id !== 1 && (
          <>
            <p>{address.streetAddress}</p>
            <p>{address.city}</p>
            <p>
              {address.state}-{address.pincode}
            </p>
          </>
        )}

        {address.title === "Add Address" ? (
          <Button variant="outlined" fullWidth onClick={openAddressModal}>
            + Add
          </Button>
        ) : (
          <Button
            variant="outlined"
            fullWidth
            onClick={() => handleSelectAddress(address)}
          >
            Select
          </Button>
        )}
      </div>
    </div>
  );
};

export default AddressCard;
