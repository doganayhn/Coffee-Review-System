export interface Review {
  id: number;
  author: string;
  rating: number;
  comment: string;
  date: string;
}

export interface Coffee {
  id: number;
  name: string;
  description: string;
  price: number;
  imageUrl: string;
  averageRating: number;
  reviews: Review[];
}
