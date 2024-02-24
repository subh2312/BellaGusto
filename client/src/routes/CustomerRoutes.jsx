import React from "react";
import { Route, Routes } from "react-router-dom";
import HomePage from "../customer/pages/HomePage";
import RestaurantPage from "../customer/pages/RestaurantPage";
import CartPage from "../customer/pages/CartPage";
import Navbar from "../customer/components/Navbar";
import Profile from "../customer/pages/Profile";

const CustomerRoutes = () => {
  return (
    <div className="relative">
      <div className="sticky top-0 z-50">
        <Navbar />
      </div>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route
          path="/restaurant/:city/:name/:id"
          element={<RestaurantPage />}
        />
        <Route path="/cart" element={<CartPage />} />
        <Route path="/profile/:id" element={<Profile />} />
      </Routes>
    </div>
  );
};

export default CustomerRoutes;
