import { Button, Card } from "@mui/material";
import React from "react";

const MenuItemCard = ({ item }) => {
  const handleAddItemToCart = () => {
    console.log("Added item to cart");
  };
  return (
    <Card className="lg:flex items-center justify-between box">
      <div className="lg:flex items-center lg:space-x-5">
        <img
          className="w-[7rem] h-[7rem] object-cover"
          src={item.imageUrl}
          alt=""
        />
        <div className="space-y-1 lg:space-y-5 lg:max-w-2xl">
          <p className="font-semibold text-xl">{item.itemName}</p>
          <p className="text-gray-400">{item.description}</p>
          <p>${item.price}</p>
        </div>
      </div>
      <div>
        <Button onClick={handleAddItemToCart}>Add to Cart</Button>
      </div>
    </Card>
  );
};

export default MenuItemCard;
