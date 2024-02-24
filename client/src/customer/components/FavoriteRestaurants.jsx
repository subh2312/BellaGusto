import React, { useState } from "react";
import handpicked from "../../utils/handpicked";
import RestaurantCard from "./RestaurantCard";

const FavoriteRestaurants = () => {
  const [handpickedRestaurants, setHandpickedRestaurants] =
    useState(handpicked);

  return (
    <div>
      <div className="flex flex-wrap items-center justify-around">
        {!!handpickedRestaurants &&
          handpickedRestaurants
            .filter((item) => item.rating >= 4.7)
            .map((restaurant) => (
              <RestaurantCard
                key={restaurant.id}
                restaurant={restaurant}
                fav={"user"}
              />
            ))}
      </div>
    </div>
  );
};

export default FavoriteRestaurants;
