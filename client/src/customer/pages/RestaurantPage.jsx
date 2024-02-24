import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import handpicked from "../../utils/handpicked";
import {
  Card,
  Divider,
  FormControl,
  FormControlLabel,
  Radio,
  RadioGroup,
  Typography,
} from "@mui/material";
import foodMenu from "../../utils/foodMenu";
import MenuItemCard from "../components/MenuItemCard";

const RestaurantPage = () => {
  const { city, name, id } = useParams();
  const [restaurant, setRestaurant] = useState({});
  const [selectedFoodType, setSelectedFoodType] = useState("");
  const [selectedCategory, setSelectedCategory] = useState("");
  const [categories, setCategories] = useState([]);
  const [foodTypes, setFoodTypes] = useState([]);
  const [foodItems, setFoodItems] = useState([]);
  useEffect(() => {
    return () => {
      const item =
        !!handpicked && handpicked.find((item) => item.id === parseInt(id));
      const categoryNames = new Set();
      const types = new Set();
      const foods = new Set();
      categoryNames.add("All");
      types.add("Both");
      foodMenu.forEach((category) => {
        categoryNames.add(category.categoryName);
        category.items.forEach((item) => {
          foods.add(item);
          const type = item.type;
          if (type) {
            types.add(type);
          }
        });
      });

      setFoodTypes(Array.from(types));
      setRestaurant(item);
      setFoodItems(Array.from(foods));
      setCategories(Array.from(categoryNames));
      setSelectedCategory("All");
      setSelectedFoodType("Both");
    };
  }, [id]);
  return (
    <div className="px-5 lg:px-20">
      <section>
        <h3 className="text-gray-500 py-2 mt-10">{`Home/India/${city}/${name}/Order Online`}</h3>
        <div>
          <img
            className="w-full h-[40vh] object-cover"
            src={restaurant.imageUrl}
            alt={restaurant.name}
          />
        </div>
        <div>
          <h1 className="text-4xl py-1 font-semibold">{name}</h1>
          <p className="text-gray-500">{restaurant.description}</p>
          <p className="text-orange-300 py-3">
            Open now 10:30am - 12midnight (Today)
          </p>
        </div>
      </section>
      <Divider />
      <section className="pt-[2rem] lg:flex relative">
        <div className="space-y-10 lg:w-[20%]">
          <Card className="p-5 space-y-5 lg:sticky top-28">
            <div>
              <Typography sx={{ paddingBottom: "1rem" }} variant="h5">
                Type
              </Typography>
              <FormControl component={"fieldset"}>
                <RadioGroup
                  name="foodType"
                  value={selectedFoodType}
                  onChange={(e) => setSelectedFoodType(e.target.value)}
                >
                  {!!foodTypes &&
                    foodTypes.map((item, index) => (
                      <FormControlLabel
                        key={index}
                        value={item}
                        control={<Radio />}
                        label={item}
                        sx={{
                          color: "gray",
                        }}
                      />
                    ))}
                </RadioGroup>
              </FormControl>
            </div>
            <Divider />
            <div>
              <Typography sx={{ paddingBottom: "1rem" }} variant="h5">
                Category
              </Typography>
              <FormControl component={"fieldset"}>
                <RadioGroup
                  name="category"
                  value={selectedCategory}
                  onChange={(e) => setSelectedCategory(e.target.value)}
                >
                  {!!categories &&
                    categories.map((item, index) => (
                      <FormControlLabel
                        key={index}
                        value={item}
                        control={<Radio />}
                        label={item}
                        sx={{
                          color: "gray",
                        }}
                      />
                    ))}
                </RadioGroup>
              </FormControl>
            </div>
          </Card>
        </div>
        <div className="lg:w-[80%] space-y-5 lg:pl-10">
          {!!foodItems && foodItems.map((item) => <MenuItemCard item={item} />)}
        </div>
      </section>
    </div>
  );
};

export default RestaurantPage;
