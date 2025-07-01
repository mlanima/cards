import React, { useState } from 'react';
import { motion, AnimatePresence } from 'framer-motion';
import { XMarkIcon } from '@heroicons/react/24/solid';
import { addCard } from '@/lib/api/card.service';

interface AddCardProps {
    isOpen: boolean;
    onClose: () => void;
    deckId: number;
    onCardAdded: () => void;
}

const AddCardDialog: React.FC<AddCardProps> = ({
    isOpen,
    onClose,
    deckId,
    onCardAdded,
}) => {
    const [newCard, setNewCard] = useState({ phrase: '', translation: '' });
    const [addingCard, setAddingCard] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const handleAddCard = async () => {
        if (!newCard.phrase.trim() || !newCard.translation.trim()) {
            setError('Both phrase and translation are required');
            return;
        }

        try {
            setAddingCard(true);
            setError(null);

            await addCard(
                deckId,
                newCard.phrase.trim(),
                newCard.translation.trim()
            );

            // Reset form
            setNewCard({ phrase: '', translation: '' });
            onCardAdded();
            onClose();
        } catch (err) {
            console.error('Error adding card:', err);
            setError('Failed to add card. Please try again.');
        } finally {
            setAddingCard(false);
        }
    };

    const handleClose = () => {
        if (!addingCard) {
            setNewCard({ phrase: '', translation: '' });
            setError(null);
            onClose();
        }
    };

    const handleKeyPress = (e: React.KeyboardEvent) => {
        if (e.key === 'Enter' && !addingCard) {
            handleAddCard();
        }
    };

    return (
        <AnimatePresence>
            {isOpen && (
                <motion.div
                    initial={{ opacity: 0 }}
                    animate={{ opacity: 1 }}
                    exit={{ opacity: 0 }}
                    className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 p-4"
                    onClick={handleClose}
                >
                    <motion.div
                        initial={{ scale: 0.9, opacity: 0, y: 20 }}
                        animate={{ scale: 1, opacity: 1, y: 0 }}
                        exit={{ scale: 0.9, opacity: 0, y: 20 }}
                        transition={{
                            type: 'spring',
                            damping: 25,
                            stiffness: 300,
                        }}
                        className="bg-white rounded-2xl shadow-2xl w-full max-w-md mx-4"
                        onClick={(e) => e.stopPropagation()}
                    >
                        {/* Header */}
                        <div className="flex items-center justify-between p-6 border-b border-slate-200">
                            <h2 className="text-xl font-semibold text-slate-900">
                                Add New Card
                            </h2>
                            <button
                                onClick={handleClose}
                                disabled={addingCard}
                                className="p-2 text-slate-400 hover:text-slate-600 hover:bg-slate-100 rounded-lg transition-colors disabled:opacity-50 disabled:cursor-not-allowed"
                            >
                                <XMarkIcon className="w-5 h-5" />
                            </button>
                        </div>

                        {/* Form */}
                        <div className="p-6 space-y-4">
                            {error && (
                                <motion.div
                                    initial={{ opacity: 0, y: -10 }}
                                    animate={{ opacity: 1, y: 0 }}
                                    className="p-3 bg-red-50 border border-red-200 rounded-lg"
                                >
                                    <p className="text-sm text-red-600">
                                        {error}
                                    </p>
                                </motion.div>
                            )}

                            <div>
                                <label className="block text-sm font-medium text-slate-700 mb-2">
                                    Phrase *
                                </label>
                                <input
                                    type="text"
                                    value={newCard.phrase}
                                    onChange={(e) =>
                                        setNewCard((prev) => ({
                                            ...prev,
                                            phrase: e.target.value,
                                        }))
                                    }
                                    onKeyPress={handleKeyPress}
                                    className="w-full px-4 py-3 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-colors"
                                    placeholder="Enter the phrase..."
                                    disabled={addingCard}
                                />
                            </div>

                            <div>
                                <label className="block text-sm font-medium text-slate-700 mb-2">
                                    Translation *
                                </label>
                                <input
                                    type="text"
                                    value={newCard.translation}
                                    onChange={(e) =>
                                        setNewCard((prev) => ({
                                            ...prev,
                                            translation: e.target.value,
                                        }))
                                    }
                                    onKeyPress={handleKeyPress}
                                    className="w-full px-4 py-3 border border-slate-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-colors"
                                    placeholder="Enter the translation..."
                                    disabled={addingCard}
                                />
                            </div>
                        </div>

                        {/* Footer */}
                        <div className="flex space-x-3 p-6 border-t border-slate-200">
                            <button
                                onClick={handleClose}
                                disabled={addingCard}
                                className="flex-1 px-4 py-3 text-slate-600 bg-slate-100 rounded-lg hover:bg-slate-200 transition-colors disabled:opacity-50 disabled:cursor-not-allowed font-medium"
                            >
                                Cancel
                            </button>
                            <button
                                onClick={handleAddCard}
                                disabled={
                                    !newCard.phrase.trim() ||
                                    !newCard.translation.trim() ||
                                    addingCard
                                }
                                className="flex-1 px-4 py-3 bg-blue-600 text-white rounded-lg hover:bg-blue-700 disabled:bg-slate-300 disabled:cursor-not-allowed transition-colors font-medium"
                            >
                                {addingCard ? (
                                    <div className="flex items-center justify-center space-x-2">
                                        <motion.div
                                            animate={{ rotate: 360 }}
                                            transition={{
                                                duration: 1,
                                                repeat: Infinity,
                                                ease: 'linear',
                                            }}
                                            className="w-4 h-4 border-2 border-white border-t-transparent rounded-full"
                                        />
                                        <span>Adding...</span>
                                    </div>
                                ) : (
                                    'Add Card'
                                )}
                            </button>
                        </div>
                    </motion.div>
                </motion.div>
            )}
        </AnimatePresence>
    );
};

export default AddCardDialog;
