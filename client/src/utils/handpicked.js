const handpicked = [
  {
    id: 1,
    imageUrl:
      "https://images.pexels.com/photos/1307698/pexels-photo-1307698.jpeg?auto=compress&cs=tinysrgb&w=600",
    name: "Taste of Bangalore",
    rating: 4.5,
    city: "Bangalore",
    description:
      "Experience the authentic flavors of Bangalore in every bite at Taste of Bangalore.",
  },
  {
    id: 2,
    imageUrl:
      "https://images.pexels.com/photos/2253643/pexels-photo-2253643.jpeg?auto=compress&cs=tinysrgb&w=600",
    name: "Spice Haven",
    rating: 4.2,
    city: "Bangalore",
    description:
      "A haven for spice lovers, offering a diverse menu with a touch of Bangalore's culinary heritage.",
  },
  {
    id: 3,
    imageUrl:
      "https://images.pexels.com/photos/1581384/pexels-photo-1581384.jpeg?auto=compress&cs=tinysrgb&w=600",
    name: "Urban Bites",
    rating: 4.7,
    city: "Bangalore",
    description:
      "Savor the urban vibes and delightful bites at this trendy Bangalore eatery.",
  },
  {
    id: 4,
    imageUrl:
      "https://images.pexels.com/photos/1449773/pexels-photo-1449773.jpeg?auto=compress&cs=tinysrgb&w=600",
    name: "Flavorsome Fusion",
    rating: 4.0,
    city: "Bangalore",
    description:
      "A fusion of flavors that promises a unique and memorable dining experience.",
  },
  {
    id: 5,
    imageUrl:
      "https://images.pexels.com/photos/1581554/pexels-photo-1581554.jpeg?auto=compress&cs=tinysrgb&w=600",
    name: "Green Garden Cafe",
    rating: 4.8,
    city: "Bangalore",
    description:
      "Relax in the greenery of this charming cafe while savoring delicious dishes inspired by Bangalore's local produce.",
  },
  {
    id: 6,
    imageUrl:
      "https://images.pexels.com/photos/776538/pexels-photo-776538.jpeg?auto=compress&cs=tinysrgb&w=600",
    name: "Chai & Chats",
    rating: 4.3,
    city: "Bangalore",
    description:
      "Enjoy the perfect blend of chai and conversations in the heart of Bangalore.",
  },
  {
    id: 7,
    imageUrl:
      "https://images.pexels.com/photos/2290753/pexels-photo-2290753.jpeg?auto=compress&cs=tinysrgb&w=600",
    name: "South Spice Delight",
    rating: 4.6,
    city: "Bangalore",
    description:
      "Embark on a journey through the rich and diverse flavors of South Indian cuisine.",
  },
  {
    id: 8,
    imageUrl:
      "https://images.pexels.com/photos/3887985/pexels-photo-3887985.jpeg?auto=compress&cs=tinysrgb&w=600",
    name: "Rooftop Retreat",
    rating: 4.4,
    city: "Bangalore",
    description:
      "Dine with a view at this rooftop restaurant, offering a retreat from the bustling city life.",
  },
  {
    id: 9,
    imageUrl:
      "https://images.pexels.com/photos/1055058/pexels-photo-1055058.jpeg?auto=compress&cs=tinysrgb&w=600",
    name: "Mango Magic",
    rating: 4.9,
    city: "Bangalore",
    description:
      "Indulge in the magic of seasonal mangoes with a delectable menu at Mango Magic.",
  },
  {
    id: 10,
    imageUrl:
      "https://images.pexels.com/photos/1378424/pexels-photo-1378424.jpeg?auto=compress&cs=tinysrgb&w=600",
    name: "Street Food Junction",
    rating: 4.1,
    city: "Bangalore",
    description:
      "Experience the vibrant street food culture of Bangalore at this lively junction.",
  },
  {
    id: 11,
    imageUrl:
      "https://images.pexels.com/photos/984888/pexels-photo-984888.jpeg?auto=compress&cs=tinysrgb&w=600",
    name: "Culinary Canvas",
    rating: 4.7,
    city: "Bangalore",
    description:
      "Artistically crafted dishes that turn each plate into a culinary canvas at this unique Bangalore restaurant.",
  },
  {
    id: 12,
    imageUrl:
      "https://images.pexels.com/photos/2290070/pexels-photo-2290070.jpeg?auto=compress&cs=tinysrgb&w=600",
    name: "Floral Fusion",
    rating: 4.5,
    city: "Bangalore",
    description:
      "Immerse yourself in the floral charm of this fusion restaurant, where every dish is a bouquet of flavors.",
  },
  {
    id: 13,
    imageUrl:
      "https://images.pexels.com/photos/1546039/pexels-photo-1546039.jpeg?auto=compress&cs=tinysrgb&w=600",
    name: "Brews & Bites",
    rating: 4.2,
    city: "Bangalore",
    description:
      "A haven for beer enthusiasts, complemented by a diverse menu of delicious bites.",
  },
  {
    id: 14,
    imageUrl:
      "https://images.pexels.com/photos/1560657/pexels-photo-1560657.jpeg?auto=compress&cs=tinysrgb&w=600",
    name: "Heritage Eats",
    rating: 4.6,
    city: "Bangalore",
    description:
      "Journey through the culinary heritage of Bangalore with a menu inspired by traditional recipes.",
  },
  {
    id: 15,
    imageUrl:
      "https://images.pexels.com/photos/3201921/pexels-photo-3201921.jpeg?auto=compress&cs=tinysrgb&w=600",
    name: "Quirky Cafeteria",
    rating: 4.3,
    city: "Bangalore",
    description:
      "Step into a quirky world of flavors and fun at this one-of-a-kind Bangalore cafeteria.",
  },
  {
    id: 16,
    imageUrl:
      "https://images.pexels.com/photos/2504911/pexels-photo-2504911.jpeg?auto=compress&cs=tinysrgb&w=600",
    name: "Coastal Charm",
    rating: 4.8,
    city: "Bangalore",
    description:
      "Savor the coastal charm of Bangalore with a menu inspired by the rich culinary traditions of the coast.",
  },
  {
    id: 17,
    imageUrl:
      "https://images.pexels.com/photos/3534750/pexels-photo-3534750.jpeg?auto=compress&cs=tinysrgb&w=600",
    name: "Zen Garden Dining",
    rating: 4.4,
    city: "Bangalore",
    description:
      "Find tranquility in the Zen garden while enjoying a delightful dining experience in Bangalore.",
  },
  {
    id: 18,
    imageUrl:
      "https://images.pexels.com/photos/2067576/pexels-photo-2067576.jpeg?auto=compress&cs=tinysrgb&w=600",
    name: "Mystic Munchies",
    rating: 4.0,
    city: "Bangalore",
    description:
      "Unravel the mysteries of flavors with every bite at this enchanting Bangalore eatery.",
  },
  {
    id: 19,
    imageUrl:
      "https://images.pexels.com/photos/1346132/pexels-photo-1346132.jpeg?auto=compress&cs=tinysrgb&w=600",
    name: "Golden Grains",
    rating: 4.5,
    city: "Bangalore",
    description:
      "Experience the richness of golden grains in a variety of dishes at this wholesome Bangalore restaurant.",
  },
  {
    id: 20,
    imageUrl:
      "https://images.pexels.com/photos/698907/pexels-photo-698907.jpeg?auto=compress&cs=tinysrgb&w=600",
    name: "Skyline Serenity",
    rating: 4.7,
    city: "Bangalore",
    description:
      "Dine amidst the skyline with serene views and a menu that elevates your dining experience.",
  },
];

export default handpicked;
