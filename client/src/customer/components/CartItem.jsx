import { IconButton } from "@mui/material";
import React from "react";
import RemoveCircleOutlineIcon from "@mui/icons-material/RemoveCircleOutline";
import AddCircleOutlineIcon from "@mui/icons-material/AddCircleOutline";

const CartItem = ({ item, addItem, removeItem }) => {
  return (
    <div className="px-5">
      <div className="lg:flex items-center lg:space-x-5">
        <div>
          <img
            className="w-[5rem] h-[5rem] object-cover"
            src={item.imageUrl}
            alt={item.title}
          />
        </div>
        <div className="flex justify-between items-center lg:w-[70%]">
          <div className="space-y-1 lg:space-y-3 w-full">
            <p>{item.title}</p>
            <div className="flex justify-between items-center">
              <div className="flex items-center space-x-1">
                <IconButton color="primary" onClick={() => removeItem(item)}>
                  <RemoveCircleOutlineIcon />
                </IconButton>
                <div className="text-xs">{item.qty}</div>
                <IconButton color="primary" onClick={() => addItem(item)}>
                  <AddCircleOutlineIcon />
                </IconButton>
              </div>
            </div>
          </div>
          <p>${item.price}</p>
        </div>
      </div>
    </div>
  );
};

export default CartItem;
