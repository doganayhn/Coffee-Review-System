import { useEffect, useState } from 'react';
import { CoffeeList } from './components/CoffeeList';
import { AddReviewModal } from './components/AddReviewModal';
import { Coffee } from './types';
import { createReview, getCoffees } from './api';

export default function App() {
  const [coffees, setCoffees] = useState<Coffee[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  const [selectedCoffee, setSelectedCoffee] = useState<Coffee | null>(null);
  const [showModal, setShowModal] = useState(false);
  const [submitting, setSubmitting] = useState(false);

  useEffect(() => {
    (async () => {
      try {
        setLoading(true);
        setError(null);
        const data = await getCoffees();
        setCoffees(data);
      } catch (e: any) {
        setError(e?.message || 'API bağlantısı kurulamadı.');
      } finally {
        setLoading(false);
      }
    })();
  }, []);

  const openReviewModal = (coffee: Coffee) => {
    setSelectedCoffee(coffee);
    setShowModal(true);
  };

  const handleAddReview = async (author: string, rating: number, comment: string) => {
    if (!selectedCoffee) return;

    try {
      setSubmitting(true);
      setError(null);

      const updatedCoffee = await createReview({
        coffeeId: selectedCoffee.id,
        author,
        rating,
        comment,
      });

      setCoffees((prev) => prev.map((c) => (c.id === updatedCoffee.id ? updatedCoffee : c)));
      setShowModal(false);
    } catch (e: any) {
      setError(e?.message || 'Yorum gönderilemedi.');
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-amber-50 to-orange-50">
      {/* Header */}
      <header className="bg-gradient-to-r from-amber-800 to-amber-600 text-white shadow-lg">
        <div className="max-w-6xl mx-auto px-4 py-6">
          <h1 className="text-white text-center">☕ Coffee Review System</h1>
          <p className="text-center text-amber-100 mt-2"></p>
        </div>
      </header>

      {/* Main Content */}
      <main className="max-w-6xl mx-auto px-4 py-8">
        {error && (
          <div className="mb-6 bg-red-50 border border-red-200 text-red-700 p-4 rounded-lg">
            {error}
          </div>
        )}

        {loading ? (
          <div className="text-center text-gray-600">Loading...</div>
        ) : (
          <CoffeeList coffees={coffees} onAddReview={openReviewModal} />
        )}
      </main>

      {/* Review Modal */}
      {showModal && selectedCoffee && (
        <AddReviewModal
          coffeeName={selectedCoffee.name}
          onClose={() => setShowModal(false)}
          onSubmit={handleAddReview}
          submitting={submitting}
        />
      )}
    </div>
  );
}
