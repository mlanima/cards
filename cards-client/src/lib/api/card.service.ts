import { getToken } from './auth.service';

export interface Card {
    id: string;
    word: string;
    translation: string;
}

const API_URL = process.env.NEXT_PUBLIC_API_URL || 'http://localhost:8080';

export async function getCards(deckId: string): Promise<Card[]> {
    const token = getToken();
    const res = await fetch(`${API_URL}/decks/${deckId}/cards`, {
        method: 'GET',
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
    if (!res.ok) throw new Error('Failed to fetch cards');
    return res.json();
}

export async function addCard(
    deckId: string,
    word: string,
    translation: string
): Promise<Card> {
    const token = getToken();
    const res = await fetch(`${API_URL}/decks/${deckId}/cards`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({ word, translation }),
    });
    if (!res.ok) throw new Error('Failed to add card');
    return res.json();
}
