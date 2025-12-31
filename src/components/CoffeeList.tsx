import { Star, MessageSquare, Plus } from 'lucide-react';
import { Coffee } from '../types';

interface CoffeeListProps {
  coffees: Coffee[];
  onAddReview: (coffee: Coffee) => void;
}

export function CoffeeList({ coffees, onAddReview }: CoffeeListProps) {
  return (
    <div>
      <div className="mb-8">
        <h2 className="text-center mb-2">Coffee Menu</h2>
        <p className="text-center text-gray-600">Choose and rate your favorite coffee.</p>
      </div>

      <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
        {coffees.map((coffee) => (
          <div
            key={coffee.id}
            className="bg-white rounded-xl shadow-md overflow-hidden hover:shadow-xl transition-shadow"
          >
            {/* Resim */}
            <div className="h-48 overflow-hidden">
              <img
                src={coffee.imageUrl}
                alt={coffee.name}
                className="w-full h-full object-cover"
              />
            </div>

            {/* İçerik */}
            <div className="p-5">
              <div className="flex justify-between items-start mb-2">
                <h3>{coffee.name}</h3>
                <span className="text-amber-700">{coffee.price} ₺</span>
              </div>

              <p className="text-gray-600 text-sm mb-4">{coffee.description}</p>

              {/* Rating */}
              <div className="flex items-center gap-2 mb-4">
                <div className="flex items-center gap-1">
                  <Star className="w-5 h-5 fill-amber-400 text-amber-400" />
                  <span>{coffee.averageRating.toFixed(1)}</span>
                </div>
                <span className="text-gray-400">•</span>
                <div className="flex items-center gap-1 text-gray-600">
                  <MessageSquare className="w-4 h-4" />
                  <span className="text-sm">{coffee.reviews.length} comment</span>
                </div>
              </div>

              {/* Yorumlar */}
              {coffee.reviews.length > 0 && (
                <div className="mb-4 border-t pt-4">
                  <div className="text-sm mb-2">Last Comments:</div>
                  <div className="space-y-2 max-h-32 overflow-y-auto">
                    {coffee.reviews.slice(-2).reverse().map((review) => (
                      <div key={review.id} className="bg-gray-50 p-2 rounded text-sm">
                        <div className="flex items-center gap-2 mb-1">
                          <span className="text-gray-700">{review.author}</span>
                          <div className="flex">
                            {[...Array(review.rating)].map((_, i) => (
                              <Star key={i} className="w-3 h-3 fill-amber-400 text-amber-400" />
                            ))}
                          </div>
                        </div>
                        <p className="text-gray-600 text-xs">{review.comment}</p>
                      </div>
                    ))}
                  </div>
                </div>
              )}

              {/* Yorum Ekle Butonu */}
              <button
                onClick={() => onAddReview(coffee)}
                className="w-full py-2 bg-amber-600 text-white rounded-lg hover:bg-amber-700 transition-colors flex items-center justify-center gap-2"
              >
                <Plus className="w-4 h-4" />
                Leave a Review
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
