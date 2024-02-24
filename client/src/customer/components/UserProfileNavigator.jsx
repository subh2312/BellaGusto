import React from "react";
import ShoppingBagIcon from "@mui/icons-material/ShoppingBag";
import HomeIcon from "@mui/icons-material/Home";
import FavoriteIcon from "@mui/icons-material/Favorite";
import AccountBalanceWalletIcon from "@mui/icons-material/AccountBalanceWallet";
import LogoutIcon from "@mui/icons-material/Logout";
import { Divider } from "@mui/material";
import { useNavigate } from "react-router-dom";

const menuItems = [
  {
    id: 1,
    title: "Home",
    icon: <HomeIcon />,
  },
  {
    id: 2,
    title: "Orders",
    icon: <ShoppingBagIcon />,
  },
  {
    id: 3,
    title: "Favorite",
    icon: <FavoriteIcon />,
  },
  {
    id: 4,
    title: "Payments",
    icon: <AccountBalanceWalletIcon />,
  },
  {
    id: 5,
    title: "Logout",
    icon: <LogoutIcon />,
  },
];
const UserProfileNavigator = ({ selectMenu }) => {
  const navigate = useNavigate();
  const handleNavigate = (title) => {
    if (title !== "Logout") {
      selectMenu(title);
    } else {
      navigate("/");
      alert("Logout");
    }
  };
  return (
    <div className="group h-[90vh] flex flex-col justify-center space-y-8 text-xl">
      <Divider />
      {menuItems.map((item) => (
        <>
          <div
            onClick={() => handleNavigate(item.title)}
            className="px-5 flex items-center space-x-5 cursor-pointer"
          >
            {item.icon}
            <span>{item.title}</span>
          </div>
          <Divider />
        </>
      ))}
    </div>
  );
};

export default UserProfileNavigator;
