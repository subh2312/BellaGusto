import React, { Fragment, useEffect, useRef, useState } from "react";
import cartItems from "../../utils/cartItems";
import CartItem from "../components/CartItem";
import * as Yup from "yup";
import {
  Box,
  Button,
  Divider,
  MenuItem,
  Modal,
  TextField,
} from "@mui/material";
import AddressCard from "../components/AddressCard";
import addresses from "../../utils/addresses";
import { useFormik } from "formik";
const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};
const CartPage = () => {
  const [items, setItems] = useState([]);
  const [itemTotal, setItemTotal] = useState();
  const [deliveryFee, setDeliveryFee] = useState();
  const [gstFee, setGstFee] = useState();
  const [totalPrice, setTotalPrice] = useState();
  const [selectedAddress, setSelectedAddress] = useState();
  const [newAddress, setNewAddress] = useState({
    title: "",
    type: "",
    streetAddress: "",
    state: "",
    pincode: "",
    city: "",
  });

  const validationSchema = Yup.object().shape({
    title: Yup.string().required("Street Address is Required"),
    type: Yup.string().required("Street Address is Required"),
    streetAddress: Yup.string().required("Street Address is Required"),
    city: Yup.string().required("Street Address is Required"),
    state: Yup.string().required("Street Address is Required"),
    pincode: Yup.string().required("Street Address is Required"),
  });

  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const formik = useFormik({
    initialValues: {
      title: "",
      type: "",
      streetAddress: "",
      city: "",
      state: "",
      pincode: "",
    },
    validationSchema: validationSchema,
    onSubmit: (values) => {
      // Handle form submission here

      const addr = {
        id: parseInt(addresses.length + 1),
        ...values,
      };
      setNewAddress(addr);
      addresses.push(addr);
      setSelectedAddress(addr);
      console.log("Form submitted with values:", addr);
      handleClose(); // Close the modal after submission
    },
  });

  const { values, handleChange, handleSubmit, errors, touched } = formik;

  const createOrder = () => {
    console.log(items);
    console.log(totalPrice);
    console.log(selectedAddress);
  };

  useEffect(() => {
    updateCart();
  }, [items.length]);

  const updateCart = () => {
    const items = cartItems;
    const total = items.reduce(
      (total, item) => total + item.qty * item.price,
      0
    );
    const delivery = 1.99;
    const gst = 0.08 * (total + delivery);
    const totalPrice = total + delivery + gst;
    setItems(items);
    setItemTotal(parseFloat(total.toFixed(2)));
    setDeliveryFee(1.99);
    setGstFee(parseFloat(gst.toFixed(2)));
    setTotalPrice(parseFloat(totalPrice.toFixed(2)));
  };

  const handleAddItem = (item) => {
    const updatedItems = [...items];
    const index = updatedItems.findIndex(
      (cartItem) => cartItem.title === item.title
    );

    if (index !== -1) {
      updatedItems[index].qty += 1;
    }

    setItems(updatedItems);
    updateCart();
  };

  const handleRemoveItem = (item) => {
    const updatedItems = [...items];
    const index = updatedItems.findIndex(
      (cartItem) => cartItem.title === item.title
    );

    if (index !== -1) {
      if (updatedItems[index].qty >= 1) {
        updatedItems[index].qty -= 1;
      } else {
        // If quantity is 1 or less, remove the item from the array
        updatedItems.splice(index, 1);
      }
    }

    setItems(updatedItems);
    updateCart();
  };

  return (
    <Fragment>
      <main className="lg:flex justify-between">
        <section className="lg:w-[30%] space-y-6 min-h-screen pt-10">
          <div className="space-y-6">
            {items.map((item, index) => (
              <CartItem
                key={index}
                item={item}
                addItem={handleAddItem}
                removeItem={handleRemoveItem}
              />
            ))}
          </div>
          <Divider />
          <div className="billDetails px-5 text-sm">
            <p className="font-extralight py-5">Bill Details</p>
            <div className="space-y-3">
              <div className="flex justify-between text-gray-400">
                <p>Item Total</p>
                <p>${itemTotal}</p>
              </div>
              <div className="flex justify-between text-gray-400">
                <p>Delivery</p>
                <p>${deliveryFee}</p>
              </div>
              <div className="flex justify-between text-gray-400">
                <p>GST and Restaurant Charges</p>
                <p>${gstFee}</p>
              </div>
              <Divider />
              <div className="flex justify-between text-gray-400">
                <p>Total</p>
                <p>${totalPrice}</p>
              </div>
              <Divider />
              <Button
                fullWidth
                variant="outlined"
                sx={{ marginTop: "10px", marginBottom: "10px" }}
                disabled={selectedAddress === undefined}
                onClick={createOrder}
              >
                Continue
              </Button>
            </div>
          </div>
        </section>
        <Divider orientation="vertical" flexItem />
        <section className="lg:w-[70%] px-5">
          <div>
            <h1 className="text-center font-semibold text-2xl py-10">
              Choose Delivery Address
            </h1>
            <div className="flex flex-wrap justify-between">
              {addresses &&
                addresses.map((address) => (
                  <AddressCard
                    key={address.id}
                    address={address}
                    selectAddress={setSelectedAddress}
                    modalOpen={handleOpen}
                    modalClose={handleClose}
                  />
                ))}
            </div>
          </div>
        </section>
      </main>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <form onSubmit={handleSubmit}>
            <TextField
              id="modal-modal-title"
              fullWidth
              placeholder="Title"
              name="title"
              value={values.title}
              onChange={handleChange}
              error={touched.title && Boolean(errors.title)}
              helperText={touched.title ? errors.title : ""}
            />
            <TextField
              id="modal-modal-title"
              select
              fullWidth
              placeholder="Type"
              name="type"
              value={values.type}
              onChange={handleChange}
              error={touched.type && Boolean(errors.type)}
              helperText={touched.type ? errors.type : ""}
            >
              <MenuItem value="Home">Home</MenuItem>
              <MenuItem value="Work">Work</MenuItem>
              <MenuItem value="Other">Other</MenuItem>
            </TextField>
            <TextField
              id="modal-modal-title"
              fullWidth
              placeholder="Street Address"
              name="streetAddress"
              value={values.streetAddress}
              onChange={handleChange}
              error={touched.streetAddress && Boolean(errors.streetAddress)}
              helperText={touched.streetAddress ? errors.streetAddress : ""}
            />
            <div className="flex items-center justify-between mt-3 space-x-3">
              <TextField
                id="modal-modal-title"
                sx={{ width: "50%" }}
                placeholder="State"
                name="state"
                value={values.state}
                onChange={handleChange}
                error={touched.state && Boolean(errors.state)}
                helperText={touched.state ? errors.state : ""}
              />
              <TextField
                id="modal-modal-title"
                sx={{ width: "50%" }}
                placeholder="Pincode"
                name="pincode"
                value={values.pincode}
                onChange={handleChange}
                error={touched.pincode && Boolean(errors.pincode)}
                helperText={touched.pincode ? errors.pincode : ""}
              />
            </div>
            <TextField
              id="modal-modal-description"
              sx={{ mt: 2 }}
              fullWidth
              placeholder="City"
              name="city"
              value={values.city}
              onChange={handleChange}
              error={touched.city && Boolean(errors.city)}
              helperText={touched.city ? errors.city : ""}
            />

            <div className="mt-3 flex items-center justify-center">
              <Button type="submit" variant="contained">
                Deliver Here
              </Button>
            </div>
          </form>
        </Box>
      </Modal>
    </Fragment>
  );
};

export default CartPage;
