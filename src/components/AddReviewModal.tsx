import { useState } from 'react';
import { X, Star, Loader2 } from 'lucide-react';

interface AddReviewModalProps {
  coffeeName: string;
  onClose: () => void;
  onSubmit: (author: string, rating: number, comment: string) => void | Promise<void>;
  submitting?: boolean;
}

export function AddReviewModal({ coffeeName, onClose, onSubmit, submitting }: AddReviewModalProps) {
  const [author, setAuthor] = useState('');
  const [rating, setRating] = useState(5);
  const [comment, setComment] = useState('');
  const [hoveredRating, setHoveredRating] = useState(0);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!author.trim() || !comment.trim()) return;
    await onSubmit(author, rating, comment);
    setAuthor('');
    setRating(5);
    setComment('');
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center p-4 z-50">
      <div className="bg-white rounded-xl shadow-2xl max-w-md w-full">
        {/* Header */}
        <div className="flex items-center justify-between p-6 border-b">
          <div>
            <h3>Leave a comment
</h3>
            <p className="text-gray-600 text-sm mt-1">{coffeeName}</p>
          </div>
          <button onClick={onClose} className="text-gray-400 hover:text-gray-600" disabled={!!submitting}>
            <X className="w-6 h-6" />
          </button>
        </div>

        {/* Form */}
        <form onSubmit={handleSubmit} className="p-6">
          {/* İsim */}
          <div className="mb-4">
            <label className="block text-gray-700 mb-2">Name</label>
            <input
              type="text"
              value={author}
              onChange={(e) => setAuthor(e.target.value)}
              className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-amber-500"
              placeholder="Enter your name"
              required
              disabled={!!submitting}
            />
          </div>

          {/* Rating */}
          <div className="mb-4">
            <label className="block text-gray-700 mb-2">Point</label>
            <div className="flex gap-2 items-center">
              {[1, 2, 3, 4, 5].map((star) => (
                <button
                  key={star}
                  type="button"
                  onClick={() => setRating(star)}
                  onMouseEnter={() => setHoveredRating(star)}
                  onMouseLeave={() => setHoveredRating(0)}
                  className="transition-transform hover:scale-110"
                  disabled={!!submitting}
                >
                  <Star
                    className={`w-8 h-8 ${
                      star <= (hoveredRating || rating) ? 'fill-amber-400 text-amber-400' : 'text-gray-300'
                    }`}
                  />
                </button>
              ))}
              <span className="ml-2 text-gray-600">{rating}/5</span>
            </div>
          </div>

          {/* Yorum */}
          <div className="mb-6">
            <label className="block text-gray-700 mb-2">Comment</label>
            <textarea
              value={comment}
              onChange={(e) => setComment(e.target.value)}
              className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-amber-500 h-24 resize-none"
              placeholder="Share your thoughts about coffee..."
              required
              disabled={!!submitting}
            />
          </div>

          {/* Buttons */}
          <div className="flex gap-3">
            <button
              type="button"
              onClick={onClose}
              className="flex-1 px-4 py-2 border border-gray-300 text-gray-700 rounded-lg hover:bg-gray-50 transition-colors"
              disabled={!!submitting}
            >
              Cancel
            </button>
            <button
              type="submit"
              className="flex-1 px-4 py-2 bg-amber-600 text-white rounded-lg hover:bg-amber-700 transition-colors flex items-center justify-center gap-2"
              disabled={!!submitting}
            >
              {submitting ? (
                <>
                  <Loader2 className="w-4 h-4 animate-spin" />
                  Sending...
                </>
              ) : (
                'Send Review'
              )}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
