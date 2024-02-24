import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import SearchIcon from "@mui/icons-material/Search";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import { IconButton, Menu, MenuItem } from "@mui/material";
import PersonIcon from "@mui/icons-material/Person";
import "./Navbar.css";
const Navbar = () => {
  const navigate = useNavigate();
  const [user, setUser] = useState({
    isAdmin: true,
    isLoggedIn: true,
  });
  const [anchorEl, setAnchorEl] = React.useState(null);
  const open = Boolean(anchorEl);

  const handleClose = () => {
    setAnchorEl(null);
  };

  const handleProfileClick = () => {
    setAnchorEl(null);
    navigate("/profile/:id");
  };

  const handleLogoutClick = () => {
    setAnchorEl(null);
  };

  const handleOnUserClick = (event) => {
    if (user.isLoggedIn) {
      if (user.isAdmin) {
        setAnchorEl(event.currentTarget);
      } else {
        navigate("/profile/:id");
      }
    } else {
      console.log("show login popup");
    }
  };
  return (
    <nav className="px-5 z-50 py-[0.8rem] bg-[#e91e63] lg:px-20 flex justify-between">
      <div className="flex items-center space-x-4">
        <div
          className="lg:mr-10 cursor-pointer flex items-center space-x-4"
          onClick={() => navigate("/")}
        >
          <li className="logo font-semibold text-2xl text-gray-300">
            YummZoom
          </li>
        </div>
      </div>
      <div className="flex items-center space-x-2">
        <IconButton>
          <SearchIcon sx={{ fontSize: "1.5rem" }} />
        </IconButton>
        {user.isLoggedIn ? (
          <span
            className="font-semibold cursor-pointer"
            onClick={(event) => handleOnUserClick(event)}
            id="basic-button"
            aria-controls={open ? "basic-menu" : undefined}
            aria-haspopup="true"
            aria-expanded={open ? "true" : undefined}
          >
            User
          </span>
        ) : (
          <IconButton>
            <PersonIcon sx={{ fontSize: "1.5rem" }} />
          </IconButton>
        )}
        <Menu
          id="basic-menu"
          anchorEl={anchorEl}
          open={open}
          onClose={handleClose}
          MenuListProps={{
            "aria-labelledby": "basic-button",
          }}
        >
          <MenuItem onClick={handleProfileClick}>Profile</MenuItem>
          <MenuItem onClick={handleLogoutClick}>Logout</MenuItem>
        </Menu>
        <IconButton>
          <ShoppingCartIcon
            sx={{ fontSize: "1.5rem" }}
            onClick={() => navigate("/cart")}
          />
        </IconButton>
      </div>
    </nav>
  );
};

export default Navbar;
