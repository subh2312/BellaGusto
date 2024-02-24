const foodMenu = [
  {
    categoryName: "Appetizers",
    description: "Delightful starters to awaken your taste buds.",
    items: [
      {
        itemName: "Samosa",
        description: "Crispy pastry filled with spiced potatoes and peas.",
        calories: 150,
        price: 3.99,
        type: "Veg",
      },
      {
        itemName: "Paneer Tikka",
        description: "Marinated and grilled cottage cheese cubes.",
        calories: 200,
        price: 5.99,
        type: "Veg",
      },
      {
        itemName: "Vegetable Pakora",
        description: "Assorted vegetables deep-fried in chickpea batter.",
        calories: 180,
        price: 4.99,
        type: "Veg",
      },
      {
        itemName: "Chicken Kebabs",
        description: "Tender chicken pieces marinated and skewered.",
        calories: 250,
        price: 7.99,
        type: "Non-Veg",
      },
    ],
  },
  {
    categoryName: "Main Course",
    description: "Heartwarming dishes to satisfy your hunger.",
    items: [
      {
        itemName: "Butter Chicken",
        description: "Tender chicken cooked in a rich buttery tomato sauce.",
        calories: 300,
        price: 11.99,
        type: "Non-Veg",
      },
      {
        itemName: "Paneer Butter Masala",
        description:
          "Soft paneer cubes in a creamy tomato and cashew nut gravy.",
        calories: 280,
        price: 10.99,
        type: "Veg",
      },
      {
        itemName: "Chicken Biryani",
        description:
          "Fragrant basmati rice cooked with aromatic spices and chicken.",
        calories: 350,
        price: 13.99,
        type: "Non-Veg",
      },
      {
        itemName: "Dal Makhani",
        description:
          "Black lentils and kidney beans simmered in creamy tomato sauce.",
        calories: 220,
        price: 8.99,
        type: "Veg",
      },
    ],
  },
  {
    categoryName: "Breads",
    description: "Freshly baked bread to accompany your main course.",
    items: [
      {
        itemName: "Naan",
        description: "Soft and fluffy Indian bread baked in a tandoor.",
        calories: 150,
        price: 2.99,
        type: "Veg",
      },
      {
        itemName: "Roti",
        description: "Whole wheat flatbread, a healthy option.",
        calories: 120,
        price: 2.49,
        type: "Veg",
      },
      {
        itemName: "Garlic Naan",
        description: "Naan bread infused with garlic and butter.",
        calories: 180,
        price: 3.49,
        type: "Veg",
      },
      {
        itemName: "Paratha",
        description: "Flaky, layered bread with various stuffing options.",
        calories: 200,
        price: 4.49,
        type: "Veg",
      },
    ],
  },
  {
    categoryName: "Rice Specialties",
    description: "Flavorful rice dishes to indulge in.",
    items: [
      {
        itemName: "Biryani",
        description: "Aromatic rice dish with meat, spices, and herbs.",
        calories: 300,
        price: 12.99,
        type: "Non-Veg",
      },
      {
        itemName: "Vegetable Pulao",
        description: "Fragrant rice cooked with mixed vegetables and spices.",
        calories: 250,
        price: 9.99,
        type: "Veg",
      },
      {
        itemName: "Jeera Rice",
        description: "Basmati rice cooked with cumin seeds for a mild flavor.",
        calories: 180,
        price: 7.99,
        type: "Veg",
      },
      {
        itemName: "Lemon Rice",
        description: "Rice flavored with lemon juice and tempered with spices.",
        calories: 200,
        price: 8.99,
        type: "Veg",
      },
    ],
  },
  {
    categoryName: "Tandoor Specials",
    description: "Grilled delicacies from the traditional tandoor oven.",
    items: [
      {
        itemName: "Tandoori Chicken",
        description:
          "Marinated chicken grilled in a tandoor for a smoky flavor.",
        calories: 280,
        price: 10.99,
        type: "Non-Veg",
      },
      {
        itemName: "Seekh Kebabs",
        description: "Minced meat skewers seasoned with spices and herbs.",
        calories: 240,
        price: 8.99,
        type: "Non-Veg",
      },
      {
        itemName: "Tandoori Fish",
        description:
          "Fish marinated in tandoori spices and grilled to perfection.",
        calories: 300,
        price: 12.99,
        type: "Non-Veg",
      },
      {
        itemName: "Tandoori Mushrooms",
        description: "Mushrooms marinated and grilled in a tandoor.",
        calories: 180,
        price: 6.99,
        type: "Veg",
      },
    ],
  },
  {
    categoryName: "Vegetarian Delights",
    description: "A selection of sumptuous vegetarian dishes.",
    items: [
      {
        itemName: "Palak Paneer",
        description: "Cottage cheese cubes in a creamy spinach gravy.",
        calories: 250,
        price: 9.99,
        type: "Veg",
      },
      {
        itemName: "Aloo Gobi",
        description: "Potatoes and cauliflower cooked with spices.",
        calories: 220,
        price: 8.49,
        type: "Veg",
      },
      {
        itemName: "Baingan Bharta",
        description:
          "Smoky-flavored roasted eggplant mashed and cooked with spices.",
        calories: 200,
        price: 7.99,
        type: "Veg",
      },
      {
        itemName: "Vegetable Korma",
        description:
          "Mixed vegetables in a rich and creamy coconut-based curry.",
        calories: 280,
        price: 10.49,
        type: "Veg",
      },
    ],
  },
  {
    categoryName: "Desserts",
    description: "Sweet treats to conclude your meal on a delightful note.",
    items: [
      {
        itemName: "Gulab Jamun",
        description: "Deep-fried milk dumplings soaked in sugar syrup.",
        calories: 200,
        price: 4.99,
        type: "Veg",
      },
      {
        itemName: "Rasgulla",
        description: "Spongy cheese balls in sugar syrup.",
        calories: 180,
        price: 3.99,
        type: "Veg",
      },
      {
        itemName: "Kheer",
        description:
          "Rice pudding flavored with cardamom and topped with nuts.",
        calories: 250,
        price: 5.99,
        type: "Veg",
      },
      {
        itemName: "Jalebi",
        description: "Deep-fried spirals soaked in sugar syrup.",
        calories: 220,
        price: 4.49,
        type: "Veg",
      },
    ],
  },
  {
    categoryName: "Street Food Specials",
    description: "Experience the flavors of Indian street food.",
    items: [
      {
        itemName: "Pani Puri",
        description:
          "Crispy hollow puris filled with spicy and tangy flavored water.",
        calories: 150,
        price: 4.49,
        type: "Veg",
      },
      {
        itemName: "Chaat",
        description:
          "A medley of savory snacks topped with chutneys and spices.",
        calories: 180,
        price: 5.49,
        type: "Veg",
      },
      {
        itemName: "Bhel Puri",
        description: "Puffed rice mixed with chutneys, vegetables, and sev.",
        calories: 160,
        price: 4.99,
        type: "Veg",
      },
      {
        itemName: "Vada Pav",
        description:
          "Spicy potato fritter sandwiched in a pav (bread) with chutneys.",
        calories: 220,
        price: 6.49,
        type: "Veg",
      },
    ],
  },
  {
    categoryName: "South Indian Classics",
    description: "Authentic dishes from the southern regions of India.",
    items: [
      {
        itemName: "Dosa",
        description: "Thin rice crepe served with coconut chutney and sambar.",
        calories: 180,
        price: 5.99,
        type: "Veg",
      },
      {
        itemName: "Idli Sambar",
        description: "Steamed rice cakes served with lentil soup (sambar).",
        calories: 160,
        price: 4.99,
        type: "Veg",
      },
      {
        itemName: "Uttapam",
        description: "Thick rice pancake topped with vegetables.",
        calories: 200,
        price: 6.49,
        type: "Veg",
      },
      {
        itemName: "Coconut Chutney",
        description:
          "Chutney made with fresh coconut, green chilies, and spices.",
        calories: 120,
        price: 3.99,
        type: "Veg",
      },
    ],
  },
  {
    categoryName: "Beverages",
    description: "Refreshing drinks to complement your meal.",
    items: [
      {
        itemName: "Masala Chai",
        description: "Spiced tea with a blend of aromatic spices.",
        calories: 80,
        price: 2.49,
        type: "Veg",
      },
      {
        itemName: "Mango Lassi",
        description: "Smooth yogurt drink blended with ripe mangoes.",
        calories: 150,
        price: 3.99,
        type: "Veg",
      },
      {
        itemName: "Buttermilk",
        description: "Chilled savory yogurt-based drink.",
        calories: 100,
        price: 2.99,
        type: "Veg",
      },
      {
        itemName: "Rose Sherbet",
        description: "Refreshing rose-flavored drink.",
        calories: 120,
        price: 3.49,
        type: "Veg",
      },
    ],
  },
];

export default foodMenu;
