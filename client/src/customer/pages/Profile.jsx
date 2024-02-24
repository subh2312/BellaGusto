import React, { useState } from "react";
import UserProfileNavigator from "../components/UserProfileNavigator";
import { Divider } from "@mui/material";
import UserProfile from "../components/UserProfile";
import HomeMenuDetails from "../components/HomeMenuDetails";
import OrderHistory from "../components/OrderHistory";
import FavoriteRestaurants from "../components/FavoriteRestaurants";
import PaymentOptions from "../components/PaymentOptions";

const Profile = () => {
  const [selectedMenu, setSelectedMenu] = useState("Home");

  const renderSelectedComponent = () => {
    switch (selectedMenu) {
      case "Home":
        return <HomeMenuDetails />;
      case "Profile":
        return <UserProfile />;
      case "Orders":
        return <OrderHistory />;
      case "Favorite":
        return <FavoriteRestaurants />;
      case "Payments":
        return <PaymentOptions />;

      default:
        return <HomeMenuDetails />; // Handle unknown menu options
    }
  };

  return (
    <div className="lg:flex">
      <div className="lg:w-[20%]">
        <UserProfileNavigator selectMenu={setSelectedMenu} />
      </div>
      <Divider orientation="vertical" flexItem />
      <div className="lg:w-[80%] flex items-center justify-center">
        {renderSelectedComponent()}
      </div>
    </div>
  );
};

export default Profile;
