import { Card } from '@/lib/api/card.service';
import { Accordion, AccordionItem } from '@heroui/react';
import React from 'react';

interface CardsListProps {
    cards: Card[];
    loading: boolean;
    error: string | null;
    filter: string;
}

const CardsList = ({ cards, loading, error, filter }: CardsListProps) => {
    return (
        <>
            {loading && <p className="text-gray-500">Loading cards...</p>}
            {error && <p className="text-red-500">{error}</p>}

            {!loading && !error && cards.length === 0 && (
                <p className="text-gray-500">
                    No cards available. Try adding one!
                </p>
            )}

            {!loading && cards.length > 0 && (
                <Accordion
                    className="text-xl font-semibold text-stone-100"
                    variant="splitted"
                >
                    {cards
                        .filter(
                            (card) =>
                                card.phrase.includes(filter) ||
                                card.translation.includes(filter)
                        )
                        .map((card) => (
                            <AccordionItem
                                key={card.id}
                                aria-label={`Card: ${card.phrase}`}
                                title={card.phrase}
                                className="bg-stone-600 rounded p-2 m-2"
                            >
                                {card.translation}
                            </AccordionItem>
                        ))}
                </Accordion>
            )}
        </>
    );
};

export default CardsList;
