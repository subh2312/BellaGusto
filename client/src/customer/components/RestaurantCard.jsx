import { Card, IconButton } from "@mui/material";
import React from "react";
import { useNavigate } from "react-router-dom";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import FavoriteIcon from "@mui/icons-material/Favorite";

const RestaurantCard = ({ restaurant }) => {
  const navigate = useNavigate();

  const handleAddToFav = () => {};
  return (
    <Card className="m-5 w-[18rem] productCard">
      <div
        onClick={() =>
          navigate(
            `/restaurant/${restaurant.city}/${restaurant.name}/${restaurant.id}`
          )
        }
      >
        <img
          className="w-full h-[10rem] rounded-t-md object-cover"
          src={restaurant.imageUrl}
          alt={restaurant.name}
        />
      </div>
      <div className="p4 textPart lg:flex w-full justify-between">
        <div className="space-y-1">
          <p className="font-semibold text-lg">{restaurant.name}</p>
          <p className="text-sm text-gray-500">
            {restaurant.description.length > 40
              ? restaurant.description.substring(0, 40) + "..."
              : restaurant.description}
          </p>
        </div>
        <IconButton onClick={handleAddToFav}>
          {restaurant.rating >= 4.5 ? (
            <FavoriteIcon color="primary" />
          ) : (
            <FavoriteBorderIcon />
          )}
        </IconButton>
      </div>
    </Card>
  );
};

export default RestaurantCard;
