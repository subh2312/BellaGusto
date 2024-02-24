import React, { useState } from 'react'
import "./HomePage.css"
import MultiItemCarrousel from '../components/MultiItemCarrousel';
import topMenuItems from '../../utils/topMenuItems';
import handpicked from '../../utils/handpicked';
import RestaurantCard from '../components/RestaurantCard';
const HomePage = () => {
    const [topItems,setTopItems] = useState(topMenuItems);
    const [handpickedRestaurants, setHandpickedRestaurants] = useState(handpicked);
  return (
    <div>
        <section className='-z-50 banner relative flex flex-col justify-center items-center'>
            <div className="w-[50vw] z-10 text-center">
                <p className="text-2xl lg:text7xl font-bold z-10 py-5">YumZoom</p>
                <p className='z-10 text-gray-300 text-xl lg:text4xl'>Delicacies Delivered Fast</p>
            </div>
            <div className="cover absolute top-0 left-0 right-0"></div>
            <div className="fadeout"></div>
        </section>
        <section className='p-10 lg:py-10 lg:px-20'>
            <div>
                <p className='text-2xl font-semibold text-gray-400 py-3 pb-10'>Top Meals</p>
            </div>
            <MultiItemCarrousel items={topItems}/>
        </section>
        <section className='handpicked px-5 lg:px-20'>
            <div>
                <h1 className='text-2xl text-gray-400 py-3 font-semibold'>
                    Order from our handpicked restaurants
                </h1>
                <div className="flex flex-wrap items-center justify-around">
                    {!!handpickedRestaurants && handpickedRestaurants.map(restaurant=>(
                        <RestaurantCard key={restaurant.id} restaurant={restaurant}/>
                    )) }
                </div>
            </div>
        </section>
    </div>
  )
}

export default HomePage;