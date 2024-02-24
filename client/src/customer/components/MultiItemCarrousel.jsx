import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import CarrouselItem from './CarrouselItem';

const MultiItemCarrousel = ({items}) => {
    const settings = {
        dots:true,
        infinite: true,
        speed: 500,
        slidesToShow: 5, // Set the number of slides to show at a time
        slidesToScroll: 1,
        autoplay:true,
        autoplaySpeed:2000,
        arrows:false,
        responsive: [
          {
            breakpoint: 768,
            settings: {
              slidesToShow: 2,
            },
          },
          {
            breakpoint: 480,
            settings: {
              slidesToShow: 1,
            },
          },
        ],
      };
  return (
        <Slider {...settings}>
    {items && items.map((item) => (
      <CarrouselItem key={item.id} image={item.image} title={item.title}/>
    ))}
  </Slider>
  )
}

export default MultiItemCarrousel